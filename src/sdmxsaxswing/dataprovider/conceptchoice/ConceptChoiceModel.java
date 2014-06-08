/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmxsaxswing.dataprovider.conceptchoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sdmx.Registry;
import sdmx.commonreferences.ConceptReferenceType;
import sdmx.commonreferences.DataStructureReferenceType;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.NestedIDType;
import sdmx.commonreferences.NestedNCNameIDType;
import sdmx.commonreferences.VersionType;
import sdmx.message.DataQueryMessage;
import sdmx.message.DataStructure;
import sdmx.query.base.QueryIDType;
import sdmx.query.base.TimeValue;
import sdmx.query.data.DataParametersAndType;
import sdmx.query.data.DataParametersOrType;
import sdmx.query.data.DataParametersType;
import sdmx.query.data.DataQuery;
import sdmx.query.data.DimensionValueType;
import sdmx.query.data.TimeDimensionValueType;
import sdmx.structure.base.Component;
import sdmx.structure.base.RepresentationType;
import sdmx.structure.codelist.CodelistType;
import sdmx.structure.concept.ConceptSchemeType;
import sdmx.structure.concept.ConceptType;
import sdmx.structure.datastructure.AttributeType;
import sdmx.structure.datastructure.DataStructureType;
import sdmx.structure.datastructure.DimensionType;
import sdmx.structure.datastructure.PrimaryMeasure;
import sdmx.structure.datastructure.TimeDimensionType;
import sdmx.version.twopointzero.Sdmx20SOAPQueryable;
import sdmx.xml.DateTime;

/**
 * This file is part of SdmxSax.
 *
 * SdmxSax is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * SdmxSax is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * SdmxSax. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright James Gardner 2014
 */
public class ConceptChoiceModel {

    public static final ConceptChoiceModel MODEL = new ConceptChoiceModel();

    private List<ConceptChoice> conceptChoices = new ArrayList<ConceptChoice>();
    private TimeValueConceptChoice time = null;
    private ObsValueConceptChoice obs = null;

    private Registry registry = null;
    private DataStructureReferenceType structureRef = null;
    private DataStructureType structure = null;

    public void setDataStructure(Registry registry, DataStructureReferenceType ref) {
        conceptChoices = new ArrayList<ConceptChoice>();
        NestedNCNameIDType agency = ref.getRef().getAgencyId();
        NestedIDType id = ref.getRef().getId();
        VersionType vers = ref.getRef().getVersion();
        this.setStructureRef(ref);
        DataStructureType ds = registry.findDataStructure(agency, new IDType(id.toString()), vers);
        this.setStructure(ds);
        this.registry = registry;
        for (int i = 0; i < ds.getDataStructureComponents().getDimensionList().size(); i++) {
            DimensionType dim = ds.getDataStructureComponents().getDimensionList().getDimension(i);
            String concept = dim.getConceptIdentity().getRef().getId().toString();
            SingleValueConceptChoice choice = new SingleValueConceptChoice(registry, structure, concept);
            choice.setId(concept);
            CodelistType codelist = getPossibleCodes(registry, ds, concept);
            if (codelist != null) {
                choice.setDefaultChoice(codelist.getItem(0).getId().toString());
            }
            conceptChoices.add(choice);
        }
        TimeDimensionType timed = ds.getDataStructureComponents().getTimeDimension();
        String concept = timed.getConceptIdentity().getRef().getId().toString();
        setTime(new TimeValueConceptChoice(registry, structure, concept));
        PrimaryMeasure prim = ds.getDataStructureComponents().getMeasureList().getPrimaryMeasure();
        if (prim != null) {
            concept = prim.getConceptIdentity().getRef().getId().toString();
            obs = new ObsValueConceptChoice(registry, structure, concept);
        }
    }

    public CodelistType getPossibleCodes(String concept) {
        return getPossibleCodes(registry, structure, concept);
    }

    public static CodelistType getPossibleCodes(Registry registry, DataStructureType struct, String column) {
        Component dim = struct.getDataStructureComponents().findDimension(column);
        ConceptReferenceType conceptRef = dim.getConceptIdentity();
        RepresentationType rep = null;
        ConceptType concept = null;
        if (conceptRef != null) {
            ConceptSchemeType con = registry.findConceptScheme(struct.getAgencyID(), conceptRef);
            if (con == null) {
                System.out.println("Cant find concept:" + conceptRef.getRef().getId().getString());
            }
            concept = con.findConcept(dim.getConceptIdentity().getRef().getId());
            rep = concept != null ? concept.getCoreRepresentation() : null;
        }
        RepresentationType localRep = dim.getLocalRepresentation();
        if (localRep != null) {
            rep = localRep;
        }
        if (rep != null) {
            if (rep.getEnumeration() != null) {
                CodelistType codelist = registry.findCodelist(rep.getEnumeration());
                if (codelist == null) {
                    throw new RuntimeException("Cant find codelist");
                } else {
                    return codelist;
                }
            }
        }
        return null;
    }


    public void setConceptChoice(String concept, ConceptChoice cc) {
        for(int i=0;i<conceptChoices.size();i++) {
            if( conceptChoices.get(i).getId().equals(concept)){
                conceptChoices.set(i, cc);
            }
        }
    }
    public ConceptChoice getConceptChoice(String concept) {
        for(int i=0;i<conceptChoices.size();i++) {
            if( conceptChoices.get(i).getId().equals(concept))return conceptChoices.get(i);
        }
        return null;
    }

    /**
     * @return the structureRef
     */
    public DataStructureReferenceType getStructureRef() {
        return structureRef;
    }

    /**
     * @param structureRef the structureRef to set
     */
    public void setStructureRef(DataStructureReferenceType structureRef) {
        this.structureRef = structureRef;
    }

    /**
     * @return the structure
     */
    public DataStructureType getStructure() {
        return structure;
    }

    /**
     * @param structure the structure to set
     */
    public void setStructure(DataStructureType structure) {
        this.structure = structure;
    }

    public Registry getRegistry() {
        return registry;
    }

    public ConceptChoice getConceptChoice(int i) {
        return conceptChoices.get(i);
    }
    
    public int size() {
        return this.conceptChoices.size();
    }

    public DataQueryMessage toDataQuery() {
        DataQueryMessage query = new DataQueryMessage();
        if (registry instanceof Sdmx20SOAPQueryable) {
            Sdmx20SOAPQueryable soap = (Sdmx20SOAPQueryable) registry;
            query.setHeader(soap.getBaseHeader());
        }
        DataQuery q = new DataQuery();
        DataParametersAndType dw = new DataParametersAndType();
        dw.setDataSetId(Collections.singletonList(new QueryIDType(structure.getId().toString())));
        List<DataParametersOrType> ors = new ArrayList<DataParametersOrType>();
        for (int i = 0; i < conceptChoices.size(); i++) {
            DataParametersOrType or = new DataParametersOrType();
            List<DimensionValueType> dims = new ArrayList<DimensionValueType>();
            for (int j = 0; j < conceptChoices.get(i).getChoiceList().size(); j++) {
                dims.add(new DimensionValueType(conceptChoices.get(i).getId(), conceptChoices.get(i).getChoiceList().get(j)));
            }
            or.setDimensionValue(dims);
            ors.add(or);
        }
        dw.setOr(ors);
        dw.setTimeDimensionValue(Collections.singletonList(new TimeDimensionValueType(new TimeValue(getTime().getFrom().toString()), new TimeValue(getTime().getTo().toString()))));
        DataParametersType dpt = new DataParametersType();
        List<DataParametersAndType> ands = new ArrayList<DataParametersAndType>();
        ands.add(dw);
        dpt.setAnd(ands);
        q.setDataWhere(dpt);
        query.setQuery(q);
        return query;
    }

    /**
     * @return the time
     */
    public TimeValueConceptChoice getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(TimeValueConceptChoice time) {
        this.time = time;
    }
    public void dump() {
        for(int i=0;i<conceptChoices.size();i++) {
            conceptChoices.get(i).dump();
        }
    }
    
}

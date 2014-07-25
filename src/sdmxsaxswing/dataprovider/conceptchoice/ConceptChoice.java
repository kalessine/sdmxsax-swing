/**
 *  This file is part of SdmxSax.
 *
 *   SdmxSax is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 
 *  SdmxSax is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SdmxSax.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Copyright James Gardner 2014
 */

package sdmxsaxswing.dataprovider.conceptchoice;

import java.util.List;
import java.util.Locale;
import sdmx.Registry;
import sdmx.common.Description;
import sdmx.common.Name;
import sdmx.commonreferences.ConceptReferenceType;
import sdmx.structure.base.Component;
import sdmx.structure.base.ItemSchemeType;
import sdmx.structure.codelist.CodelistType;
import sdmx.structure.concept.ConceptSchemeType;
import sdmx.structure.concept.ConceptType;
import sdmx.structure.datastructure.DataStructureType;

/**
 *  This file is part of SdmxSax.
 *
 *   SdmxSax is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 
 *   SdmxSax is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SdmxSax.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Copyright James Gardner 2014
 */
public abstract class ConceptChoice {

    public static final int DISPLAY_COLOUR = 0;
    public static final int DISPLAY_SPATIAL = 1;
    public static final int DISPLAY_X = 2;
    public static final int DISPLAY_Y = 3;
    
    
    private Registry registry = null;
    private DataStructureType structure = null;
    private int boundTo = DISPLAY_X;
    public ConceptChoice(Registry reg, DataStructureType struct,String concept){
        this.id=concept;
        this.registry=reg;
        this.structure=struct;
    }
    
    private String id = null;
    private int index = 0;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the index of the concept as it appears in the DSD
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }
    public abstract List<String> getChoiceList();

    /**
     * @return the registry
     */
    public Registry getRegistry() {
        return registry;
    }

    /**
     * @param registry the registry to set
     */
    public void setRegistry(Registry registry) {
        this.registry = registry;
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
    public String getName() {
        Registry registry = ConceptChoiceModel.MODEL.getRegistry();
        String conceptString = getId().toString();
        Component c = ConceptChoiceModel.MODEL.getStructure().getDataStructureComponents().findDimension(conceptString);
        ConceptReferenceType conceptRef = c.getConceptIdentity();
        ConceptType concept = null;
        if (conceptRef != null) {
            ConceptSchemeType con = registry.findConceptScheme(conceptRef.getRef().getAgencyId(), conceptRef.getRef().getMaintainableParentId());
            if (con == null) {
                System.out.println("Cant find concept:" + conceptRef.getRef().getMaintainableParentId());
            }
            concept = con.findConcept(c.getConceptIdentity().getRef().getId());
            if( concept==null) System.out.println("Can't find concept::"+c.getConceptIdentity().getRef().getId().toString());
            Locale loc = Locale.getDefault();
            Name name = concept==null?null:concept.findName(loc.getLanguage());
            if (name == null) {
                return "Can't find Concept";
            }
            return name.getText();
        } 
        return conceptString;
    }
    public String getDescription() {
        Registry registry = ConceptChoiceModel.MODEL.getRegistry();
        String conceptString = getId().toString();
        Component c = ConceptChoiceModel.MODEL.getStructure().getDataStructureComponents().findDimension(conceptString);
        ConceptReferenceType conceptRef = c.getConceptIdentity();
        ConceptType concept = null;
        if (conceptRef != null) {
            ConceptSchemeType con = registry.findConceptScheme(conceptRef.getRef().getAgencyId(), conceptRef.getRef().getMaintainableParentId());
            if (con == null) {
                System.out.println("Cant find concept:" + conceptRef.getRef().getMaintainableParentId());
            }
            concept = con.findConcept(c.getConceptIdentity().getRef().getId());
            Locale loc = Locale.getDefault();
            Description name = concept==null?null:concept.findDescription(loc.getLanguage());
            if (name == null) {
                return "";
            }
            return name.getText();
        } 
        return conceptString;
    }
    public ItemSchemeType getCodes() {
        return ConceptChoiceModel.MODEL.getPossibleCodes(id);
    }

    void dump() {
        System.out.println("Concept Choice");
        System.out.println("Bound to:"+this.boundTo);
        System.out.println("ID:"+this.id);
        System.out.println("Name:"+this.getName());
        System.out.println("Desc:"+this.getDescription());
        Registry registry = ConceptChoiceModel.MODEL.getRegistry();
        String conceptString = getId().toString();
        Component c = ConceptChoiceModel.MODEL.getStructure().getDataStructureComponents().findDimension(conceptString);
        System.out.println("Component:"+c);
        ConceptReferenceType conceptRef = c.getConceptIdentity();
        System.out.println("ConceptRef:"+conceptRef.getRef().getId().toString());
        for(int i=0;i<getChoiceList().size();i++) {
            System.out.println("Choice:"+i+":"+getChoiceList().get(i));
        }
        
    }
}

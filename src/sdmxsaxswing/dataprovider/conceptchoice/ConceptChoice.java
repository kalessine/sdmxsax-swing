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
import sdmx.Queryable;
import sdmx.common.Description;
import sdmx.common.Name;
import sdmx.commonreferences.ConceptReference;
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
    
    
    private Queryable queryable = null;
    private DataStructureType structure = null;
    private int boundTo = DISPLAY_X;
    public ConceptChoice(Queryable q, DataStructureType struct,String concept){
        this.id=concept;
        this.queryable=q;
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
    public Queryable getQueryable() {
        return queryable;
    }

    /**
     * @param registry the registry to set
     */
    public void setQueryable(Queryable q) {
        this.queryable=q;
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
        Queryable q = ConceptChoiceModel.MODEL.getQueryable();
        String conceptString = getId().toString();
        Component c = ConceptChoiceModel.MODEL.getStructure().getDataStructureComponents().findDimension(conceptString);
        ConceptReference conceptRef = c.getConceptIdentity();
        ConceptType concept = null;
        if (conceptRef != null) {
            concept = q.getRegistry().find(conceptRef);
            if( concept==null) System.out.println("Can't find concept::"+c.getConceptIdentity().getId().toString());
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
        Queryable q = ConceptChoiceModel.MODEL.getQueryable();
        String conceptString = getId().toString();
        Component c = ConceptChoiceModel.MODEL.getStructure().getDataStructureComponents().findDimension(conceptString);
        ConceptReference conceptRef = c.getConceptIdentity();
        ConceptType concept = null;
        if (conceptRef != null) {
            concept = q.getRegistry().find(conceptRef);
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
        Queryable queryable = ConceptChoiceModel.MODEL.getQueryable();
        String conceptString = getId().toString();
        Component c = ConceptChoiceModel.MODEL.getStructure().getDataStructureComponents().findDimension(conceptString);
        System.out.println("Component:"+c);
        ConceptReference conceptRef = c.getConceptIdentity();
        System.out.println("ConceptRef:"+conceptRef.getId().toString());
        for(int i=0;i<getChoiceList().size();i++) {
            System.out.println("Choice:"+i+":"+getChoiceList().get(i));
        }
        
    }
}

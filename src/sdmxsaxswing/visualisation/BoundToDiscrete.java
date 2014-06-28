/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdmxsaxswing.visualisation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sdmx.Registry;
import sdmx.commonreferences.DataStructureReferenceType;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.ItemSchemeReferenceBaseType;
import sdmx.commonreferences.ItemSchemeReferenceType;
import sdmx.commonreferences.VersionType;
import sdmx.commonreferences.types.ObjectTypeCodelistType;
import sdmx.structure.base.Component;
import sdmx.structure.base.ItemType;
import sdmx.structure.codelist.CodelistType;
import sdmx.structure.concept.ConceptSchemeType;
import sdmx.structure.datastructure.DataStructureType;
import static sdmxsaxswing.visualisation.BoundTo.NOT_BOUND;

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
public class BoundToDiscrete extends BoundTo {
    private List<ItemType> currentValues = new ArrayList<ItemType>();
    private List<ItemType> possibleValues = new ArrayList<ItemType>();
    
    public BoundToDiscrete(Registry registry,DataStructureReferenceType dst,String concept) {
        super(registry,dst,concept);
        DataStructureType struct = registry.findDataStructure(dst.getRef().getAgencyId(), (IDType) dst.getRef().getId(),dst.getRef().getVersion());
        Component comp = struct.findComponent(new IDType(concept));
        System.out.println("Comp:"+concept+"="+comp);
        ItemSchemeReferenceBaseType ref = comp.getLocalRepresentation().getEnumeration();
        if( registry.findCodelist(ref)!=null) {
            CodelistType codelist = registry.findCodelist(ref);
            for(int i=0;i<codelist.size();i++) {
                possibleValues.add(codelist.getCode(i));
            }
        }else {
            System.out.println("Scheme="+ref.getRef().getAgencyId().toString()+":"+ref.getRef().getId().toString());
            ConceptSchemeType scheme = registry.findConceptScheme(ref.getRef().getAgencyId(), (IDType)ref.getRef().getId());
            for(int i=0;i<scheme.size();i++) {
                possibleValues.add(scheme.getConcept(i));
            }
        }
    }

    public int getBoundTo() { return NOT_BOUND; }
    /**
     * @return the currentValues
     */
    public List<ItemType> getCurrentValues() {
        return currentValues;
    }

    /**
     * @param currentValues the currentValues to set
     */
    public void setCurrentValues(List<ItemType> currentValues) {
        this.currentValues = currentValues;
    }

    /**
     * @return the possibleValues
     */
    public List<ItemType> getPossibleValues() {
        return possibleValues;
    }

    /**
     * @param possibleValues the possibleValues to set
     */
    public void setPossibleValues(List<ItemType> possibleValues) {
        this.possibleValues = possibleValues;
    }
    public boolean isInCurrentValues(String s) {
        Iterator<ItemType> it = currentValues.iterator();
        while(it.hasNext()) {
            ItemType item = it.next();
            if( item.getId().equals(s))return true;
        }
        return false;
    }
}

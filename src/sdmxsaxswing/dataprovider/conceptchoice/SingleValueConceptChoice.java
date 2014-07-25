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

import java.util.ArrayList;
import java.util.List;
import sdmx.Registry;
import sdmx.message.DataStructure;
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
public class SingleValueConceptChoice extends ConceptChoice {
    public static final int SINGLE_CHOICE_FIXED = 0;
    public static final int SINGLE_CHOICE_DROPDOWN = 1;

    private String defaultChoice = null;
    private int type = SINGLE_CHOICE_DROPDOWN;
    private List<String> choiceList = null;
    
    public SingleValueConceptChoice(Registry reg, DataStructureType struct, String concept) {
        super(reg,struct,concept);
        
    }
    @Override
    public List<String> getChoiceList() {
        return choiceList;
    }

    /**
     * @return the defaultChoice
     */
    public String getDefaultChoice() {
        return defaultChoice;
    }

    /**
     * @param defaultChoice the defaultChoice to set
     */
    public void setDefaultChoice(String defaultChoice) {
        this.defaultChoice = defaultChoice;
        this.choiceList= new ArrayList<String>();
        choiceList.add(defaultChoice);
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
    
}

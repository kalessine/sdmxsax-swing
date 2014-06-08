/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdmxsaxswing.dataprovider.conceptchoice;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import sdmx.Registry;
import sdmx.structure.datastructure.DataStructureType;
import sdmx.xml.DateTime;

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
public class TimeValueConceptChoice extends ConceptChoice {
    
    public TimeValueConceptChoice(Registry reg, DataStructureType struct, String concept) {
        super(reg,struct,concept);
        
        
    }
    @Override
    public List<String> getChoiceList() {
        return Collections.EMPTY_LIST;
    }

    private String from = "2008";
    private String to = "2014";

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }
    
}

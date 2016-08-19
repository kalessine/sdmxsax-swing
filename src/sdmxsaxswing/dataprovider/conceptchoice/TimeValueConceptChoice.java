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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sdmx.Queryable;
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
    
    public TimeValueConceptChoice(Queryable q, DataStructureType struct, String concept) {
        super(q,struct,concept);
        
        
    }
    @Override
    public List<String> getChoiceList() {
        return Collections.EMPTY_LIST;
    }
    public static final SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String from = "2000-01-01";
    private String to = "2016-08-01";

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }
    public Date getFromDate() {
        try {
            return displayFormat.parse(this.from);
        } catch (ParseException ex) {
            Logger.getLogger(TimeValueConceptChoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();
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
    public Date getToDate() {
        try {
            return displayFormat.parse(to);
        } catch (ParseException ex) {
            Logger.getLogger(TimeValueConceptChoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();
    }
    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }
    
}

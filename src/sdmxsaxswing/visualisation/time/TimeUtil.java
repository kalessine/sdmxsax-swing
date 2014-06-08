/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdmxsaxswing.visualisation.time;

import java.util.Calendar;
import java.util.Locale;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;
import sdmx.data.key.FullKey;
import sdmx.structure.base.ItemType;

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
public class TimeUtil {
   public static RegularTimePeriod createTimePeriod(FullKey key,String time) {
       ItemType freq = (ItemType)key.getComponent("FREQ");
       if( freq == null ) freq = (ItemType)key.getComponent("FREQUENCY");
       if( freq == null ) freq = (ItemType)key.getComponent("TIME_FORMAT");
       RegularTimePeriod rtime =  parseTime(freq.getId().toString(),time);
       System.out.println("Time="+rtime);
       return rtime;
   }
   public static RegularTimePeriod parseTime(String freq, String s) {
        if ("".equals(s)) {
            throw new RuntimeException("Time Detail of \'\'");
        }
        if (freq != null) {
            if ("A".equals(freq)) {
                return Year.parseYear(s);
            } else if ("B".equals(freq)) {
                return Day.parseDay(s);
            } else if ("D".equals(freq)) {
                return Day.parseDay(s);
            } else if ("M".equals(freq)) {
                return Month.parseMonth(s);
            } else if ("Q".equals(freq)) {
                return Quarter.parseQuarter(s);
            } else if ("S".equals(freq)) {
                return Semester.parseSemester(s);
            } else if ("W".equals(freq)) {
                return Week.parseWeek(s);
            }
        }
        return null;
   }
}

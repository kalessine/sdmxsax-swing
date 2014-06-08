/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdmxsaxswing.visualisation;

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
public class SdmxVisualisation {
    private String provider = "ABS";
    private String protocol = "SOAP";
    private String url = "http://stat.abs.gov.au/sdmxws/sdmx.asmx";
    private String soapnamespace = "http://stats.oecd.org/OECDStatWS/SDMX/";
    
    private String dataSet = "CPI";
    
    Registry registry = null;
    

    public SdmxVisualisation(DataStructure ds) {
        
    }

}
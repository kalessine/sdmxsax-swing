/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdmxsaxswing.dataprovider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sdmx.Registry;
import sdmx.version.common.Queryable;
import sdmx.version.twopointzero.Sdmx20RESTQueryable;
import sdmx.version.twopointzero.Sdmx20SDWSOAPQueryable;

/**
 *
 * @author James
 */
public class DataProvider {
    private static final List<DataProvider> LIST = new ArrayList<DataProvider>();
    
    static{
        try {
            LIST.add(new SoapDataProvider("ABS","http://stat.abs.gov.au/sdmxws/sdmx.asmx","http://stats.oecd.org/OECDStatWS/SDMX/"));
            LIST.add(new SoapDataProvider("OECD","http://stats.oecd.org/SDMXWS/sdmx.asmx","http://stats.oecd.org/OECDStatWS/SDMX/"));
            LIST.add(new SoapDataProvider("IMF","http://sdmxws.imf.org/IMFStatWS_SDMX2/sdmx.asmx","http://stats.imf.org/DotStatWS/SDMX/"));
            LIST.add(new RESTDataProvider("ESTAT","http://www.ec.europa.eu/eurostat/SDMX/diss-web/rest"));
            //LIST.add(new RESTDataProvider("FAO","http://www.fao.org/figis/sdmx/registry"));
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public DataProvider() {}
    public static List<DataProvider>  getList() { return LIST; }
    public Registry getRegistry() {
        return null;
    }
    
    public String getAgencyId() { return ""; }
    public String toString() { return getAgencyId(); }

}

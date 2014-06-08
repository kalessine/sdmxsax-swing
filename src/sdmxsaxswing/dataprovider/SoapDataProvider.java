/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmxsaxswing.dataprovider;

import java.net.MalformedURLException;
import java.net.URL;
import sdmx.Registry;
import sdmx.registry.QueryableServiceRegistry;
import sdmx.version.common.Queryable;
import sdmx.version.twopointzero.Sdmx20SOAPQueryable;

/**
 *
 * @author James
 */
public class SoapDataProvider extends DataProvider {

    private String agencyId = null;
    private URL serviceURL = null;
    private String soapNamespace = null;
    Sdmx20SOAPQueryable q = null;

    public SoapDataProvider(String agency, String serviceURL, String soapNamespace) throws MalformedURLException {
        this.agencyId = agency;
        this.serviceURL = new URL(serviceURL);
        this.soapNamespace = soapNamespace;
        q = new Sdmx20SOAPQueryable(agencyId, serviceURL);
        q.setSoapNamespace(soapNamespace);
    }

    public Registry getRegistry() {
        return new QueryableServiceRegistry(q);
    }
    
    public String getAgencyId() {
        return agencyId;
    }
}

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
import sdmx.registry.RESTServiceRegistry;
import sdmx.version.common.Queryable;

/**
 *
 * @author James
 */
public class RESTDataProvider extends DataProvider {

    private String agencyId = null;
    private URL serviceURL = null;
    private RESTServiceRegistry registry = null;

    public RESTDataProvider(String agency, String serviceURL) throws MalformedURLException {
        this.agencyId = agency;
        this.serviceURL = new URL(serviceURL);
        this.registry = new RESTServiceRegistry(agencyId, serviceURL.toString());
    }


    public String getAgencyId() {
        return agencyId;
    }
    public Registry getRegistry() {
        return registry;
    }
}

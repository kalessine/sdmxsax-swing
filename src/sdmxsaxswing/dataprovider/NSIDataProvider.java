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
package sdmxsaxswing.dataprovider;

import java.net.MalformedURLException;
import java.net.URL;
import sdmx.Registry;
import sdmx.registry.QueryableServiceRegistry;
import sdmx.version.common.Queryable;
import sdmx.version.twopointzero.Sdmx20NSIQueryable;
import sdmx.version.twopointzero.Sdmx20SDWSOAPQueryable;

/**
 *
 * @author James
 */
public class NSIDataProvider extends DataProvider {

    private String agencyId = null;
    private URL serviceURL = null;
    private String soapNamespace = null;
    Sdmx20NSIQueryable q = null;

    public NSIDataProvider(String agency, String serviceURL, String soapNamespace) throws MalformedURLException {
        this.agencyId = agency;
        this.serviceURL = new URL(serviceURL);
        this.soapNamespace = soapNamespace;
        q = new Sdmx20NSIQueryable(agencyId, serviceURL);
        q.setSoapNamespace(soapNamespace);
    }

    public Registry getRegistry() {
        return new QueryableServiceRegistry(q);
    }
    
    public String getAgencyId() {
        return agencyId;
    }
}

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
import sdmx.structure.datastructure.DataStructureType;
import sdmxsaxswing.visualisation.bound.BoundToSeries;

/**
 * This file is part of SdmxSax.
 *
 * SdmxSax is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * SdmxSax is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * SdmxSax. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright James Gardner 2014
 */
public class Bindings {

    private List<BoundTo> bindings = new ArrayList<BoundTo>();

    private Registry registry = null;
    private DataStructureReferenceType dataStructure = null;
    
    
    public Bindings(Registry registry, DataStructureReferenceType reference) {
        this.registry=registry;
        this.dataStructure=reference;
    }
    
    
    
    public BoundTo findBinding(String concept) {
        for (int i = 0; i < bindings.size(); i++) {
            if (bindings.get(i).getConcept().equals(concept)) {
                return bindings.get(i);
            }
        }
        return null;
    }

    public int size() {
        return bindings.size();
    }

    public void addBinding(BoundTo bound) {
        bindings.add(bound);
    }

    public BoundTo getBinding(int i) {
        return bindings.get(i);
    }

    public BoundToDiscrete getDiscreteMultiBinding(int n) {
        Iterator<BoundTo> it = bindings.iterator();
        while (it.hasNext()) {
            BoundTo b = it.next();
            if (b instanceof BoundToDiscrete) {
                BoundToDiscrete bd = (BoundToDiscrete) b;
                if (bd.getCurrentValues().size() > 1) {
                    n--;
                    if (n == -1) {
                        return (BoundToDiscrete) b;
                    }
                }
            }
        }
        return null;
    }

    public BoundToContinuous getContinuousBinding(int n) {
        Iterator<BoundTo> it = bindings.iterator();
        while (it.hasNext()) {
            BoundTo b = it.next();
            if (b instanceof BoundToContinuous) {
                n--;
                if (n == -1) {
                    return (BoundToContinuous) b;
                }
            }
        }
        return null;
    }
    public BoundToTime getBoundToTime() {
        Iterator<BoundTo> it = bindings.iterator();
        while(it.hasNext()) {
            BoundTo b = it.next();
            if( b instanceof BoundToTime ) {
                return (BoundToTime)b;
            }
        }
        return null;
    }
    public BoundToSeries getBoundToSeries() {
        Iterator<BoundTo> it = bindings.iterator();
        while(it.hasNext()) {
            BoundTo b = it.next();
            if( b instanceof BoundToSeries ) {
                return (BoundToSeries)b;
            }
        }
        return null;
    }
    public DataStructureType getDataStructure() {
        return registry.findDataStructure(this.dataStructure.getRef().getAgencyId(), this.dataStructure.getRef().getId().asID(),this.dataStructure.getRef().getVersion());
    }
}
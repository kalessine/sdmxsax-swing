/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmxsaxswing.visualisation.bound;

import java.util.Collections;
import java.util.List;
import sdmx.Registry;
import sdmx.commonreferences.DataStructureReferenceType;
import sdmx.structure.base.ItemType;
import sdmxsaxswing.visualisation.BoundTo;
import sdmxsaxswing.visualisation.BoundToDiscrete;

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
public class BoundToSingleDiscreteValue extends BoundToDiscrete {

    private ItemType value = null;
    
    public BoundToSingleDiscreteValue(Registry registry, DataStructureReferenceType dst, String concept) {
        super(registry, dst, concept);
    }
    /**
     * @return the currentValues
     */
    public List<ItemType> getCurrentValues() {
        return Collections.singletonList(getValue());
    }

    /**
     * @param currentValues the currentValues to set
     */
    public void setCurrentValues(List<ItemType> currentValues) {
        this.setValue(currentValues.get(0));
    }

    /**
     * @return the value
     */
    public ItemType getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(ItemType value) {
        this.value = value;
    }
}
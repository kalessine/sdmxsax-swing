/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmxsaxswing.visualisation.adapter;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.RegularTimePeriod;
import sdmx.common.Name;
import sdmx.common.TextType;
import sdmx.cube.Cube;
import sdmx.data.key.FullKey;
import sdmx.structure.base.ItemType;
import sdmxsaxswing.visualisation.Bindings;
import sdmxsaxswing.visualisation.BoundTo;
import sdmxsaxswing.visualisation.BoundToContinuous;
import sdmxsaxswing.visualisation.BoundToDiscrete;
import sdmxsaxswing.visualisation.BoundToTime;
import sdmxsaxswing.visualisation.bound.BoundToContinuousY;
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
public class SingleSparkline extends Adapter {

    public SingleSparkline() {
    }

    @Override
    public Dataset createDataset(Bindings bindings, List<FullKey> table) {
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        Iterator<FullKey> it = table.iterator();
        while (it.hasNext()) {
            FullKey key = it.next();
            Double value = (Double) key.getComponent(bindings.getContinuousBinding(0).getConcept());
            RegularTimePeriod time = (RegularTimePeriod) key.getComponent(bindings.getBoundToTime().getConcept());
            dcd.addValue(value, "", time);
        }
        return dcd;
    }

    @Override
    public boolean canCreateDatasetFromBindings(Bindings bindings) {
        int singleBinds = 0;
        int multiBinds = 0;
        int continuousBinds = 0;
        int time = 0;
        int series = 0;
        for (int i = 0; i < bindings.size(); i++) {
            BoundTo b = bindings.getBinding(i);
            if (b instanceof BoundToDiscrete) {
                BoundToDiscrete discrete = (BoundToDiscrete) b;
                if (discrete.getCurrentValues().size() == 1) {
                    singleBinds++;
                }
                if (discrete.getCurrentValues().size() > 1) {
                    multiBinds++;
                }
            }
            if (b instanceof BoundToContinuous) {
                BoundToContinuous continuous = (BoundToContinuous) b;
                if (continuous instanceof BoundToContinuousY) {
                    continuousBinds++;
                }
            }
            if (b instanceof BoundToTime) {
                BoundToTime btime = (BoundToTime) b;
                time++;
            }
            if( b instanceof BoundToSeries ) {
                series++;
            }
        }
        return multiBinds == 0 && time == 1 && continuousBinds == 1&&series==0;
    }

}

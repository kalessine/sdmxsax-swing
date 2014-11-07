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

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import sdmx.net.list.DataProvider;

/**
 *
 * @author James
 */
public class DataProviderComboBoxModel implements ComboBoxModel {

    DataProvider selected = null;
    
    public DataProviderComboBoxModel() {
    }
    @Override
    public int getSize() {
        return DataProvider.getList().size();
    }

    @Override
    public Object getElementAt(int index) {
        return DataProvider.getList().get(index);
    }

    List<ListDataListener> listeners = new ArrayList<ListDataListener>();
    
    @Override
    public void addListDataListener(ListDataListener l) {
       listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = (DataProvider)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
    
}

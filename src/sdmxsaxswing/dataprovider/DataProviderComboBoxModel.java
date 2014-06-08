/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdmxsaxswing.dataprovider;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

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

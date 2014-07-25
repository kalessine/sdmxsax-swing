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
package sdmxsaxswing;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import sdmx.commonreferences.DataStructureReferenceType;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.NestedIDType;
import sdmx.commonreferences.NestedNCNameIDType;
import sdmx.commonreferences.VersionType;

/**
 *
 * @author James
 */
public class DataStructureReferenceComboBoxModel implements ComboBoxModel {

    public DataStructureReferenceComboBoxModel(){
    
    }
    
    List<DataStructureReferenceType> list = null;
    
    DataStructureReferenceType selected = null;
    
    @Override
    public int getSize() {
        if(list==null)return 0;
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        DataStructureReferenceType ref = list.get(index);
        return ref;
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
    public void setList(List<DataStructureReferenceType> list) {
        this.list=list;
        ListDataEvent event = new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,0,list.size());
        for(int i=0;i<listeners.size();i++) {
            listeners.get(i).contentsChanged(event);
        }
    }
    public DataStructureReferenceType getDataStructureReference(int i) {
        return list.get(i);
    }

    @Override
    public void setSelectedItem(Object anItem) {
    selected = (DataStructureReferenceType)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
}

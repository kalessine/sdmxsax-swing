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
package sdmxsaxswing.dataprovider.conceptchoice;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import sdmx.structure.base.ItemSchemeType;
import sdmx.structure.base.ItemType;
import sdmx.structure.codelist.CodeType;
import sdmx.structure.codelist.CodelistType;

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
public class CodelistListModel implements ListModel {

    List<ItemType> codes = new ArrayList<ItemType>();

    @Override
    public int getSize() {
        if (codes == null) {
            return 0;
        }
        return codes.size();
    }

    @Override
    public Object getElementAt(int index) {
        return codes.get(index);
    }

    List<ListDataListener> listeners = new ArrayList<>();

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

    public void setCodelist(ItemSchemeType codel) {
        if (codel == null) {
            this.codes = null;
        } else {
            this.codes = new ArrayList<ItemType>();
            for (int i = 0; i < codel.size(); i++) {
                codes.add(codel.getItem(i));
            }
        }
        ListDataEvent lme = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).contentsChanged(lme);
        }
    }

    public ItemType remove(ItemType code) {
        codes.remove(code);
        ListDataEvent lme = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).contentsChanged(lme);
        }
        return code;
    }

    public void add(ItemType code) {
        codes.add(code);
        ListDataEvent lme = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).contentsChanged(lme);
        }
    }

    public boolean contains(String id) {
        for (int i = 0; i < getSize(); i++) {
            if (codes.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public ItemType findCode(String id) {
        for (int i = 0; i < getSize(); i++) {
            if (codes.get(i).getId().equals(id)) {
                return codes.get(i);
            }
        }
        return null;
    }
}

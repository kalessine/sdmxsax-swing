/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmxsaxswing.dataprovider.conceptchoice;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
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
public class CodeTypeComboBoxModel implements ComboBoxModel<CodeType> {

    CodeType selected = null;
    List<CodeType> codes = null;

    @Override
    public void setSelectedItem(Object anItem) {
        this.selected = (CodeType) anItem;

    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        if (codes == null) {
            return 0;
        }
        return codes.size();
    }

    @Override
    public CodeType getElementAt(int index) {
        return codes.get(index);
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

    public void setCodes(CodelistType codel) {
        if (codel == null) {
            codes = null;
        } else {
            codes = new ArrayList<CodeType>();
            for (int i = 0; i < codel.size(); i++) {
                codes.add(codel.getCode(i));
            }
        }
        ListDataEvent lde = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).contentsChanged(lde);
        }
    }

    public void setCodes(CodelistType codes, String defaultChoice) {
        if( codes == null ) return;
        setCodes(codes);
        for (int i = 0; i < codes.size(); i++) {
            if (codes.getId().equals(defaultChoice)) {
                selected = codes.getCode(i);
                return;
            }
        }
        selected = null;
    }

}

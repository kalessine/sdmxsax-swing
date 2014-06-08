/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdmxsaxswing.dataprovider.conceptchoice;

import java.awt.Component;
import java.util.Locale;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import sdmx.common.Name;
import sdmx.structure.codelist.CodeType;

/**
 *  This file is part of SdmxSax.
 *
 *   SdmxSax is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 
 *   SdmxSax is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SdmxSax.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Copyright James Gardner 2014
 */
public class CodelistCellRenderer extends JLabel implements ListCellRenderer<CodeType> {

    public Component getListCellRendererComponent(JList<? extends CodeType> list, CodeType value, int index, boolean isSelected, boolean cellHasFocus) {
        Locale loc = Locale.getDefault();
        Name name = value.findName(loc.getLanguage());
        if( name!=null ) {setText(name.getText());}
        else setText(value.getId().toString());
        return this;
    }

}

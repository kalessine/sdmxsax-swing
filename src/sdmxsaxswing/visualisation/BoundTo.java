/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmxsaxswing.visualisation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import sdmx.Registry;
import sdmx.commonreferences.DataStructureReferenceType;
import sdmx.structure.codelist.CodelistType;
import sdmx.structure.datastructure.DataStructureType;

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
public class BoundTo {

    public static final int NOT_BOUND = -1;
    public static final int BOUND_X = 0;
    public static final int BOUND_Y = 1;
    public static final int BOUND_AREA = 2;
    public static final int BOUND_COLOUR = 3;
    public static final int BOUND_SIZE = 4;
    public static final int BOUND_TOOLTIP = 5;

    public static final int BOUND_DISCRETE_DROPDOWN = 6;
    public static final int BOUND_DISCRETE_LIST = 7;
    public static final int BOUND_DISCRETE_STATIC = 8;

    public static final int BOUND_CONTINUOUS_BETWEEN = 9;
    public static final int BOUND_CONTINUOUS_GREATERTHAN = 10;
    public static final int BOUND_CONTINUOUS_LESSTHAN = 11;
    
    private String concept = null;
    private int boundTo = NOT_BOUND;
    private boolean continuous = false;
    private Comparator order = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return o1.toString().compareTo(o2.toString());
        }
    };
    
    private boolean measureDescriptor = false;
    
    private Registry registry = null;
    private DataStructureReferenceType dataStruct = null;
    
    public BoundTo(Registry registry,DataStructureReferenceType dst,String concept) {
        this.registry=registry;
        this.dataStruct=dst;
        this.concept=concept;
    }
    

    /**
     * @return the concept
     */
    public String getConcept() {
        return concept;
    }

    /**
     * @param concept the concept to set
     */
    public void setConcept(String concept) {
        this.concept = concept;
    }

    /**
     * @return the boundTo
     */
    public int getBoundTo() {
        return NOT_BOUND;
    }

    /**
     * @return the continuous
     */
    public boolean isContinuous() {
        return continuous;
    }

    /**
     * @param continuous the continuous to set
     */
    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
    }

    public boolean isDiscrete() {
        return !continuous;
    }

    /**
     * @return the order
     */
    public Comparator getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Comparator order) {
        this.order = order;
    }
    public int expectValues() {
        return 1;
    }

    /**
     * @return the measureDescriptor
     */
    public boolean isMeasureDescriptor() {
        return measureDescriptor;
    }

    /**
     * @param measureDescriptor the measureDescriptor to set
     */
    public void setMeasureDescriptor(boolean measureDescriptor) {
        this.measureDescriptor = measureDescriptor;
    }
}

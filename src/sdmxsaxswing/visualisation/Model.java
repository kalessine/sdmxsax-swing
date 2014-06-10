/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmxsaxswing.visualisation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import sdmx.Registry;
import sdmx.SdmxIO;
import sdmx.commonreferences.DataStructureRefType;
import sdmx.commonreferences.DataStructureReferenceType;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.NestedNCNameIDType;
import sdmx.cube.Cube;
import sdmx.cube.CubeDimension;
import sdmx.cube.CubeObservation;
import sdmx.cube.TimeCubeDimension;
import sdmx.data.key.FullKey;
import sdmx.data.key.PartialKey;
import sdmx.exception.ParseException;
import sdmx.message.DataMessage;
import sdmx.message.StructureType;
import sdmx.structure.base.Component;
import sdmx.structure.base.ComponentList;
import sdmx.structure.base.ItemType;
import sdmx.structure.dataflow.DataflowType;
import sdmx.structure.datastructure.DataStructureType;
import sdmxsaxswing.visualisation.adapter.SeriesSparkline;
import sdmxsaxswing.visualisation.bound.BoundToContinuousY;
import sdmxsaxswing.visualisation.bound.BoundToDropDown;
import sdmxsaxswing.visualisation.bound.BoundToSeries;
import sdmxsaxswing.visualisation.graph.SparklineChart;
import sdmxsaxswing.visualisation.time.TimeUtil;

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
public class Model {

    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
        InputStream in = new FileInputStream("alc-data.xml");
        InputStream in2 = new FileInputStream("alc-structure.xml");
        StructureType struct = SdmxIO.parseStructure(in2);
        DataMessage data = SdmxIO.parseData(in);
        Cube cube = new Cube(struct.getStructures().getDataStructures().getDataStructures().get(0));
        data.getDataSets().get(0).query(cube, null);
        Model model = new Model(struct,struct.getStructures().getDataStructures().getDataStructures().get(0).asDataflow());
        model.CUBE = cube;
        DataStructureRefType ref1 = new DataStructureRefType(new NestedNCNameIDType("ABS"), new IDType("ALC"), null);
        DataStructureReferenceType ref = new DataStructureReferenceType(ref1, null);
        BoundToDropDown b1 = new BoundToDropDown(struct, ref, "TYP");
        init(b1);
        BoundToDropDown b2 = new BoundToDropDown(struct, ref, "MEA");
        init(b2);
        b2.setMeasureDescriptor(true);
        BoundToDropDown b3 = new BoundToDropDown(struct, ref, "BEVT");
        List<ItemType> b3vals = b3.getPossibleValues();
        b3.setCurrentValues(Collections.singletonList(b3vals.get(0)));
        BoundToSeries b4 = new BoundToSeries(struct, ref, "SUB");
        List<ItemType> b4vals = b4.getPossibleValues();
        b4.setCurrentValues(b4vals);
        BoundToDropDown b5 = new BoundToDropDown(struct, ref, "FREQUENCY");
        init(b5);
        BoundToTime b6 = new BoundToTime(struct, ref, "TIME");
        BoundToContinuousY b7 = new BoundToContinuousY(struct, ref, "OBS_VALUE");
        model.bindings.addBinding(b1);
        model.bindings.addBinding(b2);
        model.bindings.addBinding(b3);
        model.bindings.addBinding(b4);
        model.bindings.addBinding(b5);
        model.bindings.addBinding(b6);
        model.bindings.addBinding(b7);
        model.visit();
        System.out.println("***Start Table***");
        for (int i = 0; i < model.table.size(); i++) {
            model.table.get(i).dump();
        }
        System.out.println("***End Table***");
        new SparklineChart(new SeriesSparkline(), model.bindings, model.table).showFrame();
    }

    private static void init(BoundToDropDown dd) {
        List<ItemType> items = dd.getPossibleValues();
        List<ItemType> c = Collections.singletonList(items.get(0));
        dd.setCurrentValues(c);
    }

    private DataStructureType dataStructure = null;
    private Cube CUBE = null;
    private Bindings bindings = null;

    List<FullKey> table = new LinkedList<FullKey>();
    FullKey key = new FullKey();

    public Model(Registry registry, DataflowType flow) {
        this.bindings = new Bindings(registry,flow);
    }

    public void visit() {
        CubeDimension current = CUBE.getRootCubeDimension();
        Iterator<CubeDimension> it = current.listSubDimensions().iterator();
        while (it.hasNext()) {
            key = new FullKey();
            CubeDimension dim = it.next();
            BoundTo b = bindings.findBinding(dim.getSubDimension());
            if (b instanceof BoundToDiscrete) {
                BoundToDiscrete discrete = (BoundToDiscrete) b;
                if (discrete.isInCurrentValues(dim.getValue())) {
                    CubeDimension inner = null;
                    for (int j = 0; j < discrete.getCurrentValues().size(); j++) {
                        ItemType value = discrete.getCurrentValues().get(j);
                        inner = dim.getSubDimension(value.getId().toString());
                        //System.out.println("Dim:"+dim.getSubDimension()+":"+value.getId().toString());
                        if (inner == null) {
                            //System.out.println("Can't Find:" + dim.getConcept() + ":" + value.getId().toString());
                            //key.dump();
                        } else {
                            key.setComponent(dim.getConcept(), value);
                            visit(bindings, dim);
                            key.setComponent(dim.getConcept(), null);
                        }
                    }
                } else if (b instanceof BoundToTime) {
                    Iterator<CubeDimension> it2 = dim.listSubDimensions().iterator();
                    while (it2.hasNext()) {
                        TimeCubeDimension time = (TimeCubeDimension) it2.next();
                        key.setComponent(time.getConcept(), time.getValue());
                        visit(bindings, time);
                        key.setComponent(time.getConcept(), null);
                    }
                } else {
                    System.out.println("Not Bound");
                }
            }
        }
    }

    public void visit(Bindings bindings, CubeDimension dim) {
        //System.out.println("Visit:" + dim.getConcept() + ":" + dim.getValue());
        //if( dim.getConcept().equals("BEVT") ) dim.dump();
        BoundTo b = bindings.findBinding(dim.getSubDimension());
        if (b instanceof BoundToDiscrete) {
            BoundToDiscrete discrete = (BoundToDiscrete) b;
            CubeDimension inner = null;
            for (int j = 0; j < discrete.getCurrentValues().size(); j++) {
                ItemType value = discrete.getCurrentValues().get(j);
                inner = dim.getSubDimension(value.getId().toString());
                //System.out.println("Dim:"+dim.getSubDimension()+":"+value.getId().toString());
                if (inner == null) {
                    //System.out.println("Can't Find:" + dim.getConcept() + ":" + value.getId().toString());
                    //key.dump();
                } else {
                    //if (discrete.getCurrentValues().size() > 1) {
                    key.setComponent(inner.getConcept(), value);
                    visit(bindings, inner);
                    key.setComponent(inner.getConcept(), null);
                    //} else {
                    //visit(bindings, inner);
                    //}
                    //System.out.println("Recursing");
                }
            }
        } else if (b instanceof BoundToTime) {
            Iterator<CubeDimension> it = dim.listSubDimensions().iterator();
            while (it.hasNext()) {
                TimeCubeDimension time = (TimeCubeDimension) it.next();
                key.setComponent(time.getConcept(), TimeUtil.createTimePeriod(key, time.getValue()));
                visit(bindings, time);
                key.setComponent(time.getConcept(), null);
            }
        } else {
            System.out.println("Not Bound");
        }
    }

    public void visit(Bindings binds, TimeCubeDimension dim) {
        //System.out.println("Visit:"+dim.getConcept());
        Collection<CubeObservation> obsList = dim.listObservations();
        Iterator<CubeObservation> it = obsList.iterator();
        while (it.hasNext()) {
            CubeObservation ob = it.next();
            visit(bindings, ob);
        }
    }

    public void visit(Bindings binds, CubeObservation dim) {
        if (dim.getCrossSection() != null) {
            key.setComponent(dim.getConcept(), dim.getCrossSection());
        }
        String concept = dim.getConcept();
        BoundTo binding = binds.findBinding(concept);
        BoundToContinuous continuous = (BoundToContinuous) binding;
        key.setComponent(dim.getObservationConcept(), Double.parseDouble(dim.getValue()));
        table.add(key.clone());
        //System.out.println("Adding obs");
    }
}

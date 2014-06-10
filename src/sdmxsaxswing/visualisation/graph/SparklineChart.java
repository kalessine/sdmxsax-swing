/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmxsaxswing.visualisation.graph;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.net.URL;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import sdmx.data.key.FullKey;
import sdmxsaxswing.visualisation.Bindings;
import sdmxsaxswing.visualisation.BoundTo;
import sdmxsaxswing.visualisation.adapter.SeriesSparkline;

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
public class SparklineChart extends JFrame {

    private SeriesSparkline spark = null;
    private Bindings bindings = null;
    private List<FullKey> table = null;
    public SparklineChart(SeriesSparkline spark, Bindings bindings, List<FullKey> table) {
       this.spark=spark;
       this.bindings=bindings;
       this.table=table;
    }
    public JFreeChart createChart(SeriesSparkline spark, Bindings bindings, List<FullKey> table) {
        String title = bindings.getDataFlow().toString();
        BoundTo measureDescriptor = bindings.getMeasureDescriptor();
        String axis = "";
        if( table.size()>0){
            axis = table.get(0).getComponent(measureDescriptor.getConcept()).toString();
        }
        JFreeChart chart = ChartFactory.createLineChart(
                title, // chart title
                "Time", // domain axis label
                axis, (CategoryDataset) spark.createDataset(bindings, table), // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
        );
        //chart.addSubtitle(new TextTitle("Number of Classes By Release"));
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        plot.setRangeGridlinesVisible(false);
        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartUtilities.applyCurrentTheme(chart);

        // customise the renderer...
        LineAndShapeRenderer renderer
                = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);
        renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        renderer.setSeriesOutlineStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesShape(0, new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));
        return chart;
    }
    public void showFrame() {
        JFreeChart chart = createChart(spark, bindings, table);
        ChartPanel panel = new ChartPanel(chart);
        getContentPane().add(panel,BorderLayout.CENTER);
        setSize(new Dimension(500,500));
        setVisible(true);
    }
}

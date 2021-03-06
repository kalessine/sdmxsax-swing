/**
 *  This file is part of SdmxSax.
 *
 *   SdmxSax is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *  SdmxSax is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SdmxSax.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright James Gardner 2014
 */
package sdmxsaxswing.dataprovider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import sdmx.commonreferences.DataStructureReference;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.Version;
import sdmx.exception.QueryableException;
import sdmx.message.DataMessage;
import sdmx.message.DataQueryMessage;
import sdmx.query.data.DataQuery;
import sdmx.structure.dataflow.DataflowType;
import sdmx.structure.datastructure.DataStructureType;
import sdmx.structureddata.StructuredDataMessage;
import sdmxsaxswing.MainJFrame;
import sdmxsaxswing.dataandstructure.CombinedDataJFrame;
import sdmxsaxswing.dataprovider.conceptchoice.ConceptChoice;
import sdmxsaxswing.dataprovider.conceptchoice.ConceptChoiceJPanel;
import sdmxsaxswing.dataprovider.conceptchoice.ConceptChoiceModel;
import sdmx.Queryable;
import sdmx.Registry;
import sdmx.exception.ParseException;
import sdmx.querykey.Query;
import sdmx.version.common.ParseParams;

/**
 *
 * @author James
 */
public class DataProviderJFrame extends javax.swing.JFrame {

    List<JPanel> panels = new ArrayList<JPanel>();

    /**
     * Creates new form DataProviderJFrame
     */
    public DataProviderJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        timeValueJPanel1 = new sdmxsaxswing.dataprovider.TimeValueJPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addTab("Time", timeValueJPanel1);

        jButton1.setText("Query");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Query q = ConceptChoiceModel.MODEL.toQuery();
        //System.out.println("Blah!" + registry);
        //System.out.println("Query");
        ParseParams params = new ParseParams();
        params.setRegistry(queryable.getRegistry());
        DataMessage message = null;
        try {
            message = queryable.getRepository().query(q);
        } catch (ParseException ex) {
            Logger.getLogger(DataProviderJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataProviderJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (message.getHeader().getStructures() == null || message.getHeader().getStructures().size() == 0) {
            message.setDataStructure(ref, null);
        }
        //System.out.println("REg=" + registry);
        System.out.println("Ref=" + ref.getAgencyId() + ":" + ref.getMaintainableParentId() + ":" + ref.getVersion());
        DataStructureType ds = queryable.getRegistry().find(ref);
        //System.out.println("DS=" + ds);

        StructuredDataMessage structured = new StructuredDataMessage(message, queryable.getRegistry());
        //System.out.println("Made structured");
        CombinedDataJFrame frame = new CombinedDataJFrame();
        frame.setCombinedDataAndStructure(structured);
        //System.out.println("Set doc");
        frame.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataProviderJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataProviderJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataProviderJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataProviderJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataProviderJFrame().setVisible(true);
            }
        });
    }

    private Queryable queryable = null;
    private DataStructureReference ref = null;

    public void setDataStructure(Queryable q, DataflowType flow) {
        this.setTitle(flow.toString());
        this.queryable = q;
        this.ref = flow.getStructure();
        ConceptChoiceModel.MODEL.setDataStructure(queryable, flow);
        for (int i = 0; i < ConceptChoiceModel.MODEL.size(); i++) {
            ConceptChoice cc = ConceptChoiceModel.MODEL.getConceptChoice(i);
            ConceptChoiceJPanel panel = new ConceptChoiceJPanel();
            panel.setId(cc.getId());
            jTabbedPane1.add(panel, cc.getId());
            panel.get();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private sdmxsaxswing.dataprovider.TimeValueJPanel timeValueJPanel1;
    // End of variables declaration//GEN-END:variables
}

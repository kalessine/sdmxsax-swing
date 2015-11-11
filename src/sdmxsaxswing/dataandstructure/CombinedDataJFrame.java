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
package sdmxsaxswing.dataandstructure;

import java.awt.Dimension;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import sdmx.SdmxIO;
import sdmx.cube.Cube;
import sdmx.message.StructureType;
import sdmx.structure.dataflow.DataflowType;
import sdmx.structureddata.StructuredDataMessage;
import sdmx.version.common.ParseParams;
import sdmxsaxswing.MainJFrame;

/**
 *
 * @author James
 */
public class CombinedDataJFrame extends javax.swing.JFrame {

    private DataflowType dataflow = null;
    /**
     * Creates new form CombinedDataJFrame2
     */
    public CombinedDataJFrame() {
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmFileOpen = new javax.swing.JMenuItem();
        jmFileSaveJSON = new javax.swing.JMenuItem();
        jmEdit = new javax.swing.JMenu();
        jmLocale = new javax.swing.JMenuItem();
        jmnCube = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        jmFileOpen.setText("Open");
        jmFileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmFileOpenActionPerformed(evt);
            }
        });
        jMenu1.add(jmFileOpen);

        jmFileSaveJSON.setText("Save JSON-STAT");
        jmFileSaveJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmFileSaveJSONActionPerformed(evt);
            }
        });
        jMenu1.add(jmFileSaveJSON);

        jMenuBar1.add(jMenu1);

        jmEdit.setText("Edit");

        jmLocale.setText("Locale");
        jmLocale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmLocaleActionPerformed(evt);
            }
        });
        jmEdit.add(jmLocale);

        jmnCube.setText("Cube");
        jmnCube.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnCubeActionPerformed(evt);
            }
        });
        jmEdit.add(jmnCube);

        jMenuBar1.add(jmEdit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmFileOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmFileOpenActionPerformed
        MainJFrame.FRAME.showRequest(MainJFrame.FRAME);
        dispose();
    }//GEN-LAST:event_jmFileOpenActionPerformed

    private void jmLocaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmLocaleActionPerformed
        LocaleJDialog dialog = new LocaleJDialog(this,true);
        dialog.setSize(new Dimension(300,400));
        dialog.setVisible(true);
        this.jTable2.setModel(new DefaultTableModel());
        this.jTable2.setModel(model);
    }//GEN-LAST:event_jmLocaleActionPerformed

    private void jmFileSaveJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmFileSaveJSONActionPerformed
        FileOutputStream fos = null;
        try {
            JFileChooser jfc = new JFileChooser();
            jfc.showSaveDialog(jMenu1);
            File f = jfc.getSelectedFile();
            if( f == null ) return;
            fos = new FileOutputStream(f);
            ParseParams params = new ParseParams();
            params.setRegistry(cds.getRegistry());
            params.setDataflow(dataflow);
            SdmxIO.write(params,"application/json", cds.getDataMessage(), fos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CombinedDataJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CombinedDataJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(CombinedDataJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jmFileSaveJSONActionPerformed

    private void jmnCubeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnCubeActionPerformed
        Cube cube = new Cube(cds.getRegistry().find(cds.getDataflow().getStructure()));
        for(int i=0;i<cds.getDataMessage().getDataSets().get(0).size();i++) {
            cube.putObservation(null,cds.getDataMessage().getDataSets().get(0).getColumnMapper(),cds.getDataMessage().getDataSets().get(0).getFlatObs(i));
        }
    }//GEN-LAST:event_jmnCubeActionPerformed

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
            java.util.logging.Logger.getLogger(CombinedDataJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CombinedDataJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CombinedDataJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CombinedDataJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CombinedDataJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JMenu jmEdit;
    private javax.swing.JMenuItem jmFileOpen;
    private javax.swing.JMenuItem jmFileSaveJSON;
    private javax.swing.JMenuItem jmLocale;
    private javax.swing.JMenuItem jmnCube;
    // End of variables declaration//GEN-END:variables

    private StructuredDataMessage cds = null;
    private CombinedDataTableModel model = new CombinedDataTableModel();
    private StructureType structure = null;
    public void setCombinedDataAndStructure(StructuredDataMessage cds) {
        this.cds=cds;
        model.setCombinedDataMessage(cds);
        this.jTable2.setModel(model);
        MainJFrame.FRAME.showRequest(this);
    }

    /**
     * @return the dataflow
     */
    public DataflowType getDataflow() {
        return dataflow;
    }

    /**
     * @param dataflow the dataflow to set
     */
    public void setDataflow(DataflowType dataflow) {
        this.dataflow = dataflow;
    }
}

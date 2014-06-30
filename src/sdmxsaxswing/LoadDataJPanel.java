/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmxsaxswing;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import sdmx.Registry;
import sdmx.SdmxIO;
import sdmx.commonreferences.DataStructureReferenceType;
import sdmx.exception.ParseException;
import sdmx.message.DataMessage;
import sdmx.message.StructureType;
import sdmx.registry.LocalRegistry;
import sdmx.registry.QueryableServiceRegistry;
import sdmx.structure.dataflow.DataflowType;
import sdmx.structureddata.StructuredDataMessage;
import sdmx.version.common.Queryable;
import sdmxsaxswing.dataandstructure.CombinedDataJFrame;
import sdmxsaxswing.dataonly.DisplayDataMessageJFrame;
import sdmxsaxswing.dataprovider.DataProvider;
import sdmxsaxswing.dataprovider.DataProviderComboBoxModel;
import sdmxsaxswing.dataprovider.DataProviderJFrame;

/**
 *
 * @author James
 */
public class LoadDataJPanel extends javax.swing.JPanel {

    DataflowComboBoxModel refModel = new DataflowComboBoxModel();

    /**
     * Creates new form LoadDataJPanel
     */
    public LoadDataJPanel() {
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
        browseFile = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jtStructure = new javax.swing.JTextField();
        browseStructure = new javax.swing.JButton();
        browseData = new javax.swing.JButton();
        jtData = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jbLoadBoth = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcbProvider = new javax.swing.JComboBox();
        jcbDataSet = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Load Data Message"));

        browseFile.setText("Browse");
        browseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseFileActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setText("CompactData, GenericData(2.0), StructureSpecific, GenericData(2.1)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(browseFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseFile)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Load Data Message with Data Structure"));

        jtStructure.setEditable(false);

        browseStructure.setText("Browse");
        browseStructure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseStructureActionPerformed(evt);
            }
        });

        browseData.setText("Browse");
        browseData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseDataActionPerformed(evt);
            }
        });

        jtData.setEditable(false);

        jLabel1.setText("Data");

        jLabel2.setText("Structure");

        jbLoadBoth.setText("Load");
        jbLoadBoth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLoadBothActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(browseStructure)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtStructure))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(browseData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtData))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jbLoadBoth)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseStructure)
                    .addComponent(jtStructure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbLoadBoth)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Service"));

        jLabel3.setText("Provider");

        jLabel4.setText("DataSet");

        jcbProvider.setModel(new sdmxsaxswing.dataprovider.DataProviderComboBoxModel());
        jcbProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbProviderActionPerformed(evt);
            }
        });

        jcbDataSet.setModel(refModel);
        jcbDataSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDataSetActionPerformed(evt);
            }
        });

        jButton1.setText("Load");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbProvider, 0, 317, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbDataSet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbProvider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jcbDataSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void browseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseFileActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(this);
        File f = jfc.getSelectedFile();
        DisplayDataMessageJFrame frame = new DisplayDataMessageJFrame();
        SwingUtilities.getRoot(this).setVisible(false);
        frame.setVisible(true);
        frame.setFile(f);
    }//GEN-LAST:event_browseFileActionPerformed

    private void browseStructureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseStructureActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(this);
        File file = jfc.getSelectedFile();
        if (file == null) {
            return;
        }
        this.jtStructure.setText(file.getPath());
    }//GEN-LAST:event_browseStructureActionPerformed

    private void browseDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseDataActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(this);
        File file = jfc.getSelectedFile();
        if (file == null) {
            return;
        }
        this.jtData.setText(file.getPath());
    }//GEN-LAST:event_browseDataActionPerformed

    private void jbLoadBothActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLoadBothActionPerformed
        FileInputStream dataFis = null;
        FileInputStream strucFis = null;
        try {
            File structure = new File(jtStructure.getText());
            File data = new File(jtData.getText());
            strucFis = new FileInputStream(structure);
            dataFis = new FileInputStream(data);
            StructureType struct = SdmxIO.parseStructure(LocalRegistry.getDefaultWorkspace(),strucFis);
            DataMessage dataMsg = SdmxIO.parseData(dataFis,false);
            if( struct.getStructures().getDataStructures().getDataStructures().size()==0 ) return;
            if (struct.getStructures().getDataStructures().getDataStructures().size() > 1) {
                ChooseDataStructureJDialog dialog = new ChooseDataStructureJDialog(MainJFrame.FRAME, true);
                dialog.setDataMessage(dataMsg);
                dialog.setStructure(struct);
                dialog.setVisible(true);
            }else if( struct.getStructures().getDataStructures().getDataStructures().size()==1 ) {
                dataMsg.setDataStructure(struct.getStructures().getDataStructures().getDataStructures().get(0).asReference(),null);
                CombinedDataJFrame frame = new CombinedDataJFrame();
                StructuredDataMessage cds= new StructuredDataMessage(dataMsg,LocalRegistry.getDefaultWorkspace());
                frame.setCombinedDataAndStructure(cds);
                MainJFrame.FRAME.showRequest(frame);
            }
                File f = new File(System.currentTimeMillis()+".xml");
                System.out.println("f="+f.getPath());
                FileOutputStream fos = new FileOutputStream(f);
                sdmx.version.twopointone.writer.Sdmx21StructureWriter.write(struct, fos);
         
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadDataJPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadDataJPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(LoadDataJPanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (dataFis != null) {
                    dataFis.close();
                }
                if (strucFis != null) {
                    strucFis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(LoadDataJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jbLoadBothActionPerformed


    private void jcbProviderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbProviderActionPerformed
        try {
            Registry reg = ((DataProvider)jcbProvider.getSelectedItem()).getRegistry();
            refModel.setList(reg.listDataflows());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        jcbDataSet.setSelectedItem(null);
    }//GEN-LAST:event_jcbProviderActionPerformed

    private void jcbDataSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDataSetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbDataSetActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DataProviderJFrame frame = new DataProviderJFrame();
        Registry reg = ((DataProvider)jcbProvider.getSelectedItem()).getRegistry();
        reg.reset();
        frame.setDataStructure(((DataProvider)jcbProvider.getSelectedItem()).getRegistry(), (DataflowType) refModel.getSelectedItem());
        MainJFrame.FRAME.showRequest(frame);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseData;
    private javax.swing.JButton browseFile;
    private javax.swing.JButton browseStructure;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbLoadBoth;
    private javax.swing.JComboBox jcbDataSet;
    private javax.swing.JComboBox jcbProvider;
    private javax.swing.JTextField jtData;
    private javax.swing.JTextField jtStructure;
    // End of variables declaration//GEN-END:variables
}

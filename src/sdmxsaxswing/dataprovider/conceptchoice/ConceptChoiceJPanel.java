/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmxsaxswing.dataprovider.conceptchoice;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import sdmx.Registry;
import sdmx.common.Description;
import sdmx.common.Name;
import sdmx.commonreferences.ConceptReferenceType;
import sdmx.structure.base.Component;
import sdmx.structure.codelist.CodeType;
import sdmx.structure.concept.ConceptSchemeType;
import sdmx.structure.concept.ConceptType;
import sdmxsaxswing.dataprovider.conceptchoice.AllValueConceptChoice;
import sdmxsaxswing.dataprovider.conceptchoice.CodeTypeComboBoxModel;
import sdmxsaxswing.dataprovider.conceptchoice.ConceptChoice;
import sdmxsaxswing.dataprovider.conceptchoice.ConceptChoiceModel;
import sdmxsaxswing.dataprovider.conceptchoice.MultipleValueConceptChoice;
import sdmxsaxswing.dataprovider.conceptchoice.SingleValueConceptChoice;

/**
 *
 * @author James
 */
public class ConceptChoiceJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ConceptChoiceJPanel
     */
    public ConceptChoiceJPanel() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jrSingle = new javax.swing.JRadioButton();
        jrMany = new javax.swing.JRadioButton();
        jrAll = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jpSingle = new javax.swing.JPanel();
        jcbSingleValue = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jpMany = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlAvailable = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlSelected = new javax.swing.JList();
        jbAdd = new javax.swing.JButton();
        jbRemove = new javax.swing.JButton();
        jpAll = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jlConceptName = new javax.swing.JLabel();
        jlConceptDescription = new javax.swing.JLabel();

        buttonGroup1.add(jrSingle);
        jrSingle.setText("Single Value");
        jrSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrSingleActionPerformed(evt);
            }
        });

        buttonGroup1.add(jrMany);
        jrMany.setText("Many Values");
        jrMany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrManyActionPerformed(evt);
            }
        });

        buttonGroup1.add(jrAll);
        jrAll.setText("All Values");
        jrAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrAllActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.CardLayout());

        jcbSingleValue.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbSingleValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSingleValueActionPerformed(evt);
            }
        });

        jLabel1.setText("Single Value");

        javax.swing.GroupLayout jpSingleLayout = new javax.swing.GroupLayout(jpSingle);
        jpSingle.setLayout(jpSingleLayout);
        jpSingleLayout.setHorizontalGroup(
            jpSingleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSingleLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jpSingleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbSingleValue, javax.swing.GroupLayout.Alignment.TRAILING, 0, 373, Short.MAX_VALUE)
                    .addGroup(jpSingleLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 260, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpSingleLayout.setVerticalGroup(
            jpSingleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSingleLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbSingleValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        jPanel1.add(jpSingle, "Single");

        jlAvailable.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlAvailable);

        jlSelected.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jlSelected);

        jbAdd.setText(">");
        jbAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddActionPerformed(evt);
            }
        });

        jbRemove.setText("<");
        jbRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpManyLayout = new javax.swing.GroupLayout(jpMany);
        jpMany.setLayout(jpManyLayout);
        jpManyLayout.setHorizontalGroup(
            jpManyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpManyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpManyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpManyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jpManyLayout.createSequentialGroup()
                        .addComponent(jbRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 157, Short.MAX_VALUE))))
        );
        jpManyLayout.setVerticalGroup(
            jpManyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpManyLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jpManyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbRemove)
                    .addComponent(jbAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpManyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 0, 0))
        );

        jPanel1.add(jpMany, "Many");

        jLabel4.setText("All Values For This Dimension");

        javax.swing.GroupLayout jpAllLayout = new javax.swing.GroupLayout(jpAll);
        jpAll.setLayout(jpAllLayout);
        jpAllLayout.setHorizontalGroup(
            jpAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAllLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel4)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jpAllLayout.setVerticalGroup(
            jpAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAllLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel4)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        jPanel1.add(jpAll, "All");

        jlConceptName.setText("Concept");

        jlConceptDescription.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrMany, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jrSingle)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(3, 3, 3))
                    .addComponent(jlConceptDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlConceptName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jlConceptName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlConceptDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrSingle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrMany)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrAll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private String id = null;
    
    CodelistListModel available = new CodelistListModel();
    CodelistListModel selected = new CodelistListModel();
    
    
    private void jrSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrSingleActionPerformed
        CardLayout layout = (CardLayout) jPanel1.getLayout();
        layout.show(jPanel1, "Single");
        ConceptChoice cc2 = new SingleValueConceptChoice(ConceptChoiceModel.MODEL.getRegistry(), ConceptChoiceModel.MODEL.getStructure(), getId());
        ConceptChoiceModel.MODEL.setConceptChoice(getId(), cc2);
        get();

    }//GEN-LAST:event_jrSingleActionPerformed

    private void jrManyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrManyActionPerformed
        CardLayout layout = (CardLayout) jPanel1.getLayout();
        layout.show(jPanel1, "Many");
        ConceptChoice cc2 = new MultipleValueConceptChoice(ConceptChoiceModel.MODEL.getRegistry(), ConceptChoiceModel.MODEL.getStructure(), getId());
        ConceptChoiceModel.MODEL.setConceptChoice(id, cc2);
        available = new CodelistListModel();
        available.setCodelist(cc2.getCodes());
        jlAvailable.setModel(available);
        selected = new CodelistListModel();
        jlSelected.setModel(selected);
    }//GEN-LAST:event_jrManyActionPerformed

    private void jrAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrAllActionPerformed
        CardLayout layout = (CardLayout) jPanel1.getLayout();
        layout.show(jPanel1, "All");
        ConceptChoice cc2 = new AllValueConceptChoice(ConceptChoiceModel.MODEL.getRegistry(), ConceptChoiceModel.MODEL.getStructure(), getId());
        ConceptChoiceModel.MODEL.setConceptChoice(getId(), cc2);
        get();
    }//GEN-LAST:event_jrAllActionPerformed

    private void jcbSingleValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSingleValueActionPerformed
        SingleValueConceptChoice cc = (SingleValueConceptChoice) ConceptChoiceModel.MODEL.getConceptChoice(getId());
        cc.setDefaultChoice(((CodeType) jcbSingleValue.getSelectedItem()).getId().toString());
    }//GEN-LAST:event_jcbSingleValueActionPerformed

    private void jbAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddActionPerformed
        int index = jlAvailable.getSelectedIndex();
        //System.out.println("Index="+index);
        selected.add((CodeType) available.getElementAt(index));
        available.remove((CodeType) available.getElementAt(index));
        set();
    }//GEN-LAST:event_jbAddActionPerformed

    private void jbRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoveActionPerformed
        int index = jlSelected.getSelectedIndex();
        available.add((CodeType) available.getElementAt(index));
        selected.remove((CodeType) selected.getElementAt(index));
        set();
    }//GEN-LAST:event_jbRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbAdd;
    private javax.swing.JButton jbRemove;
    private javax.swing.JComboBox jcbSingleValue;
    private javax.swing.JList jlAvailable;
    private javax.swing.JLabel jlConceptDescription;
    private javax.swing.JLabel jlConceptName;
    private javax.swing.JList jlSelected;
    private javax.swing.JPanel jpAll;
    private javax.swing.JPanel jpMany;
    private javax.swing.JPanel jpSingle;
    private javax.swing.JRadioButton jrAll;
    private javax.swing.JRadioButton jrMany;
    private javax.swing.JRadioButton jrSingle;
    // End of variables declaration//GEN-END:variables

    public void get() {
        if( getId() == null ) return;
        CardLayout layout = (CardLayout) jPanel1.getLayout();
        ConceptChoice cc = ConceptChoiceModel.MODEL.getConceptChoice(getId());
        jlConceptName.setText(cc.getName());
        jlConceptDescription.setText(cc.getDescription());

        if (cc instanceof SingleValueConceptChoice) {
            SingleValueConceptChoice sv = (SingleValueConceptChoice) cc;
            CodeTypeComboBoxModel model = new CodeTypeComboBoxModel();
            model.setCodes(sv.getCodes(), sv.getDefaultChoice());
            this.jcbSingleValue.setModel(model);
            layout.show(jPanel1, "Single");
        }
        if (cc instanceof MultipleValueConceptChoice) {
            MultipleValueConceptChoice sv = (MultipleValueConceptChoice) cc;
            available = new CodelistListModel();
            available.setCodelist(sv.getCodes());
            CodelistListModel selected = new CodelistListModel();
            for (int i = 0; i < sv.getChoiceList().size(); i++) {
                if (selected.contains(sv.getChoiceList().get(i))) {
                    selected.add(available.remove(available.findCode(sv.getChoiceList().get(i))));
                }
            }
            layout.show(jPanel1, "Many");
        }
        if (cc instanceof AllValueConceptChoice) {
            AllValueConceptChoice sv = (AllValueConceptChoice) cc;
            layout.show(jPanel1, "All");
        }
    }

    public void set() {
        ConceptChoice cc = ConceptChoiceModel.MODEL.getConceptChoice(getId());
        //System.out.println("CC="+cc);
        //cc.dump();
        if (cc instanceof MultipleValueConceptChoice) {
            MultipleValueConceptChoice sv = (MultipleValueConceptChoice) cc;
            List<String> choices = new ArrayList<String>();
            for(int i=0;i<selected.getSize();i++) {
                choices.add(((CodeType)selected.getElementAt(i)).getId().toString());
            }
            sv.setChoiceList(choices);
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}

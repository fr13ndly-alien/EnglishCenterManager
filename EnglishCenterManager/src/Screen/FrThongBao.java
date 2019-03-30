/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screen;

import JDBC.DBHocPhan;

/**
 *
 * @author Fr13nd
 */
public class FrThongBao extends javax.swing.JFrame {
    private String maHP;
            
    /**
     * Creates new form FrThongBao
     */
    public FrThongBao(String maHP) {
        this.maHP = maHP;
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

        jScrollPane1 = new javax.swing.JScrollPane();
        txtaNoiDung = new javax.swing.JTextArea();
        btnGui = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Thông báo cho lớp");

        txtaNoiDung.setColumns(20);
        txtaNoiDung.setRows(5);
        jScrollPane1.setViewportView(txtaNoiDung);

        btnGui.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGui.setForeground(new java.awt.Color(0, 0, 255));
        btnGui.setText("Gửi");
        btnGui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nhập thông báo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGui)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiActionPerformed
        // TODO add your handling code here:
        // Lay noi dung thong bao
        String noiDung = txtaNoiDung.getText();
        // Them thong bao vao trong CSDL
        DBHocPhan dbHP = new DBHocPhan();
        dbHP.dangThongBao(maHP, noiDung);
        
        System.out.println("NOI DUNG THONG BAO:\n\t"+ noiDung);
        this.setVisible(false);
    }//GEN-LAST:event_btnGuiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGui;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtaNoiDung;
    // End of variables declaration//GEN-END:variables
}

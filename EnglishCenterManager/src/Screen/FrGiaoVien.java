/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screen;
  
import Entities.GiaoVien;
import Entities.HocPhan;
import Entities.HocSinh;
import JDBC.DBHocPhan;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Fr13nd
 */
public class FrGiaoVien extends javax.swing.JFrame {
    private GiaoVien giaoVien;
    //private GiaoVien gvHienTai;
    private String maGV, maHP;
    private Hashtable<String,String> dataHocPhan;
    private ArrayList<HocSinh> dsLop;
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
    private DefaultTableModel tableModel;
    
    /**
     * Creates new form FrGiaoVien
     */
    public FrGiaoVien(GiaoVien gv) {
        this.giaoVien = gv; // giao vien dang lam viec voi giao dien nay
        maHP = null; // ma hoc phan da chon trong table, de xu ly
        dataHocPhan = new Hashtable<String,String>();
        dsLop = new ArrayList<HocSinh>();
        initComponents();
        initContent();
    }
    
    private void initContent(){
        lbthongBaoChon.setVisible(false);
        lbTenGV.setText(giaoVien.getHoTen());
        
        //lay danh sach khoa hoc cua giao vien
        DBHocPhan dbHocPhan = new DBHocPhan();
        //Hashtable<String,String> dsHP = new Hashtable<String,String>();
        ArrayList<HocPhan> ds = dbHocPhan.layDSHP(giaoVien.getUsername());
        
        for(int i=0; i< ds.size(); i++){
            dataHocPhan.put(ds.get(i).getTenHP(), ds.get(i).getMaHP());
        }
        
        // Hien thi danh sach hoc phan cua giao vien
        String[] model = new String[20];
        int j = 0;
        for(String s : dataHocPhan.keySet()){
            model[j] = s;
            System.out.println(s);
            j ++;
        }
        
        final DefaultComboBoxModel cmbbxModel = new DefaultComboBoxModel(model);
        cbbxDsHocPhan.setModel(cmbbxModel);
    }
    
    // Hien thi danh sach lóp
    private void hienThiDanhSachLop(String maHP){
        System.out.println("- Chuan bi lay du lieu cua hoc phan: "+ maHP);
        tableModel = (DefaultTableModel) tbDSLop.getModel();
        tableModel.setRowCount(0);
        Vector row;
        
        //lay du lieu tu database
        DBHocPhan dBHocPhan = new DBHocPhan();
        dsLop = dBHocPhan.layDSLop(maHP);       
        HocSinh  hs;
        for(int i=0 ; i<dsLop.size(); i++){
            hs = dsLop.get(i);
            row = new Vector();
            row.add(hs.getUsername());
            row.add(hs.getHoTen());
            row.add(hs.getNgaySinh().toString());
            row.add(hs.getCmnd());
            row.add(hs.getDiaChi());
            row.add(hs.getKhoiLop());
            row.add(hs.getTenTruong());
            //them hang cho bang
            tableModel.addRow(row);
        }
        
        //tbDSLop.setModel(tableModel);
        
    }
    
    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbTenGV = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbxDsHocPhan = new javax.swing.JComboBox<>();
        btnLayDSLop = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDSLop = new javax.swing.JTable();
        btnTaoMoiHocSinh = new javax.swing.JButton();
        btnSuaHS = new javax.swing.JButton();
        btnXoaHS = new javax.swing.JButton();
        btnDanhSachHocSinh = new javax.swing.JButton();
        lbthongBaoChon = new javax.swing.JLabel();
        btnDanhSachHocSinh1 = new javax.swing.JButton();
        btnDanhSachHocSinh2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trang quản lí Học sinh");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("XIN CHÀO: ");

        lbTenGV.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lbTenGV.setForeground(new java.awt.Color(255, 0, 0));
        lbTenGV.setText("ten-giao-vien");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Vui lòng chọn khóa học:");

        cbbxDsHocPhan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbbxDsHocPhan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLayDSLop.setBackground(new java.awt.Color(153, 255, 255));
        btnLayDSLop.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnLayDSLop.setText("Lấy danh sách lớp");
        btnLayDSLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayDSLopActionPerformed(evt);
            }
        });

        tbDSLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Họ tên", "Ngày sinh", "CMND", "Quê quán", "Lớp", "Tên trường"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbDSLop);

        btnTaoMoiHocSinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnTaoMoiHocSinh.setText("Tạo mới học sinh");
        btnTaoMoiHocSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiHocSinhActionPerformed(evt);
            }
        });

        btnSuaHS.setBackground(new java.awt.Color(255, 153, 102));
        btnSuaHS.setText("Sửa");
        btnSuaHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaHSActionPerformed(evt);
            }
        });

        btnXoaHS.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaHS.setText("Xóa Học Sinh khỏi lớp học");
        btnXoaHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHSActionPerformed(evt);
            }
        });

        btnDanhSachHocSinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDanhSachHocSinh.setText("Danh sách học sinh");
        btnDanhSachHocSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachHocSinhActionPerformed(evt);
            }
        });

        lbthongBaoChon.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lbthongBaoChon.setForeground(new java.awt.Color(255, 255, 51));
        lbthongBaoChon.setText("Vui  lòng chọn học sinh!");

        btnDanhSachHocSinh1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDanhSachHocSinh1.setText("Đăng thông báo");
        btnDanhSachHocSinh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachHocSinh1ActionPerformed(evt);
            }
        });

        btnDanhSachHocSinh2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDanhSachHocSinh2.setText("Bảng điểm");
        btnDanhSachHocSinh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachHocSinh2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTenGV)
                .addGap(540, 1114, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSuaHS, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaHS))
                            .addComponent(btnLayDSLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbxDsHocPhan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbthongBaoChon))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnDanhSachHocSinh1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDanhSachHocSinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnTaoMoiHocSinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDanhSachHocSinh2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 589, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTenGV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbxDsHocPhan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDanhSachHocSinh)
                    .addComponent(btnTaoMoiHocSinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLayDSLop)
                    .addComponent(btnDanhSachHocSinh1)
                    .addComponent(btnDanhSachHocSinh2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaHS)
                    .addComponent(btnXoaHS)
                    .addComponent(lbthongBaoChon))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLayDSLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayDSLopActionPerformed
        // TODO add your handling code here:
        String tenHP = (String)cbbxDsHocPhan.getSelectedItem();
        System.out.println("- Combobox: lay duoc ten Hoc phan: "+ tenHP);
        maHP = dataHocPhan.get(tenHP);
        hienThiDanhSachLop(dataHocPhan.get(tenHP));
    }//GEN-LAST:event_btnLayDSLopActionPerformed

    private void btnTaoMoiHocSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiHocSinhActionPerformed
        // TODO add your handling code here:
        FrThemHocSinh frThemHS = new FrThemHocSinh(maHP);
        frThemHS.setVisible(true);
        frThemHS.setLocation(100, 250);
    }//GEN-LAST:event_btnTaoMoiHocSinhActionPerformed

    private void btnSuaHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaHSActionPerformed
        // TODO add your handling code here:
        System.out.println("- Sua thong tin hoc sinh");
        int selectedRow = tbDSLop.getSelectedRow();
        
        if(selectedRow >= 0){
            //da chon
            lbthongBaoChon.setVisible(false);
            //hien thi bang sua thong tin
            FrSuaHocSinh frSuaHS = new FrSuaHocSinh(dsLop.get(selectedRow));
            frSuaHS.setVisible(true);
            //cap nhat vao CSDL
            
            //hien thi lai danh sach lop
        }else{
            //chua chon
            lbthongBaoChon.setVisible(true);
        }
        hienThiDanhSachLop(maHP);
    }//GEN-LAST:event_btnSuaHSActionPerformed

    private void btnDanhSachHocSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachHocSinhActionPerformed
        // TODO add your handling code here:
        if(maHP != null){
            FrDanhSachHocSinh frDSHS = new FrDanhSachHocSinh(maHP, dsLop);
            frDSHS.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this,"Vui lòng chọn lớp", "Bạn chưa chọn lớp", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_btnDanhSachHocSinhActionPerformed

    private void btnXoaHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHSActionPerformed
        System.out.println("- Xoa thong tin hoc sinh");
        int selectedRow = tbDSLop.getSelectedRow();
        
        if(selectedRow >= 0){
            //da chon
            lbthongBaoChon.setVisible(false);
            //hien thi bang sua thong tin
            HocSinh hs = dsLop.get(selectedRow);
            //xoa hoc sinh khoi danh sach
            DBHocPhan dbHP = new DBHocPhan();
            dbHP.xoaHocSinhKhoiDanhSach(hs.getUsername());
        }else{
            //chua chon
            JOptionPane.showMessageDialog(this,"Vui lòng chọn học sinh muốn xóa", "Bạn chưa chọn học sinh", JOptionPane.INFORMATION_MESSAGE);
        }
        hienThiDanhSachLop(maHP);
    }//GEN-LAST:event_btnXoaHSActionPerformed

    private void btnDanhSachHocSinh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachHocSinh1ActionPerformed
        // TODO add your handling code here:
        if(maHP != null){
            FrThongBao frThongBao = new FrThongBao(maHP);
            frThongBao.setLocation(100, 100);
            frThongBao.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this,"Vui lòng chọn lớp", "Bạn chưa chọn lớp", JOptionPane.INFORMATION_MESSAGE);
        }     
    }//GEN-LAST:event_btnDanhSachHocSinh1ActionPerformed

    private void btnDanhSachHocSinh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachHocSinh2ActionPerformed
        // TODO add your handling code here:
        if(maHP != null){
            FrXemBangDiem bangDiem = new FrXemBangDiem(dataHocPhan, maHP, dsLop);
            bangDiem.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this,"Vui lòng chọn lớp", "Bạn chưa chọn lớp", JOptionPane.INFORMATION_MESSAGE);
        }
       
    }//GEN-LAST:event_btnDanhSachHocSinh2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDanhSachHocSinh;
    private javax.swing.JButton btnDanhSachHocSinh1;
    private javax.swing.JButton btnDanhSachHocSinh2;
    private javax.swing.JButton btnLayDSLop;
    private javax.swing.JButton btnSuaHS;
    private javax.swing.JButton btnTaoMoiHocSinh;
    private javax.swing.JButton btnXoaHS;
    private javax.swing.JComboBox<String> cbbxDsHocPhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTenGV;
    private javax.swing.JLabel lbthongBaoChon;
    private javax.swing.JTable tbDSLop;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screen;

import Entities.BangDiem;
import Entities.HocPhan;
import Entities.HocSinh;
import Entities.ThongBao;
import JDBC.DBHocPhan;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Fr13nd
 */
public class FrHocSinh extends javax.swing.JFrame {
    private HocSinh hs;
    private Hashtable<String,String> dataHocPhan;
    private String maHP;
    private ArrayList<HocSinh> dsLop;
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
    private DefaultTableModel tableModel;
    private ArrayList<HocPhan> dsHP ;
    private DefaultListModel listModel;
    private ArrayList<ThongBao> dsThongBao = new ArrayList<ThongBao>();
    /**
     * Creates new form FrHocSinh
     */
    public FrHocSinh(HocSinh hs) {
        this.hs = hs;
        this.maHP = null;
        this.dsHP = null;
        dataHocPhan = new Hashtable<String,String>();
        this.listModel = new DefaultListModel();
        
        initComponents();
        
        txtNoiDung.setEditable(false);
        txtNoiDung.setLineWrap(true);
        txtNoiDung.setWrapStyleWord(true);
        //chuan bi du lieu cho form
        initContent();
    }
    
    private void initContent(){
        lbTen.setText(hs.getHoTen());
        txt1idHS.setText(hs.getUsername());
        txt1idHS.setEditable(false);
        
        txt2hoTen.setText(hs.getHoTen());
        txt2hoTen.setEditable(false);
        txt3ngaySinh.setText(hs.getNgaySinh().toString());
        txt3ngaySinh.setEditable(false);
        txt4soCMND.setText(hs.getCmnd());
        txt4soCMND.setEditable(false);
        txt5diaChi.setText(hs.getDiaChi());
        txt5diaChi.setEditable(false);
        txt6khoiLop.setText(hs.getKhoiLop()+"");
        txt6khoiLop.setEditable(false);
        txt7tenTruong.setText(hs.getTenTruong());
        txt7tenTruong.setEditable(false);
        
        //lay danh sach khoa hoc cua Hoc sinh
        DBHocPhan dbHocPhan = new DBHocPhan();
        dsHP = new ArrayList<HocPhan>();
        //lay danh sach ma hoc phan
        ArrayList<String> dsMaHP = dbHocPhan.layDSmaHPtheoIDHS(hs.getUsername());
        //lay danh sach hoc phan theo ds ma hoc  phan
        for(int j=0; j< dsMaHP.size(); j ++){
            dsHP.add(dbHocPhan.layHP(dsMaHP.get(j)));
        }
        
        for(int i=0; i< dsHP.size(); i++){
            dataHocPhan.put(dsHP.get(i).getTenHP(), dsHP.get(i).getMaHP());
        }
        
        // Hien thi danh sach hoc phan cua hoc sinh
        String[] model = new String[20];
        int j = 0;
        for(String s : dataHocPhan.keySet()){
            model[j] = s;
            System.out.println(s);
            j ++;
        }
        
        final DefaultComboBoxModel cmbbxModel = new DefaultComboBoxModel(model);
        cbbxDsHocPhan.setModel(cmbbxModel);
        hienThiThongBao();
        hienThiBangDiem();
    }
    
    // Hien thi danh sach lóp
   
    private void hienThiThongBao(){  
        lstThongBao.setCellRenderer(new MyCellRenderer((80)));
        
        DBHocPhan dbHocPhan = new DBHocPhan();
        //lay thong bao theo danh sach hoc phan
        for(int i=0; i< dsHP.size(); i++){
            dsThongBao.addAll(dbHocPhan.layThongBao(dsHP.get(i).getMaHP()));
        }
        // Tao list thong bao
        for(int j =0; j< dsThongBao.size(); j++){
            listModel.add(j, dsThongBao.get(j));
        }
        lstThongBao.setModel(listModel);
    }
    
    private void hienThiBangDiem(){
        DBHocPhan dbHP = new DBHocPhan();
        ArrayList<BangDiem> dsBD = new ArrayList<BangDiem>();
        BangDiem bd;
        DefaultTableModel tbModel = (DefaultTableModel) tbBangDiem.getModel();
        Vector row;
        for(int i=0; i<dsHP.size(); i++){
            bd = dbHP.layBangDiem(maHP, hs.getUsername());
            row = new Vector();
            row.addElement(dsHP.get(i).getTenHP());
            row.addElement(bd.getChuyenCan());
            row.addElement(bd.getGiuaKy());
            row.addElement(bd.getCuoiKy());
            tbModel.addRow(row);
        }
    }
    
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
    
    // Xac dinh lai do dai cua o item
    class MyCellRenderer extends DefaultListCellRenderer {
        public static final String HTML_1 = "<html><body style='width: ";
        public static final String HTML_2 = "px'>";
        public static final String HTML_3 = "</html>";
        private int width;

        public MyCellRenderer(int width) {
            this.width = width;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
            String text = HTML_1 + String.valueOf(width) + HTML_2 + value.toString()
                + HTML_3;
            return super.getListCellRendererComponent(list, text, index, isSelected,
                cellHasFocus);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbTen = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt1idHS = new javax.swing.JTextField();
        txt2hoTen = new javax.swing.JTextField();
        txt3ngaySinh = new javax.swing.JTextField();
        txt4soCMND = new javax.swing.JTextField();
        txt5diaChi = new javax.swing.JTextField();
        txt6khoiLop = new javax.swing.JTextField();
        txt7tenTruong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnXemDiem1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDSLop = new javax.swing.JTable();
        btnLayDSLop = new javax.swing.JButton();
        cbbxDsHocPhan = new javax.swing.JComboBox<>();
        btnXemDiem = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstThongBao = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JTextArea();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbBangDiem = new javax.swing.JTable();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập học sinh");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("XIN CHÀO:");

        lbTen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTen.setForeground(new java.awt.Color(0, 0, 204));
        lbTen.setText("           ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Thông tin cá nhân");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Học sinh khối:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Tên trường:");

        txt1idHS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt2hoTen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt3ngaySinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt3ngaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt3ngaySinhActionPerformed(evt);
            }
        });

        txt4soCMND.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt5diaChi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt6khoiLop.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt7tenTruong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("ID học sinh:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Họ tên:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Ngày sinh:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Số CMND:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Địa chỉ:");

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnXemDiem1.setBackground(new java.awt.Color(153, 255, 255));
        btnXemDiem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnXemDiem1.setText("Xửa thông tin");
        btnXemDiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemDiem1ActionPerformed(evt);
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

        btnLayDSLop.setBackground(new java.awt.Color(153, 255, 255));
        btnLayDSLop.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnLayDSLop.setText("Lấy danh sách lớp");
        btnLayDSLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayDSLopActionPerformed(evt);
            }
        });

        cbbxDsHocPhan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbbxDsHocPhan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnXemDiem.setBackground(new java.awt.Color(153, 255, 255));
        btnXemDiem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnXemDiem.setText("Xem điểm");
        btnXemDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemDiemActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Vui lòng chọn khóa học:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLayDSLop)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbbxDsHocPhan, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXemDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbxDsHocPhan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemDiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLayDSLop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Xem thông tin khóa học", jPanel2);

        lstThongBao.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstThongBao);

        txtNoiDung.setColumns(20);
        txtNoiDung.setRows(5);
        txtNoiDung.setWrapStyleWord(true);
        txtNoiDung.setAutoscrolls(false);
        txtNoiDung.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane3.setViewportView(txtNoiDung);

        jToggleButton1.setBackground(new java.awt.Color(153, 255, 255));
        jToggleButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jToggleButton1.setText("Đọc");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jToggleButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thông báo", jPanel3);

        tbBangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên học phần", "Chuyên cần", "Giữa kì", "Cuối kì"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbBangDiem);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Xem điểm", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTen)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt4soCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt5diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt3ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt2hoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt1idHS, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt6khoiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt7tenTruong, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnXemDiem1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbTen))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txt1idHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt2hoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt3ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt4soCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt5diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt6khoiLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt7tenTruong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXemDiem1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 4, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt3ngaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt3ngaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt3ngaySinhActionPerformed

    
    private void btnXemDiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemDiem1ActionPerformed
        // TODO add your handling code here:
        FrSuaHocSinh frSuaHS = new FrSuaHocSinh(hs);
            frSuaHS.setVisible(true);
    }//GEN-LAST:event_btnXemDiem1ActionPerformed

    private void btnXemDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemDiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXemDiemActionPerformed

    private void btnLayDSLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayDSLopActionPerformed
        // TODO add your handling code here:
        String tenHP = (String)cbbxDsHocPhan.getSelectedItem();
        System.out.println("- Combobox: lay duoc ten Hoc phan: "+ tenHP);
        maHP = dataHocPhan.get(tenHP);
        hienThiDanhSachLop(dataHocPhan.get(tenHP));
    }//GEN-LAST:event_btnLayDSLopActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        int vt= lstThongBao.getSelectedIndex();
        if(vt >= 0){
            ThongBao tb = dsThongBao.get(vt);
            String noiDung ="";
            noiDung += "Ngay dang: "+ tb.getNgayDang()+"\n";
            noiDung += "Mo ta: "+ tb.getMoTa()+ "\n";
            noiDung += "* Noi dung: "+ tb.getNoiDung()+ "\n";
            txtNoiDung.setText(noiDung);
        }            
    }//GEN-LAST:event_jToggleButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLayDSLop;
    private javax.swing.JButton btnXemDiem;
    private javax.swing.JButton btnXemDiem1;
    private javax.swing.JComboBox<String> cbbxDsHocPhan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lbTen;
    private javax.swing.JList<String> lstThongBao;
    private javax.swing.JTable tbBangDiem;
    private javax.swing.JTable tbDSLop;
    private javax.swing.JTextField txt1idHS;
    private javax.swing.JTextField txt2hoTen;
    private javax.swing.JTextField txt3ngaySinh;
    private javax.swing.JTextField txt4soCMND;
    private javax.swing.JTextField txt5diaChi;
    private javax.swing.JTextField txt6khoiLop;
    private javax.swing.JTextField txt7tenTruong;
    private javax.swing.JTextArea txtNoiDung;
    // End of variables declaration//GEN-END:variables
}

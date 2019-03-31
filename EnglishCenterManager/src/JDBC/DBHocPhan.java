/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Entities.BangDiem;
import Entities.HocPhan;
import Entities.HocSinh;
import Entities.ThongBao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fr13nd
 */
public class DBHocPhan {
    private Connection connection;
    private Statement stm;
    private ResultSet rs;
    
    public DBHocPhan() {
        connection = ConnectDB.getConnectDB();
        stm = null;
        rs = null;
    }
    
    public ArrayList<HocSinh> layDSLop(String maHP){
        ArrayList<HocSinh> dslop = new ArrayList<HocSinh>();
        //lay danh sach ma hoc sinh tu bang BANGDDIEM
        //lay hoc sinh theo danh sach hoc sinh roi tra ve
        String[] dsMaHS = new String[100];
        
        if(connection != null) {
            String query = String.format("SELECT IDHS " +
                    "FROM BANGDIEM "+
                    "WHERE MAHP = '%s'", maHP); 
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(query);
                int i=0;
                
                DBHocSinh dbHS = new DBHocSinh();
                HocSinh hs;
                while (rs.next()){
                    // lay hoc sinh
                    hs = dbHS.layHocSinh(rs.getString("IDHS"));
                    //System.out.println(hs.toString());
                    dslop.add(hs);
                }
            } catch (Exception e) {
                System.out.println("- DB Hoc Phan");
                e.printStackTrace();
            }
        }
        return dslop;
    }
    
    public ArrayList<HocPhan> layDSHP (String idGV){
        ArrayList<HocPhan> dshp = new ArrayList<HocPhan>();
        
        if(connection != null){
            String query = String.format("SELECT * FROM HOCPHAN WHERE IDGV = '%s'", idGV);
            HocPhan hp;
            DBGiaoVien dbGv = new DBGiaoVien();
            DBHocPhan dbhp = new DBHocPhan();
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(query);
                int i=1;
                while(rs.next()){
                    System.out.println("**** Lay hoc phan: "+ i);
                    hp = new HocPhan();
                    hp.setMaHP(idGV);
                    hp.setMaHP(rs.getString(1)); //loi
                    hp.setTenHP(rs.getString("TENHP"));
                    hp.setHocPhi(rs.getFloat(5));
                    hp.setSiSo(rs.getInt(3));
                    
                    //loi goi chung mot bien result set nen phai tao mot doi tuong khac de xu ly
                    hp.setDanhSachLop(dbhp.layDSLop(hp.getMaHP()));
                    hp.setGiaoVien(dbGv.layGiaoVien(idGV));
                    
                    System.out.println(hp.toString());
                    dshp.add(hp);
                    i++;
                }                    
            } catch (Exception e) {
                System.out.println("- DB Hoc Phan");
                e.printStackTrace();
            }
        }
        return dshp;
    }
    
    
    //lay hoc phan
    public HocPhan layHP (String maHP) {
        HocPhan hp = new HocPhan();
        if(connection != null){
            String query = String.format("SELECT * FROM HOCPHAN WHERE MAHP='%s'", maHP);
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(query);
                if (rs.next()){
                    hp.setMaHP(rs.getString(1)); //loi
                    hp.setTenHP(rs.getString("TENHP"));
                    hp.setHocPhi(rs.getFloat(5));
                    hp.setSiSo(rs.getInt(3));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return hp;
    }
    
    public void xoaHocSinhKhoiDanhSach (String idHS){
        if(connection != null ){
            String query = String.format("DELETE FROM BANGDIEM WHERE IDHS = '%s'", idHS);
            try {
                stm = connection.createStatement();
                stm.execute(query);
                System.out.println("*** Xoa thanh cong hoc sinh khoi danh sach lop");
            } catch (Exception e) {
                System.out.println("LOI XOA HOC SINH KHOI DANH SACH LOP");
                e.printStackTrace();
            }
        }
    }
    
    public ArrayList<BangDiem> layBangDiem(ArrayList<HocSinh> dshs){
        ArrayList<BangDiem> dsBangDiem = new ArrayList<BangDiem>();
        String maSV;
        int dsSize = dshs.size();
        
        if(connection != null){
            String query;
            try {
                String user = "sa";
                String pass = "huuhien77";
                String uRL="jdbc:sqlserver://Localhost:1433;databaseName=EnglishCenterManager;user="+user+";password="+pass;
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //System.out.println("Chuoi ket noi: "+ uRL);
            //tra ve ket noi
                stm =  DriverManager.getConnection(uRL).createStatement();
                ResultSet rsBD;
                BangDiem bd;
                for(int i =0; i<dsSize; i++){
                    query = String.format("SELECT * FROM BANGDIEM WHERE IDHS = '%s'", dshs.get(i).getUsername().trim());
                    System.out.println(query);
                    rsBD = stm.executeQuery(query);
                    //lay bang diem cho mot hoc sinh
                    if(rsBD.next()){
                        System.out.println(rsBD.toString() + rs);
                        bd = new BangDiem();
                        bd.setidHS(dshs.get(i).getUsername().trim());
                        
                        bd.setChuyenCan(rsBD.getInt("CHUYENCAN"));
                        bd.setGiuaKy(rsBD.getInt("GIUAKI"));
                        bd.setCuoiKy(rsBD.getFloat("CUOIKI"));
                        
                        //System.out.println("\n"+ bd.toString());
                        dsBangDiem.add(bd);
                    }
                }
            } catch (Exception e) {
                System.out.println("- Loi DB Bang Diem");
                e.printStackTrace();
            }
            
            
        }
        //lay bang diem cho moi sinh vien
        return dsBangDiem;
    }
 
    public BangDiem layBangDiem(String maHP, String idHS){
        BangDiem bd = new BangDiem();
        if(connection != null){
            String query = String.format("SELECT * FROM BANGDIEM WHERE MAHP='%s' AND IDHS='%s'", maHP, idHS);
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(query);
                if(rs.next()){                       
                    bd.setChuyenCan(rs.getInt("CHUYENCAN"));
                    bd.setGiuaKy(rs.getInt("GIUAKI"));
                    bd.setCuoiKy(rs.getFloat("CUOIKI"));
                }   
            } catch (Exception e) {
                System.out.println("- Loi DB Bang Diem");
                e.printStackTrace();
            }        
            
        }
        return bd;
    }
    
    public void themHSvaoHP(String maHS, String maHP){
        if(connection != null){
            String query = String.format("INSERT INTO BANGDIEM VALUES ('%s','%s',0,0,0)", maHP, maHS);
            try {
                stm = connection.createStatement();
                stm.execute(query);
            } catch (Exception e) {
                System.out.println("- LOI THEM HS VAO HP");
                e.printStackTrace();
            }
        }
    }
    
    // Cap nhat diem cho hoc sinh
    //UPDATE BANGDIEM
    //SET CHUYENCAN=10, GIUAKI=10, CUOIKI=10
    //WHERE IDHS='HS02'
    public void capNhatDiem(String idHS, int chuyenCan, int giuaKi, float cuoiKy){
        
        if(connection != null){
            String query = String.format("UPDATE BANGDIEM"+
                    " SET CHUYENCAN=%d, GIUAKI=%d, CUOIKI=%f"+
                    "WHERE IDHS='%s'", chuyenCan, giuaKi, cuoiKy, idHS);
            try{
                stm = connection.createStatement();
                stm.execute(query);
                System.out.println("*** CAP NHAT DIEM THANH CONG ***"+ "id = "+ idHS);
            }catch(Exception e){
                System.out.println("- LOI SUA DIEM");
                e.printStackTrace();
            }
        }
    }
    
    //lay danh sach ma hoc phan theo IDHS
    public ArrayList<String> layDSmaHPtheoIDHS(String idHS){
        ArrayList<String> kq = new ArrayList<String>();
        if(connection != null){
            String query = String.format("SELECT MAHP FROM BANGDIEM WHERE IDHS='%s'", idHS);
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(query);
                
                while(rs.next()){
                    kq.add(rs.getString("MAHP"));
                }
            } catch (Exception e) {
                System.out.println("* LOI LAY DANH SACH HOC PHAN THEO MA HOCSINH");
                e.printStackTrace();
            }
        }
        return kq;
    }
    
    // Chuc nang dang thong bao cho hoc phan
    public void dangThongBao(String maHP, String noiDung, String moTa, Date ngayDang){
        if(connection != null){
            String query = String.format("INSERT INTO THONGBAOHOCPHAN "+
                    "VALUES ('%s','%s','%s','%s')", maHP, moTa, noiDung, ngayDang.toString());
            try {
                stm = connection.createStatement();
                stm.execute(query);
                System.out.println("*** THEM THONG BAO THANH CONG");
            } catch (Exception e) {
            }
        }
    }
    
    // Chuc nang lay thong bao theo lop hoc
    public ArrayList<ThongBao> layThongBao(String maHP){
        ArrayList<ThongBao> kq = new ArrayList<ThongBao>();
        ThongBao tb = new ThongBao();
        
        String query = String.format("SELECT * FROM THONGBAOHOCPHAN "+
                        "WHERE MAHP='%s'", maHP);
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(query);
            
            while(rs.next()){
                tb = new ThongBao(rs.getString(1), rs.getString(3), rs.getString(2), rs.getDate(4));
                System.out.println(tb.toString());
                kq.add(tb);
            }
        } catch (Exception e) {
        }
        return kq;
    }

}

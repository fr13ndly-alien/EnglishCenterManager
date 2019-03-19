/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Entities.BangDiem;
import Entities.HocPhan;
import Entities.HocSinh;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
                    if(rs.next()){
                        System.out.println(rsBD.toString() + rs);
                        bd = new BangDiem();
                        bd.setHocSinh(dshs.get(i).getHoTen());
                       
                        bd.setChuyenCan(rsBD.getInt("CHUYENCAN"));
                         bd.setGiuaKy(rsBD.getInt("GIUAKY"));
                        bd.setCuoiKy(rsBD.getFloat("CUOIKI"));
                        
                        System.out.println("\n"+ bd.toString());
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
}

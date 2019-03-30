/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Entities.GiaoVien;
import Entities.HocPhan;
import Entities.HocSinh;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Fr13nd
 */
public class DBGiaoVien {
    private Connection connection;
    private Statement stm;
    private ResultSet rs;
    
    public DBGiaoVien() {
        connection = ConnectDB.getConnectDB();
        stm = null;
        rs = null;
    }
    
    public GiaoVien layGiaoVien (String id){
        GiaoVien gv =  null;
        String hoTen, cmnd, diaChi, tenTruong;
        Date ngaySinh;
        int kinhNghiem;
        ArrayList<HocPhan> dshp = null;
        
        //lay du lieu giao vien tu csdl 
        if(connection != null){
            String sql = String.format("SELECT * FROM GIAOVIEN WHERE IDGV = '%s'", id);
        
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(sql);
                if(rs.next()){   
                    gv = new GiaoVien();
                    //gan thong tin cho giao vien
                    gv.setHoTen(rs.getString("HOTEN"));
                    diaChi = rs.getString("DIACHI");
                    gv.setCmnd(rs.getString("CMND"));
                    gv.setDiaChi(rs.getString("DIACHI"));
                    gv.setNgaySinh(rs.getDate("NGAYSINH"));
                    gv.setKinhNghiem(rs.getInt("KINHNGHIEM"));
                    gv.setUsername(id);
                    System.out.println("Hoan thanh lay thong tin giao vien");
                    if (gv != null ){
                        System.out.println(gv.toString());
                    }else System.out.println("GV not found!");
                }
            } catch (Exception e) {
                System.out.println("- DB Giao Vien");
                e.printStackTrace();
            }
        }else
            System.out.println("====> Khong the lay ket noi den co so du lieu!");
        return gv;
    }
    
    public ArrayList<GiaoVien> layDSGV(){
        ArrayList<GiaoVien> dsgv = new ArrayList<GiaoVien>();
        GiaoVien gv =  null;
        String hoTen, cmnd, diaChi, tenTruong;
        Date ngaySinh;
        int kinhNghiem;
        ArrayList<HocPhan> dshp = null;
        
        //lay du lieu giao vien tu csdl 
        if(connection != null){
            String sql = String.format("SELECT * FROM GIAOVIEN");
        
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){   
                    gv = new GiaoVien();
                    //gan thong tin cho giao vien
                    gv.setHoTen(rs.getString("HOTEN"));
                    diaChi = rs.getString("DIACHI");
                    gv.setCmnd(rs.getString("CMND"));
                    gv.setDiaChi(rs.getString("DIACHI"));
                    gv.setNgaySinh(rs.getDate("NGAYSINH"));
                    gv.setKinhNghiem(rs.getInt("KINHNGHIEM"));
                    System.out.println("Hoan thanh lay thong tin giao vien");
                        
                    dsgv.add(gv);
                }
            } catch (Exception e) {
                System.out.println("- DB Giao Vien");
                e.printStackTrace();
            }
        }else
            System.out.println("====> Khong the lay ket noi den co so du lieu!");
        return dsgv; 
    }
    
    public void themGiaoVien(GiaoVien gv){
        if(connection != null){            
            String query = "INSERT INTO GIAOVIEN VALUES ('"
                    + gv.getUsername().trim() + "','"
                    + gv.getHoTen().trim() + "','"
                    + gv.getNgaySinh() +  "','"
                    + gv.getCmnd().trim() +  "','"
                    + gv.getDiaChi().trim() +  "','"
                    + gv.getKinhNghiem()+  "','"
                    + gv.getTruongDH() +  "')";
            System.out.println("- Query: "+ query);
            try {
                stm = connection.createStatement();
                stm.execute(query);
                System.out.println("Them thanh cong hoc sinh co id: "+ gv.getUsername());
            } catch (Exception e) {
                System.out.println("- DB Giao Vien");
                e.printStackTrace();
            }
        }
    }
    
    public void xoaGiaoVien (String id){
        if(connection != null){
            String query = String.format("DELETE FROM GIAOVIEN WHERE IDGV = '%s'", id);
            try {
                stm = connection.createStatement();
                stm.execute(query);
            } catch (Exception e) {
                System.out.println("- DB Giao Vien");
                e.printStackTrace();
            }
        }
    }
}

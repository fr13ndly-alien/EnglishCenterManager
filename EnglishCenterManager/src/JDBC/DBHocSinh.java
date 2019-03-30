/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Entities.HocPhan;
import Entities.HocSinh;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Fr13nd
 */
public class DBHocSinh {
    private Connection connection;
    private Statement stm;
    private ResultSet rs;

    public DBHocSinh() {
        
        connection = ConnectDB.getConnectDB();
        stm = null;
        rs = null;
       
    }
    
    public HocSinh layHocSinh(String id){
        HocSinh hs = null;
        String hoTen, cmnd, diaChi, tenTruong;
        Date ngaySinh;
        int khoiLop;
        ArrayList<HocPhan> dshp = null;
        
        //lay du lieu hoc sinh tu csdl 
        if(connection != null){
            String sql = String.format("SELECT * FROM HOCSINH WHERE IDHS = '%s' ", id);
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(sql);
                if(rs.next()){                    
                    hoTen = rs.getString("HOTEN").trim();
                    cmnd = rs.getString("CMND").trim();
                    diaChi = rs.getString("DIACHI").trim();
                    tenTruong = rs.getString("TRUONG").trim();
                    ngaySinh = rs.getDate("NAMSINH");
                    khoiLop = rs.getInt("KHOILOP");
                    hs = new HocSinh(khoiLop, tenTruong, hoTen, ngaySinh, cmnd, diaChi);
                    hs.setUsername(id);
                    if (hs != null ){
                        System.out.println("Lay duoc thong tin hoc sinh");
                    }else System.out.println("Khong tim thay hoc sinh!");
                }
            } catch (Exception e) {
                System.out.println("- DB Hoc Sinh");
                e.printStackTrace();
            }
        }else
            System.out.println("====> Khong the lay ket noi den co so du lieu!");
        
        return hs;
    }
    
    public ArrayList<HocSinh> layDSHS(String tenTruong){
        ArrayList<HocSinh> dshs = new ArrayList<>();
        HocSinh hs = null;
        String hoTen, cmnd, diaChi;
        Date ngaySinh;
        int khoiLop;

        if(connection != null){
            String sql = String.format("SELECT * FROM HOCSINH WHERE TRUONG = '%s'", tenTruong);
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){                    
                    hoTen = rs.getString("HOTEN").trim();
                    cmnd = rs.getString("CMND").trim();
                    diaChi = rs.getString("DIACHI").trim();
                    tenTruong = rs.getString("TRUONG").trim();
                    ngaySinh = rs.getDate("NAMSINH");
                    khoiLop = rs.getInt("KHOILOP");
                    hs = new HocSinh(khoiLop, tenTruong, hoTen, ngaySinh, cmnd, diaChi);
                    dshs.add(hs);
                    System.out.println("\t- Them hoc sinh vao danh sach");
                    if (hs != null ){
                        System.out.println(hs.toString());
                    }else System.out.println("HS not found!");
                }
            } catch (Exception e) {
                System.out.println("- DB Hoc Sinh");
                e.printStackTrace();
            }
        }else
            System.out.println("====> Khong the lay ket noi den co so du lieu!");
        
        
        return dshs;
    }
    
    public ArrayList<HocSinh> layDSHS(int lop){
        //code here
        return null;
    }
    
    public ArrayList<HocSinh> layDSHS(){
        ArrayList<HocSinh> dshs = new ArrayList<>();
        HocSinh hs = null;
        String hoTen, cmnd, diaChi, tenTruong;
        Date ngaySinh;
        int khoiLop;

        if(connection != null){
            String sql = String.format("SELECT * FROM HOCSINH");
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(sql);
                while(rs.next()){                    
                    hoTen = rs.getString("HOTEN").trim();
                    cmnd = rs.getString("CMND").trim();
                    diaChi = rs.getString("DIACHI").trim();
                    tenTruong = rs.getString("TRUONG").trim();
                    ngaySinh = rs.getDate("NAMSINH");
                    khoiLop = rs.getInt("KHOILOP");
                    hs = new HocSinh(khoiLop, tenTruong, hoTen, ngaySinh, cmnd, diaChi);
                    hs.setUsername(rs.getString("IDHS"));
                    dshs.add(hs);
                    System.out.println("\t- Them hoc sinh vao danh sach");
                    if (hs != null ){
                        System.out.println(hs.toString());
                    }else System.out.println("HS not found!");
                }
            } catch (Exception e) {
                System.out.println("- DB Hoc Sinh");
                e.printStackTrace();
            }
        }else
            System.out.println("====> Khong the lay ket noi den co so du lieu!");
        
        
        return dshs;
    }
    
    public void themHS(HocSinh hs){
        
        if(connection != null){            
            String query = "INSERT INTO HOCSINH VALUES ('"
                    + hs.getUsername().trim() + "','"
                    + hs.getHoTen().trim() + "','"
                    + hs.getNgaySinh() +  "','"
                    + hs.getCmnd().trim() +  "','"
                    + hs.getDiaChi().trim() +  "','"
                    + hs.getKhoiLop() +  "','"
                    + hs.getTenTruong().trim() +  "')";
            System.out.println("- Query: "+ query);
            try {
                stm = connection.createStatement();
                stm.execute(query);
                System.out.println("Them thanh cong hoc sinh co id: "+ hs.getUsername());
            } catch (Exception e) {
                System.out.println("- DB Hoc Sinh");
                e.printStackTrace();
            }
        }
    }
    
    public void xoaHS(String id){
        if(connection != null){
            String query = String.format("DELETE FROM HOCSINH WHERE IDHS = '%s'", id);
            try {
                stm = connection.createStatement();
                stm.execute(query);
                System.out.println("Xoa thanh cong hoc sinh co id: " + id);
            } catch (Exception e) {
                System.out.println("- DB Hoc Sinh");
                e.printStackTrace();
            }
           
        }else {System.out.println("Khong lay duoc ket noi!");}
    }
    
    public void suaHS(HocSinh hs){
        if(connection != null){
            String query ="UPDATE HOCSINH "+
                    "SET "
                    +"HOTEN = '"+ hs.getHoTen().trim() + "',"
                    +"NAMSINH = '"+ hs.getNgaySinh() +  "',"
                    +"CMND = '"+ hs.getCmnd().trim() +  "',"
                    +"DIACHI = '"+ hs.getDiaChi().trim() +  "',"
                    +"KHOILOP = '"+ hs.getKhoiLop() +  "',"
                    +"TRUONG = '"+ hs.getTenTruong().trim() + "' "
                    +"WHERE IDHS = '"+ hs.getUsername()+ "'";
            System.out.println("*** UPDATE HOC SINH QUERY: "+ query);
            try {
                stm = connection.createStatement();
                stm.execute(query);
                System.out.println("Cap nhat thanh cong hoc sinh co id: " + hs.getUsername());
            } catch (Exception e) {
                System.out.println("- DB Hoc Sinh");
                e.printStackTrace();
            }
           
        }else {System.out.println("Khong lay duoc ket noi!");}
    }
}

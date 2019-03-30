/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Fr13nd
 */
public class DangNhap {
    private Connection connection;
    private Statement stm;
    private ResultSet rs;

    public DangNhap() {
        connection = ConnectDB.getConnectDB();
        stm =null;
        rs = null;
    }
    
    
    public String kiemTraDangNhap(String id, String pass){
        String chucVu = "";
        
        if(connection != null){
            String query = String.format("SELECT CHUCVU FROM DANGNHAP WHERE ID='%s' AND MATKHAU='%s'", id,pass);
            
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(query);
                
                if(rs.next()){
                    chucVu = rs.getString("CHUCVU");
                }else chucVu =null;
            } catch (Exception e) {
                System.out.println("LOI DANG NHAP");
                e.printStackTrace();
            }
        }
        
        return chucVu;
    }
    
    public void themDangNhap(String id, String matKhau, String phanQuyen){
        if(connection != null){
            String query = String.format("INSERT INTO DANGNHAP VALUES ('%s','%s','%s')", id, matKhau, phanQuyen);
            
            try {
                stm = connection.createStatement();
                stm.execute(query);
            } catch (Exception e) {
                System.out.println("* LOI THEM DANG NHAP");
                e.printStackTrace();
            }
            
        }
    }
}

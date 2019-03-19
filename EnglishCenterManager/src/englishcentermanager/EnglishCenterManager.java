/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package englishcentermanager;
import Entities.*;
import JDBC.ConnectDB;
import JDBC.DBGiaoVien;
import JDBC.DBHocPhan;
import JDBC.DBHocSinh;
import Screen.FrDangNhap;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Fr13nd
 */
public class EnglishCenterManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Date ns = new Date(97,6,7);
//        HocSinh hs = new HocSinh(10, "Hung Vuong", "Le Huu Hien", "lhhien", ns , "123125", "Quy Nhon");
//        System.out.println(hs);
        
        
        //test login form
        FrDangNhap dangNhap = new FrDangNhap();
        
        
        
        Connection connnection = ConnectDB.getConnectDB();        
        //kiem tra dang nhap
        int kq= 0;
        Statement stm = null;
        ResultSet rs = null;
        
//        if(connnection != null){
//            String sql = String.format("SELECT COUNT(*) FROM DANGNHAP WHERE IDHS = '%s' AND MATKHAU = '%s'", "SV01", "123");
//            try {
//                stm = connnection.createStatement();
//                rs = stm.executeQuery(sql);
//                System.out.println("\t\tKet qua dang nhap: " + rs.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else {System.out.println("Khong the lay ket noi den co so du lieu");}
//        System.out.println("Hoan thanh kiem tra dang nhap!");
//        

//        DBHocPhan dbHP = new DBHocPhan();
//        System.out.println("========== LAY BANG DIEM ===========");
//        ArrayList<BangDiem> dspd = dbHP.layBangDiem(dbHP.layDSLop("HP01"));
    }

}

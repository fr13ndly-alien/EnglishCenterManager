/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;
import java.sql.*;
import java.rmi.*;

/**
 *
 * @author Admin
 */
public class ConnectDB {
    Connection con = null;
    public static Connection getConnectDB(){
        try{
            String user = "sa";
            String pass = "huuhien77";
            String uRL="jdbc:sqlserver://Localhost:1433;databaseName=EnglishCenterManager;user="+user+";password="+pass;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //System.out.println("Chuoi ket noi: "+ uRL);
            //tra ve ket noi
            return DriverManager.getConnection(uRL);
        }
        catch(Exception ex){
            System.out.println("Ket noi that bai");
            ex.printStackTrace();
        }
        return null;
    }
}

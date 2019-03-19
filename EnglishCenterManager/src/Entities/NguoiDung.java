/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Fr13nd
 */
public class NguoiDung {
    protected String username;
    protected String hoTen;
    protected Date ngaySinh;
    protected String cmnd;
    protected String diaChi;

    public NguoiDung(String username, String hoTen, Date ngaySinh, String cmnd, String queQuan) {
        this.username = username;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.cmnd = cmnd;
        this.diaChi = queQuan;
    }
    
    public NguoiDung(String hoTen, Date ngaySinh, String cmnd, String queQuan) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.cmnd = cmnd;
        this.diaChi = queQuan;
    }
    
    protected NguoiDung(){
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setQueQuan(String queQuan) {
        this.diaChi = queQuan;
    }

    @Override
    public String toString() {
        return "Nguoi dung\n{" + 
                "\n\tusername : " + username + 
                "\n\thoTen : " + hoTen + 
                "\n\tngaySinh : " + ngaySinh + 
                "\n\tcmnd : " + cmnd + 
                "\n\tdiachi : " + diaChi + 
                "\n}";
    }
    
    
}

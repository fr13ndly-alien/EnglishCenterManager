/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fr13nd
 */
public class GiaoVien extends NguoiDung{
    private int kinhNghiem;
    private String truongDH;
    private ArrayList<String> lopDay;

    public GiaoVien(String truongDH, int kinhNghiem, ArrayList<String> lopDay, String username, String hoTen, Date namSinh, String cmnd, String diaChi) {
        super(username, hoTen, namSinh, cmnd, diaChi);
        this.truongDH = truongDH;
        this.kinhNghiem = kinhNghiem;
        this.lopDay = lopDay;
    }
    
    public GiaoVien() {
    }

    public String getTruongDH() {
        return truongDH;
    }

    public void setTruongDH(String truongDH) {
        this.truongDH = truongDH;
    }
    

    public int getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(int kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }

    public ArrayList<String> getLopDay() {
        return lopDay;
    }

    public void setLopDay(ArrayList<String> lopDay) {
        this.lopDay = lopDay;
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

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    @Override
    public String toString() {
        int soLop = (lopDay == null) ? 0: lopDay.size();
        return "Giao Vien\n{" + 
                "\n\tusername : " + username + 
                "\n\thoTen : " + hoTen + 
                "\n\tngaySinh : " + ngaySinh + 
                "\n\tcmnd : " + cmnd + 
                "\n\tdia chi : " + diaChi + 
                "\n\tso nam giang day:"+ kinhNghiem+
                "\n\ttruong dai hoc: " + truongDH +
                "\n\tso lop dang day: "+ soLop +
                "\n}";
    }
}

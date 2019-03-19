/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fr13nd
 */
public class HocSinh extends NguoiDung{
    
    private int khoiLop;
    private String tenTruong;
    private ArrayList<String> khoaHoc;
    private ArrayList<String> thongBao;

    public HocSinh(int khoiLop, String tenTruong, ArrayList<String> khoaHoc, String username, String hoTen, Date namSinh, String cmnd, String diaChi) {
        super(username, hoTen, namSinh, cmnd, diaChi);
        this.khoiLop = khoiLop;
        this.tenTruong = tenTruong;
        this.khoaHoc = khoaHoc;
        this.khoaHoc = new ArrayList<>();
    }
    
    // khoi tao hoc sinh chua dang ki khoa hoc nao
    public HocSinh(int khoiLop, String tenTruong, String username, String hoTen, Date namSinh, String cmnd, String queQuan) {
        super(username, hoTen, namSinh, cmnd, queQuan);
        this.khoiLop = khoiLop;
        this.tenTruong = tenTruong;
        this.khoaHoc = khoaHoc;
    }
    
    public HocSinh(int khoiLop, String tenTruong, String hoTen, Date namSinh, String cmnd, String queQuan) {
        super(hoTen, namSinh, cmnd, queQuan);
        this.khoiLop = khoiLop;
        this.tenTruong = tenTruong;
        this.khoaHoc = khoaHoc;
    }
    
    public HocSinh(){
    }

    public int getKhoiLop() {
        return khoiLop;
    }

    public void setKhoiLop(int khoiLop) {
        this.khoiLop = khoiLop;
    }

    public String getTenTruong() {
        return tenTruong;
    }

    public void setTenTruong(String tenTruong) {
        this.tenTruong = tenTruong;
    }

    public ArrayList<String> getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(ArrayList<String> khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public ArrayList<String> getThongBao() {
        return thongBao;
    }

    public void setThongBao(ArrayList<String> thongBao) {
        this.thongBao = thongBao;
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

    public void setNgaySinh(Date namSinh) {
        this.ngaySinh = namSinh;
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
        int dskh = (khoaHoc == null)? 0 : khoaHoc.size();
        int tb = (thongBao == null)? 0 : thongBao.size();
        return "Hoc sinh\n{" + 
                "\n\tusername : " + username + 
                "\n\thoTen : " + hoTen + 
                "\n\tngaySinh : " + ngaySinh + 
                "\n\tcmnd=" + cmnd + 
                "\n\tdiaChi : " + diaChi + 
                "\n\tkhoi lop : " + khoiLop +
                "\n\tten truong : " + tenTruong +
                "\n\tso khoa hoc dang tham gia : " + dskh +
                "\n\tthong bao : " + tb + 
                "\n}";
    }
    
}

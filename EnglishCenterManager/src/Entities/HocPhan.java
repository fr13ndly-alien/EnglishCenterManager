/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Fr13nd
 */
public class HocPhan {
    private String maHP;
    private int siSo;
    private GiaoVien giaoVien;
    private ArrayList<HocSinh> danhSachLop;
    private float hocPhi;

    public HocPhan(String maHP, int siSo, GiaoVien giaoVien, ArrayList<HocSinh> danhSachLop, float hocPhi) {
        this.maHP = maHP;
        this.siSo = siSo;
        this.giaoVien = giaoVien;
        this.danhSachLop = danhSachLop;
        this.hocPhi = hocPhi;
    }

    public HocPhan() {
        
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public int getSiSo() {
        return siSo;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
    }

    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    public ArrayList<HocSinh> getDanhSachLop() {
        return danhSachLop;
    }

    public void setDanhSachLop(ArrayList<HocSinh> danhSachLop) {
        this.danhSachLop = danhSachLop;
    }

    public float getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(float hocPhi) {
        this.hocPhi = hocPhi;
    }
    
    public String toString() {
        return "Hoc Phan\n{" + 
                "\n\tma hoc phan : " + maHP   +
                "\n\tgiao vien : " + giaoVien.getHoTen()+
                "\n\tsi so lop : " + siSo  +
                "\n\thoc phi : " + hocPhi  +
                "\n}";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Fr13nd
 */
public class ThongBao {
    private String maHP;
    private String moTa;
    private String noiDung;
    private Date ngayDang;

    public ThongBao(String maHP, String noiDung, String moTa, Date ngayDang) {
        this.maHP = maHP;
        this.noiDung = noiDung;
        this.moTa = moTa;
        this.ngayDang = ngayDang;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }
    
    
    public ThongBao() {
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Override
    public String toString() {
        return ngayDang.toString()+": "+moTa;
    }

    
    
}

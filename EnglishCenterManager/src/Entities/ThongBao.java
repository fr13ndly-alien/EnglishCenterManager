/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Fr13nd
 */
public class ThongBao {
    private String maHP;
    private String noiDung;

    public ThongBao(String maHP, String noiDung) {
        this.maHP = maHP;
        this.noiDung = noiDung;
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
        return "ThongBao{" + "maHP=" + maHP + ", noiDung=" + noiDung + '}';
    }
    
    
}

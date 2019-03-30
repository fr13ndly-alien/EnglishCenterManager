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
public class BangDiem {
    private String idHS;
    private int chuyenCan;
    private int giuaKy;
    private float cuoiKy;

    public BangDiem(String idHSh, int chuyenCan, int giuaKy, float cuoiKy) {
        this.idHS = idHS;
        this.chuyenCan = chuyenCan;
        this.giuaKy = giuaKy;
        this.cuoiKy = cuoiKy;
    }

    public BangDiem() {
        
    }

    public String getidHS() {
        return idHS;
    }

    public void setidHS(String idHS) {
        this.idHS = idHS;
    }

    public int getChuyenCan() {
        return chuyenCan;
    }

    public void setChuyenCan(int chuyenCan) {
        this.chuyenCan = chuyenCan;
    }

    public int getGiuaKy() {
        return giuaKy;
    }

    public void setGiuaKy(int giuaKy) {
        this.giuaKy = giuaKy;
    }

    public float getCuoiKy() {
        return cuoiKy;
    }

    public void setCuoiKy(float cuoiKy) {
        this.cuoiKy = cuoiKy;
    }

    @Override
    public String toString() {
        return "BangDiem{" + "idHS=" + idHS + ", chuyenCan=" + chuyenCan + ", giuaKy=" + giuaKy + ", cuoiKy=" + cuoiKy + '}';
    }
    
    
}
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
public class TaiLieu {
    private String maTaiLieu;
    private String ten;
    private int khoiLop;
    private String link; 

    public TaiLieu(String maTaiLieu, String ten,  int khoiLop, String link) {
        this.maTaiLieu = maTaiLieu;
        this.ten = ten;
        this.khoiLop = khoiLop;
        this.link = link;
    }

    public String getMaTaiLieu() {
        return maTaiLieu;
    }

    public void setMaTaiLieu(String maTaiLieu) {
        this.maTaiLieu = maTaiLieu;
    }

    public int getKhoiLop() {
        return khoiLop;
    }

    public void setKhoiLop(int khoiLop) {
        this.khoiLop = khoiLop;
    }

    public String getLink() {
        return link;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "TaiLieu{\n" + 
                "\n\tmaTaiLieu=" + maTaiLieu + 
                "\n\tten=" + ten + 
                "\n\tkhoiLop : " + khoiLop + 
                "\n\tlink : " + link + "\n}";
    }
    
    
}

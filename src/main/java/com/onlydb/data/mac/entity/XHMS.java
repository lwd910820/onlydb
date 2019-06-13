package com.onlydb.data.mac.entity;

import java.util.List;

public class XHMS {

    private String id;
    private String cznr;
    private String czzl;
    private String csyw;
    private Integer cscd;
    private String csjz;
    private String jlzt;
    private String gzbzw;
    private String dxbz;
    private Integer sjwz;
    private Integer sjcd;
    private List<XHMSGZ> xhmsgzs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCznr() {
        return cznr;
    }

    public void setCznr(String cznr) {
        this.cznr = cznr;
    }

    public String getCzzl() {
        return czzl;
    }

    public void setCzzl(String czzl) {
        this.czzl = czzl;
    }

    public String getCsyw() {
        return csyw;
    }

    public void setCsyw(String csyw) {
        this.csyw = csyw;
    }

    public Integer getCscd() {
        return cscd;
    }

    public void setCscd(Integer cscd) {
        this.cscd = cscd;
    }

    public String getCsjz() {
        return csjz;
    }

    public void setCsjz(String csjz) {
        this.csjz = csjz;
    }

    public String getJlzt() {
        return jlzt;
    }

    public void setJlzt(String jlzt) {
        this.jlzt = jlzt;
    }

    public String getGzbzw() {
        return gzbzw;
    }

    public void setGzbzw(String gzbzw) {
        this.gzbzw = gzbzw;
    }

    public String getDxbz() {
        return dxbz;
    }

    public void setDxbz(String dxbz) {
        this.dxbz = dxbz;
    }

    public Integer getSjwz() {
        return sjwz;
    }

    public void setSjwz(Integer sjwz) {
        this.sjwz = sjwz;
    }

    public Integer getSjcd() {
        return sjcd;
    }

    public void setSjcd(Integer sjcd) {
        this.sjcd = sjcd;
    }

    public List<XHMSGZ> getXhmsgzs() {
        return xhmsgzs;
    }

    public void setXhmsgzs(List<XHMSGZ> xhmsgzs) {
        this.xhmsgzs = xhmsgzs;
    }

    @Override
    public String toString() {
        return "XHMS{" +
                "cznr='" + cznr + '\'' +
                ", czzl='" + czzl + '\'' +
                ", csyw='" + csyw + '\'' +
                ", cscd=" + cscd +
                ", csjz='" + csjz + '\'' +
                ", jlzt='" + jlzt + '\'' +
                ", gzbzw='" + gzbzw + '\'' +
                ", dxbz='" + dxbz + '\'' +
                ", sjwz=" + sjwz +
                ", sjcd=" + sjcd +
                ", xhmsgzs=" + xhmsgzs +
                '}';
    }
}

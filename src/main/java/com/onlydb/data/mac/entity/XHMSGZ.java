package com.onlydb.data.mac.entity;

import java.util.List;

public class XHMSGZ {

    private String id;
    private String xhmsid;
    private String sxm;
    private String zwsxm;
    private String sjwz;
    private String sjcd;
    private String cysj;
    private String gzbz;
    private List<XHMSVAL> xhmsvals;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXhmsid() {
        return xhmsid;
    }

    public void setXhmsid(String xhmsid) {
        this.xhmsid = xhmsid;
    }

    public String getSxm() {
        return sxm;
    }

    public void setSxm(String sxm) {
        this.sxm = sxm;
    }

    public String getZwsxm() {
        return zwsxm;
    }

    public void setZwsxm(String zwsxm) {
        this.zwsxm = zwsxm;
    }

    public String getSjwz() {
        return sjwz;
    }

    public void setSjwz(String sjwz) {
        this.sjwz = sjwz;
    }

    public String getSjcd() {
        return sjcd;
    }

    public void setSjcd(String sjcd) {
        this.sjcd = sjcd;
    }

    public String getCysj() {
        return cysj;
    }

    public void setCysj(String cysj) {
        this.cysj = cysj;
    }

    public String getGzbz() {
        return gzbz;
    }

    public void setGzbz(String gzbz) {
        this.gzbz = gzbz;
    }

    public List<XHMSVAL> getXhmsvals() {
        return xhmsvals;
    }

    public void setXhmsvals(List<XHMSVAL> xhmsvals) {
        this.xhmsvals = xhmsvals;
    }

    @Override
    public String toString() {
        return "XHMSGZ{" +
                "id='" + id + '\'' +
                ", xhmsid='" + xhmsid + '\'' +
                ", sxm='" + sxm + '\'' +
                ", zwsxm='" + zwsxm + '\'' +
                ", sjwz='" + sjwz + '\'' +
                ", sjcd='" + sjcd + '\'' +
                ", cysj='" + cysj + '\'' +
                ", gzbz='" + gzbz + '\'' +
                ", xhmsvals=" + xhmsvals +
                '}';
    }
}

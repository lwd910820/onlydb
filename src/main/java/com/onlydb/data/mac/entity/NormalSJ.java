package com.onlydb.data.mac.entity;

import java.util.Map;

public class NormalSJ {

    private String jqid;
    private String kzbz;
    private String msxz;
    private String gzdm;
    private Float hswd;
    private Float cswd;
    private Float sdwd;
    private Float hjwd;
    private String jqlx;
    private String jqljzt = "0";
    private Integer sjlength = 4;

    public NormalSJ(){}

    public NormalSJ(String s,String jqid){
        this.jqid = jqid;
        if(s.length()==18){
            setKzbz(s.substring(2,2));
            setMsxz(s.substring(4,6));
            setGzdm(s.substring(6,8));
            setHswd((float) (Integer.valueOf(s.substring(8,10),16)/10));
            setCswd((float) (Integer.valueOf(s.substring(10,12),16)/10));
            setSdwd((float) (Integer.valueOf(s.substring(12,14),16)/10));
            setHjwd((float) (Integer.valueOf(s.substring(14,16),16)/10));
            setJqlx(s.substring(16,18));
        }
    }

    public String getJqid() {
        return jqid;
    }

    public void setJqid(String jqid) {
        this.jqid = jqid;
    }

    public String getKzbz() {
        return kzbz;
    }

    public void setKzbz(String kzbz) {
        this.kzbz = kzbz;
    }

    public String getMsxz() {
        return msxz;
    }

    public void setMsxz(String msxz) {
        this.msxz = msxz;
    }

    public String getGzdm() {
        return gzdm;
    }

    public void setGzdm(String gzdm) {
        this.gzdm = gzdm;
        if(gzdm.equals("FF")) setJqljzt("0");
        else if(gzdm.equals("FE")) setJqljzt("1");
        else setJqljzt("2");
    }

    public Float getHswd() {
        return hswd;
    }

    public void setHswd(Float hswd) {
        this.hswd = hswd;
    }

    public Float getCswd() {
        return cswd;
    }

    public void setCswd(Float cswd) {
        this.cswd = cswd;
    }

    public Float getSdwd() {
        return sdwd;
    }

    public void setSdwd(Float sdwd) {
        this.sdwd = sdwd;
    }

    public Float getHjwd() { return hjwd; }

    public void setHjwd(Float hjwd) { this.hjwd = hjwd; }

    public String getJqlx() {
        return jqlx;
    }

    public void setJqlx(String jqlx) {
        this.jqlx = jqlx;
    }

    public String getJqljzt() { return jqljzt; }

    public void setJqljzt(String jqljzt) { this.jqljzt = jqljzt; }

    @Override
    public String toString() {
        return "NormalSJ{" +
                "jqid='" + jqid + '\'' +
                ", kzbz='" + kzbz + '\'' +
                ", msxz='" + msxz + '\'' +
                ", gzdm='" + gzdm + '\'' +
                ", hswd=" + hswd +
                ", cswd=" + cswd +
                ", sdwd=" + sdwd +
                ", hjwd='" + hjwd + '\'' +
                ", jqlx='" + jqlx + '\'' +
                ", jqljzt='" + jqljzt + '\'' +
                '}';
    }
}

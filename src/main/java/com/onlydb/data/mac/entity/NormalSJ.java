package com.onlydb.data.mac.entity;

import com.onlydb.util.SJGZUtil;


public class NormalSJ {

    private String jqid;
    private String kzbz;
    private String msxz;
    private String gzdm;
    private Double hswd;
    private Double cswd;
    private Double sdwd;
    private Double hjwd;
    private String jqlx;
    private String jqljzt = "0";

    public NormalSJ(){}

    public NormalSJ(String s,String jqid,JZTZ jztz){
        this.jqid = jqid;
        if(s.length()==jztz.getSinlen()*8){
            setKzbz((String) SJGZUtil.transMsg(s.substring(jztz.getKzbz(),jztz.getKzbz()+jztz.getSinlen()),jztz.getKzbzgz()));
            setMsxz((String) SJGZUtil.transMsg(s.substring(jztz.getMsxz(),jztz.getMsxz()+jztz.getSinlen()),jztz.getMsxzgz()));
            setGzdm((String) SJGZUtil.transMsg(s.substring(jztz.getGzdm(),jztz.getGzdm()+jztz.getSinlen()),jztz.getGzdmgz()));
            setHswd((Double) SJGZUtil.transMsg(s.substring(jztz.getHswd(),jztz.getHswd()+jztz.getSinlen()),jztz.getHswdgz()));
            setCswd((Double) SJGZUtil.transMsg(s.substring(jztz.getCswd(),jztz.getCswd()+jztz.getSinlen()),jztz.getCswdgz()));
            setSdwd((Double) SJGZUtil.transMsg(s.substring(jztz.getSdwd(),jztz.getSdwd()+jztz.getSinlen()),jztz.getSdwdgz()));
            setHjwd((Double) SJGZUtil.transMsg(s.substring(jztz.getHswd(),jztz.getHswd()+jztz.getSinlen()),jztz.getHswdgz()));
            setJqlx((String) SJGZUtil.transMsg(s.substring(jztz.getJqlx(),jztz.getJqlx()+jztz.getSinlen()),jztz.getJqlxgz()));
        }
    }

    public NormalSJ(String jqid){
        this.jqid = jqid;
    }

    public NormalSJ setProp(String s,JZTZ jztz){
        if(s.length()==jztz.getSinsjlen()){
            setKzbz((String) SJGZUtil.transMsg(s.substring(jztz.getKzbz(),jztz.getKzbz()+jztz.getSinlen()),jztz.getKzbzgz()));
            setMsxz((String) SJGZUtil.transMsg(s.substring(jztz.getMsxz(),jztz.getMsxz()+jztz.getSinlen()),jztz.getMsxzgz()));
            setGzdm((String) SJGZUtil.transMsg(s.substring(jztz.getGzdm(),jztz.getGzdm()+jztz.getSinlen()),jztz.getGzdmgz()));
            setHswd((Double) SJGZUtil.transMsg(s.substring(jztz.getHswd(),jztz.getHswd()+jztz.getSinlen()),jztz.getHswdgz()));
            setCswd((Double) SJGZUtil.transMsg(s.substring(jztz.getCswd(),jztz.getCswd()+jztz.getSinlen()),jztz.getCswdgz()));
            setSdwd((Double) SJGZUtil.transMsg(s.substring(jztz.getSdwd(),jztz.getSdwd()+jztz.getSinlen()),jztz.getSdwdgz()));
            setHjwd((Double) SJGZUtil.transMsg(s.substring(jztz.getHswd(),jztz.getHswd()+jztz.getSinlen()),jztz.getHswdgz()));
            setJqlx((String) SJGZUtil.transMsg(s.substring(jztz.getJqlx(),jztz.getJqlx()+jztz.getSinlen()),jztz.getJqlxgz()));
        }
        return this;
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

    public Double getHswd() {
        return hswd;
    }

    public void setHswd(Double hswd) {
        this.hswd = hswd;
    }

    public Double getCswd() {
        return cswd;
    }

    public void setCswd(Double cswd) {
        this.cswd = cswd;
    }

    public Double getSdwd() {
        return sdwd;
    }

    public void setSdwd(Double sdwd) {
        this.sdwd = sdwd;
    }

    public Double getHjwd() {
        return hjwd;
    }

    public void setHjwd(Double hjwd) {
        this.hjwd = hjwd;
    }

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

package com.onlydb.data.mac.entity;

import com.onlydb.util.SJGZUtil;


public class NormalSJ {

    private String jqid;
    private String kzbz;
    private String msxz;
    private String gzdm;
    private String hswd;
    private String cswd;
    private String sdwd;
    private String hjwd;
    private String jqlx;
    private String nogz;
    private String jqljzt = "0";

    public NormalSJ(){}

    public NormalSJ(String s,String jqid,JZTZ jztz){
        this.jqid = jqid;
        if(s.length()==jztz.getSinlen()*8){
            setKzbz(SJGZUtil.transMsg(s.substring(jztz.getKzbz(),jztz.getKzbz()+jztz.getSinlen()),jztz.getKzbzgz()));
            setMsxz(SJGZUtil.transMsg(s.substring(jztz.getMsxz(),jztz.getMsxz()+jztz.getSinlen()),jztz.getMsxzgz()));
            setGzdm(SJGZUtil.transMsg(s.substring(jztz.getGzdm(),jztz.getGzdm()+jztz.getSinlen()),jztz.getGzdmgz()));
            setHswd(SJGZUtil.transMsg(s.substring(jztz.getHswd(),jztz.getHswd()+jztz.getSinlen()),jztz.getHswdgz()));
            setCswd(SJGZUtil.transMsg(s.substring(jztz.getCswd(),jztz.getCswd()+jztz.getSinlen()),jztz.getCswdgz()));
            setSdwd(SJGZUtil.transMsg(s.substring(jztz.getSdwd(),jztz.getSdwd()+jztz.getSinlen()),jztz.getSdwdgz()));
            setHjwd(SJGZUtil.transMsg(s.substring(jztz.getHswd(),jztz.getHswd()+jztz.getSinlen()),jztz.getHswdgz()));
            setJqlx(SJGZUtil.transMsg(s.substring(jztz.getJqlx(),jztz.getJqlx()+jztz.getSinlen()),jztz.getJqlxgz()));
        }
    }

    public NormalSJ(String jqid,String nogz){
        this.jqid = jqid;
        this.nogz = nogz;
    }

    public NormalSJ setProp(String s,JZTZ jztz){
        if(s.length()==jztz.getSinsjlen()){
            setKzbz(SJGZUtil.transMsg(s.substring(jztz.getKzbz(),jztz.getKzbz()+jztz.getSinlen()),jztz.getKzbzgz()));
            setMsxz(SJGZUtil.transMsg(s.substring(jztz.getMsxz(),jztz.getMsxz()+jztz.getSinlen()),jztz.getMsxzgz()));
            setGzdm(SJGZUtil.transMsg(s.substring(jztz.getGzdm(),jztz.getGzdm()+jztz.getSinlen()),jztz.getGzdmgz()));
            setHswd(SJGZUtil.transMsg(s.substring(jztz.getHswd(),jztz.getHswd()+jztz.getSinlen()),jztz.getHswdgz()));
            setCswd(SJGZUtil.transMsg(s.substring(jztz.getCswd(),jztz.getCswd()+jztz.getSinlen()),jztz.getCswdgz()));
            setSdwd(SJGZUtil.transMsg(s.substring(jztz.getSdwd(),jztz.getSdwd()+jztz.getSinlen()),jztz.getSdwdgz()));
            setHjwd(SJGZUtil.transMsg(s.substring(jztz.getHswd(),jztz.getHswd()+jztz.getSinlen()),jztz.getHswdgz()));
            setJqlx(SJGZUtil.transMsg(s.substring(jztz.getJqlx(),jztz.getJqlx()+jztz.getSinlen()),jztz.getJqlxgz()));
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
        if(gzdm.equals(nogz)) setJqljzt("0");
        else setJqljzt("2");
    }

    public String getHswd() {
        return hswd;
    }

    public void setHswd(String hswd) {
        this.hswd = hswd;
    }

    public String getCswd() {
        return cswd;
    }

    public void setCswd(String cswd) {
        this.cswd = cswd;
    }

    public String getSdwd() {
        return sdwd;
    }

    public void setSdwd(String sdwd) {
        this.sdwd = sdwd;
    }

    public String getHjwd() {
        return hjwd;
    }

    public void setHjwd(String hjwd) {
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

    public String getNogz() {
        return nogz;
    }

    public void setNogz(String nogz) {
        this.nogz = nogz;
    }

    @Override
    public String toString() {
        return "NormalSJ{" +
                "jqid='" + jqid + '\'' +
                ", kzbz='" + kzbz + '\'' +
                ", msxz='" + msxz + '\'' +
                ", gzdm='" + gzdm + '\'' +
                ", hswd='" + hswd + '\'' +
                ", cswd='" + cswd + '\'' +
                ", sdwd='" + sdwd + '\'' +
                ", hjwd='" + hjwd + '\'' +
                ", jqlx='" + jqlx + '\'' +
                ", nogz='" + nogz + '\'' +
                ", jqljzt='" + jqljzt + '\'' +
                '}';
    }
}

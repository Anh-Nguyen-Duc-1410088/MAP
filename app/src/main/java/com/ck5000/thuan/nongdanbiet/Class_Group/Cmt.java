package com.ck5000.thuan.nongdanbiet.Class_Group;



public class Cmt {

    private String NoiDung, Time, TacGia;

    public void Post(String TacGia, String Time, String NoiDung)
    {
        this.TacGia = TacGia;
        this.NoiDung = NoiDung;
        this.Time = Time;
    }
    public String getNoiDung()
    {
        return NoiDung;
    }
    public String getTime()
    {
        return Time;
    }
    public String getTacGia()
    {
        return TacGia;
    }

    public Cmt setNoiDung(String NoiDung)
    {
        this.NoiDung = NoiDung;
        return this;
    }
    public Cmt setTacGia(String TacGia)
    {
        this.TacGia = TacGia;
        return this;
    }
    public Cmt setTime(String Time)
    {
        this.Time = Time;
        return this;
    }

}

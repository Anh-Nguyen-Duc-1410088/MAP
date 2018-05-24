package com.ck5000.thuan.nongdanbiet.Class_Group;



public class Post {

    private String TieuDe, NoiDung, Time, TacGia;

    public void Post(String TieuDe, String NoiDung, String Time, String TacGia)
    {
        this.TieuDe = TieuDe;
        this.TacGia = TacGia;
        this.NoiDung = NoiDung;
        this.Time = Time;
    }
    public String getTieuDe()
    {
        return TieuDe;
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

    public Post setTieuDe(String TieuDe)
    {
        this.TieuDe = TieuDe;
        return this;
    }
    public Post setNoiDung(String NoiDung)
    {
        this.NoiDung = NoiDung;
        return this;
    }
    public Post setTacGia(String TacGia)
    {
        this.TacGia = TacGia;
        return this;
    }
    public Post setTime(String Time)
    {
        this.Time = Time;
        return this;
    }

}

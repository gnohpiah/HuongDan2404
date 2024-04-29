package com.example.huongdan2404;

public class Student {
    private String sbd;
    private String name;
    private double toan,ly,hoa;

    public Student(String sbd, String name, double toan, double ly, double hoa) {
        this.sbd = sbd;
        this.name = name;
        this.toan = toan;
        this.ly = ly;
        this.hoa = hoa;
    }
    public Student(){}

    public String getSbd() {
        return sbd;
    }

    public void setSbd(String sbd) {
        this.sbd = sbd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getToan() {
        return toan;
    }

    public void setToan(double toan) {
        this.toan = toan;
    }

    public double getLy() {
        return ly;
    }

    public void setLy(double ly) {
        this.ly = ly;
    }

    public double getHoa() {
        return hoa;
    }

    public void setHoa(double hoa) {
        this.hoa = hoa;
    }
    public double getSum(){
        return this.toan+this.ly+this.hoa;
    }
    public double getAvg(){
        return (this.toan+this.ly+this.hoa)/3;
    }
}

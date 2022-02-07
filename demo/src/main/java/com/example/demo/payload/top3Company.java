package com.example.demo.payload;

public class top3Company {
    private String companyname;
    private  Integer total;

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public top3Company(String companyname, Integer total) {
        this.companyname = companyname;
        this.total = total;
    }

    public top3Company() {
    }
}

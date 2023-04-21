package org.example.airportmanagementsystem.entity;

import java.util.Date;

public class Company {
    private int companyId;
    private String name;
    private Date foundDate;

    public Company(int companyId, String name, Date foundDate) {
        this.companyId = companyId;
        this.name = name;
        this.foundDate = foundDate;
    }

    public Company(String name, Date foundDate) {
        this.name = name;
        this.foundDate = foundDate;
    }

    public Company() {
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

    @Override
    public String toString() {
        return "" + companyId + " " + name + " " + foundDate;
    }
}

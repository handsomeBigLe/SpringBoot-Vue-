package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="doctorinfo")
public class Doctor {
    @Column(name="name")
    private String name;
    @Column(name = "account")
    private String account;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "sex")
    private String sex;
    @Column(name = "department")
    private String department;
    @Column(name = "consultationNum")
    private Integer consultationNum;

    private Integer pageSize;
    private Integer pageNum;

    public Integer getConsultationNum() {
        return consultationNum;
    }

    public void setConsultationNum(Integer consultationNum) {
        this.consultationNum = consultationNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

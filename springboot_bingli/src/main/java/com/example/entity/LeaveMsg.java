package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class LeaveMsg {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name="patient")
    private String patient;
    @Column(name="recordId")
    private String recordId;
    @Column(name = "content")
    private String content;
    @Column(name = "reply")
    private String reply;
    @Column(name = "doctor")
    private String doctor;
    @Column(name = "createTime")
    private String createTime;
    @Column(name = "replyTime")
    private String replyTime;
    @Column(name = "status")
    private String status;

    private Integer pageSize;
    private Integer pageNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}

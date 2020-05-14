package com.diploma.Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "conversations", schema = "public", catalog = "diploma")
public class ConversationsEntity {
    private UUID cid = UUID.randomUUID();
    private String request;
    private Timestamp reqDate = new Timestamp(new Date().getTime());
    private String response = "";
    private Timestamp resDate;
    private int performedBy;
    private String authorName;
    private String cstatus = "request created";

    @Id
    @Column(name = "cid")
    public UUID getCid() {
        return cid;
    }

    public void setCid(UUID cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "request")
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Basic
    @Column(name = "req_date")
    public Timestamp getReqDate() {
        return reqDate;
    }

    public void setReqDate(Timestamp reqDate) {
        this.reqDate = reqDate;
    }

    @Basic
    @Column(name = "response")
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Basic
    @Column(name = "res_date")
    public Timestamp getResDate() {
        return resDate;
    }

    public void setResDate(Timestamp resDate) {
        this.resDate = resDate;
    }

    @Basic
    @Column(name = "performed_by")
    public int getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(int performedBy) {
        this.performedBy = performedBy;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) { this.authorName = authorName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationsEntity that = (ConversationsEntity) o;
        return cid == that.cid &&
                Objects.equals(request, that.request) &&
                Objects.equals(reqDate, that.reqDate) &&
                Objects.equals(response, that.response) &&
                Objects.equals(resDate, that.resDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, request, reqDate, response, resDate);
    }

    @Basic
    @Column(name = "cstatus")
    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }
}

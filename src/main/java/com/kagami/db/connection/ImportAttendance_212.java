package com.kagami.db.connection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kagami.TransactionEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "importattendance_212")
public class ImportAttendance_212 extends TransactionEntity
        implements
        java.io.Serializable {

    public ImportAttendance_212(String id, String status, String employeeno,
            Long attenddate, Long intime, Long outtime) {
        this.id = id;
        this.status = status;
        this.employeeno = employeeno;
        this.attenddate = attenddate;
        this.intime = intime;
        this.outtime = outtime;
    }

    @Column
    private String id;
    @Column(name = "status")
    private String status;
    @Column(name = "employeeno")
    private String employeeno;
    @Column(name = "attenddate")
    private Long attenddate;
    @Column(name = "intime")
    private Long intime;
    @Column(name = "outtime")
    private Long outtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeno() {
        return employeeno;
    }

    public void setEmployeeno(String employeeno) {
        this.employeeno = employeeno;
    }

    public Long getAttenddate() {
        return attenddate;
    }

    public void setAttenddate(Long attenddate) {
        this.attenddate = attenddate;
    }

    public Long getIntime() {
        return intime;
    }

    public void setIntime(Long intime) {
        this.intime = intime;
    }

    public Long getOuttime() {
        return outtime;
    }

    public void setOuttime(Long outtime) {
        this.outtime = outtime;
    }

}
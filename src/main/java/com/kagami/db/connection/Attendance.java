package com.kagami.db.connection;

import java.util.Date;

public class Attendance
{
    private int employeeId;
    private Date attendaceDate;
    private Date inTime;
    private Date outTime;
    private double numberOfHours;
    private String shift;

    public Attendance(int employeeId,Date attendaceDate, Date inTime, Date outTime, double numberOfHours, String shift) {
        this.employeeId = employeeId;
        this.attendaceDate = attendaceDate;
        this.inTime = inTime;
        this.outTime = outTime;
        this.numberOfHours = numberOfHours;
        this.shift = shift;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "employeeId=" + employeeId +
                ", attendaceDate=" + attendaceDate +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", numberOfHours=" + numberOfHours +
                '}';
    }
}

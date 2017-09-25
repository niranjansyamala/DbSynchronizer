package com.kagami.db.connection;


import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AccessConnector {
    public static Connection getConnection() throws IOException {
        DatabaseBuilder databaseBuilder = new DatabaseBuilder(new File("C:\\Users\\niranjan\\Desktop\\eTimeTrackLite_2015_03_24_1.mdb"));
        Table table = databaseBuilder.open().getTable("AttendanceLogs");
        List<Attendance> attendance  = new ArrayList();
        Map<String, Object> row = null;
        int count = 0;
        while((row = table.getNextRow())!=null){
            attendance.add(new Attendance(Integer.parseInt(row.get("EmployeeId").toString()), parseDate(row.get("AttendanceDate").toString()), parseDate(row.get("InTime").toString()), parseDate(row.get("OutTime").toString()),new Double(row.get("Duration").toString())/60,"GS"));
        }
        System.out.println(attendance);
        return null;
    }


    public static Date parseDate(String date){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd HH:mm:SS");
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        AccessConnector.getConnection();
    }

}

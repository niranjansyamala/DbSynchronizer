package com.kagami.db.connection;


import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.kagami.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class AccessConnector {


    @Autowired
    AttendanceRepo repo;

    public Connection getConnection() throws IOException {
        DatabaseBuilder databaseBuilder = new DatabaseBuilder(new File("C:\\Users\\niranjan\\Desktop\\DbSynchronizer\\src\\main\\resources\\eTimeTrackLite_2015_03_24_1.mdb"));
        Table table = databaseBuilder.open().getTable("AttendanceLogs");
        List<ImportAttendance_212> attendance = new ArrayList();
        Map<String, Object> row = null;
        int count = 0;
        while ((row = table.getNextRow()) != null && count < 101) {

            if (row.get("EmployeeId").toString().equals("2540") && row.get("InDeviceId").toString().equals("TCPL"))
                attendance.add(new ImportAttendance_212("" + count++, "open", row.get("EmployeeId").toString(),
                        parseDate(row.get("AttendanceDate").toString()),
                        parseDate(row.get("InTime").toString()),
                        parseDate(row.get("OutTime").toString())));
        }

        repo.save(attendance);


        return null;
    }


    public static Long parseDate(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           Date dateFortedd =  dateFormat.parse(date);
            return dateFortedd.getTime();
        } catch (ParseException e) {
          return   parseDate(date, false).getTime();
        }
    }

    //E MMM dd HH:mm:ss Z yyyy
    public static Date parseDate(String dateString, boolean isOther) {
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date date = null;
        try {
            date = (Date) formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
       // System.out.println("formatedDate : " + formatedDate);
        return date;
    }
    }

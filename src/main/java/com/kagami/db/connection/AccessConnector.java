    package com.kagami.db.connection;


import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.kagami.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class AccessConnector {


    @Autowired AttendanceRepo repo;

    public Connection getConnection() throws IOException {
        DatabaseBuilder databaseBuilder = new DatabaseBuilder(new File("/home/niranjan/Workshop/DbSynchronizer/src/main/resources/eTimeTrackLite_2015_03_24_1.mdb"));
        Table table = databaseBuilder.open().getTable("AttendanceLogs");
        List<ImportAttendance_212> attendance  = new ArrayList();
        Map<String, Object> row = null;
        int count = 0;
        while((row = table.getNextRow())!=null && count<101){
            attendance.add(new ImportAttendance_212("" + count++, "open", row.get("EmployeeId").toString(),
                            parseDate(row.get("InTime").toString()),
                            parseDate(row.get("InTime").toString()),
                            parseDate(row.get("InTime").toString())));
        }

        long start = System.currentTimeMillis();
        repo.save(attendance);
        long end = System.currentTimeMillis();

System.out.println("TIME TAKEN"+(end-start));

        return null;
    }



    public static Long parseDate(String date){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd HH:mm:SS");
            return dateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}

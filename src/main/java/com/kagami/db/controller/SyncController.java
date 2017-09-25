package com.kagami.db.controller;

import com.kagami.db.Database;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/db")
public class SyncController {



    @ResponseBody
    @RequestMapping("/sync")
    public boolean syncDb(Database src, Database destination){



        return false;
    }

}

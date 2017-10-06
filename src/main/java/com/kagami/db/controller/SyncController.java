package com.kagami.db.controller;

import com.kagami.db.Database;
import com.kagami.db.connection.AccessConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("/db") public class SyncController {

	@Autowired AccessConnector accessConnector;

	@ResponseBody @RequestMapping("/sync") public boolean syncDb() throws IOException {

		accessConnector.getConnection();

		return false;
	}

}

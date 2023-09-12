package com.projectJDBC.escola;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projectJDBC.escola.db.DB;

@SpringBootApplication
public class EscolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscolaApplication.class, args);
//		Connection conn = DB.getConnection();
//		DB.closeConnection();
	}

}

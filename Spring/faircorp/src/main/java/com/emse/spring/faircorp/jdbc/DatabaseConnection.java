// package com.emse.spring.faircorp.jdbc;

// import java.sql.*;

// public class DatabaseConnection {
//     public static void main(String[] args) throws Exception {
//         try{
//             Class.forName("org.h2.Drive");
//         }
//         catch(ClassNotFoundException e){
//             // logger.error("Unable to load JDBC Driver", e);
//         }
//         try{
//             String database_url = "jdbc:h2:mem:faircorp;DB_CLOSE_DELAY=-1";
//             String username = "sa";
//             String password = "password";
//             Connection connection = DriverManager.getConnection(database_url, username, password);
//         }
//         catch(SQLException e) {
//         //    logger.error("Unable to connect to database", e);
//         }
//     }
// }

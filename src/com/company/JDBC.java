package com.company;

import java.sql.*;

public class JDBC {

    static final String DB_URL = "jdbc:mariadb://localhost:3306/DB";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, title, author, submission_date FROM tutorials_tbl;";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id  = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                Date submission_date = rs.getDate("submission_date");
                System.out.print("ID: " + id);
                System.out.print(", title: " + title);
                System.out.print(", author: " + author);
                System.out.println(", submission_date: " + submission_date);
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
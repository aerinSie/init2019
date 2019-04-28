package com.jdbc;

import java.sql.*;

public class mysql {
    static final String DB_URL = "jdbc:mysql://localhost:3306/db?allowPublicKeyRetrieval=true&useSSL=false";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT column1, column2 FROM tutorials_tbl;";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String column1 = rs.getString("column1");
                String column2 = rs.getString("column2");
                System.out.print("column1: " + column1);
                System.out.print(", column2: " + column2);
                System.out.println();
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
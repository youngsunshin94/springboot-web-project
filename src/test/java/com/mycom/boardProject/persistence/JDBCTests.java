package com.mycom.boardProject.persistence;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTests {

    @Test
    public void test() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/board_ex?useSSL=false&serverTimezone=Asia/Seoul",
                "root",
                "root")) {
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

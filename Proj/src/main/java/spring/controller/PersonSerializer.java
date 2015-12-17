/*
 * The MIT License
 *
 * Copyright 2015 IS204-5.
 * Application developed for Amsterdam University of Applied Sciences and
 * Gemeente Amsterdam.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package spring.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ryan
 */
public class PersonSerializer {
    
  static final String WRITE_OBJECT_SQL = "INSERT INTO personObject(file) VALUES (?)";
  static final String READ_OBJECT_SQL = "SELECT object_value FROM java_objects WHERE id = ?";

  public static Connection getConnection() throws Exception {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://oege.ie.hva.nl:3306/zshayann001";
    String username = "shayann001";
    String password = "UGZbYm/GX/KM$/";
    Class.forName(driver);
    Connection conn = DriverManager.getConnection(url, username, password);
    return conn;
  }

  public static long writeJavaObject(Connection conn, Object object) throws Exception {
    PreparedStatement pstmt = conn.prepareStatement(WRITE_OBJECT_SQL);

    // set input parameters
    pstmt.setObject(1, object);
    pstmt.executeUpdate();

    // get the generated key for the id
    ResultSet rs = pstmt.getGeneratedKeys();
    int id = -1;
    if (rs.next()) {
      id = rs.getInt(1);
    }
    rs.close();
    pstmt.close();
    System.out.println("writeJavaObject: done serializing:");
    return id;
  }

  public static Object readJavaObject(Connection conn, long id) throws Exception {
    PreparedStatement pstmt = conn.prepareStatement(READ_OBJECT_SQL);
    pstmt.setLong(1, id);
    ResultSet rs = pstmt.executeQuery();
    rs.next();
    Object object = rs.getObject(1);

    rs.close();
    pstmt.close();
    System.out.println("readJavaObject: done de-serializing: ");
    return object;
  } 
}

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
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author chrisverra 
 * Deze class zorgt enkel voor de database connectie
 */
public class DBController {

    public Connection connection;
    PreparedStatement ps;

    /**
     * Opent database connectie
     *
     * @throws Exception
     */
    public void openConnection() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://oege.ie.hva.nl:3306/zshayann001";
        String username = "shayann001";
        String password = "UGZbYm/GX/KM$/";

        //open connection
        connection = DriverManager.getConnection(url, username, password);

    }

    /**
     * Sluit de database connectie
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * voert query uit
     *
     * @param query query die gevraagd moet worden
     * @return
     * @throws Exception
     */
    public ResultSet doQuery(String query) throws Exception {
        ResultSet result = null;
        Statement statement = connection.createStatement();
        result = statement.executeQuery(query);
        return result;
    }
    
    /**
     * haalt alle data uit de database
     * @return
     * @throws SQLException 
     */
    public ResultSet getAllData() throws SQLException {
        ResultSet result = null;
        ps = connection.prepareStatement(null);
        
        String query = "SELECT * FROM personObject";
        result = ps.executeQuery(query);

        return result;
    }
}

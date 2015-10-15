/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Connector;

/**
 *
 * @author Stefan
 */
public class DatabaseController {
    
    Connector dbLink;

    public DatabaseController() {
        this.dbLink = new Connector();
    }
    
}

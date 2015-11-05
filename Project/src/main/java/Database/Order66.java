/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Controller.JsonController;
import Controller.PersonController;
import Model.PersonObject;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Ryan
 */
public class Order66 {
    public static void main (String[]args){
        JsonController JC = new JsonController("recording001_short.json");
        PersonController PC = new PersonController();
        PC.convertJsonToPerson(JC.getList());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        PersonObject_Database db = new PersonObject_Database(sessionFactory);
        for (PersonObject object:PC.getList()){
            db.insertObjects(object.getJsonList());
        }
    }
    
}

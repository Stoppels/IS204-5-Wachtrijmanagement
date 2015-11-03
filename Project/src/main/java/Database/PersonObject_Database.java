/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Model.JsonObject;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Ryan
 */
public class PersonObject_Database {
    private Session session;
    public PersonObject_Database() {
    }
    
    public List<JsonObject> getAll(){
        String hql = "FROM JsonLine ";
        Query query = session.createQuery(hql);
        return query.list();
}
    public int countObjects(){
        String hql = "COUNT (distinct track_id) FROM JsonLine";
        Query query = session.createQuery(hql);
        return query.getFirstResult();
    }
}

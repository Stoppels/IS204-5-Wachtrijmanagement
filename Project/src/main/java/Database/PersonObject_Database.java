/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Model.JsonObject;
import Resources.Position;
import Resources.Timestamp;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Ryan
 */
public class PersonObject_Database {

    private SessionFactory factory;

    public PersonObject_Database(SessionFactory factory) {
        this.factory = factory;
    }

    public List<JsonObject> ObjectsAll() {
        Session session = factory.openSession();
        Transaction ts = null;
        Query query = null;
        List list = null;
        try {
            ts.begin();
            String hql = "FROM JsonLine ";
            query = session.createQuery(hql);
            list = query.list();
        } catch (HibernateException e) {
            ts.rollback();
            System.out.println(e);
        }
        finally {
            ts.commit();
            session.close();
        }
        return list;
    }

    public int countObjects() {
        String hql = "COUNT (distinct track_id) FROM JsonLine";
        Session session = factory.openSession();
        Transaction ts = null;
        Query query = null;
        try {
            ts.begin();
            query = session.createQuery(hql);
            return query.getFirstResult();}
        catch(HibernateException e){
            ts.rollback();
            System.out.println(e);
            }
        finally {
            ts.commit();
            session.close();
        }
        return 0;
    }

    public List<JsonObject> ObjectsTrackId(int criteria) {
        String hql = "FROM JsonLine j WHERE j.track_id = :criteria GROUP BY j.track_id";
        Session session = factory.openSession();
        Transaction ts = null;
        Query query = null;
        try {
            ts.begin();
            query = session.createQuery(hql);
            query.setParameter("criteria", criteria);
            return query.list();}
        catch(HibernateException e){
            ts.rollback();
            System.out.println(e);
                }
        finally{
            ts.commit();
            session.close();
        }
        return null;
    }
    

    public List<JsonObject> ObjectsTimestamp(Timestamp timestamp) {
        String hql = "FROM JsonLine j WHERE j.time_stamp =" + timestamp.toString();
         Session session = factory.openSession();
        Transaction ts = null;
        Query query = null;
        try {
            ts.begin();
            query = session.createQuery(hql);
            return query.list();}
        catch(HibernateException e){
            ts.rollback();
            System.out.println(e);
                }
        finally{
            ts.commit();
            session.close();
        }
        return null;
    }

    public List<JsonObject> ObjectsPosition(Position position) {
        String hql = "FROM JsonLine j WHERE j.position =" + position.toString();
         Session session = factory.openSession();
        Transaction ts = null;
        Query query = null;
        try {
            ts.begin();
            query = session.createQuery(hql);
            return query.list();}
        catch(HibernateException e){
            ts.rollback();
            System.out.println(e);
                }
        finally{
            ts.commit();
            session.close();
        }
        return null;
    }

    public void insertObjects(List<JsonObject> list) {
        Session session = factory.openSession();
        for (JsonObject object: list){
        session.save(object);
    }
}}

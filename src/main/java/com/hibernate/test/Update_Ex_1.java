package com.hibernate.test;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.entity.User;

public class Update_Ex_1 {
 
    public static void main(String[] args) {
    	
    	SessionFactory sessionFactoryObj = null;
    	Session session = null;
    	
        try {
        	
        	// Creating Configuration Instance & Passing Hibernate Configuration File
            Configuration configObj = new Configuration();
            configObj.configure("hibernate.cfg.xml");
            
            configObj.addAnnotatedClass(com.entity.User.class);
     
            // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
            ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
     
            // Creating Hibernate SessionFactory Instance
            sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
            
        	session = sessionFactoryObj.openSession();
            session.beginTransaction();
 
           
            String quer = "update User set user_name= 'Y' where id=1012";
            Query q1=session.createQuery(quer);  
            int status = q1.executeUpdate(); 
            if(status != 0) {
            	System.out.println("Data Found and updated");
            }
            else {
            	System.out.println("Where condition didn't found any records");
            }
            
            Query q=session.createQuery("update User set user_name=:name where id=:id");  
            q.setParameter("name","ABCD");  
            q.setParameter("id",1015);  
              
            int status1=q.executeUpdate();  
            System.out.println(status1);  
            
            
            
            // Committing The Transactions To The Database
            session.getTransaction().commit();
        } catch(Exception sqlException) {
        	System.out.println("Exception :" + sqlException);
        	
            if(null != session && null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
    
}

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

public class Insert_Ex_1 {
 
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
            
            /**
             * User user = new User();
            user.setUser_id(1015);
            user.setUser_name("ABC");
            user.setUser_email("anc@gmail.com");
            user.setCreated_date(new Date());
           
            sessionObj.save(user);
             */
 
            //session.create
            /*
            Query q=session.createQuery("insert into User values(:id, :name, :email, :date)");  
            q.setParameter("name","XYZ");  
            q.setParameter("id",1011);
            q.setParameter("email","bsa@gmal.com");
            q.setParameter("emdateail",new Date());
            */
            
            Query q=session.createQuery("insert into User(user_id, user_name, user_email,created_date) values (1021,'ABC','email',"+new Date()+")");
              
            int status=q.executeUpdate();  
            System.out.println(status);  
            
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

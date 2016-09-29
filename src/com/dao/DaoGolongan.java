package com.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.model.Golongan;

public class DaoGolongan {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public DaoGolongan() {
		try {
			//factory = new Configuration().configure().buildSessionFactory();
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory((org.hibernate.service.ServiceRegistry) serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
		}
	}
	
	public List<Golongan> daftarGolongan(){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Golongan> res = new ArrayList<Golongan>();
		try {
			tx = session.beginTransaction();
			List gol = session.createQuery("FROM Golongan").list();
			for (Iterator iterator = gol.iterator(); iterator.hasNext();){
				res.add((Golongan) iterator.next());
			}
		} catch (HibernateException e) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}
	
	public static void main(String[] args) {
		for (Golongan gol : new DaoGolongan().daftarGolongan()) {
			System.out.println(gol.getIdgolongan()+" "+gol.getNama_golongan()+" "+gol.getGaji());
		}
	}
}

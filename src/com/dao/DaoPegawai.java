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

import com.app.ManagePegawai;
import com.model.Golongan;
import com.model.Pegawai;

public class DaoPegawai {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public DaoPegawai() {
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
	
	public int tambahPegawai(Pegawai pegawai){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int res=0;
		try {
			tx = session.beginTransaction();
			Golongan gol = (Golongan) session.get(Golongan.class, pegawai.getGolongan().getIdgolongan());
			pegawai.setGolongan(gol);
			res = (int) session.save(pegawai);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}
	
	public int editPegawai(Pegawai pegawai){
		Session session = sessionFactory.openSession();
		Transaction tx = null;		
		int res=0;
		try {
			tx = session.beginTransaction();
			Pegawai data = (Pegawai) session.get(Pegawai.class, pegawai.getIdpegawai());
			data.setNama(pegawai.getNama());
			data.setAlamat(pegawai.getAlamat());
			data.setJenis_kelamin(pegawai.getJenis_kelamin());
			Golongan gol = (Golongan) session.get(Golongan.class, pegawai.getGolongan().getIdgolongan());
			data.setGolongan(gol);
			session.update(data);
			tx.commit();
			res = 1;
		} catch (HibernateException e) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}
	
	public int hapusPegawai(int idpegawai){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int res=0;
		try {
			tx = session.beginTransaction();
			Pegawai data = (Pegawai) session.get(Pegawai.class, idpegawai);
			session.delete(data);
			tx.commit();
			res=1;
		} catch (HibernateException e) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}
		return res;
	}
	
	public List<Pegawai> ambilDaftarPegawai( ){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Pegawai> res = new ArrayList<Pegawai>();
		try{
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM Pegawai").list(); 
			for (Iterator iterator = employees.iterator(); 
			iterator.hasNext();){
				 res.add((Pegawai) iterator.next()); 
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return res;
	}
	
	public Pegawai ambilPegawai(int idpegawai){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Pegawai res = new Pegawai();
		try {
			tx = session.beginTransaction();
			res = (Pegawai) session.get(Pegawai.class, idpegawai);
		} catch (HibernateException e) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}
	
	public static void main(String[] args) {
		Pegawai peg = new Pegawai();
		Golongan gol = new Golongan();
		peg.setNama("zazaza");
		peg.setJenis_kelamin("Perempuan");
		peg.setAlamat("asasasasas");
		peg.setFoto("X");
		gol.setIdgolongan(5);
		peg.setGolongan(gol);
		int x = new DaoPegawai().tambahPegawai(peg);
	}
}

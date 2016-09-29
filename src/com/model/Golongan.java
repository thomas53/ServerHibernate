package com.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_golongan")
public class Golongan implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="idgolongan")
	private int idgolongan;
	
	@Column(name="nama_golongan")
	private String nama_golongan;
	
	@Column(name="gaji")
	private int gaji;
	
	@OneToMany(mappedBy="golongan")
	private Set<Pegawai> pegawai;
	
	public Golongan(){}

	public Golongan(String nama_golongan, int gaji, Set<Pegawai> pegawai) {
		super();
		this.nama_golongan = nama_golongan;
		this.gaji = gaji;
		this.pegawai = pegawai;
	}

	public int getIdgolongan() {
		return idgolongan;
	}

	public void setIdgolongan(int idgolongan) {
		this.idgolongan = idgolongan;
	}

	public String getNama_golongan() {
		return nama_golongan;
	}

	public void setNama_golongan(String nama_golongan) {
		this.nama_golongan = nama_golongan;
	}

	public int getGaji() {
		return gaji;
	}

	public void setGaji(int gaji) {
		this.gaji = gaji;
	}

	public Set<Pegawai> getPegawai() {
		return pegawai;
	}

	public void setPegawai(Set<Pegawai> pegawai) {
		this.pegawai = pegawai;
	}	
	
	
}

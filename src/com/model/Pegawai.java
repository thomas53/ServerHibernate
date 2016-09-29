package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_pegawai")
public class Pegawai implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="idpegawai")
	private int idpegawai;
	
	@Column(name="nama")
	private String nama;
	
	@Column(name="jenis_kelamin")
	private String jenis_kelamin;
	
	@Column(name="alamat")
	private String alamat;
	
	@Column(name="foto")
	private String foto;
	
	@ManyToOne
	@JoinColumn(name="idgolongan")
	private Golongan golongan;
	
	public Pegawai(){}

	public Pegawai(String nama, String jenis_kelamin, String alamat, String foto) {
		this.nama = nama;
		this.jenis_kelamin = jenis_kelamin;
		this.alamat = alamat;
		this.foto = foto;
	}

	public int getIdpegawai() {
		return idpegawai;
	}

	public void setIdpegawai(int idpegawai) {
		this.idpegawai = idpegawai;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getJenis_kelamin() {
		return jenis_kelamin;
	}

	public void setJenis_kelamin(String jenis_kelamin) {
		this.jenis_kelamin = jenis_kelamin;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Golongan getGolongan() {
		return golongan;
	}

	public void setGolongan(Golongan golongan) {
		this.golongan = golongan;
	}
	
	
}

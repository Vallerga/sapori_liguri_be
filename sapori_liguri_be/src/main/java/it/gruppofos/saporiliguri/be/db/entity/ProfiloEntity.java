package it.gruppofos.saporiliguri.be.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "Profilo")
@Table(name = "Profilo",
uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class ProfiloEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id", nullable = false, unique = true, length = 11)
	private Integer id;
	
	@Column(name="email", nullable = true, unique = true, length = 50)
	private String email;
	
	@Column(name="password", length = 50, nullable = true)
	private String password;
	
	@Column(name="nazionalita", length = 20, nullable = true)
	private String nazionalita;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
}

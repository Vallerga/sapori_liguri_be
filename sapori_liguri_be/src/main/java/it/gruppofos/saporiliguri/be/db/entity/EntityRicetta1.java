package it.gruppofos.saporiliguri.be.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "Ricetta1")
@Table(name = "Ricetta1", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class EntityRicetta1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true, length = 11)
	private Integer id;

	@Column(name = "indice", length = 40, nullable = true, unique = true)
	private Integer indice;

	@Column(name = "ingrediente", length = 100, nullable = true)
	private String ingrediente;

	@Column(name = "prezzo", length = 45, nullable = true)
	private String prezzo;

	@Column(name = "quantita", length = 45, nullable = true)
	private String quantita;

	@Column(name = "descrizione", length = 1000, nullable = true)
	private String descrizione;

	@Column(name = "imgurl", length = 400, nullable = true)
	private String imgUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public String getQuantita() {
		return quantita;
	}

	public void setQuantita(String quantita) {
		this.quantita = quantita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "EntityRicetta [id=" + id + ", indice=" + indice + ", ingrediente=" + ingrediente + ", prezzo=" + prezzo
				+ ", quantita=" + quantita + ", descrizione=" + descrizione + ", imgUrl=" + imgUrl + "]";
	}
}

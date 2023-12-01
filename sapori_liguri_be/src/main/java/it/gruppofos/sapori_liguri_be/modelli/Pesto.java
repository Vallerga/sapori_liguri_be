package it.gruppofos.sapori_liguri_be.modelli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "Pesto")
@Table(name = "Pesto",
uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class Pesto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "ID", nullable = false, unique = true, length = 11)
	private Integer id;
	
	@Column (name = "indice",length = 40, nullable = true)
	private Integer indice;
	
	@Column(name="ingrediente", length = 100, nullable = true)
	private String ingrediente;
	
	@Column(name="prezzo", length = 40, nullable = true)
	private String prezzo;
	
	@Column(name="quantita", length = 40, nullable = true)
	private String quantita;
	
	@Column(name="descrizione", length = 1000, nullable = true)
	private String descrizione;
	
	@Column(name="img", length = 100, nullable = true)
	private String img;

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}

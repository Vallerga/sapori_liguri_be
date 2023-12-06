package it.gruppofos.saporiliguri.be.db.entity;

public class EntityRicetta1Builder {
	private Integer id;
	private Integer indice;
	private String ingrediente;
	private String prezzo;
	private String quantita;
	private String descrizione;
	private String imgUrl;
	
	public EntityRicetta1Builder setId(Integer id) {
		this.id = id;
		return this;
	}
	
	public EntityRicetta1Builder setIndice(Integer indice) {
		this.indice = indice;
		return this;
	}

	public EntityRicetta1Builder setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
		return this;
	}

	public EntityRicetta1Builder setPrezzo(String prezzo) {
		this.prezzo = prezzo;
		return this;
	}

	public EntityRicetta1Builder setQuantita(String quantita) {
		this.quantita = quantita;
		return this;
	}

	public EntityRicetta1Builder setDescrizione(String descrizione) {
		this.descrizione = descrizione;
		return this;
	}

	public EntityRicetta1Builder setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
		return this;
	}

	public EntityRicetta1 build() {
		return new EntityRicetta1(id, indice, ingrediente, prezzo, quantita, descrizione, imgUrl);
	}
}

package it.gruppofos.saporiliguri.be.presentation.model;

public class PojoRicettaBuilder {
	private Integer indice;
	private String ingrediente;
	private String prezzo;
	private String quantita;
	private String descrizione;
	private String imgUrl;
	
	public PojoRicettaBuilder setIndice(Integer indice) {
		this.indice = indice;
		return this;
	}

	public PojoRicettaBuilder setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
		return this;
	}

	public PojoRicettaBuilder setPrezzo(String prezzo) {
		this.prezzo = prezzo;
		return this;
	}

	public PojoRicettaBuilder setQuantita(String quantita) {
		this.quantita = quantita;
		return this;
	}

	public PojoRicettaBuilder setDescrizione(String descrizione) {
		this.descrizione = descrizione;
		return this;
	}

	public PojoRicettaBuilder setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
		return this;
	}

	public PojoRicetta build() {
		PojoRicetta newPojo =  new PojoRicetta();
		
		newPojo.setIndice(indice);
		newPojo.setIngrediente(ingrediente);
		newPojo.setPrezzo(prezzo);
		newPojo.setQuantita(quantita);
		newPojo.setDescrizione(descrizione);
		newPojo.setImgUrl(imgUrl);
		
		return newPojo;
	}
}
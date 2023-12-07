package it.gruppofos.saporiliguri.be.presentation.model;

public class PojoRicetta {
	private Integer indice;
	private String ingrediente;
	private String prezzo;
	private String quantita;
	private String descrizione;
	private String imgUrl;

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
}
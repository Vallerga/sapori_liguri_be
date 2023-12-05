package it.gruppofos.saporiliguri.be.presentation.model;

public class PestoModel {

	private Integer indice;
	private String ingrediente;
	private String prezzo;
	private String quantita;
	private String descrizione;
	private String imgUrl;
	
	public Integer getIndice() {
		return indice;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public String getQuantita() {
		return quantita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public PestoModel() {}
	
	private PestoModel(PestoModelBuilder builder) {
		this.indice = builder.indice;
		this.ingrediente = builder.ingrediente;
		this.prezzo = builder.prezzo;
		this.quantita = builder.quantita;
		this.descrizione = builder.descrizione;
		this.imgUrl = builder.imgUrl;
	}

	// Builder class
	public static class PestoModelBuilder {
		private Integer indice;
		private String ingrediente;
		private String prezzo;
		private String quantita;
		private String descrizione;
		private String imgUrl;

		public PestoModelBuilder setIndice(Integer indice) {
			this.indice = indice;
			return this;
		}

		public PestoModelBuilder setIngrediente(String ingrediente) {
			this.ingrediente = ingrediente;
			return this;
		}

		public PestoModelBuilder setPrezzo(String prezzo) {
			this.prezzo = prezzo;
			return this;
		}

		public PestoModelBuilder setQuantita(String quantita) {
			this.quantita = quantita;
			return this;
		}

		public PestoModelBuilder setDescrizione(String descrizione) {
			this.descrizione = descrizione;
			return this;
		}

		public PestoModelBuilder setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
			return this;
		}

		public PestoModel build() {
			return new PestoModel(this);
		}
	}
}
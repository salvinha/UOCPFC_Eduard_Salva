package edu.uoc.pfc2012.edusalva.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class KoncepteParaula {
	private String id;
	private String idLlista;
	
	private String textcat;
	private String textjap;
	
	@JsonIgnore
	private String proncat;
	
	
	private String pronjap;

	@JsonIgnore
	private String audioCatala;
	
	@JsonIgnore
	private String audioJapones;
	
	public KoncepteParaula() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdLlista() {
		return idLlista;
	}
	
	public void setIdLlista(String idLlista) {
		this.idLlista = idLlista;
	}
	
	public String getTextcat() {
		return textcat;
	}

	public void setTextcat(String textcat) {
		this.textcat = textcat;
	}
	
	public String getTextjap() {
		return textjap;
	}

	public void setTextjap(String textJapones) {
		this.textjap = textJapones;
	}
	
	public String getPronjap() {
		return this.pronjap;
	}
	
	
	public void setPronjap(String pronjap) {
		this.pronjap = pronjap;
	}
	

	public String getAudioCatala() {
		return audioCatala;
	}

	public void setAudioCatala(String audioCatala) {
		this.audioCatala = audioCatala;
	}

	public String getAudioJapones() {
		return audioJapones;
	}

	public void setAudioJapones(String audioJapones) {
		this.audioJapones = audioJapones;
	}

	@Override
	public String toString() {
		return "KoncepteParaula [textCatala=" + textcat + ", textJapones="
				+ textjap + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((textcat == null) ? 0 : textcat.hashCode());
		result = prime * result
				+ ((textjap == null) ? 0 : textjap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KoncepteParaula other = (KoncepteParaula) obj;
		if (textcat == null) {
			if (other.textcat != null)
				return false;
		} else if (!textcat.equals(other.textcat))
			return false;
		if (textjap == null) {
			if (other.textjap != null)
				return false;
		} else if (!textjap.equals(other.textjap))
			return false;
		return true;
	}

	public void setProncat(String proncat) {
		this.proncat = proncat;
	}

	public String getProncat() {
		return proncat;
	}
}

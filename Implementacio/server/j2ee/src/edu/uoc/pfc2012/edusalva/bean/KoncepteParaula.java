package edu.uoc.pfc2012.edusalva.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class KoncepteParaula {
	private String id;

	private String textCatala;
	private String textJapones;
	private String audioCatala;
	private String audioJapones;
	
	public KoncepteParaula() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTextCatala() {
		return textCatala;
	}

	public void setTextCatala(String textCatala) {
		this.textCatala = textCatala;
	}

	public String getTextJapones() {
		return textJapones;
	}

	public void setTextJapones(String textJapones) {
		this.textJapones = textJapones;
	}
	
	
}

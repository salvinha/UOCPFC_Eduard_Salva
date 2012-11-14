package edu.uoc.pfc2012.edusalva.bean;

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
		return "KoncepteParaula [textCatala=" + textCatala + ", textJapones="
				+ textJapones + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((textCatala == null) ? 0 : textCatala.hashCode());
		result = prime * result
				+ ((textJapones == null) ? 0 : textJapones.hashCode());
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
		if (textCatala == null) {
			if (other.textCatala != null)
				return false;
		} else if (!textCatala.equals(other.textCatala))
			return false;
		if (textJapones == null) {
			if (other.textJapones != null)
				return false;
		} else if (!textJapones.equals(other.textJapones))
			return false;
		return true;
	}
	
	
}

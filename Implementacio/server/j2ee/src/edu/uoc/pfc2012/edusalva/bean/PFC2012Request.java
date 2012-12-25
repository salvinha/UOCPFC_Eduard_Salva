package edu.uoc.pfc2012.edusalva.bean;

import java.util.HashSet;
import java.util.Set;

import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

public class PFC2012Request {

	private String path;
	private Set<String> mandatory;
	private Set<String> optional;
	private int minimumOptional;
	
	public PFC2012Request(String path) {
		this.path = path;
		this.mandatory = new HashSet<String>();
		this.optional = new HashSet<String>();
		setupParameters();
	}

	private void setupParameters() {
		if (PFCConstants.PATH_CREATE_CONCEPTE_PARAULA.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_TEXT_CA);
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_TEXT_JP);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP);
			setMinimumOptional(0);
		} else if (PFCConstants.PATH_EDIT_CONCEPTE_PARAULA.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_ID);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_TEXT_CA);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_TEXT_JP);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP);
			setMinimumOptional(1);
		} else if (PFCConstants.PATH_SEARCH_CONCEPTE_PARAULA.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_TEXT_SEARCH);
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_IDIOMA);
			setMinimumOptional(0);
		} else if (PFCConstants.PATH_GET_CONCEPTE_PARAULA.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_ID);
			setMinimumOptional(0);
		} else if (PFCConstants.PATH_GET_SOUND.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_ID);
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_IDIOMA);
			setMinimumOptional(0);
		} else if (PFCConstants.PATH_LIST_WORDS.equals(getPath())) {
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_MAX_RESULTS);
			setMinimumOptional(0);
		}
		
		optional.add("false");
		optional.add("_dc");
		optional.add("callback");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		PFC2012Request other = (PFC2012Request) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PFC2012Request [path=" + path + ", mandatory=" + mandatory
				+ ", optional=" + optional + "]";
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set<String> getMandatory() {
		return mandatory;
	}

	public void setMandatory(Set<String> mandatory) {
		this.mandatory = mandatory;
	}

	public Set<String> getOptional() {
		return optional;
	}

	public void setOptional(Set<String> optional) {
		this.optional = optional;
	}
	
	public int getMinimumOptional() {
		return minimumOptional;
	}

	public void setMinimumOptional(int minimumOptional) {
		this.minimumOptional = minimumOptional;
	}
}

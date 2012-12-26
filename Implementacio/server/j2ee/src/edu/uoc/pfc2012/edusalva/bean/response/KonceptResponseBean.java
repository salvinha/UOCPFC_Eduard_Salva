package edu.uoc.pfc2012.edusalva.bean.response;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;

public class KonceptResponseBean extends ResponseBean {

	private KoncepteParaula koncept;
	
	public KonceptResponseBean(KoncepteParaula koncept) {
		super();
		setSuccess(true);
		this.koncept = koncept;
	}

	public KoncepteParaula getKoncept() {
		return koncept;
	}

}

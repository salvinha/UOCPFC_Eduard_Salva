package edu.uoc.pfc2012.edusalva.bean.response;

import java.util.List;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;

public class WordListResponseBean extends ResponseBean {

	private List<KoncepteParaula> list;
	
	public WordListResponseBean(List<KoncepteParaula> list) {
		this.list = list;
		setSuccess(true);
	}

	public List<KoncepteParaula> getList() {
		return list;
	}
}

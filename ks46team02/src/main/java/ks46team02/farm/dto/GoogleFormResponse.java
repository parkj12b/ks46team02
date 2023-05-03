package ks46team02.farm.dto;

import java.util.List;

public class GoogleFormResponse {
	private String form_id;
	private String form_title;
	private List<GoogleFormResult> results;
	public String getForm_id() {
		return form_id;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}
	public String getForm_title() {
		return form_title;
	}
	public void setForm_title(String form_title) {
		this.form_title = form_title;
	}
	public List<GoogleFormResult> getResults() {
		return results;
	}
	public void setResults(List<GoogleFormResult> results) {
		this.results = results;
	}
	@Override
	public String toString() {
		return "GoogleFormResponse [form_id=" + form_id + ", form_title=" + form_title + ", results=" + results + "]";
	}
	
	
	
	
}

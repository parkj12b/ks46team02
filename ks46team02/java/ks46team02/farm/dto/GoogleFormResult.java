package ks46team02.farm.dto;

public class GoogleFormResult {
	
	public GoogleFormResult(String type, String title, String response) {
        this.type = type;
        this.title = title;
        this.response = response;
    }
	
	private String type;
	private String title;
	private String response;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "GoogleFormResult [type=" + type + ", title=" + title + ", response=" + response + "]";
	}
	
	
}

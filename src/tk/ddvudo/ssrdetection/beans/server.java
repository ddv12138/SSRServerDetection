package tk.ddvudo.ssrdetection.beans;

public class server {
	String id = "";
	String ratio = "";
	String remarks = "";
	String server = "";
	public server(String id, String ratio, String remarks, String server) {
		super();
		this.id = id;
		this.ratio = ratio;
		this.remarks = remarks;
		this.server = server;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	
}

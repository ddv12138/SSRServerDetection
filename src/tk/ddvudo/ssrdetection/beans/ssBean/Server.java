package tk.ddvudo.ssrdetection.beans.ssBean;

public class Server {
	String id = "";
	String ratio = "";
	String remarks = "";
	String server = "";
	public Server(String id, String ratio, String remarks, String server) {
		super();
		this.id = id;
		this.ratio = ratio;
		this.remarks = remarks;
		this.server = server.trim();
	}
	public String getId() {
		return id;
	}
	public String getRatio() {
		return ratio;
	}
	public String getRemarks() {
		return remarks;
	}
	public String getServer() {
		return server;
	}
	@Override
	public String toString() {
		return "server [id=" + id + ", ratio=" + ratio + ", remarks=" + remarks + ", server=" + server + "]";
	}
	
}

package tk.ddvudo.ssrdetection.beans.ssBean;

import tk.ddvudo.ssrdetection.beans.Server;

public class SSServer extends Server{
	String id = "";
	String ratio = "";
	public SSServer(String id, String ratio, String remarks, String server) {
		super();
		this.id = id;
		this.ratio = ratio;
		this.remarks = remarks.trim();
		this.server = server;
	}
	public String getId() {
		return id;
	}
	public String getRatio() {
		return ratio;
	}
	@Override
	public String getServer() {
		return server;
	}
	@Override
	public String toString() {
		return "server [id=" + id + ", ratio=" + ratio + ", remarks=" + remarks + ", server=" + server + "]";
	}
	@Override
	public String getRemarks() {
		return this.remarks;
	}
	
}

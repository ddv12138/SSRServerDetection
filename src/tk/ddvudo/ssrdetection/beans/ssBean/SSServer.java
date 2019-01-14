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
	public void setId(String id) {
		this.id = id;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public String getPort() {
		return this.port;
	}
	public void setPort(String port) {
		 this.port = port;
	}
	public String getMethod() {
		return this.method;
	}
	public void setMethod(String method) {
		 this.method = method;
	}
	@Override
	public String getServer() {
		return server;
	}
	@Override
	public String getRemarks() {
		return this.remarks;
	}
	@Override
	public String toString() {
		return "server [id=" + id + ", ratio=" + ratio + ", remarks=" + remarks + ", server=" + server + "]";
	}
	@Override
	public void setServer(String server) {
		this.server = server;
	}
	@Override
	public String getPasswd() {
		return this.passwd;
	}
	@Override
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@Override
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}

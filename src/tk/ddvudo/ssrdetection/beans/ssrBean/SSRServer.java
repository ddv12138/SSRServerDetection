package tk.ddvudo.ssrdetection.beans.ssrBean;

import tk.ddvudo.ssrdetection.beans.Server;

public class SSRServer extends Server{
	String obfsparam = null;
	String protoparam = null;
	String group = null;
	private String protocol  = null;
	private String obfs  = null;
	
	@Override
	public String toString() {
		return "SSRServer [group=" + group + ", server=" + server + ", remarks=" + remarks + "]";
	}

	public SSRServer(String server,String port,String method,String passwd,String protocol,String obfs,
			String obfsparam, String protoparam, String remarks,String group) {
		this.server = server;
		this.port = port;
		this.method = method;
		this.passwd = passwd;
		this.protocol = protocol;
		this.obfs = obfs;
		this.obfsparam = obfsparam;
		this.protoparam = protoparam;
		this.remarks = remarks.trim();
		this.obfsparam = obfsparam;
		this.group = group;
	}
	
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getObfs() {
		return obfs;
	}
	public void setObfs(String obfs) {
		this.obfs = obfs;
	}
	@Override
	public String getServer() {
		return this.server;
	}
	@Override
	public String getRemarks() {
		return this.remarks;
	}
	@Override
	public void setServer(String server) {
		this.server = server;
	}
	@Override
	public String getPort() {
		return this.port;
	}
	@Override
	public void setPort(String port) {
		this.port = port;
	}
	@Override
	public String getMethod() {
		return this.method;
	}
	@Override
	public void setMethod(String method) {
		this.method = method;
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
	public String getObfsparam() {
		return obfsparam;
	}
	public void setObfsparam(String obfsparam) {
		this.obfsparam = obfsparam;
	}
	public String getProtoparam() {
		return protoparam;
	}
	public void setProtoparam(String protoparam) {
		this.protoparam = protoparam;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
}

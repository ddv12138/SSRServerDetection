package tk.ddvudo.ssrdetection.beans.ssrBean;

import tk.ddvudo.ssrdetection.beans.Server;

public class SSRServer extends Server{
	String obfsparam = null;
	String protoparam = null;
	String group = null;
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

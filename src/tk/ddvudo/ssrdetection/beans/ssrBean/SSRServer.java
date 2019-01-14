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
}

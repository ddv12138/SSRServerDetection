package tk.ddvudo.ssrdetection.beans.ssrBean;

import java.util.ArrayList;
import java.util.List;

import tk.ddvudo.ssrdetection.beans.Airport;
import tk.ddvudo.ssrdetection.beans.Server;

public class SSRAirport extends Airport {

	private List<SSRServer> servers;

	@Override
	public String getGroup() {
		return this.group;
	}

	@Override
	public void setGroup(String group) {
		this.group = group;
	}

	public SSRServer[] getServers() {
		return this.servers.toArray(new SSRServer[this.servers.size()]);
	}

	public void setServers(List<SSRServer> servers) {
		this.servers = servers;
	}

}

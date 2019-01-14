package tk.ddvudo.ssrdetection.beans.ssBean;

import java.util.List;

public class SSAirport {
	String group = "";
	String method = "";
	String expiry = "";
	String password = "";
	String port = "";
	String traffic_total = "";
	String traffic_used = "";
	String url = "";
	List<SSServer> servers = null;
	public SSAirport(String airport, String encryption, String expiry, String password, String port,
			String traffic_total, String traffic_used, String url, List<SSServer> servers) {
		super();
		this.group = airport;
		this.method = encryption;
		this.expiry = expiry;
		this.password = password;
		this.port = port;
		this.traffic_total = traffic_total;
		this.traffic_used = traffic_used;
		this.url = url;
		this.servers = servers;
		System.out.println("一共抓取到"+this.servers.size()+"个服务器");
	}
	public String getGroup() {
		return group;
	}
	public String getMethod() {
		return method;
	}
	public String getExpiry() {
		return expiry;
	}
	public String getPassword() {
		return password;
	}
	public String getPort() {
		return port;
	}
	public String getTraffic_total() {
		return traffic_total;
	}
	public String getTraffic_used() {
		return traffic_used;
	}
	public String getUrl() {
		return url;
	}
	public List<SSServer> getServers() {
		return servers;
	}
	
}

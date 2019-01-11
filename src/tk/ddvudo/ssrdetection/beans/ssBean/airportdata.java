package tk.ddvudo.ssrdetection.beans.ssBean;

import java.util.List;

public class airportdata {
	String airport = "";
	String encryption	 = "";
	String expiry = "";
	String password = "";
	String port = "";
	String traffic_total = "";
	String traffic_used = "";
	String url = "";
	List<Server> servers = null;
	public airportdata(String airport, String encryption, String expiry, String password, String port,
			String traffic_total, String traffic_used, String url, List<Server> servers) {
		super();
		this.airport = airport;
		this.encryption = encryption;
		this.expiry = expiry;
		this.password = password;
		this.port = port;
		this.traffic_total = traffic_total;
		this.traffic_used = traffic_used;
		this.url = url;
		this.servers = servers;
		System.out.println("一共抓取到"+this.servers.size()+"个服务器");
	}
	public String getAirport() {
		return airport;
	}
	public String getEncryption() {
		return encryption;
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
	public List<Server> getServers() {
		return servers;
	}
	
}

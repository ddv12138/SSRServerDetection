package tk.ddvudo.ssrdetection.beans;

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
	List<server> servers = null;
	public airportdata(String airport, String encryption, String expiry, String password, String port,
			String traffic_total, String traffic_used, String url, List<server> servers) {
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
	}
	public String getAirport() {
		return airport;
	}
	public void setAirport(String airport) {
		this.airport = airport;
	}
	public String getEncryption() {
		return encryption;
	}
	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getTraffic_total() {
		return traffic_total;
	}
	public void setTraffic_total(String traffic_total) {
		this.traffic_total = traffic_total;
	}
	public String getTraffic_used() {
		return traffic_used;
	}
	public void setTraffic_used(String traffic_used) {
		this.traffic_used = traffic_used;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<server> getServers() {
		return servers;
	}
	public void setServers(List<server> servers) {
		this.servers = servers;
	}
	
}

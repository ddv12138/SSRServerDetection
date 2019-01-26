package tk.ddvudo.ssrdetection.beans.ssBean;

import java.util.List;

import tk.ddvudo.ssrdetection.Utils.Global;
import tk.ddvudo.ssrdetection.beans.Airport;

public class SSAirport extends Airport{
	private String group;
	private String method;
	private String expiry;
	private String password;
	private String port;
	private String traffic_total;
	private String traffic_used;
	private String url;
	private List<SSServer> servers;
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
		Global.getInstance().getLogger().info("一共抓取到"+this.servers.size()+"个服务器");
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
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
	public SSServer[] getServers() {
		return this.servers.toArray(new SSServer[0]);
	}
	public void setServers(List<SSServer> servers) {
		this.servers = servers;
	}
}

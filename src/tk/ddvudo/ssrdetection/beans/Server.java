package tk.ddvudo.ssrdetection.beans;

public abstract class Server {
	protected String server = null;
	protected String port = null;
	protected String method = null;
	protected String passwd = null;
	protected String remarks = "";
	public abstract String getServer();
	public abstract String getRemarks();
}

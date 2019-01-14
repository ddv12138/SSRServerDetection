package tk.ddvudo.ssrdetection.beans;

public abstract class Server {
	protected String server = null;
	protected String port = null;
	protected String method = null;
	protected String passwd = null;
	protected String remarks = null;
	public abstract String getServer();
	public abstract void setServer(String server);
	public abstract String getPort();
	public abstract void setPort(String port);
	public abstract String getMethod();
	public abstract void setMethod(String method);
	public abstract String getPasswd();
	public abstract void setPasswd(String passwd);
	public abstract String getRemarks();
	public abstract void setRemarks(String remarks);
	
}

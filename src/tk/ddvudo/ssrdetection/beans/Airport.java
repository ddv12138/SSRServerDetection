package tk.ddvudo.ssrdetection.beans;

public abstract class Airport {
	protected String group = "";
	public abstract String getGroup();
	public abstract void setGroup(String group);
	public abstract Server[] getServers();
	
}

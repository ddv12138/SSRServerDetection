package tk.ddvudo.ssrdetection.beans;

import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;

public class Result {
	private PingResult pingResult;
	private Server server;
	public Result(PingResult result, Server s) {
		this.pingResult = result;
		this.server = s;
	}
	public PingResult getResult() {
		return pingResult;
	}
	public Server getServer() {
		return server;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"pingResult\":")
				.append(pingResult);
		sb.append(",\"server\":")
				.append(server);
		sb.append('}');
		return sb.toString();
	}
}

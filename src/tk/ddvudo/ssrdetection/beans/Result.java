package tk.ddvudo.ssrdetection.beans;

import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;

public class Result {
	private final PingResult result;
	private final Server server;
	public Result(PingResult result, Server s) {
		super();
		this.result = result;
		this.server = s;
	}
	public PingResult getResult() {
		return result;
	}
	public Server getServer() {
		return server;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"result\":")
				.append(result);
		sb.append(",\"server\":")
				.append(server);
		sb.append('}');
		return sb.toString();
	}

}

package tk.ddvudo.ssrdetection.beans.ssBean;

import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;

public class ssResult {
	PingResult result = null;
	Server server = null;
	public ssResult(PingResult result, Server server) {
		super();
		this.result = result;
		this.server = server;
	}
	public PingResult getResult() {
		return result;
	}
	public Server getServer() {
		return server;
	}
	@Override
	public String toString() {
		return "测试结果: [节点名称<" + server.getRemarks() + ">, 平均延迟=" + result.rtt_avg() + "ms]";
	}
	
}

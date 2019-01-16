package tk.ddvudo.ssrdetection.beans;

import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;

public class Result {
	PingResult result = null;
	Server server = null;
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
		return "测试结果: [节点名称<" + server.getRemarks() + ">, 平均延迟=" + result.rtt_avg() + "ms]";
	}
	
}

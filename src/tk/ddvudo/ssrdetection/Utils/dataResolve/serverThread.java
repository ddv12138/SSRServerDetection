package tk.ddvudo.ssrdetection.Utils.dataResolve;

import java.util.List;
import java.util.concurrent.Callable;

import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingArguments;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping.Backend;
import tk.ddvudo.ssrdetection.beans.Server;

public class serverThread implements Callable<Object> {
	
	private List<Server> servers = null;
	
	public serverThread(List<Server> servers) {
		this.servers = servers;
	}

	@Override
	public Object call() {
		for(Server s : servers) {
			PingArguments arguments = new PingArguments.Builder().url(s.getServer()).timeout(500).count(2).bytes(32).build();
			PingResult results = Ping.ping(arguments, Backend.WINDOWS_zhCN);
			if(results.rtt_avg()>0) {
				System.out.println(s.getRemarks()+"测试结果"+results.rtt_avg()+"ms");
			}
		}
		return servers;
	}

}

package tk.ddvudo.ssrdetection.Utils.dataResolve;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingArguments;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping.Backend;
import tk.ddvudo.ssrdetection.beans.ssBean.SSServer;
import tk.ddvudo.ssrdetection.beans.ssBean.ssResult;

public class serverThread implements Callable<ArrayList<ssResult>> {
	
	private List<SSServer> servers = null;
	
	public serverThread(List<SSServer> servers) {
		this.servers = servers;
	}

	@Override
	public ArrayList<ssResult> call() {
		ArrayList<ssResult> resarr = new ArrayList<>();
		for(SSServer s : servers) {
			PingArguments arguments = new PingArguments.Builder().url(s.getServer()).timeout(500).count(2).bytes(32).build();
			PingResult results = Ping.ping(arguments, Backend.WINDOWS_zhCN);
			if(results.rtt_avg()>0) {
				ssResult res = new ssResult(results, s);
				resarr.add(res);
			}
		}
		return resarr;
	}

}

package tk.ddvudo.ssrdetection.Utils.dataResolve;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingArguments;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping.Backend;
import tk.ddvudo.ssrdetection.beans.Result;
import tk.ddvudo.ssrdetection.beans.Server;

public class serverThread implements Callable<ArrayList<Result>> {
	
	private List<Server> servers = null;
	
	public serverThread(List<Server> tmpList) {
		this.servers = tmpList;
	}

	@Override
	public ArrayList<Result> call() {
		ArrayList<Result> resarr = new ArrayList<>();
		for(Server s : servers) {
			PingArguments arguments = new PingArguments.Builder().url(s.getServer()).timeout(50).count(1).bytes(32).build();
			PingResult results = Ping.ping(arguments, Backend.WINDOWS_zhCN);
			if(results.rtt_avg()>0) {
				Result res = new Result(results, s);
				resarr.add(res);
			}
		}
		return resarr;
	}

}

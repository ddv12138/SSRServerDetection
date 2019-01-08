package tk.ddvudo.ssrdetection.Main;

import java.net.URLConnection;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLConnHandler;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLIOHandler;
import tk.ddvudo.ssrdetection.Utils.dataResolve.DataResolve;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingArguments;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping.Backend;
import tk.ddvudo.ssrdetection.beans.Server;
import tk.ddvudo.ssrdetection.beans.airportdata;

public class Main {

	public static void main(String[] args) {
		URLIOHandler iohandler = null;
		try {
			iohandler = URLIOHandler.getInstance();
			String linkurl = iohandler.getInputUrl();
			if(null == linkurl || linkurl.equals("")) {
				System.out.println("链接为空，退出");
			}
			URLConnection con = URLConnHandler.getInstance(linkurl).getConnection();
			airportdata data = DataResolve.getInstance().decode(iohandler.getResponseContent(con));
			for(Server s : data.getServers()) {
				System.out.println(">>>>>>开始测试"+s.getId()+"----->"+s.getRemarks());
				PingArguments arguments = new PingArguments.Builder().url(s.getServer()).timeout(500).count(2).bytes(32).build();
				PingResult results = Ping.ping(arguments, Backend.WINDOWS_zhCN);
				if(results.rtt_avg()>0) {
					System.out.println(s.getRemarks()+"-------测试结果"+results.rtt_avg()+"ms");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package tk.ddvudo.ssrdetection.Main;

import java.net.URLConnection;

import org.apache.commons.lang.StringUtils;

import tk.ddvudo.ssrdetection.Utils.URLHandler.URLConnHandler;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLIOHandler;
import tk.ddvudo.ssrdetection.Utils.dataResolve.DataResolve;
import tk.ddvudo.ssrdetection.beans.Server;
import tk.ddvudo.ssrdetection.beans.airportdata;

public class Main {

	public static void main(String[] args) {
		URLIOHandler iohandler = null;
		try {
			iohandler = URLIOHandler.getInstance();
			String linkurl = iohandler.getInputUrl();
			if(StringUtils.isEmpty(linkurl)) {
				System.out.println("链接为空，退出");
				return;
			}
			URLConnection con = URLConnHandler.getInstance(linkurl).getConnection();
			airportdata data = DataResolve.getInstance().ssDecode(iohandler.getResponseContent(con));
			DataResolve.getInstance().serverPingTestMultiThread(data.getServers());
			DataResolve.getInstance().serverPingTestSingleThread(data.getServers().toArray(new Server[data.getServers().size()]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

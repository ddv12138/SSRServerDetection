package tk.ddvudo.ssrdetection.Main;

import java.net.URLConnection;

import org.apache.commons.lang.StringUtils;

import tk.ddvudo.ssrdetection.Utils.URLHandler.URLConnHandler;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLIOHandler;
import tk.ddvudo.ssrdetection.Utils.dataResolve.DataResolve;
import tk.ddvudo.ssrdetection.beans.ssBean.airportdata;

public class Main {

	public static void main(String[] args) {
		URLIOHandler iohandler = null;
		DataResolve dr = null;
		try {
			dr = DataResolve.getInstance();
			iohandler = URLIOHandler.getInstance();
			String linkurl = iohandler.getInputUrl();
			if(StringUtils.isEmpty(linkurl)) {
				System.out.println("链接为空，退出");
				return;
			}
			URLConnection con = URLConnHandler.getInstance(linkurl).getConnection();
			airportdata data = dr.ssDecode(iohandler.getResponseContent(con));
			dr.serverPingTestMultiThread(data.getServers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

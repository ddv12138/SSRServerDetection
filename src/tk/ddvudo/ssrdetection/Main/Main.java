package tk.ddvudo.ssrdetection.Main;

import java.io.IOException;
import java.net.URLConnection;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLConnHandler;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLIOHandler;
import tk.ddvudo.ssrdetection.Utils.dataResolve.DataResolve;
import tk.ddvudo.ssrdetection.beans.airportdata;

public class Main {

	public static void main(String[] args) {
		URLIOHandler iohandler = null;
		try {
			//https://lisuanlaoji.me/link/U4X5hiULzvWINa0c?mu=3
			iohandler = URLIOHandler.getInstance();
			String linkurl = iohandler.getInputUrl();
			if(null == linkurl || linkurl.equals(""))linkurl = "https://lisuanlaoji.me/link/U4X5hiULzvWINa0c?mu=3";
			URLConnection con = URLConnHandler.getInstance(linkurl).getConnection();
			airportdata data = DataResolve.getInstance().decode(iohandler.getResponseContent(con));
			System.out.println(data.getServers().get(0).getRemarks());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package tk.ddvudo.ssrdetection.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.StringUtils;

import tk.ddvudo.ssrdetection.Utils.Global;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLConnHandler;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLIOHandler;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLIOHandler.LinkType;
import tk.ddvudo.ssrdetection.Utils.dataResolve.DataResolve;
import tk.ddvudo.ssrdetection.beans.Airport;
import tk.ddvudo.ssrdetection.beans.Result;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

class SampleCode {

	public static void main(String[] args) {
		try {
			DataResolve dr = DataResolve.getInstance();
			URLIOHandler iohandler = URLIOHandler.getInstance(LinkType.SSR);
			String linkurl = iohandler.getInputUrl(LinkType.SSR);
			if(StringUtils.isEmpty(linkurl)) {
				Global.getInstance().getLogger().debug("链接为空，退出");
				return;
			}
			URLConnection con = URLConnHandler.getInstance(linkurl).getConnection();
			Airport data = dr.Decode(iohandler.getResponseContent(con),LinkType.SSR);
			List<Result> res = dr.serverPingTestMultiThread(1000, data.getServers());
			Global.getInstance().getLogger().info(JSON.toJSONString(res));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

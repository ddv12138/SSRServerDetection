package tk.ddvudo.ssrdetection.Utils.dataResolve;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;

import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingArguments;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping.Backend;
import tk.ddvudo.ssrdetection.beans.Server;
import tk.ddvudo.ssrdetection.beans.airportdata;

public class DataResolve {
	private DataResolve() {
	}

	public airportdata decode(String str) throws UnsupportedEncodingException {
		String decoded = new String(Base64.decodeBase64(str.replaceAll("\r|\n|\t", "")));
		return (airportdata) JSON.parseObject(decoded, airportdata.class);
	}

	public String unicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			String group = matcher.group(2);
			ch = (char) Integer.parseInt(group, 16);
			String group1 = matcher.group(1);
			str = str.replace(group1, ch + "");
		}
		return str;
	}
	
	public void serverPingTestSingleThread(Server... servers) {
		long t1 = System.currentTimeMillis();
		for(Server s : servers) {
			System.out.println("开始测试"+s.getId()+"----->"+s.getRemarks());
			PingArguments arguments = new PingArguments.Builder().url(s.getServer()).timeout(500).count(2).bytes(32).build();
			PingResult results = Ping.ping(arguments, Backend.WINDOWS_zhCN);
			if(results.rtt_avg()>0) {
				System.out.println(s.getRemarks()+"测试结果"+results.rtt_avg()+"ms");
			}
		}
		long t2 = System.currentTimeMillis();
		System.out.println("测试结束,耗时"+(t2-t1)+"ms");
	}
	
	public static final DataResolve getInstance() {
		return new DataResolve();
	}
}

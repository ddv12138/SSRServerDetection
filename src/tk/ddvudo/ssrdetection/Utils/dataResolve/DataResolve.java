package tk.ddvudo.ssrdetection.Utils.dataResolve;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;

import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingArguments;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping.Backend;
import tk.ddvudo.ssrdetection.beans.ssBean.Server;
import tk.ddvudo.ssrdetection.beans.ssBean.airportdata;
import tk.ddvudo.ssrdetection.beans.ssBean.ssResult;

public class DataResolve {
	private DataResolve() {
	}

	public airportdata ssDecode(String str) throws UnsupportedEncodingException {
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
			PingArguments arguments = new PingArguments.Builder().url(s.getServer()).timeout(500).count(2).bytes(32).build();
			PingResult results = Ping.ping(arguments, Backend.WINDOWS_zhCN);
			if(results.rtt_avg()>0) {
				System.out.println(s.getRemarks()+"测试结果"+results.rtt_avg()+"ms");
			}
		}
		long t2 = System.currentTimeMillis();
		System.out.println("测试结束,耗时"+(t2-t1)+"ms");
	}
	
	public void serverPingTestMultiThread(List<Server> servers) {
		long t1 = System.currentTimeMillis();
		ExecutorService pool = null;
		try {
			int userlength = servers.size();
			int corenum = Runtime.getRuntime().availableProcessors();
			int dataPreThread = (int) Math.round(Math.ceil(servers.size() / (double) (corenum)));
			int groupnum = (int) Math.round(Math.ceil(userlength / (double) dataPreThread));
			
			System.out.println("线程数---"+groupnum);
			
			pool = Executors.newFixedThreadPool(groupnum);
			
			ArrayList<Future<ArrayList<ssResult>>> list = new ArrayList<Future<ArrayList<ssResult>>>();
			
			for (int i = 0; i < groupnum; i++) {
				int startindex = i * dataPreThread;
				int endindex = (i + 1) * dataPreThread;
				if (endindex > servers.size()) {
					endindex = servers.size();
				}
				List<Server> tmpList = servers.subList(startindex, endindex);
				Callable<ArrayList<ssResult>> call = new serverThread(tmpList);
				list.add(pool.submit(call));
			}
			pool.shutdown();
			while (true) {
				if (pool.isTerminated()) {
					pool = null;
					break;
				}
			}
			ArrayList<ssResult> res = new ArrayList<>();
			for(Future<ArrayList<ssResult>> f : list) {
				res.addAll(f.get());
			}
			if (res.size() == 0) {
				System.out.println("没有结果");
				return;
			}
			for(ssResult r : res) {
				System.out.println(r.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pool != null) {
				pool.shutdown();
				pool = null;
			}
		}
		long t2 = System.currentTimeMillis();
		System.out.println("测试结束,耗时"+(t2-t1)+"ms");
	}
	
	public static final DataResolve getInstance() {
		return new DataResolve();
	}
}

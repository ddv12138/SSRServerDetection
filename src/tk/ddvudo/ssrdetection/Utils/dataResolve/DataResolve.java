package tk.ddvudo.ssrdetection.Utils.dataResolve;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

import tk.ddvudo.ssrdetection.Utils.URLHandler.URLIOHandler.LinkType;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingArguments;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping.Backend;
import tk.ddvudo.ssrdetection.beans.ssBean.SSServer;
import tk.ddvudo.ssrdetection.beans.ssrBean.SSRAirport;
import tk.ddvudo.ssrdetection.beans.ssrBean.SSRServer;
import tk.ddvudo.ssrdetection.beans.ssBean.SSAirport;
import tk.ddvudo.ssrdetection.beans.Airport;
import tk.ddvudo.ssrdetection.beans.Result;
import tk.ddvudo.ssrdetection.beans.Server;

public class DataResolve {
	private DataResolve() {}

	public Airport Decode(String str,LinkType linktype) throws UnsupportedEncodingException {
		Airport airport = null;
		String decoded = linkBase64Decode(str);
		if(linktype == LinkType.SS) {
			airport = (SSAirport) JSON.parseObject(decoded, SSAirport.class);
		}else if(linktype == LinkType.SSR) {
			airport = this.parseSSRAirport(decoded);
		}
		return airport;
	}

	private SSRAirport parseSSRAirport(String decoded) {
		String[] links = decoded.split("ssr://");
		SSRAirport airport = new SSRAirport();
		List<SSRServer> servers = new ArrayList<>();
		for(String link : links) {
			if(StringUtils.isEmpty(link))continue;
			String decode1 = linkBase64Decode(link.trim());
			String basic = decode1.split("\\/\\?")[0];
			String params = decode1.split("\\/\\?")[1];
			String[] basicinfo = basic.split(":");
			String[] paramsinfo = params.split("&");
			if(basicinfo.length != 6)continue;
			String serveraddr = basicinfo[0];
			String port = basicinfo[1];
			String protocol = basicinfo[2];
			String method = basicinfo[3];
			String obfs = basicinfo[4];
			String passwd = linkBase64Decode(basicinfo[5]);
			if(paramsinfo.length != 4)continue;
			String obfsparam = null;
			if(paramsinfo[0].split("=").length > 1)obfsparam = linkBase64Decode(paramsinfo[0].split("=")[1]);
			String protoparam = null;
			if(paramsinfo[1].split("=").length > 1)protoparam = linkBase64Decode(paramsinfo[1].split("=")[1]);
			String remarks = null;
			if(paramsinfo[2].split("=").length > 1)remarks = linkBase64Decode(paramsinfo[2].split("=")[1]);
			String group = "";
			if(paramsinfo[3].split("=").length > 1)group = linkBase64Decode(paramsinfo[3].split("=")[1]);
			SSRServer server = new SSRServer(serveraddr, port, method, passwd, protocol, obfs, obfsparam, protoparam, remarks, group);
			System.out.println(server.toString());
			airport.setGroup(group);
			servers.add(server);
		}
		airport.setServers(servers);
		return airport;
	}

	public String linkBase64Decode(String str) {
		return new String(Base64.decodeBase64(str.replaceAll("-", "+").replaceAll("_", "/").replaceAll("\r|\n|\t", "")));
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
	
	public void serverPingTestSingleThread(SSServer... servers) {
		long t1 = System.currentTimeMillis();
		for(SSServer s : servers) {
			PingArguments arguments = new PingArguments.Builder().url(s.getServer()).timeout(500).count(2).bytes(32).build();
			PingResult results = Ping.ping(arguments, Backend.WINDOWS_zhCN);
			if(results.rtt_avg()>0) {
				System.out.println(s.getRemarks()+"测试结果"+results.rtt_avg()+"ms");
			}
		}
		long t2 = System.currentTimeMillis();
		System.out.println("测试结束,耗时"+(t2-t1)+"ms");
	}
	
	public void serverPingTestMultiThread(Server... servers) throws Exception {
		long t1 = System.currentTimeMillis();
		ExecutorService pool = null;
		try {
			List<Server> serverList = new ArrayList<>();
			serverList.addAll(Arrays.asList(servers));
			int userlength = serverList.size();
			int corenum = Runtime.getRuntime().availableProcessors();
			int dataPreThread = (int) Math.round(Math.ceil(serverList.size() / (double) (corenum)));
			int groupnum = (int) Math.round(Math.ceil(userlength / (double) dataPreThread));
			
			System.out.println("线程数---"+groupnum);
			
			pool = Executors.newFixedThreadPool(groupnum);
			
			ArrayList<Future<ArrayList<Result>>> list = new ArrayList<Future<ArrayList<Result>>>();
			
			for (int i = 0; i < groupnum; i++) {
				int startindex = i * dataPreThread;
				int endindex = (i + 1) * dataPreThread;
				if (endindex > serverList.size()) {
					endindex = serverList.size();
				}
				List<Server> tmpList = serverList.subList(startindex, endindex);
				Callable<ArrayList<Result>> call = new serverThread(tmpList);
				list.add(pool.submit(call));
			}
			pool.shutdown();
			while (true) {
				if (pool.isTerminated()) {
					pool = null;
					break;
				}
			}
			ArrayList<Result> res = new ArrayList<>();
			for(Future<ArrayList<Result>> f : list) {
				res.addAll(f.get());
			}
			if (res.size() == 0) {
				System.out.println("没有结果");
				return;
			}
			for(Result r : res) {
				System.out.println(r.toString());
			}
		} catch (Exception e) {
			throw e;
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

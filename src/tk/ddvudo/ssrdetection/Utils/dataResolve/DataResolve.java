package tk.ddvudo.ssrdetection.Utils.dataResolve;

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

import tk.ddvudo.ssrdetection.Utils.Global;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLIOHandler.LinkType;
import tk.ddvudo.ssrdetection.Utils.netHadler.NetReach.NetReachable;
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

	public Airport Decode(String str,LinkType linktype) {
		Airport airport = null;
		String decoded = linkBase64Decode(str);
		if(linktype == LinkType.SS) {
			airport = JSON.parseObject(decoded, SSAirport.class);
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
			String basic = decode1.split("/\\?")[0];
			String obfsparam = null;
			String protoparam = null;
			String remarks = null;
			String group = "";
			if(decode1.split("/\\?").length>1) {
				String params = decode1.split("/\\?")[1];
				String[] paramsinfo = params.split("&");
				if(paramsinfo.length != 4)continue;
				if(paramsinfo[0].split("=").length > 1)obfsparam = linkBase64Decode(paramsinfo[0].split("=")[1]);
				if(paramsinfo[1].split("=").length > 1)protoparam = linkBase64Decode(paramsinfo[1].split("=")[1]);
				if(paramsinfo[2].split("=").length > 1)remarks = linkBase64Decode(paramsinfo[2].split("=")[1]);
				if(paramsinfo[3].split("=").length > 1)group = linkBase64Decode(paramsinfo[3].split("=")[1]);
			}
			String[] basicinfo = basic.split(":");
			if(basicinfo.length != 6)continue;
			String serveraddr = basicinfo[0];
			String port = basicinfo[1];
			String protocol = basicinfo[2];
			String method = basicinfo[3];
			String obfs = basicinfo[4];
			String passwd = linkBase64Decode(basicinfo[5]);
			SSRServer server = new SSRServer(serveraddr, port, method, passwd, protocol, obfs, obfsparam, protoparam, remarks, group);
			Global.getInstance().getLogger().info(server.toString());
			airport.setGroup(group);
			servers.add(server);
		}
		airport.setServers(servers);
		Global.getInstance().getLogger().info("一共获取到"+servers.size()+"个服务器");
		return airport;
	}

	private String linkBase64Decode(String str) {
		return new String(Base64.decodeBase64(str.replaceAll("-", "+").replaceAll("_", "/").replaceAll("[\r\n\t]", "")));
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
				Global.getInstance().getLogger().info(s.getRemarks()+"测试结果"+results.rtt_avg()+"ms");
			}
		}
		long t2 = System.currentTimeMillis();
		Global.getInstance().getLogger().info("测试结束,耗时"+(t2-t1)+"ms");
	}
	
	public ArrayList<Result> serverPingTestMultiThread(int timeout,Server... servers) throws Exception {
		ArrayList<Result> res;
		long t1 = System.currentTimeMillis();
		ExecutorService pool = null;
		try {
			List<Server> serverList = new ArrayList<>(Arrays.asList(servers));
			int userlength = serverList.size();
			int corenum = Runtime.getRuntime().availableProcessors();
			int dataPreThread = (int) Math.round(Math.ceil(serverList.size() / (double) (corenum)));
			int groupnum = (int) Math.round(Math.ceil(userlength / (double) dataPreThread));
			
			Global.getInstance().getLogger().info("线程数---"+groupnum);
			
			pool = Executors.newFixedThreadPool(groupnum);
			
			ArrayList<Future<ArrayList<Result>>> list = new ArrayList<>();
			
			for (int i = 0; i < groupnum; i++) {
				int startindex = i * dataPreThread;
				int endindex = (i + 1) * dataPreThread;
				if (endindex > serverList.size()) {
					endindex = serverList.size();
				}
				List<Server> tmpList = serverList.subList(startindex, endindex);
				Callable<ArrayList<Result>> call = new serverThread(tmpList, timeout);
				list.add(pool.submit(call));
			}
			pool.shutdown();
			while (true) {
				if (pool.isTerminated()) {
					pool = null;
					break;
				}
			}
			res = new ArrayList<>();
			for(Future<ArrayList<Result>> f : list) {
				res.addAll(f.get());
			}
			if (res.size() == 0) {
				Global.getInstance().getLogger().error("没有结果");
				return null;
			}
			for(Result r : res) {
				Global.getInstance().getLogger().info(r.toString());
			}
		} finally {
			if (pool != null) {
				pool.shutdown();
			}
		}
		long t2 = System.currentTimeMillis();
		Global.getInstance().getLogger().info("测试结束,耗时"+(t2-t1)+"ms,共"+res.size()+"个节点可用");
		return res;
	}
	
	public void serverPingTestWithParallec(Server... servers) {
		ArrayList<String> serverhosts = new ArrayList<>();
		for(Server s:servers) {
			serverhosts.add(s.getServer());
		}
		NetReachable.getInstance().starttest(serverhosts);
	}
	
	public static DataResolve getInstance() {
		return new DataResolve();
	}
}

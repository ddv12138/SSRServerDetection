package tk.ddvudo.ssrdetection.Utils.netHadler.NetReach;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

//import io.parallec.core.ParallelClient;
import tk.ddvudo.ssrdetection.Utils.Global;
import tk.ddvudo.ssrdetection.beans.Server;

public class NetReachable {
//	private final static NetReachable netreach = new NetReachable();
//	private final static ParallelClient pc = new ParallelClient();
//	public static NetReachable getInstance() {
//		return netreach;
//	}
//	public void starttest(ArrayList<String> servers) {
//		long t1 = System.currentTimeMillis();
//		if(null == servers || servers.size() == 0) {
//			Global.getInstance().getLogger().error("结果为空");
//		}
//		HashMap<String,Integer> useable = new HashMap<>();
//		useable.put("useable", 0);
//		pc.preparePing().setConcurrency(100).setPingTimeoutMillis(100)
//		.setAutoSaveLogToLocal(true)
//		.setTargetHostsFromList(servers)
//		.execute((res, responseContext) -> {
//			try {
//				Global.getInstance().getLogger().info(res.getHost()+"["+res.getStatusCode()+"]" +"<--->"+res.getOperationTimeMillis());
//				if(res.getStatusCode().equals("LIVE")) {
//					useable.put("useable", useable.get("useable")+1);
//				}
//			} catch (Exception e) {
//				Global.getInstance().getLogger().error(e);
//				e.printStackTrace();
//			}
//		});
//		pc.releaseExternalResources();
//		long t2 = System.currentTimeMillis();
//		Global.getInstance().getLogger().info("测试结束,耗时"+(t2-t1)+"ms,共"+useable.get("useable")+"个节点可用");
//	}
	public void starttest2(List<Server> servers, int timeout) throws IOException {
		long t1 = System.currentTimeMillis();
		if(null == servers || servers.size() == 0) {
			Global.getInstance().getLogger().error("结果为空");
		}
		int useable = 0;
		for(Server s:servers) {
			try (Socket socket = new Socket();) {
				long t3 = System.currentTimeMillis();
				socket.connect(new InetSocketAddress(s.getServer(), Integer.parseInt(s.getPort())), timeout);
				long t4 = System.currentTimeMillis();
				socket.setSoTimeout(timeout);
				if (socket.isConnected()) {
					useable++;
					Global.getInstance().getLogger().info(s.getServer() + "--->" + "连接成功,延迟" + (t4 - t3) + "ms");
				}
			} catch (ConnectException e) {
				Global.getInstance().getLogger().info(s.getServer()+"--连接超时");
			} catch (SocketTimeoutException e) {
				Global.getInstance().getLogger().info(s.getServer()+"--读取超时");
			} catch (UnknownHostException e) {
				Global.getInstance().getLogger().info(s.getServer()+"--地址无法解析");
			}
		}
		
		long t2 = System.currentTimeMillis();
		Global.getInstance().getLogger().info("测试结束,耗时"+(t2-t1)+"ms,共"+useable+"个节点可用");
	}
}
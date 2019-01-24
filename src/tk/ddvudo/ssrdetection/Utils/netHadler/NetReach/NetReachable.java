package tk.ddvudo.ssrdetection.Utils.netHadler.NetReach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import io.parallec.core.ParallecResponseHandler;
import io.parallec.core.ParallelClient;
import io.parallec.core.ResponseOnSingleTask;
import tk.ddvudo.ssrdetection.Utils.Global;

public class NetReachable {
	final static NetReachable netreach = new NetReachable();
	final static ParallelClient pc = new ParallelClient();
	public static NetReachable getInstance() {
		return netreach;
	}
	public void starttest(ArrayList<String> servers) {
		if(null == servers || servers.size() == 0) {
			Global.getInstance().getLogger().error("结果为空");
		}
		pc.preparePing().setConcurrency(1).setPingTimeoutMillis(50).setTcpConnectTimeoutMillis(50).setUdpIdleTimeoutSec(50)
		.setAutoSaveLogToLocal(true)
		.setTargetHostsFromList(servers)
		.execute(new ParallecResponseHandler() {
			@Override
			public void onCompleted(ResponseOnSingleTask res, Map<String, Object> responseContext) {
				try {
					Global.getInstance().getLogger().info(res.getHost()+"["+res.getStatusCode()+"]" +"<--->"+res.getOperationTimeMillis());
				} catch (Exception e) {
					Global.getInstance().getLogger().error(e);
					e.printStackTrace();
				}
			}
		});
		pc.releaseExternalResources();
	}
}
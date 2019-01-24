package tk.ddvudo.ssrdetection.Utils.netHadler.NetReach;

import java.util.Map;

import io.parallec.core.ParallecResponseHandler;
import io.parallec.core.ParallelClient;
import io.parallec.core.ResponseOnSingleTask;
import io.parallec.core.bean.ping.PingMode;
import tk.ddvudo.ssrdetection.Utils.Global;

public class NetReachable {
	public static void main(String... args) {
		 try {
			ParallelClient pc = new ParallelClient();
			pc.preparePing().setPingMode(PingMode.INET_ADDRESS_REACHABLE_NEED_ROOT).setTargetHostsFromString("lisuanlaoji.co www.baidu.com www.cctv.com wwww.bilibili.com www.youku.com").execute(new ParallecResponseHandler() {
				
				@Override
				public void onCompleted(ResponseOnSingleTask res, Map<String, Object> responseContext) {
					Global.getInstance().getLogger().info(res.getHost()+"-------->"+res.getOperationTimeMillis());
				}
			});
			pc.releaseExternalResources();
//			InetAddress address = InetAddress.getByName("www.bilibili.com");
//			Global.getInstance().getLogger().info(address.isReachable(5000));
		} catch (Exception e) {
			Global.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
	}
}

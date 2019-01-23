package tk.ddvudo.ssrdetection.Utils.netHadler.NetReach;

import java.util.Map;

import io.parallec.core.ParallecResponseHandler;
import io.parallec.core.ParallelClient;
import io.parallec.core.ResponseOnSingleTask;
import tk.ddvudo.ssrdetection.Utils.Global;

public class NetReachable {
	public static void main(String... args) {
		 try {
			ParallelClient pc = new ParallelClient();
			long a = pc.preparePing().setTargetHostsFromString("lisuanlaoji.co").execute(new ParallecResponseHandler() {
				
				@Override
				public void onCompleted(ResponseOnSingleTask res, Map<String, Object> responseContext) {
					Global.getInstance().getLogger().info("ddv");
					Global.getInstance().getLogger().info(res.getHost()+"-------->"+res.getOperationTimeMillis());
				}
			}).getSubmitTime();
			pc.releaseExternalResources();
			Global.getInstance().getLogger().info("ddv----"+a);
//			InetAddress address = InetAddress.getByName("www.bilibili.com");
//			Global.getInstance().getLogger().info(address.isReachable(5000));
		} catch (Exception e) {
			Global.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
	}
}

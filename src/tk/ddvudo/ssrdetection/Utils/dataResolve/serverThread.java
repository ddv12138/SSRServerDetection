package tk.ddvudo.ssrdetection.Utils.dataResolve;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import tk.ddvudo.ssrdetection.Utils.Global;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingRequest;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingResult;
import tk.ddvudo.ssrdetection.beans.Result;
import tk.ddvudo.ssrdetection.beans.Server;

class serverThread implements Callable<ArrayList<Result>> {

	private final List<Server> servers;
	private final int timeout;

	public serverThread(List<Server> tmpList, int timeout) {
		this.servers = tmpList;
		this.timeout = timeout;
	}

	@Override
	public ArrayList<Result> call() {
		ArrayList<Result> resarr = new ArrayList<>();
		for (Server s : servers) {
			Socket socket = new Socket();
			try {
				long t3 = System.currentTimeMillis();
				socket.connect(new InetSocketAddress(s.getServer(), Integer.parseInt(s.getPort())), timeout);
				socket.setSoTimeout(timeout);
				long t4 = System.currentTimeMillis();
				if (socket.isConnected()) {
					Result res = new Result(new PingResult(null) {
						@Override
						protected int parsePayload(List<String> lines) {
							return 0;
						}
						@Override
						protected int matchTransmitted(List<String> lines) {
							return 0;
						}
						@Override
						protected int matchTime(List<String> lines) {
							return (int) (t4 - t3);
						}
						@Override
						protected int matchTTL(List<String> lines) {
							return 0;
						}
						@Override
						protected float matchRttMin(List<String> lines) {
							return (int) (t4 - t3);
						}
						@Override
						protected float matchRttMdev(List<String> lines) {
							return (int) (t4 - t3);
						}
						@Override
						protected float matchRttMax(List<String> lines) {
							return (int) (t4 - t3);
						}
						@Override
						protected float matchRttAvg(List<String> lines) {
							return (int) (t4 - t3);
						}
						@Override
						protected int matchReceived(List<String> lines) {
							return 0;
						}
						@Override
						protected String matchIP(List<String> lines) {
							return socket.getInetAddress().getHostAddress();
						}
						@Override
						public List<PingRequest> getRequests() {
							return null;
						}
					}, s);
					resarr.add(res);
				}
			} catch (ConnectException e) {
				Global.getInstance().getLogger().error(s.getServer()+"--连接超时");
			} catch (SocketTimeoutException e) {
				Global.getInstance().getLogger().error(s.getServer()+"--读取超时");
			} catch (UnknownHostException e) {
				Global.getInstance().getLogger().error(s.getServer()+"--地址无法解析");
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resarr;
	}

}

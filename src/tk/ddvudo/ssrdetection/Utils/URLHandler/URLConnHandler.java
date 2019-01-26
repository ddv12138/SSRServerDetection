package tk.ddvudo.ssrdetection.Utils.URLHandler;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import tk.ddvudo.ssrdetection.Utils.Global;

public class URLConnHandler {
	private String url = null;

	private URLConnHandler(String url) {
		this.url = url;
	}

	private URLConnHandler() {
	}

	public URLConnection getConnection() throws IOException {
		URL url = new URL(this.url);
//		SocketAddress addr = new InetSocketAddress("127.0.0.1", 1080);
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
		URLConnection con = url.openConnection();
		con.setRequestProperty("accept", "*/*");
		con.setRequestProperty("connection", "Keep-Alive");
		con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		con.setReadTimeout(5000);
		con.connect();
		Global.getInstance().getLogger().info("连接成功，进入数据处理");
		return con;
	}

	public static URLConnHandler getInstance(String url) {
		return new URLConnHandler(url);
	}
}

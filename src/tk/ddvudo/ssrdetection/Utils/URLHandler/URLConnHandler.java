package tk.ddvudo.ssrdetection.Utils.URLHandler;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnHandler {
	String url = null;

	private URLConnHandler(String url) {
		this.url = url;
	}

	private URLConnHandler() {
	}

	public URLConnection getConnection() throws IOException {
		URL url = new URL(this.url);
		URLConnection con = url.openConnection();
		con.setRequestProperty("accept", "*/*");
		con.setRequestProperty("connection", "Keep-Alive");
		con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		con.connect();
		return con;
	}

	public static final URLConnHandler getInstance(String url) {
		return new URLConnHandler(url);
	}
}

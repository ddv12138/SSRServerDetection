package tk.ddvudo.ssrdetection.Utils.URLHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;

import tk.ddvudo.ssrdetection.Utils.Global;

public class URLIOHandler {
	public enum LinkType {
		SS {
			@Override
			public int getSliceEnd() {
				return 6;
			}
		},
		
		SSR {
			@Override
			public int getSliceEnd() {
				return 0;
			}
		};

		protected abstract int getSliceEnd();
	}

	private LinkType linktype;
	
	private URLIOHandler() {
	}
	public static URLIOHandler getInstance() {
		return new URLIOHandler();
	}

	public String getInputUrl(LinkType linktype) throws IOException {
		String str;
		this.linktype = linktype;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			str = null;
			if (linktype.equals(LinkType.SS)) {
				Global.getInstance().getLogger().info("请输入ss/ssd订阅链接:");
				str = br.readLine();
			} else if (linktype.equals(LinkType.SSR)) {
				Global.getInstance().getLogger().info("请输入ssr订阅链接:");
				str = br.readLine();
			}
		}
		return str;
	}

	public String getResponseContent(URLConnection con) throws IOException {
		String content;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			content = sb.toString().replaceAll("-", "+").replaceAll("_", "/");
			content = content.substring(this.linktype.getSliceEnd());
		}
		return content;
	}
}

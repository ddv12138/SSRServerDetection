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

		public abstract int getSliceEnd();
	}

	private LinkType linktype;
	
	private URLIOHandler() {
	}
	public static final URLIOHandler getInstance() {
		return new URLIOHandler();
	}

	public String getInputUrl(LinkType linktype) throws IOException {
		String str = null;
		BufferedReader br = null;
		this.linktype = linktype;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			str = null;
			if(linktype.equals(LinkType.SS)) {
				Global.getInstance().getLogger().info("请输入ss/ssd订阅链接:");
				str = br.readLine();
			}else if(linktype.equals(LinkType.SSR)) {
				Global.getInstance().getLogger().info("请输入ssr订阅链接:");
				str = br.readLine();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			br.close();
		}
		return str;
	}

	public String getResponseContent(URLConnection con) throws IOException {
		String content = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			content = sb.toString().replaceAll("-", "+").replaceAll("_", "/");
			content = content.substring(this.linktype.getSliceEnd(), content.length());
		} catch (Exception e) {
			throw e;
		} finally {
			br.close();
		}
		return content;
	}
}

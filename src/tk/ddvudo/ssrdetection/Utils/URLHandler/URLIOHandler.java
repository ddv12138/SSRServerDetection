package tk.ddvudo.ssrdetection.Utils.URLHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;

public class URLIOHandler {
	private URLIOHandler() {
	}

	public static final URLIOHandler getInstance() {
		return new URLIOHandler();
	}

	public String getInputUrl() throws IOException {
		String str = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			str = null;
			System.out.println("�·�����ss/ssr���ĵ�ַ:");
			str = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
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
			content = sb.toString();
			content.replaceAll("-", "+");
			content.replaceAll("_", "/");
			content = content.substring(6, content.length());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return content;
	}
}

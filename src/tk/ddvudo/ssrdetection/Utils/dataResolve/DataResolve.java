package tk.ddvudo.ssrdetection.Utils.dataResolve;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import tk.ddvudo.ssrdetection.beans.airportdata;

public class DataResolve {
	private DataResolve() {
	}

	public airportdata decode(String str) throws UnsupportedEncodingException {
		String decoded = new String(Base64.decodeBase64(str.replaceAll("\r|\n|\t", "")));
		return (airportdata) JSON.parseObject(decoded, airportdata.class);
	}

	public static String unicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			String group = matcher.group(2);
			ch = (char) Integer.parseInt(group, 16);
			String group1 = matcher.group(1);
			str = str.replace(group1, ch + "");
		}
		return str;
	}

	public static final DataResolve getInstance() {
		return new DataResolve();
	}
}

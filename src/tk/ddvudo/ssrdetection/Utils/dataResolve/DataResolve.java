package tk.ddvudo.ssrdetection.Utils.dataResolve;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import tk.ddvudo.ssrdetection.beans.airportdata;

public class DataResolve {
	private DataResolve() {
	}

	public airportdata decode(String str) {
		String decoded = new String(Base64.getDecoder().decode(str));
		return (airportdata) JSON.parseObject(unicodeToString(decoded), airportdata.class);
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

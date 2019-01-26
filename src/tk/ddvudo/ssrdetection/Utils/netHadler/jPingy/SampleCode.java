/*
 Copyright (c) 2012 Thomas Goossens

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package tk.ddvudo.ssrdetection.Utils.netHadler.jPingy;

import java.util.Locale;

import tk.ddvudo.ssrdetection.Utils.Global;
import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.Ping.Backend;

/**
 * 
 * @author Thomas Goossens
 * @version 0.1a
 * 
 */
class SampleCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		PingArguments arguments = new PingArguments.Builder().url("baidu.com")
				.timeout(5000).count(2).bytes(32).build();

		PingResult results = Ping.ping(arguments, Backend.WINDOWS_zhCN);
		
		Locale locale = Locale.getDefault();  
		Global.getInstance().getLogger().info(locale.getLanguage());  
		Global.getInstance().getLogger().info(locale.getCountry());
	
		Global.getInstance().getLogger().info("TTL: " + results.ttl());

		Global.getInstance().getLogger().info("RTT Minimum: " + results.rtt_min());

		Global.getInstance().getLogger().info("Received : " + results.received());
		
		Global.getInstance().getLogger().info(results.getRequests());
	}

}

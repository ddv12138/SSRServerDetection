/*
 Copyright (c) 2012 Thomas Goossens

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package tk.ddvudo.ssrdetection.Utils.netHadler.jPingy;

import java.util.List;

import tk.ddvudo.ssrdetection.Utils.Global;

/**
 *
 * @author Thomas Goossens
 * @version 0.1a
 *
 */
public abstract class PingResult {

	protected List<String> lines;

	protected String address;
	protected int transmitted = -1;
	protected int ttl = -1;
	protected long time = -1;
	protected int received = -1;
	protected int payload = -1;
	protected float rtt_min = -1;
	protected float rtt_avg = -1;
	protected float rtt_max = -1;
	protected float rtt_mdev = -1;

	public String getAddress() {
		return address;
	}

	public int getTransmitted() {
		return transmitted;
	}

	public int getTtl() {
		return ttl;
	}

	public long getTime() {
		return time;
	}

	public int getReceived() {
		return received;
	}

	public int getPayload() {
		return payload;
	}

	public float getRtt_min() {
		return rtt_min;
	}

	public float getRtt_avg() {
		return rtt_avg;
	}

	public float getRtt_max() {
		return rtt_max;
	}

	public float getRtt_mdev() {
		return rtt_mdev;
	}

	protected PingResult(List<String> pingOutput) {

		try {
			this.lines = pingOutput;
			transmitted = matchTransmitted(pingOutput);
			received = matchReceived(pingOutput);
			time = matchTime(pingOutput);

			rtt_min = matchRttMin(pingOutput);
			rtt_avg = matchRttAvg(pingOutput);
			rtt_max = matchRttMax(pingOutput);
			rtt_mdev = matchRttMdev(pingOutput);

			ttl = matchTTL(pingOutput);

			address = matchIP(pingOutput);

			payload = parsePayload(pingOutput);
		} catch (Exception e) {
			Global.getInstance().getLogger().error(pingOutput.toString()+"---》连接失败");
		}

	}

	List<String> getLines() {
		return lines;
	}

	protected abstract int parsePayload(List<String> lines);

	protected abstract int matchTransmitted(List<String> lines);

	protected abstract int matchReceived(List<String> lines);

	protected abstract int matchTime(List<String> lines);

	protected abstract float matchRttMin(List<String> lines);

	protected abstract float matchRttAvg(List<String> lines);

	protected abstract float matchRttMax(List<String> lines);

	protected abstract float matchRttMdev(List<String> lines);

	protected abstract String matchIP(List<String> lines);

	protected abstract int matchTTL(List<String> lines);

	public abstract List<PingRequest> getRequests();

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"lines\":")
				.append(lines);
		sb.append(",\"address\":\"")
				.append(address).append('\"');
		sb.append(",\"transmitted\":")
				.append(transmitted);
		sb.append(",\"ttl\":")
				.append(ttl);
		sb.append(",\"time\":")
				.append(time);
		sb.append(",\"received\":")
				.append(received);
		sb.append(",\"payload\":")
				.append(payload);
		sb.append(",\"rtt_min\":")
				.append(rtt_min);
		sb.append(",\"rtt_avg\":")
				.append(rtt_avg);
		sb.append(",\"rtt_max\":")
				.append(rtt_max);
		sb.append(",\"rtt_mdev\":")
				.append(rtt_mdev);
		sb.append('}');
		return sb.toString();
	}
}
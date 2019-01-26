package tk.ddvudo.ssrdetection.test;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLConnHandler;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLIOHandler;
import tk.ddvudo.ssrdetection.Utils.dataResolve.DataResolve;
import tk.ddvudo.ssrdetection.beans.Airport;

import java.io.IOException;
import java.net.URLConnection;

class TestSample {
    @Test
    void testSampleCode() {
        URLConnection con = null;
        try {
            con = URLConnHandler.getInstance("https://lisuanlaoji.link/sub/TEjoV2azd1Vzg5PE?mu=0").getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        URLIOHandler iohandler = URLIOHandler.getInstance();
        Airport data = null;
        DataResolve dr = DataResolve.getInstance();
        try {
            data = dr.Decode(iohandler.getResponseContent(con), URLIOHandler.LinkType.SSR);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (data != null) {
                Assert.assertNotNull(dr.serverPingTestMultiThread(200, data.getServers()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Assert.assertEquals(result, 3);
    }
}

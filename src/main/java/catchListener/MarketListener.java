package catchListener;

import marketTool.MarketCmd;
import util.HttpUtil;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

/**
 * Created by tangyifeng on 2017/8/26.
 * Email: yifengtang_hust@outlook.com
 */
public class MarketListener implements IOnCatchListener {

    private String content;
    private String groupUid;

    public MarketListener(String group) {
        groupUid = group;
    }

    public boolean onGetMsg(String json) {
        String[] jsons = json.split("\"");
        for (int i = 0; i < jsons.length; i++) {
            if (jsons[i].equals("content")) {
                content = jsons[i + 2];
                System.out.println(content);
            }
            if (jsons[i].equals("group_uid")){
                if (jsons[i + 1].equals(":" + groupUid + ",")) {
                    if (content.contains("market")) {
                        System.out.println("Gottya");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void onHandleMsg() {
        String marketData;
        MarketCmd marketCmd = new MarketCmd(content);
        marketData = marketCmd.executeCmd();
        try {
            HttpUtil.sendGet("http://127.0.0.1:5000/openqq/send_group_message?uid=" + groupUid + "&content=" +
                    URLDecoder.decode(marketData, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

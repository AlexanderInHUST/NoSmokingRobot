package catchListener;

import util.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * Created by tangyifeng on 2017/3/26.
 * Email: yifengtang_hust@outlook.com
 */
public class AnswerListener implements IOnCatchListener {

    private String groupUid;
    private String curKey;
    private HashMap<String, String> answerMap;

    public AnswerListener(String groupUid, String keyWord, String response) {
        this.groupUid = groupUid;
        answerMap = new HashMap<String, String>();
        answerMap.put(keyWord, response);
    }

    public AnswerListener(String groupUid, HashMap<String, String> answerMap) {
        this.groupUid = groupUid;
        this.answerMap = answerMap;
    }

    public boolean onGetMsg(String json) {
        String content = "";
        System.out.println(json);
        String[] jsons = json.split("\"");
        for (int i = 0; i < jsons.length; i++) {
            if (jsons[i].equals("class")) {
                if (jsons[i + 2].equals("send"))
                    return false;
            }
            if (jsons[i].equals("content")) {
                content = jsons[i + 2];
                System.out.println(content);
            }
            if (jsons[i].equals("group_uid")){
                if (jsons[i + 1].equals(":" + groupUid + ",")) {
                    for (String keyWord : answerMap.keySet()) {
                        if (content.contains(keyWord)) {
                            System.out.println("Gottya");
                            curKey = keyWord;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void onHandleMsg() {
        try {
            HttpUtil.sendGet("http://127.0.0.1:5000/openqq/send_group_message?uid=" + groupUid + "&content=" +
                    URLDecoder.decode(answerMap.get(curKey), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}

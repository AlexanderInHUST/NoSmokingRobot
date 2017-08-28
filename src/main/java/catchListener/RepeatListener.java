package catchListener;

import util.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by tangyifeng on 2017/4/1.
 * Email: yifengtang_hust@outlook.com
 */
public class RepeatListener implements IOnCatchListener {

    private HashMap<String, LinkedList<String>> msgMap;
    private HashSet<String> listenList;
    private String content;
    private String groupUid;
    private String lastRepeat;

    private static final String blackString[] = {"系统图片","唐艺峰","爱","撸","我","喜欢","瓜皮","love","I"};

    public RepeatListener(String... group) {
        this.msgMap = new HashMap<String, LinkedList<String>>();
        this.listenList = new HashSet<String>();
        this.lastRepeat = "";
        listenList.addAll(Arrays.asList(group));
    }

    public boolean onGetMsg(String json) {
        content = "";
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
                String group_uid = jsons[i + 1].substring(1, jsons[i + 1].length() - 1);
                if (listenList.contains(group_uid)) {
                    if (msgMap.keySet().contains(group_uid)) {
                        boolean isBlocked = false;
                        for (String s : blackString) {
                            if (content.contains(s)) {
                                isBlocked = true;
                            }
                        }
                        if (!isBlocked) {
                            LinkedList<String> groupMsg = msgMap.get(group_uid);
                            if (groupMsg.contains(content)) {
                                if (!content.equals(lastRepeat)) {
                                    lastRepeat = content;
                                    groupUid = group_uid;
                                    System.out.println("Gottya!");
                                    return true;
                                }
                            }
                            if (groupMsg.size() >= 5) {
                                groupMsg.remove(groupMsg.size() - 1);
                            }
                            groupMsg.add(0, content);
                            msgMap.put(group_uid, groupMsg);
                        }
                    } else {
                        msgMap.put(group_uid, new LinkedList<String>());
                    }
                }
            }
        }
        return false;
    }

    public void onHandleMsg() {
        try {
            HttpUtil.sendGet("http://127.0.0.1:5000/openqq/send_group_message?uid=" + groupUid + "&content=" +
                    URLDecoder.decode(content, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

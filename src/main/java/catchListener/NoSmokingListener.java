package catchListener;

import catchListener.IOnCatchListener;
import util.HttpUtil;

/**
 * Created by tangyifeng on 2017/3/26.
 * Email: yifengtang_hust@outlook.com
 */
public class NoSmokingListener implements IOnCatchListener {

    private String content;
    private String groupUid;
    private String keyWord;
    private String keySymbol;

    public NoSmokingListener(String groupUid, String keyWord, String keySymbol) {
        this.groupUid = groupUid;
        this.keyWord = keyWord;
        this.keySymbol = keySymbol;
    }

    public boolean onGetMsg(String json) {
        System.out.println(json);
        String[] jsons = json.split("\"");
        for (int i = 0; i < jsons.length; i++) {
            if (jsons[i].equals("content")) {
                content = jsons[i + 2];
                System.out.println(content);
            }
            if (jsons[i].equals("group_uid")){
                if (jsons[i + 1].equals(":" + groupUid + ",")) {
                    if (content.contains(keyWord) && content.contains(keySymbol)) {
                        System.out.println("Gottya");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void onHandleMsg() {
        try{
            int id = Integer.parseInt(content.split(keySymbol)[1].trim());
            System.out.println(id);
            HttpUtil.sendGet("http://127.0.0.1:5000/openqq/shutup_group_member?group_uid=" + groupUid + "&member_uid=" + id + "&time=60");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

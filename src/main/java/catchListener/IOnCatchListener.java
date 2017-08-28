package catchListener;

/**
 * Created by tangyifeng on 2017/3/26.
 * Email: yifengtang_hust@outlook.com
 */
public interface IOnCatchListener {
    boolean onGetMsg(String json);
    void onHandleMsg();
}
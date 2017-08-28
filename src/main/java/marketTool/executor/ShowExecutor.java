package marketTool.executor;

import marketTool.NetworkHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by tangyifeng on 2017/8/28.
 * Email: yifengtang_hust@outlook.com
 */
public class ShowExecutor extends ICommandExecutor {

    public ShowExecutor() {
        numArg = 1;
    }

    public String execute() {
        String arg = args.get(0);
        NetworkHelper helper = new NetworkHelper();
        helper.getData(arg);
        return "symbol:_" + helper.getSymbol()
                + ",_last_price:_" + helper.getLastPrice()
                + ",_bid_price:_" + helper.getBidPrice()
                + ",_ask_price:_" + helper.getAskPrice();
    }
}

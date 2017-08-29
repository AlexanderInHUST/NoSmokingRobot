package marketTool.executor;

import marketTool.util.NetworkHelper;

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

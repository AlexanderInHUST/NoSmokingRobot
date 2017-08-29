package marketTool.executor;

import marketTool.util.NetworkHelper;

/**
 * Created by tangyifeng on 2017/8/28.
 * Email: yifengtang_hust@outlook.com
 */
public class MoneyExecutor extends ICommandExecutor {

    private static final double COST = 5000.89;
    private static final double ETH_COST = 2211.98 * 0.0005 + 2200 * 1.6812;
    private static final double ICO_COST = 222.0049 * 5.86;
    private static final double ETH_NUM = 1.6812;
    private static final double ICO_NUM = 222.0049;

    public MoneyExecutor() {
        numArg = 0;
    }

    public String execute() {
        NetworkHelper helper = new NetworkHelper();
        double curMoney, curEth, curIco;
        double ethChange, icoChange;
        helper.getData("eth");
        curEth = helper.getLastPrice() * ETH_NUM;
        helper.getData("ico");
        curIco = helper.getLastPrice() * ICO_NUM;
        ethChange = curEth - ETH_COST;
        icoChange = curIco - ICO_COST;
        curMoney = curEth + curIco - COST;
        StringBuilder builder = new StringBuilder();

        builder.append("ETH_change:_");
        if (ethChange < 0) {
            builder.append("d_");
        } else {
            builder.append("i_");
        }
        builder.append(Math.abs(ethChange));
        builder.append(",");

        builder.append("ICO_change:_");
        if (icoChange < 0) {
            builder.append("d_");
        } else {
            builder.append("i_");
        }
        builder.append(Math.abs(icoChange));
        builder.append(",");

        builder.append("All_change:_");
        if (curMoney < 0) {
            builder.append("d_");
        } else {
            builder.append("i_");
        }
        builder.append(Math.abs(curMoney));
        builder.append(".");
        return builder.toString();
    }
}

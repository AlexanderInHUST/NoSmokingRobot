package marketTool.executor;

import marketTool.util.NetworkHelper;

/**
 * Created by tangyifeng on 2017/8/28.
 * Email: yifengtang_hust@outlook.com
 */
public class MoneyExecutor extends ICommandExecutor {

    private static final double COST = 5000.89;
    private static final double ETH_COST = 2211.98 * 0.0005 + 2200 * 1.6812 - 0.001 * 2525;
    private static final double ICO_FINAL = 222.0049 * (4.62 - 5.86);
    private static final double LTC_COST = 2.094 * 488.68 + 2.065 * 506.26 - 499.9 * 2.09 + 0.001 * 508;
    private static final double ETH_NUM = 1.6812;
    private static final double LTC_NUM = 2.063;

    public MoneyExecutor() {
        numArg = 0;
    }

    public String execute() {
        NetworkHelper helper = new NetworkHelper();
        double curMoney, curEth, curLtc;
        double ethChange, ltcChange;

        helper.getData("eth");
        curEth = helper.getLastPrice() * ETH_NUM;
        helper.getData("ltc");
        curLtc = helper.getLastPrice() * LTC_NUM;

        ethChange = curEth - ETH_COST;
        ltcChange = curLtc - LTC_COST;
        curMoney = curEth + curLtc - COST;
        StringBuilder builder = new StringBuilder();

        builder.append("ETH_change:_");
        if (ethChange < 0) {
            builder.append("d_");
        } else {
            builder.append("i_");
        }
        builder.append(Math.abs(ethChange));
        builder.append(",");

        builder.append("LTC_change:_");
        if (ltcChange < 0) {
            builder.append("d_");
        } else {
            builder.append("i_");
        }
        builder.append(Math.abs(ltcChange));
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

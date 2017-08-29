package marketTool.util;

/**
 * Created by tangyifeng on 2017/8/28.
 * Email: yifengtang_hust@outlook.com
 */
public class MarketEntity {

    private String symbol;
    private double bidPrice;
    private double askPrice;
    private double lastPrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }
}

package marketTool.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by tangyifeng on 2017/8/28.
 * Email: yifengtang_hust@outlook.com
 */
public class NetworkHelper {

    private static final int ETH_ID = 1;
    private static final int ICO_ID = 2;
    private static final String ICO_ADD = "https://plus-api.btcchina.com/market/ticker?symbol=ICOCNY";
    private static final String ETH_ADD = "https://plus-api.btcchina.com/market/ticker?symbol=ETHCNY";
    private static HashMap<Integer, String> urlMap;
    private static HashMap<String, Integer> whoMap;

    private MarketEntity entity;

    public NetworkHelper() {
        if (urlMap == null) {
            urlMap = new HashMap<Integer, String>();
            whoMap = new HashMap<String, Integer>();
            initialUrlMap();
            initialWhoMap();
        }
    }

    private static void initialUrlMap() {
        urlMap.put(ETH_ID, ETH_ADD);
        urlMap.put(ICO_ID, ICO_ADD);
    }

    private static void initialWhoMap() {
        whoMap.put("eth", ETH_ID);
        whoMap.put("ico", ICO_ID);
    }

    public void getData(String whoS) {
        int who = whoMap.get(whoS);
        String marketData = getMarketData(who);
        entity = parseEntity(marketData);
    }

    public String getSymbol() {
        return entity.getSymbol();
    }

    public double getLastPrice() {
        return entity.getLastPrice();
    }

    public double getBidPrice() {
        return entity.getBidPrice();
    }

    public double getAskPrice() {
        return entity.getAskPrice();
    }

    private String getMarketData(int which) {
        try {
            URL url;
            String urlS;
            URLConnection connection;
            urlS = urlMap.get(which);
            url = new URL(urlS);
            connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String s;
            StringBuilder builder = new StringBuilder();
            while ((s = reader.readLine()) != null) {
                builder.append(s);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MarketEntity parseEntity(String json) {
        MarketEntity entity = new MarketEntity();
        String[] jsons = json.split("\"");
        for (int i = 0; i < jsons.length; i++) {
            if (jsons[i].equals("Last")) {
                entity.setLastPrice(Double.parseDouble(jsons[i + 1].split(":|,")[1]));
            }
            if (jsons[i].equals("Symbol")) {
                entity.setSymbol(jsons[i + 2]);
            }
            if (jsons[i].equals("BidPrice")) {
                entity.setBidPrice(Double.parseDouble(jsons[i + 1].split(":|,")[1]));
            }
            if (jsons[i].equals("AskPrice")) {
                entity.setAskPrice(Double.parseDouble(jsons[i + 1].split(":|,")[1]));
            }
        }
        return entity;
    }
}

import marketTool.util.ImageVerifier;

import javax.mail.Session;

/**
 * Created by tangyifeng on 2017/3/26.
 * Email: yifengtang_hust@outlook.com
 */
public class MainTest {

    public static void main(String[] args) {
//        Server server = new Server(5001);
//        server.start(new MarketListener("639339461"));

//        MarketCmd cmd = new MarketCmd("market -m");
//        System.out.println(cmd.executeCmd());

        ImageVerifier verifier = new ImageVerifier();
        verifier.startVerifier("/Users/tangyifeng/Desktop/text.txt");
    }

}

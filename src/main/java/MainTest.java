import catchListener.MarketListener;
import marketTool.MarketCmd;
import marketTool.util.ImageVerifier;
import server.Server;
import util.MailSender;

import javax.mail.Session;
import java.io.File;

/**
 * Created by tangyifeng on 2017/3/26.
 * Email: yifengtang_hust@outlook.com
 */
public class MainTest {

    public static void main(String[] args) {

//        new Thread(new Runnable() {
//            public void run() {
//                ImageVerifier verifier = new ImageVerifier();
//                final MailSender mailSender = new MailSender();
//                verifier.startVerifier("/tmp/mojo_webqq_qrcode_default.png", new ImageVerifier.OnChangedListener() {
//                    public void onChange() {
//                        mailSender.sendMail("[WebQQ]Qcode has been sent.", "Scan to login",
//                                "yifengtang_hust@outlook.com",
//                                new File("/tmp/mojo_webqq_qrcode_default.png"));
//                    }
//                });
//            }
//        }).start();
//
//        Server server = new Server(5001);
//        server.start(new MarketListener("481280255"));

        MarketCmd cmd = new MarketCmd("market -s ico");
        System.out.println(cmd.executeCmd());

//        MailSender sender = new MailSender();
//        System.out.println(sender.sendMail("test", "test", "yifengtang_hust@outlook.com",
//                new File("/Users/tangyifeng/Desktop/text.txt")));
    }

}

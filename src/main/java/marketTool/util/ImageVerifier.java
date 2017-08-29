package marketTool.util;

import util.FileVerifier;

/**
 * Created by tangyifeng on 2017/8/29.
 * Email: yifengtang_hust@outlook.com
 */
public class ImageVerifier {

    public void startVerifier(String file) {
        FileVerifier verifier = new FileVerifier();
        while (true) {
            try {
                Thread.sleep(1000);
                if (!verifier.verify(file)) {
                    System.out.println("File has changed!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

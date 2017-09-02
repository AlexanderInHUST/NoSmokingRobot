package marketTool.util;

import util.FileVerifier;

/**
 * Created by tangyifeng on 2017/8/29.
 * Email: yifengtang_hust@outlook.com
 */
public class ImageVerifier {

    private OnChangedListener listener;

    public interface OnChangedListener {
        public void onChange();
    }

    public void startVerifier(String file, OnChangedListener listener) {
        FileVerifier verifier = new FileVerifier();
        while (true) {
            try {
                Thread.sleep(1000);
                if (!verifier.verify(file)) {
                    listener.onChange();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

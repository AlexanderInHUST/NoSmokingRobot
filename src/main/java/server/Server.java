package server;

import catchListener.IOnCatchListener;
import util.HttpUtil;

import java.io.*;
import java.net.ServerSocket;

/**
 * Created by tangyifeng on 2017/3/26.
 * Email: yifengtang_hust@outlook.com
 */
public class Server {

    private ServerSocket serverSocket;
    private Thread thread;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(final IOnCatchListener listener) {
        thread = new Thread(new Runnable() {
            public void run() {
                while(true) {
                    String json = getJson();
                    if(listener.onGetMsg(json)) {
                        listener.onHandleMsg();
                    }
                }
            }
        });
        thread.start();
    }

    public void close() {
        try {
            thread.interrupt();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJson() {
        try {
            return HttpUtil.getJson(serverSocket.accept());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
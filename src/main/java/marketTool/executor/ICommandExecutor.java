package marketTool.executor;

import java.util.ArrayList;

/**
 * Created by tangyifeng on 2017/8/28.
 * Email: yifengtang_hust@outlook.com
 */
public abstract class ICommandExecutor {

    protected ArrayList<String> args;
    protected int numArg;

    public void setArgs(ArrayList<String> args) {
        this.args = args;
    }

    public int getNumArg() {
        return numArg;
    }

    public abstract String execute();


}

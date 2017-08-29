package marketTool.util;

import marketTool.executor.HelpExecutor;
import marketTool.executor.ICommandExecutor;
import marketTool.executor.MoneyExecutor;
import marketTool.executor.ShowExecutor;

import java.util.HashMap;

/**
 * Created by tangyifeng on 2017/8/28.
 * Email: yifengtang_hust@outlook.com
 */
public class Commands {

    protected static final String _HELP = "-h";
    protected static final String _SHOW = "-s";
    protected static final String _MONEY = "-m";
    protected static HashMap<String, Class<? extends ICommandExecutor>> argMap;

    public Commands() {
        if (argMap == null) {
            argMap = new HashMap<String, Class<? extends ICommandExecutor>>();
            initialMap();
        }
    }

    public HashMap<String, Class<? extends ICommandExecutor>> getArgMap() {
        return argMap;
    }

    private static void initialMap() {
        argMap.put(_HELP, HelpExecutor.class);
        argMap.put(_SHOW, ShowExecutor.class);
        argMap.put(_MONEY, MoneyExecutor.class);
    }
}

package marketTool.util;

import marketTool.executor.ICommandExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by tangyifeng on 2017/8/28.
 * Email: yifengtang_hust@outlook.com
 */
public class ExecutorGenerator {

    private HashMap<String, Class<? extends ICommandExecutor>> argsMap;

    public ExecutorGenerator(HashMap<String, Class<? extends ICommandExecutor>> argsMap) {
        this.argsMap = argsMap;
    }

    public ArrayList<ICommandExecutor> generate(ArrayList<String> args) throws ArgsNotFoundException {
        Iterator<String> it = args.iterator();
        ArrayList<ICommandExecutor> executors = new ArrayList<ICommandExecutor>();
        while(it.hasNext()) {
            String cmd = it.next();
            if (argsMap.keySet().contains(cmd)) {
                try {
                    ICommandExecutor executor = argsMap.get(cmd).newInstance();
                    ArrayList<String> a = new ArrayList<String>();
                    for (int i = 0; i < executor.getNumArg(); i++) {
                        if (it.hasNext()) {
                            a.add(it.next());
                        } else {
                            throw new ArgsNotFoundException();
                        }
                    }
                    executor.setArgs(a);
                    executors.add(executor);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                throw new ArgsNotFoundException();
            }
        }
        return executors;
    }

}

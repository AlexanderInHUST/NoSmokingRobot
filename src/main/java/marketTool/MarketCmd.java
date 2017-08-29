package marketTool;

import marketTool.executor.ICommandExecutor;
import marketTool.util.ArgsNotFoundException;
import marketTool.util.CommandParser;
import marketTool.util.Commands;
import marketTool.util.ExecutorGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tangyifeng on 2017/8/28.
 * Email: yifengtang_hust@outlook.com
 */
public class MarketCmd {

    private ArrayList<ICommandExecutor> executors;

    public MarketCmd(String cmd) {
        CommandParser parser;
        ArrayList<String> commands;
        HashMap<String, Class<? extends ICommandExecutor>> argMap;
        ExecutorGenerator generator;

        parser = new CommandParser(cmd);
        commands = parser.parse();
        argMap = new Commands().getArgMap();
        generator = new ExecutorGenerator(argMap);
        try {
            executors = generator.generate(commands);
        } catch (ArgsNotFoundException e) {
            executors = null;
        }
    }

    public String executeCmd() {
        StringBuilder builder = new StringBuilder();
        if (executors != null) {
            for (ICommandExecutor executor : executors) {
                builder.append(executor.execute());
                builder.append("___");
            }
            return builder.toString();
        }
        return "Here_comes_a_snakeskin_exception.";
    }
}

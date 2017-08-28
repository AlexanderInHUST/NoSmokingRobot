package marketTool;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tangyifeng on 2017/8/28.
 * Email: yifengtang_hust@outlook.com
 */
public class CommandParser {

    private String command;
    private ArrayList<String> commandList;

    public CommandParser(String command) {
        this.command = command;
        commandList = new ArrayList<String>();
    }

    public ArrayList<String> parse() {
        String[] commands = command.split(" ");
        if (!commands[0].equals("market")) {
            return null;
        } else {
            commandList.addAll(Arrays.asList(commands));
            commandList.remove(0);
        }
        return commandList;
    }
}

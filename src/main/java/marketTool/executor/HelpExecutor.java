package marketTool.executor;

/**
 * Created by tangyifeng on 2017/8/28.
 * Email: yifengtang_hust@outlook.com
 */
public class HelpExecutor extends ICommandExecutor {

    public HelpExecutor() {
        numArg = 0;
    }

    public String execute() {
        return "Im_too_tired_to_write_help_info,_good_luck_guys.";
    }
}

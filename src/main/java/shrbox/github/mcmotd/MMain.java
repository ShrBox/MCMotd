package shrbox.github.mcmotd;

import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.message.GroupMessageEvent;

class MMain extends PluginBase {
    public static int api = 1;
    public void onEnable() {
        getEventListener().subscribeAlways(GroupMessageEvent.class, (GroupMessageEvent e) -> {
            if (e.getMessage().contentToString().contains("!motdpe ")) {
                MThread mThread = new MThread();
                mThread.boot(e);
            }
        });
    }
}
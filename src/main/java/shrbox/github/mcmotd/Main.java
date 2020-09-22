package shrbox.github.mcmotd;

import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.message.GroupMessageEvent;

class Main extends PluginBase {
    public void onEnable() {
        getEventListener().subscribeAlways(GroupMessageEvent.class, (GroupMessageEvent e) -> {
            if (e.getMessage().contentToString().contains("!motdpe ")) {
                Thread thread = new Thread();
                thread.boot(e);
            }
        });
    }
}
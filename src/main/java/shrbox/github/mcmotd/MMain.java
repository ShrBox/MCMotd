package shrbox.github.mcmotd;

import com.google.gson.Gson;
import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageUtils;

class MMain extends PluginBase {
    public void onLoad() {}

    public void onEnable() {
        getEventListener().subscribeAlways(GroupMessageEvent.class, (GroupMessageEvent e) -> {
            String msg = e.getMessage().contentToString();
            if (msg.contains("!motdpe ")) {
                MThread mThread = new MThread();
                mThread.boot(e);
            }
        });
    }

}          
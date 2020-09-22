package shrbox.github.mcmotd;

import com.google.gson.Gson;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageUtils;

public class Thread extends java.lang.Thread {
    GroupMessageEvent e;
    public void boot(GroupMessageEvent event) {
        e = event;
        start();
    }
    @Override
    public void run() {
        String msg = e.getMessage().contentToString();
        String domain = msg.replace("!motdpe", "").toLowerCase().trim();
        if (e.getMessage().contentToString().equals("")) {
            e.getGroup().sendMessage(MessageUtils.newChain(new At(e.getSender()))
                    .plus("请输入一个地址"));
            return;
        }
        String port = "19132";
        if (domain.contains(":")) {
            port = msg.split(":")[1];
        }
        String Json = Connection.getURL(domain, port);
        if (Json.equals("")) {
            e.getGroup().sendMessage(("[MCMotd] 接口无响应"));
            return;
        }
        Gson gson = new Gson();
        Serverinfo serverinfo = gson.fromJson(Json, Serverinfo.class);
        if (serverinfo.status.equals("offline")) {
            e.getGroup().sendMessage("[MCMotd] 服务器不在线");
            return;
        }
        e.getGroup().sendMessage("[MCMotd]\nMotd: " + serverinfo.motd + "\n协议版本: " + serverinfo.agreement + "\n游戏版本: " + serverinfo.version + "\n在线: " + serverinfo.online + "/" + serverinfo.max + "\n游戏模式: " + serverinfo.gamemode);
    }
}

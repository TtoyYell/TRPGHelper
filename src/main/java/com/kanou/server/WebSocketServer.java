package com.kanou.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanou.util.StringUtils;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/6/30 16:24
 */
@ServerEndpoint("/SocketServer/{userId}")
@Component
public class WebSocketServer {

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     * key是使用该端点的用户id,value是WebSocketServer这个端点本身
     * */
    private static final ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    static Log log= LogFactory.getLog(WebSocketServer.class);

    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;

    /**接收userId*/
    private String userId="";

    /**
     * 连接建立成功调用的方法
     * */
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.session = session;
        this.userId=userId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //加入set中
            webSocketMap.put(userId,this);
        }else{
            //加入set中
            webSocketMap.put(userId,this);
            addOnlineCount();
            //在线数加1
        }

        log.info("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("用户:"+userId+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * */
    @SneakyThrows
    @OnMessage
    public void onMessage(String message, Session session){
        log.info("用户消息:  用户id"+userId+",报文:"+message);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(message, Map.class);
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)) {
            //追加发送人(防止串改)
            map.put("fromUserId",this.userId);
            String toUserId=map.get("toUserId");
            //传送给对应toUserId用户的websocket
            if(StringUtils.isNotBlank(toUserId)&&webSocketMap.containsKey(toUserId)){
                // 发送
                webSocketMap.get(toUserId).sendMessage(objectMapper.writeValueAsString(map));
                log.info("服务器消息，已经把用户:"+this.userId+" 的消息发送给用户:"+toUserId);
            }else{
                log.error("请求的userId:"+toUserId+"不在该服务器上");
                //否则不在这个服务器上，发送到mysql或者redis
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:"+userId+"，报文:"+message);
        if(StringUtils.isNotBlank(userId)&&webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            log.error("用户"+userId+",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}

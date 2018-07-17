package com.example.demo.endpoint;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

//@ServerEndpoint(value = "/websocket/{user}")
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。  
    private static int onlineCount = 0; 
    
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。  
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    
    //与某个客户端的连接会话，需要通过它来给客户端发送数据  
    private Session session;  
	
    /**
     * 连接建立成功调用的方法
     * @param session
     */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;  
        webSocketSet.add(this);     //加入set中  
        addOnlineCount();           //在线数加1  
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());  
        try {  
             sendMessage("连接成功");  
        } catch (IOException e) {  
            logger.error("websocket IO异常");  
        }  
	}
	
//	//连接打开时执行  
//	@OnOpen  
//	public void onOpen(@PathParam("user") String user, Session session) {
//		currentUser = user;
//		System.out.println("Connected ... " + session.getId());
//	} 
	
	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);  //从set中删除  
        subOnlineCount();           //在线数减1  
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount()); 
	}
	
	/**
	 * 收到客户端消息后调用的方法
	 * @param session
	 * @param message
	 */
	@OnMessage
	public void onMessage(Session session, String message) {
		logger.debug("onMessage");
		//群发消息  
        for (WebSocketServer item : webSocketSet) {  
            try {  
                item.sendMessage(message);  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
	}
	
	@OnError  
    public void onError(Session session, Throwable error) {  
        logger.error("error");  
        error.printStackTrace();  
    }
	
	/**
	 * 发送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {  
        this.session.getBasicRemote().sendText(message);  
    }
	
	/**
	 * 群发消息
	 * @param message
	 * @throws IOException
	 */
	public static void sendInfo(String message) throws IOException {  
        logger.info(message);  
        for (WebSocketServer item : webSocketSet) {  
            try {  
                item.sendMessage(message);  
            } catch (IOException e) {  
                continue;  
            }  
        }  
    }
	
	public static synchronized int getOnlineCount() {  
        return WebSocketServer.onlineCount;  
    }  
  
    public static synchronized void addOnlineCount() {  
        WebSocketServer.onlineCount++;  
    }  
  
    public static synchronized void subOnlineCount() {  
        WebSocketServer.onlineCount--;  
    }
}

package websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ws_chat")
public class WebSocketEndpoint {

    private static Set<Session> peers = new CopyOnWriteArraySet<>();

    @OnOpen
    public void connect(Session peer) {
        peers.add(peer);
    }

    @OnClose
    public void disconnect(Session peer) {
        peers.remove(peer);
    }

    @OnMessage
    public void echoTextMessage(String message) throws IOException {
        for (Session peer : peers) {
            peer.getBasicRemote().sendText(message);
        }
    }

    @OnMessage
    public void echoBinaryMessage(Session session, ByteBuffer buffer) throws IOException {
        for (Session peer : peers) {
            peer.getBasicRemote().sendBinary(buffer);
        }
    }

}
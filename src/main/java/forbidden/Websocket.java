package forbidden;

import io.quarkus.logging.Log;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

@ServerEndpoint("/ws")
public class Websocket {

    static final Map<String, Session> SESSIONS = new ConcurrentHashMap<>();

    Websocket() {
    }

    @OnMessage
    public void onMessage(Session session, String messageString) {
        Log.info("onMessage> " + " " + messageString);
        Thread.startVirtualThread(() -> {
            System.err.println(messageString);
            JSONObject aiChatOutput = new JSONObject();
            aiChatOutput.put("response", "I have no clue.").put("mine", false);
            JSONObject answer = new JSONObject().put("type", "ai_chat").put("ai_chat_output", aiChatOutput);
            send(session, answer);
        });
    }

    void send(Session session, JSONObject answer) {
        session.getAsyncRemote().sendText(answer.toString(), outcome -> {
            if (outcome.getException() != null) {
                Log.warn("Unable to send message: ", outcome.getException());
            }
        });
    }

    @OnOpen
    public void onOpen(Session session) {
        Log.info("onOpen");
        SESSIONS.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session) {
        Log.info("onClose");
        SESSIONS.values().remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        Log.error("onError!", throwable);
        SESSIONS.values().remove(session);
    }
}

package main.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {
    private final Map<UUID, GameSession> activeSessions;

    public SessionManager() {
        this.activeSessions = new HashMap<>();
    }

    public UUID createSession() {
        GameSession session = new GameSession();
        UUID sessionId = UUID.randomUUID();
        activeSessions.put(sessionId, session);

        return sessionId;
    }

    public GameSession getSession(UUID sessionId) {
        return activeSessions.get(sessionId);
    }

    public void removeSession(UUID sessionId) {
        activeSessions.remove(sessionId);
    }
}

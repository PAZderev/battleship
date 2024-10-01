package main.utils.factory;

import main.engine.SessionManager;

public class SessionManagerFactory {

    private static SessionManager instance;

    private SessionManagerFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
}

package server.sessions;

public class UserSession {
    private static String adminName;

    public static void setAdminName(String name) {
        adminName = name;
    }

    public static String getAdminName() {
        return adminName;
    }
}

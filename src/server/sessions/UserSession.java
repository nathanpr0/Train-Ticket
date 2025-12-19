package server.sessions;

public class UserSession {

    private static String adminId;
    private static String adminName;

    public static void setAdmin(String id, String name) {
        adminId = id;
        adminName = name;
    }

    public static String getAdminId() {
        return adminId;
    }

    public static String getAdminName() {
        return adminName;
    }

    public static void clear() {
        adminId = null;
        adminName = null;
    }
}

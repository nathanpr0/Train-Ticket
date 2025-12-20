package server.controller.booking_menu;

public class BiayaAdmin {

    public static int getFee(String kelas) {
        return switch (kelas) {
            case "Economy" ->
                2000;
            case "Business" ->
                4000;
            case "Executive" ->
                7000;
            default ->
                0;
        };
    }
}

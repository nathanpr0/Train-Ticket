package server.models;

// UNTUK MENDAPATKAN DATA TABLE DARI SCHEDULE PADA InputBookingTicket.java
public class ScheduleItems {

    private final int idSchedule;
    private final String destination;
    private final String kelas;
    private final String departure;
    private final int price;

    public ScheduleItems(int idSchedule,
                        String destination,
                        String kelas,
                        String departure,
                        int price) {
        this.idSchedule = idSchedule;
        this.destination = destination;
        this.kelas = kelas;
        this.departure = departure;
        this.price = price;
    }

    public int getIdSchedule() { return idSchedule; }
    public String getDestination() { return destination; }
    public String getKelas() { return kelas; }
    public String getDeparture() { return departure; }
    public int getPrice() { return price; }

    @Override
    public String toString() {
        return destination + " | " + kelas + " | " + departure;
    }
}

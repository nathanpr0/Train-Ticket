package server.models;

// UNTUK MENGIRIM HASIL InputBookingTicket.java di tampilkan ke GUI
public class BookingSummary {
    private String origin;
    private String destination;
    private String bookingName;
    private String date;
    private int cost;
    private int additionalCost;
    private int totalCost;

    // GETTER & SETTER
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getBookingName() { return bookingName; }
    public void setBookingName(String bookingName) { this.bookingName = bookingName; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    public int getAdditionalCost() { return additionalCost; }
    public void setAdditionalCost(int additionalCost) { this.additionalCost = additionalCost; }

    public int getTotalCost() { return totalCost; }
    public void setTotalCost(int totalCost) { this.totalCost = totalCost; }
}
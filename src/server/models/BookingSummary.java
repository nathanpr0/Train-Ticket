package server.models;

// UNTUK MENGIRIM HASIL InputBookingTicket.java di tampilkan ke GUI
public class BookingSummary {
    private String bookingCode;
    private String status;
    private String bookingName;
    private String date;

    private String origin;
    private String destination;
    private String trainNumber;
    private String kelas;
    private int carriages;
    private String departureTime;

    private int cost;
    private int additionalCost;
    private int totalCost;

    // GETTER & SETTER
    public String getBookingCode(){ return bookingCode; }
    public void setBookingCode(String bookingCode) { this.bookingCode = bookingCode; }
    
    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    
    public String getTrainNumber() { return trainNumber; }
    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }
    
    public String getKelas() { return kelas; }
    public void setKelas(String kelas) { this.kelas = kelas; }
    
    public int getCarriages() { return carriages; }
    public void setCarriages(int carriages) { this.carriages = carriages; }
    
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

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
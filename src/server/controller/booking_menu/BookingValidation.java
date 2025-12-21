/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.controller.booking_menu;

/**
 *
 * @author Nathan
 */
public class BookingValidation {
    
    // VALIDASI INPUT
    public void cekInputKosong(String bookingName,
            String destination,
            String kelas,
            String price,
            String departure,
            String carriage) throws Exception {

        if (bookingName.isEmpty()
                || destination == null
                || kelas == null
                || price == null
                || departure == null
                || carriage == null) {
            throw new Exception("Semua data booking wajib diisi!");
        }
    }
}

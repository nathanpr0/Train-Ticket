package client.views;

import client.components.RoundedBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import server.Koneksi;
import server.controller.booking_menu.InputBookingTicket;
import server.models.BookingSummary;
import server.sessions.UserSession;

public class BookingMenu extends javax.swing.JFrame {

    private Map<String, Integer> scheduleMap = new HashMap<>();
    private Map<String, Integer> priceMap = new HashMap<>();

    private int selectedScheduleId = -1;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BookingMenu.class.getName());

    /**
     * Creates new form BookingMenu
     */
    public BookingMenu() {
        initComponents();

        // LOAD DATA AWAL
        loadDestinationCombo();

        // SET EVENT LISTENER
        cmbDestination.addActionListener(e -> loadClassCombo());
        cmbClass.addActionListener(e -> loadDepartureCombo());
        cmbDepartureTime.addActionListener(e -> loadCarriagesAndPrice());
        cmbCarriage.addActionListener(this::cmbCarriageActionPerformed);

        String namaAdmin = UserSession.getAdminName();

        if (namaAdmin == null || namaAdmin.isEmpty()) {
            lblProfile.setText("Profile");
        } else {
            lblProfile.setText(namaAdmin);
        }

    }

    // MEMINDAHKAN DATA destination KE COMBOBOX
    private void loadDestinationCombo() {
        try (Connection conn = Koneksi.getConnection()) {

            // MEMBERSIHKAN COMBO BOX MENJADI KOSONG SUPAYA BISA DI LOAD PAKE DATABASE
            cmbDestination.removeAllItems();

            // MENGAMBIL HASIL QUERY destination DARI TABEL SCHEDULE
            InputBookingTicket ibt = new InputBookingTicket();
            ResultSet rs = ibt.loadDestinations(conn);

            while (rs.next()) {
                cmbDestination.addItem(rs.getString("destination"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // MEMINDAHKAN DATA `class` KE COMBOBOX
    private void loadClassCombo() {
        try (Connection conn = Koneksi.getConnection()) {

            if (cmbDestination.getSelectedItem() == null) {
                return;
            }

            String destination = cmbDestination.getSelectedItem().toString();

            // MEMBERSIHKAN COMBO BOX MENJADI KOSONG SUPAYA BISA DI LOAD PAKE DATABASE
            cmbClass.removeAllItems();

            // MENGAMBIL HASIL QUERY `Class` DARI TABEL SCHEDULE
            InputBookingTicket ibt = new InputBookingTicket();
            ResultSet rs = ibt.loadClasses(conn, destination);

            while (rs.next()) {
                cmbClass.addItem(rs.getString("class"));

            }
            System.out.println("loadClassCombo dipanggil");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // MEMINDAHKAN DATA `departure_time` KE COMBOBOX
    private void loadDepartureCombo() {
        try (Connection conn = Koneksi.getConnection()) {
            cmbDepartureTime.removeAllItems();

            String destination = cmbDestination.getSelectedItem().toString();
            String kelas = cmbClass.getSelectedItem().toString();

            InputBookingTicket ibt = new InputBookingTicket();
            ResultSet rs = ibt.loadDepartureTimes(conn, destination, kelas);

            while (rs.next()) {
                cmbDepartureTime.addItem(rs.getString("departure_time"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // MEMINDAHKAN DATA train_number, carriages KE COMBOBOX & price KE TEXT FIELD
    private void loadCarriagesAndPrice() {
        try (Connection conn = Koneksi.getConnection()) {

            if (cmbDepartureTime.getSelectedItem() == null) {
                return;
            }

            String destination = cmbDestination.getSelectedItem().toString();
            String kelas = cmbClass.getSelectedItem().toString();
            String departure = cmbDepartureTime.getSelectedItem().toString();

            cmbCarriage.removeAllItems();
            scheduleMap.clear();
            priceMap.clear();

            InputBookingTicket ibt = new InputBookingTicket();
            ResultSet rs = ibt.loadTrainCarriageAndPrice(
                    conn, destination, kelas, departure
            );

            while (rs.next()) {
                String display = rs.getString("train_number")
                        + " - "
                        + rs.getInt("carriages");

                int idSchedule = rs.getInt("id_schedule");
                int price = rs.getInt("price");

                cmbCarriage.addItem(display);
                scheduleMap.put(display, idSchedule);
                priceMap.put(display, price);
            }

            if (cmbCarriage.getItemCount() > 0) {
                cmbCarriage.setSelectedIndex(0);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.s
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        roundedPanel11 = new client.components.RoundedPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cmbDestination = new client.components.RoundedComboBox();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cmbClass = new client.components.RoundedComboBox();
        jLabel24 = new javax.swing.JLabel();
        cmbDepartureTime = new client.components.RoundedComboBox();
        jLabel25 = new javax.swing.JLabel();
        txtBookingName = new client.components.RoundedTextField();
        txtPrice = new client.components.RoundedTextField();
        jLabel65 = new javax.swing.JLabel();
        cmbCarriage = new client.components.RoundedComboBox();
        jLabel19 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        roundedPanel13 = new client.components.RoundedPanel();
        jLabel46 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblFromStation = new javax.swing.JLabel();
        lblDestination = new javax.swing.JLabel();
        lblBookingName = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblCost = new javax.swing.JLabel();
        lblAdditionalCost = new javax.swing.JLabel();
        lblTotalCost = new javax.swing.JLabel();
        confirm_booking_btn = new client.components.RoundedButton();
        roundedButton2 = new client.components.RoundedButton();
        see_schedule_btn = new client.components.RoundedButton();
        jPanel2 = new javax.swing.JPanel();
        roundedPanel12 = new client.components.RoundedPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        roundedButton4 = new client.components.RoundedButton();
        roundedButton5 = new client.components.RoundedButton();
        roundedButton6 = new client.components.RoundedButton();
        lblProfile = new javax.swing.JLabel();
        nav_admin = new client.components.RoundedButton();
        nav_schedule = new client.components.RoundedButton();
        nav_booked_data = new client.components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));

        roundedPanel11.setBackground(new java.awt.Color(68, 68, 68));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Booking Ticket");

        javax.swing.GroupLayout roundedPanel11Layout = new javax.swing.GroupLayout(roundedPanel11);
        roundedPanel11.setLayout(roundedPanel11Layout);
        roundedPanel11Layout.setHorizontalGroup(
            roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel11Layout.setVerticalGroup(
            roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
        );

        jLabel21.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(68, 68, 68));
        jLabel21.setText("Booking Name");

        cmbDestination.setBorder(null);
        cmbDestination.setForeground(new java.awt.Color(68, 68, 68));
        cmbDestination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDestinationActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(68, 68, 68));
        jLabel22.setText("Destination Station");

        jLabel23.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(68, 68, 68));
        jLabel23.setText("Class");

        cmbClass.setBorder(null);
        cmbClass.setForeground(new java.awt.Color(68, 68, 68));
        cmbClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClassActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(68, 68, 68));
        jLabel24.setText("Depature Time");

        cmbDepartureTime.setBorder(null);
        cmbDepartureTime.setForeground(new java.awt.Color(68, 68, 68));
        cmbDepartureTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDepartureTimeActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(68, 68, 68));
        jLabel25.setText("Price");

        txtBookingName.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));
        txtBookingName.setForeground(new java.awt.Color(68, 68, 68));
        txtBookingName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBookingName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBookingNameActionPerformed(evt);
            }
        });

        txtPrice.setEditable(false);
        txtPrice.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));
        txtPrice.setForeground(new java.awt.Color(68, 68, 68));
        txtPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(68, 68, 68));
        jLabel65.setText("Carriage");

        cmbCarriage.setBorder(null);
        cmbCarriage.setForeground(new java.awt.Color(68, 68, 68));
        cmbCarriage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCarriageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBookingName, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addComponent(cmbDestination, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbClass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbDepartureTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addComponent(jLabel65)
                    .addComponent(roundedPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(cmbCarriage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addGap(9, 9, 9)
                .addComponent(txtBookingName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDepartureTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCarriage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Inter 18pt Black", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(68, 68, 68));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-train-50.png"))); // NOI18N
        jLabel19.setText("UBD Express");

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));

        roundedPanel13.setBackground(new java.awt.Color(68, 68, 68));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Summary");

        javax.swing.GroupLayout roundedPanel13Layout = new javax.swing.GroupLayout(roundedPanel13);
        roundedPanel13.setLayout(roundedPanel13Layout);
        roundedPanel13Layout.setHorizontalGroup(
            roundedPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel13Layout.setVerticalGroup(
            roundedPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(68, 68, 68)));

        jLabel26.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(68, 68, 68));
        jLabel26.setText("From Station :");

        jLabel27.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(68, 68, 68));
        jLabel27.setText("Destination Station :");

        jLabel28.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(68, 68, 68));
        jLabel28.setText("Booking Name :");

        jLabel30.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(68, 68, 68));
        jLabel30.setText("Date :");

        jLabel31.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(68, 68, 68));
        jLabel31.setText("Cost :");

        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(68, 68, 68)));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel32.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(68, 68, 68));
        jLabel32.setText("Additional Cost :");

        jLabel33.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(68, 68, 68));
        jLabel33.setText("Total Cost :");

        lblFromStation.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        lblFromStation.setForeground(new java.awt.Color(68, 68, 68));
        lblFromStation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFromStation.setText("-");

        lblDestination.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        lblDestination.setForeground(new java.awt.Color(68, 68, 68));
        lblDestination.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDestination.setText("-");

        lblBookingName.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        lblBookingName.setForeground(new java.awt.Color(68, 68, 68));
        lblBookingName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBookingName.setText("-");

        lblDate.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        lblDate.setForeground(new java.awt.Color(68, 68, 68));
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDate.setText("-");

        lblCost.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        lblCost.setForeground(new java.awt.Color(68, 68, 68));
        lblCost.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCost.setText("-");

        lblAdditionalCost.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        lblAdditionalCost.setForeground(new java.awt.Color(68, 68, 68));
        lblAdditionalCost.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAdditionalCost.setText("-");

        lblTotalCost.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        lblTotalCost.setForeground(new java.awt.Color(68, 68, 68));
        lblTotalCost.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalCost.setText("-");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel32)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblFromStation, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBookingName, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCost, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblAdditionalCost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalCost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lblFromStation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(lblDestination))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lblBookingName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(lblDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(lblCost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(lblAdditionalCost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(lblTotalCost))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        confirm_booking_btn.setBackground(new java.awt.Color(68, 68, 68));
        confirm_booking_btn.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));
        confirm_booking_btn.setText("Confirm Booking");
        confirm_booking_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirm_booking_btnActionPerformed(evt);
            }
        });

        roundedButton2.setBackground(new java.awt.Color(255, 255, 255));
        roundedButton2.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));
        roundedButton2.setForeground(new java.awt.Color(68, 68, 68));
        roundedButton2.setText("Reset");
        roundedButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundedPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(confirm_booking_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirm_booking_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundedButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        see_schedule_btn.setBackground(new java.awt.Color(68, 68, 68));
        see_schedule_btn.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));
        see_schedule_btn.setText("See Schedule");
        see_schedule_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                see_schedule_btnActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));

        roundedPanel12.setBackground(new java.awt.Color(68, 68, 68));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Ticket Reservation Information");

        javax.swing.GroupLayout roundedPanel12Layout = new javax.swing.GroupLayout(roundedPanel12);
        roundedPanel12.setLayout(roundedPanel12Layout);
        roundedPanel12Layout.setHorizontalGroup(
            roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel12Layout.setVerticalGroup(
            roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
        );

        jLabel29.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(68, 68, 68));
        jLabel29.setText("Booking Code :");

        jLabel41.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(68, 68, 68));
        jLabel41.setText("Status :");

        jLabel42.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(68, 68, 68));
        jLabel42.setText("Booking Date :");

        jLabel43.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(68, 68, 68));
        jLabel43.setText("Train Number :");

        jLabel44.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(68, 68, 68));
        jLabel44.setText("Booking Name :");

        jLabel48.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(68, 68, 68));
        jLabel48.setText("Class :");

        jLabel49.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(68, 68, 68));
        jLabel49.setText("Carriages :");

        jLabel50.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(68, 68, 68));
        jLabel50.setText("Depature Time :");

        jLabel51.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(68, 68, 68));
        jLabel51.setText("Route :");

        jLabel52.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(68, 68, 68));
        jLabel52.setText("Additional Cost :");

        jLabel53.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(68, 68, 68));
        jLabel53.setText("Total Cost :");

        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(68, 68, 68)));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(68, 68, 68)));
        jPanel3.setForeground(new java.awt.Color(68, 68, 68));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel54.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(68, 68, 68));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel54.setText("-");

        jLabel55.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(68, 68, 68));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel55.setText("-");

        jLabel56.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(68, 68, 68));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel56.setText("-");

        jLabel57.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(68, 68, 68));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel57.setText("-");

        jLabel58.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(68, 68, 68));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel58.setText("-");

        jLabel59.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(68, 68, 68));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel59.setText("-");

        jLabel60.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(68, 68, 68));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel60.setText("-");

        jLabel61.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(68, 68, 68));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel61.setText("-");

        jLabel62.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(68, 68, 68));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel62.setText("-");

        jLabel63.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(68, 68, 68));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel63.setText("-");

        jLabel64.setFont(new java.awt.Font("Inter 18pt Black", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(68, 68, 68));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel64.setText("-");

        roundedButton4.setBackground(new java.awt.Color(68, 68, 68));
        roundedButton4.setBorder(null);
        roundedButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-print-25.png"))); // NOI18N
        roundedButton4.setText("Print Ticket");
        roundedButton4.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N

        roundedButton5.setBackground(new java.awt.Color(255, 255, 255));
        roundedButton5.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));
        roundedButton5.setForeground(new java.awt.Color(68, 68, 68));
        roundedButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-pdf-25.png"))); // NOI18N
        roundedButton5.setText("Export As PDF");
        roundedButton5.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N

        roundedButton6.setBackground(new java.awt.Color(255, 255, 255));
        roundedButton6.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));
        roundedButton6.setForeground(new java.awt.Color(68, 68, 68));
        roundedButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-save-25.png"))); // NOI18N
        roundedButton6.setText("Save File");
        roundedButton6.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(roundedPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(roundedButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundedButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundedButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel29)
                                        .addComponent(jLabel41)
                                        .addComponent(jLabel42))
                                    .addGap(7, 7, 7))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel43)
                                        .addComponent(jLabel44)
                                        .addComponent(jLabel48)
                                        .addComponent(jLabel50)
                                        .addComponent(jLabel51)
                                        .addComponent(jLabel49))
                                    .addGap(2, 2, 2))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel53)
                                    .addGap(28, 28, 28))))
                        .addGap(469, 469, 469)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                                        .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel56)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel62)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel64)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundedButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        lblProfile.setFont(new java.awt.Font("Inter 18pt Black", 1, 24)); // NOI18N
        lblProfile.setForeground(new java.awt.Color(68, 68, 68));
        lblProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/compresskaru_logo ubd_50x50.png"))); // NOI18N
        lblProfile.setText("Profile");

        nav_admin.setBackground(new java.awt.Color(68, 68, 68));
        nav_admin.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));
        nav_admin.setText("Admin Panel");
        nav_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_adminActionPerformed(evt);
            }
        });

        nav_schedule.setBackground(new java.awt.Color(68, 68, 68));
        nav_schedule.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));
        nav_schedule.setText("Train Schedule");
        nav_schedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_scheduleActionPerformed(evt);
            }
        });

        nav_booked_data.setBackground(new java.awt.Color(68, 68, 68));
        nav_booked_data.setBorder(new RoundedBorder(20, new Color(68,68,68), 1));
        nav_booked_data.setText("Booked Data");
        nav_booked_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_booked_dataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(see_schedule_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(nav_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nav_schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nav_booked_data, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(lblProfile))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nav_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nav_schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nav_booked_data, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(see_schedule_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nav_booked_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_booked_dataActionPerformed
        new BookedData().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_nav_booked_dataActionPerformed

    private void nav_scheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_scheduleActionPerformed
        new TrainSchedule().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_nav_scheduleActionPerformed

    private void nav_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_adminActionPerformed
        new AdminPanel().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_nav_adminActionPerformed

    private void see_schedule_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_see_schedule_btnActionPerformed
        new TrainSchedule().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_see_schedule_btnActionPerformed

    private void roundedButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton2ActionPerformed
        // RESET TEXT FIELD
        txtBookingName.setText("");
        txtPrice.setText("");

        // RESET COMBO BOX
        cmbDestination.removeAllItems();
        cmbClass.removeAllItems();
        cmbDepartureTime.removeAllItems();
        cmbCarriage.removeAllItems();

        // LOAD ULANG DESTINATION (AWAL)
        loadDestinationCombo();
    }//GEN-LAST:event_roundedButton2ActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void txtBookingNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookingNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookingNameActionPerformed

    // UNTUK MENAMPILKAN SUMMARY DARI HASIL INPUT SESUDAH INSERT KE DATABASE
    private void showSummary(BookingSummary s) {
        lblFromStation.setText(s.getOrigin());
        lblDestination.setText(s.getDestination());
        lblBookingName.setText(s.getBookingName());
        lblDate.setText(s.getDate());

        lblCost.setText(String.valueOf(s.getCost()));
        lblAdditionalCost.setText(String.valueOf(s.getAdditionalCost()));
        lblTotalCost.setText(String.valueOf(s.getTotalCost()));
    }

    private void confirm_booking_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirm_booking_btnActionPerformed

        try {
            String bookingName = txtBookingName.getText();

            String selected = (String) cmbCarriage.getSelectedItem();
            if (selected == null || !scheduleMap.containsKey(selected)) {
                throw new Exception("Schedule belum dipilih!");
            }

            int selectedScheduleId = scheduleMap.get(selected);

            try (Connection conn = Koneksi.getConnection()) {
                InputBookingTicket booking = new InputBookingTicket();
                String bookingCode = booking.createBookingByScheduleId(
                        conn,
                        bookingName,
                        selectedScheduleId
                );

                JOptionPane.showMessageDialog(this,
                        "Booking berhasil!\nKode Booking: " + bookingCode);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_confirm_booking_btnActionPerformed


    private void cmbClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClassActionPerformed
        loadDepartureCombo();
    }//GEN-LAST:event_cmbClassActionPerformed

    private void cmbDepartureTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDepartureTimeActionPerformed
        loadCarriagesAndPrice();
    }//GEN-LAST:event_cmbDepartureTimeActionPerformed

    private void cmbCarriageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCarriageActionPerformed

        if (cmbCarriage.getSelectedItem() == null) {
            return;
        }

        String key = cmbCarriage.getSelectedItem().toString();
        Integer price = priceMap.get(key);

        if (price != null) {
            txtPrice.setText(String.valueOf(price));
        }
    }//GEN-LAST:event_cmbCarriageActionPerformed

    private void cmbDestinationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDestinationActionPerformed
        loadClassCombo();
    }//GEN-LAST:event_cmbDestinationActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BookingMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private client.components.RoundedComboBox cmbCarriage;
    private client.components.RoundedComboBox cmbClass;
    private client.components.RoundedComboBox cmbDepartureTime;
    private client.components.RoundedComboBox cmbDestination;
    private client.components.RoundedButton confirm_booking_btn;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdditionalCost;
    private javax.swing.JLabel lblBookingName;
    private javax.swing.JLabel lblCost;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDestination;
    private javax.swing.JLabel lblFromStation;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblTotalCost;
    private client.components.RoundedButton nav_admin;
    private client.components.RoundedButton nav_booked_data;
    private client.components.RoundedButton nav_schedule;
    private client.components.RoundedButton roundedButton2;
    private client.components.RoundedButton roundedButton4;
    private client.components.RoundedButton roundedButton5;
    private client.components.RoundedButton roundedButton6;
    private client.components.RoundedPanel roundedPanel11;
    private client.components.RoundedPanel roundedPanel12;
    private client.components.RoundedPanel roundedPanel13;
    private client.components.RoundedButton see_schedule_btn;
    private client.components.RoundedTextField txtBookingName;
    private client.components.RoundedTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}

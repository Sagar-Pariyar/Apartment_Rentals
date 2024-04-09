/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apartment_rentals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.Color;


/**
 *
 * @author Sagar
 */
public class ApartmentDetails extends javax.swing.JFrame {

    /**
     * Creates new form ApartmentDetails
     */
    
    private String apartmentId; // Variable to store the apartment ID
    private String tenant_id;
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    public ApartmentDetails(String apartmentId, String tenant_id) {
        initComponents();
        this.apartmentId = apartmentId; // Initialize the apartment ID
        this.tenant_id = tenant_id;
        
        updateLabels();// Call method to update labels with apartment details
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);// Set the default close operation to dispose on close
        
        try {
            boolean isShortlisted = isApartmentShortlisted(tenant_id, apartmentId);
            if (isShortlisted) {
                shortListButton.setBackground(new Color(253,55,82)); // Change button color to red to indicate shortlisted
            } else {
                shortListButton.setBackground(new Color(120,118,118)); // Change button color to green to indicate not shortlisted
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
    }
    
    // Method to check if the apartment is already shortlisted
    private boolean isApartmentShortlisted(String tenantId, String apartmentId) throws SQLException {
        boolean isShortlisted = false;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mydatabase");

            String checkSql = "SELECT * FROM ShortlistedApartment WHERE tenant_id = ? AND apartment_id = ?";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setString(1, tenantId);
            pstmt.setString(2, apartmentId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                isShortlisted = true;
            }
        } finally {
            // Close connections
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return isShortlisted;
    }

    
    // Method to update JLabels with apartment details
    private void updateLabels() {
        
        try {
            // Establish database connection
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mydatabase");
            
            // Prepare SQL query to fetch apartment details
            String sql = "SELECT * FROM Apartment WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, apartmentId);
            
            // Execute query and get the result set
            rs = pstmt.executeQuery();
            
            // Check if apartment details exist
            if (rs.next()) {
                // Update JLabels with fetched apartment details
                city.setText(rs.getString("city"));
                bhkType.setText(rs.getString("bhk_type"));
                rent.setText(rs.getString("expected_rent") + "/M");
                prefered_tenant.setText(rs.getString("preferred_tenant"));
                furnishedType.setText(rs.getString("furnished_type"));
                parking.setText(rs.getString("has_parking_space"));
                flooNo.setText(Integer.toString(rs.getInt("floor_number")));
                balcony.setText(Integer.toString(rs.getInt("num_balconies")));
                
                // Extracting date without time
                java.sql.Date availableFromDate = rs.getDate("available_from");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String availableFromText = sdf.format(availableFromDate);
                availableFrom.setText(availableFromText);
                
                // Combine street_address, city, and postal_code for address label
                String addressText = rs.getString("street_address") + ", " + rs.getString("city") + ", " + rs.getString("postal_code");
                address.setText(addressText);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        } finally {
            // Close connections
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle close connection errors
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        city = new javax.swing.JLabel();
        bhkType = new javax.swing.JLabel();
        rent = new javax.swing.JLabel();
        getOwnerDetailsButton = new javax.swing.JButton();
        address = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        prefered_tenant = new javax.swing.JLabel();
        furnishingIcon = new javax.swing.JLabel();
        furnishedType = new javax.swing.JLabel();
        parkingIcon = new javax.swing.JLabel();
        floorIcon = new javax.swing.JLabel();
        balconyIcon = new javax.swing.JLabel();
        preferredTenantIcon = new javax.swing.JLabel();
        parking = new javax.swing.JLabel();
        flooNo = new javax.swing.JLabel();
        balcony = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        calendarIcon = new javax.swing.JLabel();
        availableFrom = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        shortListButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));

        city.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        city.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        city.setText("Pune");

        bhkType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bhkType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bhkType.setText("BHK");

        rent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rent.setText("9999/M");

        getOwnerDetailsButton.setBackground(new java.awt.Color(0, 102, 102));
        getOwnerDetailsButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        getOwnerDetailsButton.setForeground(new java.awt.Color(255, 255, 255));
        getOwnerDetailsButton.setText("Get Owner Details");
        getOwnerDetailsButton.setFocusable(false);

        address.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/location.png"))); // NOI18N
        address.setText("phase 1, hijewadi, Pune");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        prefered_tenant.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        prefered_tenant.setText("Anyone");

        furnishingIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/furnishing.png"))); // NOI18N
        furnishingIcon.setText("jLabel1");

        furnishedType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        furnishedType.setText("Semi-Furnished");

        parkingIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        parkingIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/parking.png"))); // NOI18N

        floorIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/floor.png"))); // NOI18N
        floorIcon.setText("jLabel4");

        balconyIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balcony.png"))); // NOI18N
        balconyIcon.setText("jLabel5");

        preferredTenantIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        preferredTenantIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/prefered tenant.png"))); // NOI18N

        parking.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        parking.setText("YES");

        flooNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        flooNo.setText("1");

        balcony.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        balcony.setText("0");

        jLabel6.setBackground(new java.awt.Color(153, 153, 153));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setLabelFor(prefered_tenant);
        jLabel6.setText("Preferred Tenant");

        jLabel7.setBackground(new java.awt.Color(153, 153, 153));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setLabelFor(furnishedType);
        jLabel7.setText(" Furnishing Status");

        jLabel8.setBackground(new java.awt.Color(153, 153, 153));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setLabelFor(parking);
        jLabel8.setText("Parking");

        jLabel9.setBackground(new java.awt.Color(153, 153, 153));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setLabelFor(balcony);
        jLabel9.setText("Balcony");

        jLabel11.setBackground(new java.awt.Color(153, 153, 153));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setLabelFor(flooNo);
        jLabel11.setText("Floor");

        calendarIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        calendarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/calendar.png"))); // NOI18N

        availableFrom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        availableFrom.setText("12-04-2024");

        jLabel17.setBackground(new java.awt.Color(153, 153, 153));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 153, 153));
        jLabel17.setLabelFor(availableFrom);
        jLabel17.setText("Available From");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(preferredTenantIcon)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(prefered_tenant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(furnishingIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(furnishedType, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(parkingIcon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(parking, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(floorIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(flooNo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(balconyIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(balcony, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(calendarIcon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(availableFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(furnishingIcon)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(preferredTenantIcon)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(prefered_tenant)
                                    .addComponent(furnishedType))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(parkingIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(balconyIcon)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(parking)
                                            .addComponent(balcony))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))))))))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(floorIcon)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(flooNo)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel17)))
                    .addComponent(calendarIcon)
                    .addComponent(availableFrom))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setBackground(new java.awt.Color(153, 153, 153));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setLabelFor(city);
        jLabel12.setText("City");

        jLabel13.setBackground(new java.awt.Color(153, 153, 153));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setLabelFor(bhkType);
        jLabel13.setText("BHK");

        jLabel14.setBackground(new java.awt.Color(153, 153, 153));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setLabelFor(rent);
        jLabel14.setText("Rent (₹)");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        shortListButton.setBackground(new java.awt.Color(153, 153, 153));
        shortListButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hollow heart.png"))); // NOI18N
        shortListButton.setBorderPainted(false);
        shortListButton.setFocusable(false);
        shortListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortListButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(getOwnerDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(shortListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(bhkType, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rent, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel14)))
                        .addGap(27, 27, 27)))
                .addGap(0, 19, Short.MAX_VALUE))
            .addComponent(jSeparator3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(rent))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(bhkType)))
                            .addComponent(city, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jLabel14))
                            .addComponent(jLabel13))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator5)
                            .addComponent(jSeparator4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(getOwnerDetailsButton)
                        .addComponent(address))
                    .addComponent(shortListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void shortListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortListButtonActionPerformed

        try {
            boolean isShortlisted = isApartmentShortlisted(tenant_id, apartmentId);

            if (isShortlisted) {
                // If the apartment is already shortlisted, remove it from the shortlist
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mydatabase");
                String deleteSql = "DELETE FROM ShortlistedApartment WHERE tenant_id = ? AND apartment_id = ?";
                pstmt = conn.prepareStatement(deleteSql);
                pstmt.setString(1, tenant_id);
                pstmt.setString(2, apartmentId);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    shortListButton.setBackground(new Color(120,118,118)); // Change button color to gray to indicate not shortlisted
                    javax.swing.JOptionPane.showMessageDialog(this, "Apartment successfully removed from shortlist!");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Failed to remove apartment from shortlist!");
                }
            } else {
                // If the apartment is not shortlisted, add it to the shortlist
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mydatabase");
                String insertSql = "INSERT INTO ShortlistedApartment (tenant_id, apartment_id) VALUES (?, ?)";
                pstmt = conn.prepareStatement(insertSql);
                pstmt.setString(1, tenant_id);
                pstmt.setString(2, apartmentId);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    shortListButton.setBackground(new Color(253,55,82)); // Change button color to red to indicate shortlisted
                    javax.swing.JOptionPane.showMessageDialog(this, "Apartment successfully added to shortlist!");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Failed to add apartment to shortlist!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        } finally {
            // Close connections
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle close connection errors
            }
        }
    }//GEN-LAST:event_shortListButtonActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ApartmentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApartmentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApartmentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApartmentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ApartmentDetails().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private javax.swing.JLabel availableFrom;
    private javax.swing.JLabel balcony;
    private javax.swing.JLabel balconyIcon;
    private javax.swing.JLabel bhkType;
    private javax.swing.JLabel calendarIcon;
    private javax.swing.JLabel city;
    private javax.swing.JLabel flooNo;
    private javax.swing.JLabel floorIcon;
    private javax.swing.JLabel furnishedType;
    private javax.swing.JLabel furnishingIcon;
    private javax.swing.JButton getOwnerDetailsButton;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel parking;
    private javax.swing.JLabel parkingIcon;
    private javax.swing.JLabel prefered_tenant;
    private javax.swing.JLabel preferredTenantIcon;
    private javax.swing.JLabel rent;
    private javax.swing.JButton shortListButton;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
//ni ada aku modify sikit and still guna gpt punya code
package projectdatastr;

/**
 *
 * @author farijiha
 */
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalGUI extends javax.swing.JFrame {

    private int currentMonth;
    private int currentYear;

    /**
     * Creates new form CalGUI
     */
    public CalGUI() {
        initComponents();

        Calendar calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        calPanel.setLayout(new GridLayout(0, 7));
        calPanel.setPreferredSize(new Dimension(420, 300));

        updateCalendar();
        setVisible(true);
    }

    public class DatePanel extends JPanel {

        private JLabel label;
        private Color backgroundColor; // New property to store the background color

        public DatePanel(String day) {
            setLayout(new BorderLayout());
            label = new JLabel(day, SwingConstants.CENTER);
            add(label, BorderLayout.CENTER);
            setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adding border for visibility
        }

        public void setBackgroundColor(Color color) {
            this.backgroundColor = color;
            setBackground(color);
        }
    }

    private void updateCalendar() {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthLbl.setText(monthNames[currentMonth] + " " + currentYear);

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // Set the first day of the week to Monday
        calendar.set(currentYear, currentMonth, 1);

        int startDay = calendar.get(Calendar.DAY_OF_WEEK); // Get the day of the week for the 1st day of the month
        int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Calculate the starting day, considering Monday as the first day (adjust the startDay)
        int firstDay = ((startDay - calendar.getFirstDayOfWeek()) + 7) % 7 + 1;

        calPanel.removeAll();

        // Add labels for the days of the week
        String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (String day : daysOfWeek) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            calPanel.add(dayLabel);
        }

        for (int i = 1; i < firstDay; i++) {
            calPanel.add(new JLabel(""));
        }

        Color datePanelColor = Color.PINK; // Choose your desired color here

        for (int day = 1; day <= numberOfDaysInMonth; day++) {
            JPanel datePanel = new DatePanel(String.valueOf(day)); // Use the modified DatePanel
            datePanel.setLayout(new BorderLayout());
            datePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel dayLabel = new JLabel(String.valueOf(day), JLabel.CENTER);
            datePanel.add(dayLabel, BorderLayout.CENTER);

            final int finalDay = day; // Variable needs to be final or effectively final to be used in the listener

            // Add a mouse listener to each date panel
            datePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addEvent(finalDay, (DatePanel) datePanel); // Pass the DatePanel to addEvent
                }
            });
            // Set a uniform background color for all date panels
            datePanel.setBackground(datePanelColor);

            calPanel.add(datePanel);
        }

        revalidate();
        repaint();
    }

    // Method to handle adding an event for a specific day
    private void addEvent(int day, DatePanel datePanel) {
        String event = JOptionPane.showInputDialog(this, "Enter event for " + monthLbl.getText() + " " + day + ":");
        if (event != null && !event.isEmpty()) {
            // Set a specific color for the added event date
            Color eventColor = generateEventColor(day);

            // Set the background color of the DatePanel
            datePanel.setBackgroundColor(eventColor);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, currentYear);
            calendar.set(Calendar.MONTH, currentMonth);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            String formattedDate = dateFormat.format(calendar.getTime());

            String formattedEvent = String.format("Event on %s: %s\n", formattedDate, event);
            taskTxtArea.append(formattedEvent);
        }
    }

    // Method to generate a color based on the day
    private Color generateEventColor(int day) {
        // Use a predefined set of colors or generate colors based on some criteria
        Color[] eventColors = {
            new Color(173, 216, 230), // Light Blue
            new Color(144, 238, 144), // Light Green
            new Color(255, 228, 196), // Bisque
        // Add more colors as needed
        };

        // Use a modulus operation to cycle through the colors based on the day
        return eventColors[day % eventColors.length];
    }

    // Method to handle the search operation
    private void searchEvent() {
        String searchInput = JOptionPane.showInputDialog(this, "Enter event to search:");
        boolean found = false;

        // Search through the stored events in taskTxtArea
        Scanner scanner = new Scanner(taskTxtArea.getText());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(searchInput)) {
                found = true;
                break;
            }
        }
        scanner.close();

        // Show the search result in a popup
        if (found) {
            JOptionPane.showMessageDialog(this, "Event found: " + searchInput);
        } else {
            JOptionPane.showMessageDialog(this, "Event not found.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prevBtn = new javax.swing.JButton();
        monthLbl = new javax.swing.JLabel();
        nextBtn = new javax.swing.JButton();
        calPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskTxtArea = new javax.swing.JTextArea();
        searchBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        prevBtn.setText("<");
        prevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtnActionPerformed(evt);
            }
        });

        monthLbl.setFont(new java.awt.Font("Arial Black", 0, 15)); // NOI18N
        monthLbl.setText("MONTH");
        monthLbl.setInheritsPopupMenu(false);

        nextBtn.setText(">");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout calPanelLayout = new javax.swing.GroupLayout(calPanel);
        calPanel.setLayout(calPanelLayout);
        calPanelLayout.setHorizontalGroup(
            calPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        calPanelLayout.setVerticalGroup(
            calPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        taskTxtArea.setColumns(20);
        taskTxtArea.setRows(5);
        jScrollPane1.setViewportView(taskTxtArea);

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        editBtn.setText("Edit event");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(prevBtn)
                        .addGap(5, 5, 5)
                        .addComponent(monthLbl)
                        .addGap(5, 5, 5)
                        .addComponent(nextBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(calPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prevBtn)
                    .addComponent(monthLbl)
                    .addComponent(nextBtn))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(calPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBtn)
                    .addComponent(editBtn))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void prevBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBtnActionPerformed
        // TODO add your handling code here:
        if (currentMonth == 0) {
            currentMonth = 11;
            currentYear--;
        } else {
            currentMonth--;
        }
        updateCalendar();
    }//GEN-LAST:event_prevBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        // TODO add your handling code here:
        if (currentMonth == 11) {
            currentMonth = 0;
            currentYear++;
        } else {
            currentMonth++;
        }
        updateCalendar();
    }//GEN-LAST:event_nextBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:String searchTerm = JOptionPane.showInputDialog(this, "Enter event to search:");
        searchEvent();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        if (!taskTxtArea.getText().isEmpty()) {
            String[] events = taskTxtArea.getText().split("\n");
            String[] options = Arrays.copyOfRange(events, 0, events.length); // Copy the events to display in the selection box

            String selectedEvent = (String) JOptionPane.showInputDialog(this,
                    "Select an event to edit:", "Edit Event",
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (selectedEvent != null) {
                String editedEvent = JOptionPane.showInputDialog(this,
                        "Edit event:", selectedEvent);
                if (editedEvent != null && !editedEvent.trim().isEmpty()) {
                    // Replace the selected event with the edited one in the text area
                    taskTxtArea.setText(taskTxtArea.getText().replace(selectedEvent, editedEvent));
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No events to edit.");
        }
    }//GEN-LAST:event_editBtnActionPerformed

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
            java.util.logging.Logger.getLogger(CalGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel calPanel;
    private javax.swing.JButton editBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel monthLbl;
    private javax.swing.JButton nextBtn;
    private javax.swing.JButton prevBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextArea taskTxtArea;
    // End of variables declaration//GEN-END:variables
}

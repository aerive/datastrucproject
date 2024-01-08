package codingHere;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;
import java.util.List;

public class ZeroTries extends JFrame {

    private int currentMonth;
    private int currentYear;
    private JLabel monthLbl;
    private JPanel calPanel;
    private List<String> events;
    private JButton nextBtn;
    private JButton prevBtn;
    private JButton searchBtn;
    private JButton editBtn;
    private JTextArea eventsTextArea;

    public ZeroTries() {
        initComponents();

        Calendar calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        calPanel.setLayout(new GridLayout(0, 7));
        calPanel.setPreferredSize(new Dimension(420, 300));

        events = new ArrayList<>();

        updateCalendar();
        pack(); // Make the program in a size based on its components
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public class DatePanel extends JPanel {
        private JLabel label;

        public DatePanel(String day) {
            setLayout(new BorderLayout());
            label = new JLabel(day, SwingConstants.CENTER);
            add(label, BorderLayout.CENTER);
            setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adding border for visibility
        }
    }

    private void initComponents() {
        prevBtn = new JButton("<");
        monthLbl = new JLabel("MONTH");
        nextBtn = new JButton(">");
        calPanel = new JPanel();
        searchBtn = new JButton("Search");
        editBtn = new JButton("Edit");
        eventsTextArea = new JTextArea();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set colorful background for the whole application
        getContentPane().setBackground(new Color(240, 240, 240));

        prevBtn.addActionListener(this::prevBtnActionPerformed);
        nextBtn.addActionListener(this::nextBtnActionPerformed);
        searchBtn.addActionListener(this::searchBtnActionPerformed);
        editBtn.addActionListener(this::editBtnActionPerformed);

        prevBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                prevBtn.setBackground(new Color(200, 200, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                prevBtn.setBackground(UIManager.getColor("control"));
            }
        });

        nextBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextBtn.setBackground(new Color(200, 200, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextBtn.setBackground(UIManager.getColor("control"));
            }
        });

        calPanel.setLayout(new GridLayout(0, 7));

        // Set colorful background for the day labels
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : dayNames) {
            JLabel label = new JLabel(day, JLabel.CENTER);
            label.setForeground(new Color(50, 50, 50));
            label.setOpaque(true);
            label.setBackground(new Color(180, 220, 255)); // Light blue background
            calPanel.add(label);
        }

        searchBtn.setText("Search");

        editBtn.setText("Edit");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(30, Short.MAX_VALUE)
                                .addComponent(prevBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(monthLbl)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                                .addComponent(searchBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editBtn)
                                .addContainerGap(30, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(calPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(eventsTextArea))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(prevBtn)
                                        .addComponent(monthLbl)
                                        .addComponent(nextBtn)
                                        .addComponent(searchBtn)
                                        .addComponent(editBtn))
                                .addGap(18, 18, 18)
                                .addComponent(calPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(eventsTextArea, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }

    private void prevBtnActionPerformed(ActionEvent evt) {
        if (currentMonth == 0) {
            currentMonth = 11;
            currentYear--;
        } else {
            currentMonth--;
        }
        updateCalendar();
    }

    private void nextBtnActionPerformed(ActionEvent evt) {
        if (currentMonth == 11) {
            currentMonth = 0;
            currentYear++;
        } else {
            currentMonth++;
        }
        updateCalendar();
    }

    private void searchBtnActionPerformed(ActionEvent evt) {
        String searchTerm = JOptionPane.showInputDialog(this, "Enter event to search:");
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            searchEvent(searchTerm);
        }
    }

    private void editBtnActionPerformed(ActionEvent evt) {
        if (!events.isEmpty()) {
            Object[] options = events.toArray();
            String selectedEvent = (String) JOptionPane.showInputDialog(this, "Select an event to edit:",
                    "Edit Event", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (selectedEvent != null) {
                String editedEvent = JOptionPane.showInputDialog(this, "Edit event:", selectedEvent);
                if (editedEvent != null && !editedEvent.trim().isEmpty()) {
                    events.remove(selectedEvent);
                    events.add(editedEvent);
                    updateCalendar();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No events to edit.");
        }
    }

    private void searchEvent(String searchTerm) {
        for (String event : events) {
            if (event.contains(searchTerm)) {
                JOptionPane.showMessageDialog(this, "Event found: " + event);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Event not found.");
    }

    private void updateCalendar() {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        monthLbl.setText(monthNames[currentMonth] + " " + currentYear);

        Calendar calendar = Calendar.getInstance();
        calendar.set(currentYear, currentMonth, 1);

        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calPanel.removeAll();

        // Add day names as headers
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : dayNames) {
            JLabel label = new JLabel(day, JLabel.CENTER);
            label.setForeground(Color.BLUE);
            calPanel.add(label);
        }

        for (int i = 1; i < startDay; i++) {
            calPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= numberOfDaysInMonth; day++) {
            final int currentDay = day;
            JButton button = new JButton(String.valueOf(day));
            button.addActionListener(e -> {
                handleDateClick(currentDay);
            });
            calPanel.add(button);
        }

        eventsTextArea.setText("");
        for (String event : events) {
            eventsTextArea.append(event + "\n");
        }

        revalidate();
        repaint();
    }

    private void handleDateClick(int day) {
        String date = monthLbl.getText() + " " + day + ", " + currentYear;
        String event = JOptionPane.showInputDialog(this, "Enter event for " + date + ":");
        if (event != null && !event.trim().isEmpty()) {
            events.add(date + ": " + event);
            JOptionPane.showMessageDialog(this, "Event added for " + date + ": " + event);
            updateCalendar();
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ZeroTries().setVisible(true);
            }
        });
    }
}

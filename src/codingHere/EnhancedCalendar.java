package codingHere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class EnhancedCalendar extends JFrame {

    private ArrayList<Event> eventList = new ArrayList<>(); // Use ArrayList instead of List
    private JLabel monthLabel;
    private JPanel calendarPanel;
    private int currentMonth, currentYear;
    private JButton editButton;

    public EnhancedCalendar() {
        setTitle("Enhanced Calendar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Month Label
        monthLabel = new JLabel("", JLabel.CENTER);
        monthLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Calendar Panel
        calendarPanel = new JPanel(new GridLayout(0, 7));
        calendarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton searchButton = new JButton("Search Events");
        JButton editButton = new JButton("Edit");

        JPanel navPanel = new JPanel(new GridLayout(1, 3));
        navPanel.add(prevButton);
        navPanel.add(monthLabel);
        navPanel.add(nextButton);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(searchButton);
        bottomPanel.add(editButton);

        add(navPanel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initialize current month and year
        Calendar calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);

        updateCalendar();

        prevButton.addActionListener(e -> {
            if (currentMonth == 0) {
                currentMonth = 11;
                currentYear--;
            } else {
                currentMonth--;
            }
            updateCalendar();
        });

        nextButton.addActionListener(e -> {
            if (currentMonth == 11) {
                currentMonth = 0;
                currentYear++;
            } else {
                currentMonth++;
            }
            updateCalendar();
        });

        searchButton.addActionListener(e -> {
            searchEvents();
        });

        setVisible(true);

    }

    public class Event {

        private String date;
        private String title;
        private String description;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    private void updateCalendar() {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        monthLabel.setText(monthNames[currentMonth] + " " + currentYear);

        Calendar calendar = Calendar.getInstance();
        calendar.set(currentYear, currentMonth, 1);

        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendarPanel.removeAll();

        // Add day names as headers
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : dayNames) {
            JLabel label = new JLabel(day, JLabel.CENTER);
            label.setForeground(Color.BLUE);
            calendarPanel.add(label);
        }
        int day;

        for (int i = 1; i < startDay; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (day = 1; day <= numberOfDaysInMonth; day++) {
            final int currentDay = day;  // Make day effectively final
            JButton button = new JButton(String.valueOf(day));
            button.addActionListener(e -> {
                handleDateClick(currentDay);
            });
            calendarPanel.add(button);
        }
        eventList = new ArrayList<>(); // Add this line
        eventList.clear();
        String dateString = monthLabel.getText() + " " + day + ", " + currentYear;

        eventList.stream().filter(event -> event.getDate().equals(dateString)).forEach(event -> {
            JLabel eventLabel = new JLabel(event.getTitle());
            eventLabel.putClientProperty("event", event);
            eventLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    editEvent(event);
                }
            });
            calendarPanel.add(eventLabel);
        });
        revalidate();
        repaint();
    }

    private void handleDateClick(int day) {
        String date = monthLabel.getText() + " " + day + ", " + currentYear;
        String event = JOptionPane.showInputDialog(this, "Enter event for " + date + ":");
        if (event != null && !event.trim().isEmpty()) {
            // You can save the event or perform any action here
            JOptionPane.showMessageDialog(this, "Event added for " + date + ": " + event);
        }
    }

    private void searchEvents() {
        String searchTerm = JOptionPane.showInputDialog(this, "Enter search term:");
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            // You can perform the search and display the results
            JOptionPane.showMessageDialog(this, "Search results for '" + searchTerm + "': \n"
                    + "Event 1 on MM DD, YYYY\n"
                    + "Event 2 on MM DD, YYYY");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid search term.");
        }
    }

    private void editEvent(Event event) {
        // Display dialog boxes for editing event title and description
        String newTitle = JOptionPane.showInputDialog("Edit title:", event.getTitle());
        String newDescription = JOptionPane.showInputDialog("Edit description:", event.getDescription());

        // Update the event object with new information
        event.setTitle(newTitle);
        event.setDescription(newDescription);

        // Update the calendar display
        updateCalendar();
    }

    private void intializeButtons() {
        editButton.addActionListener(e -> {
            // Get the clicked event label
            JLabel clickedLabel = (JLabel) e.getSource();

            // Get the associated event object using your chosen approach (custom properties or data structure)
            Event event = (Event) clickedLabel.getClientProperty("event"); // or eventMap.get(clickedLabel);
            // Display a dialog box to the user to enter the updated event details
            String newTitle = JOptionPane.showInputDialog("Edit title:", event.getTitle());
            String newDescription = JOptionPane.showInputDialog("Edit description:", event.getDescription());

            // Update the event object
            event.setTitle(newTitle);
            event.setDescription(newDescription);

            // Update the calendar display
            updateCalendar();
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EnhancedCalendar());
    }
}

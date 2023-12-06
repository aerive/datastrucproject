/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//ni 100% chatgpt punya
package projectdatastr;

/**
 *
 * @author farijiha
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SimpleCalendar extends JFrame {

    private JLabel monthLabel;
    private JPanel calendarPanel;
    private int currentMonth, currentYear;

    public SimpleCalendar() {
        setTitle("Simple Calendar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Month Label
        monthLabel = new JLabel("", JLabel.CENTER);
        monthLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Calendar Panel
        calendarPanel = new JPanel(new GridLayout(0, 7));
        calendarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        JPanel navPanel = new JPanel(new GridLayout(1, 3));
        navPanel.add(prevButton);
        navPanel.add(monthLabel);
        navPanel.add(nextButton);

        add(navPanel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);

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

        setVisible(true);
    }

    private void updateCalendar() {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        monthLabel.setText(monthNames[currentMonth] + " " + currentYear);

        Calendar calendar = Calendar.getInstance();
        calendar.set(currentYear, currentMonth, 1);

        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendarPanel.removeAll();

        for (int i = 1; i < startDay; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= numberOfDaysInMonth; day++) {
            JLabel label = new JLabel(String.valueOf(day), JLabel.CENTER);
            calendarPanel.add(label);
        }

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new SimpleCalendar();
    }
}

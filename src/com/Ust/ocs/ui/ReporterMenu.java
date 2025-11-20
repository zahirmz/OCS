package com.Ust.ocs.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReporterMenu {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ReporterMenu window = new ReporterMenu();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private JFrame frame;

    public ReporterMenu() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Reporter Menu");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        JButton btnGenerateReport = new JButton("Generate Report");
        btnGenerateReport.addActionListener(e -> {
            // Action for generating report
            JOptionPane.showMessageDialog(frame, "Generating report...");
        });

        JButton btnViewReports = new JButton("View Reports");
        btnViewReports.addActionListener(e -> {
            // Action for viewing reports
            JOptionPane.showMessageDialog(frame, "Displaying reports...");
        });

        frame.getContentPane().add(btnGenerateReport);
        frame.getContentPane().add(btnViewReports);
    }
}

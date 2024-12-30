/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author taufan
 */
import javax.swing.*;
import java.awt.*;

public class LoadingFrame extends JFrame {
    private JProgressBar progressBar;

    public LoadingFrame() {
        setTitle("Memuat Data");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Memuat data, harap tunggu...", JLabel.CENTER);
        add(label, BorderLayout.NORTH);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        add(progressBar, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public void setProgress(int progress) {
        progressBar.setValue(progress);
    }
}

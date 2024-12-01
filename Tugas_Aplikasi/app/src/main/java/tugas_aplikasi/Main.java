package tugas_aplikasi;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Hotel Booking Application");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.addActionListener(e -> System.exit(0));
        menuFile.add(menuExit);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);

        // Inisialisasi panel input dan tabel
        BookingForm formPanel = new BookingForm();
        BookingTable tablePanel = new BookingTable();

        // Menyusun layout dengan JSplitPane
        formPanel.setBookingTablePanel(tablePanel); // Berikan referensi tablePanel ke formPanel
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
                new JScrollPane(formPanel), 
                new JScrollPane(tablePanel));
        splitPane.setDividerLocation(300);
        splitPane.setResizeWeight(0.5); // Setengah ruang untuk form, setengah untuk tabel
        splitPane.setOneTouchExpandable(true);
        
        add(splitPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}

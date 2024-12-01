package tugas_aplikasi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookingTable extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public BookingTable() {
        setLayout(new BorderLayout());
        
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Nama", "Alamat", "Jenis Kamar", "Tipe", "Fasilitas", "Layanan", "Tamu", "Malam"});
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        setBorder(BorderFactory.createTitledBorder("Daftar Pemesanan"));
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }
}



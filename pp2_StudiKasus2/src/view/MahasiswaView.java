/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author taufan
 */
import controller.MahasiswaController;
import model.Mahasiswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MahasiswaView extends JFrame {
    private MahasiswaController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField namaField, jurusanField, tahunMasukField, ipkField;
    private int selectedId = -1;

    public MahasiswaView() {
        controller = new MahasiswaController();
        setTitle("Pengelolaan Mahasiswa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Jurusan", "Tahun Masuk", "IPK"}, 0);
        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                selectedId = (int) tableModel.getValueAt(row, 0);
                namaField.setText((String) tableModel.getValueAt(row, 1));
                jurusanField.setText((String) tableModel.getValueAt(row, 2));
                tahunMasukField.setText(String.valueOf(tableModel.getValueAt(row, 3)));
                ipkField.setText(String.valueOf(tableModel.getValueAt(row, 4)));
            }
        });
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Nama:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("Jurusan:"));
        jurusanField = new JTextField();
        inputPanel.add(jurusanField);

        inputPanel.add(new JLabel("Tahun Masuk:"));
        tahunMasukField = new JTextField();
        inputPanel.add(tahunMasukField);

        inputPanel.add(new JLabel("IPK:"));
        ipkField = new JTextField();
        inputPanel.add(ipkField);

        add(inputPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4));

        JButton addButton = new JButton("Tambah");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahMahasiswa();
            }
        });
        panel.add(addButton);

        JButton updateButton = new JButton("Ubah");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ubahMahasiswa();
            }
        });
        panel.add(updateButton);

        JButton deleteButton = new JButton("Hapus");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusMahasiswa();
            }
        });
        panel.add(deleteButton);

        JButton refreshButton = new JButton("Segarkan");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMahasiswa();
            }
        });
        panel.add(refreshButton);

        add(panel, BorderLayout.SOUTH);
        loadMahasiswa();
    }

    private void tambahMahasiswa() {
        String nama = namaField.getText();
        String jurusan = jurusanField.getText();
        int tahunMasuk = Integer.parseInt(tahunMasukField.getText());
        double ipk = Double.parseDouble(ipkField.getText());

        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNama(nama);
        mahasiswa.setJurusan(jurusan);
        mahasiswa.setTahunMasuk(tahunMasuk);
        mahasiswa.setIpk(ipk);

        controller.insertMahasiswa(mahasiswa);
        loadMahasiswa();
        JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil ditambahkan!");
    }

    private void ubahMahasiswa() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih mahasiswa yang akan diubah");
            return;
        }

        String nama = namaField.getText();
        String jurusan = jurusanField.getText();
        int tahunMasuk = Integer.parseInt(tahunMasukField.getText());
        double ipk = Double.parseDouble(ipkField.getText());

        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setId(selectedId);
        mahasiswa.setNama(nama);
        mahasiswa.setJurusan(jurusan);
        mahasiswa.setTahunMasuk(tahunMasuk);
        mahasiswa.setIpk(ipk);

        controller.updateMahasiswa(mahasiswa);
        loadMahasiswa();
        JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil diubah!");
    }

    private void hapusMahasiswa() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih mahasiswa yang akan dihapus");
            return;
        }

        controller.deleteMahasiswa(selectedId);
        loadMahasiswa();
        JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil dihapus!");
    }

    private void loadMahasiswa() {
        tableModel.setRowCount(0);
        List<Mahasiswa> mahasiswaList = controller.getAllMahasiswa();
        for (Mahasiswa mahasiswa : mahasiswaList) {
            tableModel.addRow(new Object[]{mahasiswa.getId(), mahasiswa.getNama(), mahasiswa.getJurusan(), mahasiswa.getTahunMasuk(), mahasiswa.getIpk()});
        }
        clearFields();
    }

    private void clearFields() {
        selectedId = -1;
        namaField.setText("");
        jurusanField.setText("");
        tahunMasukField.setText("");
        ipkField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MahasiswaView().setVisible(true);
            }
        });
    }
}

import java.awt.event.*;
import javax.swing.*;

public class BiodataApp extends JFrame {

    public BiodataApp() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label untuk input Nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(130, 40, 100, 10);

        // TextField untuk input Nama
        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(130, 60, 150, 30);

        // Label untuk input Nomor Telepon
        JLabel labelTelepon = new JLabel("Nomor Telepon:");
        labelTelepon.setBounds(130, 100, 150, 10);

        // TextField untuk input Nomor Telepon
        JTextField textFieldTelepon = new JTextField();
        textFieldTelepon.setBounds(130, 120, 150, 30);

        // Button untuk menambahkan data
        JButton button = new JButton("Tambah Biodata");
        button.setBounds(130, 160, 150, 40);

        // TextArea untuk menampilkan biodata
        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(130, 210, 200, 200);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String telepon = textFieldTelepon.getText();
                
                if (!nama.isEmpty() && !telepon.isEmpty()) {
                    // Menambahkan biodata ke textarea dengan garis pemisah
                    txtOutput.append("Nama: " + nama + "\n");
                    txtOutput.append("Nomor Telepon: " + telepon + "\n");
                    txtOutput.append("====================\n");

                    // Mengosongkan textfield setelah ditambahkan
                    textFieldNama.setText("");
                    textFieldTelepon.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Mohon isi semua field!");
                }
            }
        });

        // Menambahkan semua komponen ke JFrame
        this.add(button);
        this.add(textFieldNama);
        this.add(labelNama);
        this.add(textFieldTelepon);
        this.add(labelTelepon);
        this.add(txtOutput);

        // Setting ukuran dan layout
        this.setSize(500, 500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BiodataApp app = new BiodataApp();
                app.setVisible(true);
            }
        });
    }
}

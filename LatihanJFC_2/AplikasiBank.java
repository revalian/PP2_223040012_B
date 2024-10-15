import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AplikasiBank extends JFrame implements ActionListener, ItemListener, ChangeListener {

    private JTextField fieldNama;
    private JTextField fieldNoHp;
    private JButton tombolSimpan;
    private JButton tombolReset;
    private JTextArea areaBiodata;
    private JCheckBox checkBoxWNA;
    private JRadioButton radioLaki;
    private JRadioButton radioPerempuan;
    private ButtonGroup grupJenisKelamin;
    private boolean isWNASelected;
    private JList<String> listJenisTabungan;
    private DefaultListModel<String> modelTabungan;
    private JMenuBar menuBar;
    private JSlider sliderFrekuensi;
    private JSpinner spinnerTanggalLahir;

    public AplikasiBank() {
        this.setTitle("BANK BERSAMA");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 750);
        this.setLayout(null);

        // Menu
        menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem itemKeluar = new JMenuItem("Keluar");

        // Field Nama
        JLabel labelNama = new JLabel("Nama :");
        labelNama.setBounds(15, 20, 100, 30);
        fieldNama = new JTextField();
        fieldNama.setBounds(85, 20, 200, 30);

        // Field No HP
        JLabel labelNoHp = new JLabel("Nomor HP :");
        labelNoHp.setBounds(15, 60, 100, 30);
        fieldNoHp = new JTextField();
        fieldNoHp.setBounds(85, 60, 200, 30);

        // JSpinner Tanggal Lahir
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir : ");
        labelTanggalLahir.setBounds(15, 100, 350, 30);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1990);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        SpinnerDateModel model = new SpinnerDateModel(calendar.getTime(), null, null, calendar.DAY_OF_MONTH);
        spinnerTanggalLahir = new JSpinner(model);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd-MM-yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
        spinnerTanggalLahir.setBounds(15, 130, 350, 30);

        // Radio Jenis Kelamin
        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin : ");
        labelJenisKelamin.setBounds(15, 170, 100, 30);
        radioLaki = new JRadioButton("Laki-Laki");
        radioLaki.setBounds(15, 195, 350, 30);
        radioPerempuan = new JRadioButton("Perempuan");
        radioPerempuan.setBounds(15, 225, 350, 30);
        grupJenisKelamin = new ButtonGroup();
        grupJenisKelamin.add(radioLaki);
        grupJenisKelamin.add(radioPerempuan);

        // Checkbox WNA
        checkBoxWNA = new JCheckBox("Warga Negara Asing");
        checkBoxWNA.setBounds(15, 250, 350, 30);
        checkBoxWNA.addItemListener(this);

        // JList Jenis Tabungan
        JLabel labelTabungan = new JLabel("Jenis Tabungan : ");
        labelTabungan.setBounds(15, 280, 100, 30);
        modelTabungan = new DefaultListModel<>();
        modelTabungan.addElement("Tabungan Anak");
        modelTabungan.addElement("Tabungan Giro");
        modelTabungan.addElement("Tabungan Mata Uang Asing");
        modelTabungan.addElement("Tabungan Berjangka");
        listJenisTabungan = new JList<>(modelTabungan);
        listJenisTabungan.setBounds(15, 306, 200, 75);
        listJenisTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Button Simpan
        tombolSimpan = new JButton("Simpan");
        tombolSimpan.setBounds(15, 625, 100, 30);
        tombolSimpan.addActionListener(this);

        // Button Reset
        tombolReset = new JButton("Reset");
        tombolReset.setBounds(150, 625, 100, 30);
        tombolReset.addActionListener(this);

        // JTextArea untuk menampung nilai
        areaBiodata = new JTextArea("");
        areaBiodata.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaBiodata);
        scrollPane.setBounds(15, 515, 280, 100);

        // JSlider
        JLabel labelFrekuensi = new JLabel("Frekuensi transaksi per bulan : ");
        labelFrekuensi.setBounds(15, 385, 500, 30);
        sliderFrekuensi = new JSlider(0, 100, 0);
        sliderFrekuensi.setBounds(15, 425, 380, 75);
        sliderFrekuensi.setPaintTicks(true);
        sliderFrekuensi.setMinorTickSpacing(5);
        sliderFrekuensi.setMajorTickSpacing(10);
        sliderFrekuensi.setPaintLabels(true);

        menuBar.add(menuFile);
        menuFile.add(itemKeluar);
        this.setJMenuBar(menuBar);

        itemKeluar.addActionListener(e -> System.exit(0));

        add(labelNama);
        add(fieldNama);
        add(labelNoHp);
        add(fieldNoHp);
        add(labelTanggalLahir);
        add(spinnerTanggalLahir);
        add(labelJenisKelamin);
        add(radioLaki);
        add(radioPerempuan);
        add(checkBoxWNA);
        add(labelTabungan);
        add(listJenisTabungan);
        add(labelFrekuensi);
        add(sliderFrekuensi);
        add(tombolSimpan);
        add(tombolReset);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        isWNASelected = e.getStateChange() == ItemEvent.SELECTED;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tombolSimpan) {
            JPasswordField passwordField = new JPasswordField();
            int inputPassword = JOptionPane.showConfirmDialog(
                    this,
                    passwordField,
                    "Masukkan Password Anda,",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (inputPassword == JOptionPane.OK_OPTION) {
                char[] password = passwordField.getPassword();
                String passwordString = new String(password);

                if (passwordString.equals("1234")) {
                    String nama = fieldNama.getText();
                    String noHp = fieldNoHp.getText();
                    Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();
                    String tanggal = new SimpleDateFormat("dd-MM-yyyy").format(tanggalLahir);
                    String jenisKelamin = "";

                    if (radioLaki.isSelected()) {
                        jenisKelamin = radioLaki.getText();
                    }
                    if (radioPerempuan.isSelected()) {
                        jenisKelamin = radioPerempuan.getText();
                    }

                    String jenisTabunganDipilih = listJenisTabungan.getSelectedValue() != null ? listJenisTabungan.getSelectedValue() : "Tidak ada tabungan yang dipilih";

                    String statusWNA = isWNASelected ? "WNA : Ya" : "WNA : Tidak";
                    int frekuensiTransaksi = sliderFrekuensi.getValue();

                    String biodata = "Nama: " + nama +
                            "\nNomor Telepon: " + noHp +
                            "\nTanggal Lahir : " + tanggal +
                            "\nJenis Kelamin : " + jenisKelamin +
                            "\n" + statusWNA +
                            "\nTabungan yang dipilih : " + jenisTabunganDipilih +
                            "\nFrekuensi Transaksi : " + frekuensiTransaksi + " kali" +
                            "\n" + "=".repeat(30) + "\n";

                    areaBiodata.append(biodata);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        if (e.getSource() == tombolReset) {
            int konfirmasi = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            JPasswordField passwordField = new JPasswordField();
            int inputPassword = JOptionPane.showConfirmDialog(
                    this,
                    passwordField,
                    "Masukkan Password Anda,",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (inputPassword == JOptionPane.OK_OPTION) {
                char[] password = passwordField.getPassword();
                String passwordString = new String(password);

                if (passwordString.equals("1234")) {
                    if (konfirmasi == JOptionPane.YES_OPTION) {
                        fieldNama.setText("");
                        fieldNoHp.setText("");
                        radioLaki.setSelected(false);
                        radioPerempuan.setSelected(false);
                        checkBoxWNA.setSelected(false);
                        listJenisTabungan.clearSelection();
                        areaBiodata.setText("");
                    }
                    JOptionPane.showMessageDialog(null, "Field Berhasil Direset", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiBank();
        });
    }
}

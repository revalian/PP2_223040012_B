package tugas_aplikasi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingForm extends JPanel {
    private JTextField txtName;
    private JTextArea txtAddress;
    private JRadioButton rbSingle, rbDouble;
    private JCheckBox cbBreakfast, cbParking;
    private JComboBox<String> cbRoomType;
    private JList<String> listFacilities;
    private JSlider sliderGuests;
    private JSpinner spinnerNights;
    private BookingTable tablePanel;

    public BookingForm () {
        setLayout(new BorderLayout());
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Pemesanan"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Komponen Form
        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("Nama Tamu:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField(20);
        panelForm.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(new JLabel("Alamat:"), gbc);
        gbc.gridx = 1;
        txtAddress = new JTextArea(3, 20);
        panelForm.add(new JScrollPane(txtAddress), gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelForm.add(new JLabel("Jenis Kamar:"), gbc);
        gbc.gridx = 1;
        rbSingle = new JRadioButton("Single");
        rbDouble = new JRadioButton("Double");
        ButtonGroup roomTypeGroup = new ButtonGroup();
        roomTypeGroup.add(rbSingle);
        roomTypeGroup.add(rbDouble);
        JPanel panelRoomType = new JPanel();
        panelRoomType.add(rbSingle);
        panelRoomType.add(rbDouble);
        panelForm.add(panelRoomType, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelForm.add(new JLabel("Tipe Kamar:"), gbc);
        gbc.gridx = 1;
        cbRoomType = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
        panelForm.add(cbRoomType, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panelForm.add(new JLabel("Fasilitas Tambahan:"), gbc);
        gbc.gridx = 1;
        cbBreakfast = new JCheckBox("Sarapan");
        cbParking = new JCheckBox("Parkir");
        JPanel panelFacilities = new JPanel();
        panelFacilities.add(cbBreakfast);
        panelFacilities.add(cbParking);
        panelForm.add(panelFacilities, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panelForm.add(new JLabel("Layanan:"), gbc);
        gbc.gridx = 1;
        listFacilities = new JList<>(new String[]{"WiFi", "Kolam Renang", "Gym"});
        listFacilities.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panelForm.add(new JScrollPane(listFacilities), gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panelForm.add(new JLabel("Jumlah Tamu:"), gbc);
        gbc.gridx = 1;
        sliderGuests = new JSlider(1, 4, 1);
        sliderGuests.setMajorTickSpacing(1);
        sliderGuests.setPaintLabels(true);
        sliderGuests.setPaintTicks(true);
        panelForm.add(sliderGuests, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panelForm.add(new JLabel("Jumlah Malam:"), gbc);
        gbc.gridx = 1;
        spinnerNights = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        panelForm.add(spinnerNights, gbc);

        gbc.gridx = 1; gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnAdd = new JButton("Tambah Pemesanan");
        panelForm.add(btnAdd, gbc);

        add(panelForm, BorderLayout.CENTER);

        // Listener untuk Tombol Tambah
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookingToTable();
            }
        });
    }

    public void setBookingTablePanel(BookingTable tablePanel) {
        this.tablePanel = tablePanel;
    }

    private void addBookingToTable() {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String roomType = rbSingle.isSelected() ? "Single" : "Double";
        String roomCategory = cbRoomType.getSelectedItem().toString();
        
        // Menggabungkan fasilitas tambahan
        String facilities = (cbBreakfast.isSelected() ? "Sarapan, " : "") + (cbParking.isSelected() ? "Parkir" : "");
        
        // Menggabungkan layanan yang dipilih
        String services = String.join(", ", listFacilities.getSelectedValuesList());
        
        int guests = sliderGuests.getValue();
        int nights = (int) spinnerNights.getValue();

        // Menambahkan data ke tabel
        if (tablePanel != null) {
            tablePanel.addRow(new Object[]{name, address, roomType, roomCategory, facilities, services, guests, nights});
        }

        // Mengosongkan field input
        txtName.setText("");
        txtAddress.setText("");
        rbSingle.setSelected(false);
        rbDouble.setSelected(false);
        cbRoomType.setSelectedIndex(0);
        cbBreakfast.setSelected(false);
        cbParking.setSelected(false);
        listFacilities.clearSelection();
        sliderGuests.setValue(1);
        spinnerNights.setValue(1);
    }
}

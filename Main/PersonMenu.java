import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PersonMenu extends JFrame {

    private JPanel mainPanel;
    private JTextField ID;
    private JTextField Nama;
    private JTextField TahunLahir;
    private JComboBox<String> Kategori;
    private JComboBox<String> Hobi;        // komponen baru
    private JButton ADDButton;
    private JButton DELETEButton;          // tombol baru
    private JButton CANCELButton;
    private JTable tablePerson;

    private DefaultTableModel tableModel;
    private final PeopleData peopleData = new PeopleData();
    private JPanel panel1;

    public PersonMenu() {
        setContentPane(mainPanel);
        setTitle("Data Sekolah");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        Kategori.removeAllItems();
        Kategori.addItem("-- Pilih Kategori --");
        Kategori.addItem("Siswa");
        Kategori.addItem("Guru");
        Kategori.addItem("Staf");

        Hobi.removeAllItems();
        Hobi.addItem("-- Pilih Hobi --");
        Hobi.addItem("Membaca");
        Hobi.addItem("Olahraga");
        Hobi.addItem("Musik");
        Hobi.addItem("Melukis");
        Hobi.addItem("Memasak");
        Hobi.addItem("Gaming");

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Nama", "Tahun Lahir", "Kategori", "Hobi"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablePerson.setModel(tableModel);
        tablePerson.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        refreshTabel();

        ADDButton.addActionListener(e -> tambahPerson());
        DELETEButton.addActionListener(e -> hapusPerson());
        CANCELButton.addActionListener(e -> bersihkanForm());
    }

    private void refreshTabel() {
        tableModel.setRowCount(0);
        for (Person p : peopleData.getList()) {
            tableModel.addRow(p.toRow());
        }
    }

    private void tambahPerson() {
        String id = ID.getText().trim();
        String nama = Nama.getText().trim();
        String tahunLahir = TahunLahir.getText().trim();
        String kategori = String.valueOf(Kategori.getSelectedItem());
        String hobi = String.valueOf(Hobi.getSelectedItem());

        if (id.isEmpty() || nama.isEmpty() || tahunLahir.isEmpty()
                || kategori.equals("-- Pilih Kategori --")
                || hobi.equals("-- Pilih Hobi --")) {
            peringatan("Semua kolom harus diisi!");
            return;
        }

        if (peopleData.isIdExist(id)) {
            peringatan("ID sudah dipakai!");
            return;
        }

        int tahun;
        try {
            tahun = Integer.parseInt(tahunLahir);
        } catch (NumberFormatException ex) {
            peringatan("Tahun lahir harus berupa angka!");
            return;
        }
        if (tahun < 1900 || tahun > 2026) {
            peringatan("Tahun lahir tidak valid!");
            return;
        }

        peopleData.add(new Person(id, nama, tahun, kategori, hobi));
        refreshTabel();
        JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
        bersihkanForm();
    }

    private void hapusPerson() {
        int viewRow = tablePerson.getSelectedRow();
        if (viewRow == -1) {
            peringatan("Pilih dulu baris yang ingin dihapus!");
            return;
        }

        int row = tablePerson.convertRowIndexToModel(viewRow);
        String nama = tableModel.getValueAt(row, 1).toString();

        // Confirmation prompt sebelum hapus
        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data \"" + nama + "\"?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            peopleData.remove(row);
            refreshTabel();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        }
    }

    private void peringatan(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }

    private void bersihkanForm() {
        ID.setText("");
        Nama.setText("");
        TahunLahir.setText("");
        Kategori.setSelectedIndex(0);
        Hobi.setSelectedIndex(0);
        tablePerson.clearSelection();
        ID.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PersonMenu().setVisible(true));
    }
}
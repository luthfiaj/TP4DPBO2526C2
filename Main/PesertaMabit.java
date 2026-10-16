import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PesertaMabit extends JFrame {

    private JPanel mainPanel;
    private JTextField ID;
    private JButton ADDButton;
    private JButton CANCELButton;
    private JTextField Nama;
    private JTextField TahunLahir;
    private JComboBox<String> Kategori;
    private JTextField Email;
    private JTable tablePeserta;

    private DefaultTableModel tableModel;
    private final ArrayList<Main> daftarPeserta = new ArrayList<>();

    public PesertaMabit() {
        setContentPane(mainPanel);
        setTitle("Data Peserta Mabit");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        Kategori.removeAllItems();
        Kategori.addItem("-- Pilih Kategori --");
        Kategori.addItem("Umum");
        Kategori.addItem("Dosen");
        Kategori.addItem("Mahasiswa");

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Nama", "Tahun Lahir", "Kategori", "Email"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablePeserta.setModel(tableModel);

        isiDataContoh();

        ADDButton.addActionListener(e -> tambahPeserta());
        CANCELButton.addActionListener(e -> bersihkanForm());
    }

    private void isiDataContoh() {
        Object[][] data = {
                {"P001", "Ahmad Fauzi",         2003, "Mahasiswa", "ahmad.fauzi@gmail.com"},
                {"P002", "Siti Aisyah",         2004, "Mahasiswa", "siti.aisyah@gmail.com"},
                {"P003", "Dr. Rizky Hidayat",   1978, "Dosen",     "rizky.hidayat@univ.ac.id"},
                {"P004", "Nur Fatimah",         1990, "Umum",      "nur.fatimah@gmail.com"},
                {"P005", "Abdullah Hakim",      2001, "Mahasiswa", "abdullah.hakim@gmail.com"},
                {"P006", "Prof. Khadijah Zahra",1972, "Dosen",     "khadijah.zahra@univ.ac.id"},
                {"P007", "Umar Faruq",          1985, "Umum",      "umar.faruq@gmail.com"},
                {"P008", "Aisyah Rahma",        2004, "Mahasiswa", "aisyah.rahma@gmail.com"},
                {"P009", "Yusuf Ramadhan",      1982, "Dosen",     "yusuf.ramadhan@univ.ac.id"},
                {"P010", "Maryam Salsabila",    2005, "Mahasiswa", "maryam.salsabila@gmail.com"},
                {"P011", "Ali Hasan",          1993, "Umum",      "ali.hasan@gmail.com"},
                {"P012", "Dr. Fatimah Azzahra",1975, "Dosen",     "fatimah.azzahra@univ.ac.id"},
                {"P013", "Ibrahim Malik",      2004, "Mahasiswa", "ibrahim.malik@gmail.com"},
                {"P014", "Zainab Nurhaliza",   1988, "Umum",      "zainab.nurhaliza@gmail.com"},
                {"P015", "Hamzah Alfarizi",    2005, "Mahasiswa", "hamzah.alfarizi@gmail.com"},
                {"P016", "Hafsah Amelia",      1980, "Dosen",     "hafsah.amelia@univ.ac.id"},
                {"P017", "Bilal Maulana",      2002, "Mahasiswa", "bilal.maulana@gmail.com"},
                {"P018", "Ruqayyah Putri",     1995, "Umum",      "ruqayyah.putri@gmail.com"},
                {"P019", "Dr. Salman Alfarisi",1976, "Dosen",     "salman.alfarisi@univ.ac.id"},
                {"P020", "Aminah Kamila",      2005, "Mahasiswa", "aminah.kamila@gmail.com"},
                {"P021", "Idris Ramadhani",    1991, "Umum",      "idris.ramadhani@gmail.com"},
                {"P022", "Sumayyah Dewi",      2002, "Mahasiswa", "sumayyah.dewi@gmail.com"},
                {"P023", "Khalid Pratama",     1983, "Dosen",     "khalid.pratama@univ.ac.id"},
                {"P024", "Halimah Safitri",    1987, "Umum",      "halimah.safitri@gmail.com"},
                {"P025", "Zaid Nugraha",       2003, "Mahasiswa", "zaid.nugraha@gmail.com"},
                {"P026", "Dr. Ummu Kultsum",   1979, "Dosen",     "ummu.kultsum@univ.ac.id"},
                {"P027", "Anas Setiawan",      1994, "Umum",      "anas.setiawan@gmail.com"},
                {"P028", "Naila Khairunnisa",  2004, "Mahasiswa", "naila.khairunnisa@gmail.com"},
                {"P029", "Taufik Hidayat",     1981, "Dosen",     "taufik.hidayat@univ.ac.id"},
                {"P030", "Layla Anjani",       1998, "Umum",      "layla.anjani@gmail.com"}
        };

        for (Object[] b : data) {
            daftarPeserta.add(new Main(
                    (String) b[0], (String) b[1], (int) b[2],
                    (String) b[3], (String) b[4]));
        }
        refreshTabel();
    }

    private void refreshTabel() {
        tableModel.setRowCount(0);
        for (Main p : daftarPeserta) {
            tableModel.addRow(p.toRow());
        }
    }

    private void tambahPeserta() {
        String id = ID.getText().trim();
        String nama = Nama.getText().trim();
        String tahunLahir = TahunLahir.getText().trim();
        String email = Email.getText().trim();

        Object pilihan = Kategori.getSelectedItem();
        String kategori = pilihan == null ? "" : pilihan.toString();

        if (id.isEmpty() || nama.isEmpty() || tahunLahir.isEmpty()
                || email.isEmpty() || kategori.isEmpty()
                || kategori.equals("-- Pilih Kategori --")) {
            peringatan("Kolom harus diisi, tidak boleh ada yang kosong");
            return;
        }

        for (Main p : daftarPeserta) {
            if (p.getId().equalsIgnoreCase(id)) {
                peringatan("ID sudah dipakai!");
                return;
            }
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

        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            peringatan("Format email tidak valid!");
            return;
        }

        daftarPeserta.add(new Main(id, nama, tahun, kategori, email));
        refreshTabel();

        JOptionPane.showMessageDialog(this, "Data peserta berhasil ditambahkan!");
        bersihkanForm();
    }

    private void peringatan(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }

    private void bersihkanForm() {
        ID.setText("");
        Nama.setText("");
        TahunLahir.setText("");
        Email.setText("");
        Kategori.setSelectedIndex(0);
        ID.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PesertaMabit frame = new PesertaMabit();
            frame.setVisible(true);
        });
    }
}

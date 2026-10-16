# TP4DPBO2526C2 - Aplikasi Data Sekolah (PeopleData)

Aplikasi desktop berbasis **Java Swing** untuk mengelola data orang (*Person*) dengan tema **sekolah**. Pengguna bisa menambah data, membatalkan isian form, dan menghapus data (dengan konfirmasi) yang ditampilkan dalam tabel.

---

## 1. Desain dan Fungsi Program

### Atribut data (class `Person`)

| Atribut | Tipe | Komponen di Form | Keterangan |
|---|---|---|---|
| ID | String | `JTextField` | Kode unik, contoh `S001`, `G001`, `T001` |
| Nama | String | `JTextField` | Nama lengkap |
| Tahun Lahir | int | `JTextField` | Angka, rentang 1900 - 2026 |
| Kategori | String | `JComboBox` | Pilihan: Siswa, Guru, Staf |
| **Hobi** (atribut baru) | String | `JComboBox` | Pilihan: Membaca, Olahraga, Musik, Melukis, Memasak, Gaming |

> Atribut baru **Hobi** memakai `JComboBox` (bukan `JTextField`).

### Fitur

| Tombol | Fungsi |
|---|---|
| **ADD** | Memvalidasi isian lalu menambahkan data baru ke tabel |
| **CANCEL** | Mengosongkan seluruh isian form dan mereset pilihan combo box |
| **DELETE** | Menghapus baris yang dipilih di tabel, **setelah** pengguna menyetujui dialog konfirmasi |

### Validasi pada ADD

- Semua kolom wajib diisi (combo box tidak boleh masih "-- Pilih ... --").
- ID tidak boleh sama dengan ID yang sudah ada.
- Tahun lahir harus berupa angka dan berada di antara 1900 sampai 2026.

---

## 2. Struktur File

```
TP4DPBO2526C2/
├── Dokumentasi/
│   ├── ADD/
│   │   ├── 1.png
│   │   └── 2.png
│   ├── CANCEL/
│   │   ├── 1.png
│   │   └── 2.png
│   └── DELETE/
│       ├── 1.png
│       ├── 2.png
│       └── 3.png
└── Main/
    ├── PeopleData.java
    ├── Person.java
    ├── PersonMenu.form
    └── PersonMenu.java
```

| File | Peran |
|---|---|
| `Person.java` | Class model. Menyimpan satu data orang (ID, Nama, Tahun Lahir, Kategori, Hobi) dan method `toRow()` untuk mengubahnya jadi satu baris tabel |
| `PeopleData.java` | Class penyimpan data. Berisi `ArrayList<Person>`, method `populateList()` untuk data awal, serta `add()`, `remove()`, `isIdExist()` |
| `PersonMenu.form` | Desain tampilan (dibuat dengan GUI Designer IntelliJ) |
| `PersonMenu.java` | Logika tampilan: mengisi combo box, menampilkan tabel, dan menangani tombol ADD, CANCEL, DELETE. Berisi `main()` sebagai titik masuk program |

---

## 3. Penjelasan Alur

### Alur umum saat program dijalankan

1. `main()` di `PersonMenu` membuat jendela `PersonMenu`.
2. `PersonMenu` membuat objek `PeopleData`. Di dalamnya `populateList()` mengisi 12 data awal.
3. Combo box **Kategori** dan **Hobi** diisi pilihannya.
4. Tabel diisi dari list lewat `refreshTabel()` (setiap `Person` diubah menjadi baris dengan `toRow()`).
5. Program menunggu klik tombol dari pengguna.

```
PersonMenu  --memakai-->  PeopleData  --menyimpan-->  Person
 (tampilan)               (kumpulan data)             (satu data)
```

### Alur tombol ADD

1. Pengguna mengisi ID, Nama, Tahun Lahir, lalu memilih Kategori dan Hobi.
2. Klik **ADD**.
3. Program mengecek: kolom kosong, ID duplikat, dan tahun lahir. Jika ada yang salah, muncul pesan peringatan dan data tidak ditambahkan.
4. Jika valid, objek `Person` baru dibuat dan dimasukkan ke `PeopleData`.
5. Tabel di-refresh, muncul pesan "Data berhasil ditambahkan!", dan form dikosongkan.

### Alur tombol CANCEL

1. Pengguna sudah mengisi sebagian atau seluruh form.
2. Klik **CANCEL**.
3. Semua text field dikosongkan, combo box kembali ke pilihan awal, seleksi tabel dihapus, dan kursor kembali ke kolom ID. Data di tabel tidak berubah.

### Alur tombol DELETE

1. Pengguna memilih (klik) satu baris di tabel.
2. Klik **DELETE**. Jika belum ada baris yang dipilih, muncul peringatan.
3. Muncul **dialog konfirmasi**: *"Yakin ingin menghapus data ...?"* dengan pilihan Yes / No.
4. Jika **Yes**, data dihapus dari `PeopleData`, tabel di-refresh, dan muncul pesan "Data berhasil dihapus!".
5. Jika **No**, tidak ada yang berubah.

DOKUMENTASI ADA PADA FILE DOKUMENTASI
---

## 4. Cara Menjalankan

1. Buka folder `Main` sebagai project di **IntelliJ IDEA**.
2. Pastikan `PersonMenu.java` dan `PersonMenu.form` berada dalam folder yang sama.
3. Pastikan di Designer: root panel ber-field name `mainPanel`, dan `JTable` (field name `tablePerson`) berada di dalam `JScrollPane`.
4. **Build > Rebuild Project**.
5. Klik kanan `PersonMenu.java` > **Run 'PersonMenu.main()'**.

> Catatan: jika muncul `NullPointerException` pada `tablePerson`, berarti `JTable` di Designer belum ter-bind ke field name tersebut.

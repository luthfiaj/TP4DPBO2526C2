public class Person {
    private String id;
    private String nama;
    private int tahunLahir;
    private String kategori;
    private String hobi;   // atribut baru

    public Person(String id, String nama, int tahunLahir, String kategori, String hobi) {
        this.id = id;
        this.nama = nama;
        this.tahunLahir = tahunLahir;
        this.kategori = kategori;
        this.hobi = hobi;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public int getTahunLahir() { return tahunLahir; }
    public String getKategori() { return kategori; }
    public String getHobi() { return hobi; }

    public Object[] toRow() {
        return new Object[]{id, nama, tahunLahir, kategori, hobi};
    }
}
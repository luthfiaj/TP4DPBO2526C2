import java.util.ArrayList;

public class PeopleData {
    private final ArrayList<Person> list = new ArrayList<>();

    public PeopleData() {
        populateList();
    }

    private void populateList() {
        list.add(new Person("S001", "Ahmad Fauzi",      2008, "Siswa", "Membaca"));
        list.add(new Person("S002", "Siti Aisyah",      2009, "Siswa", "Olahraga"));
        list.add(new Person("S003", "Rizky Hidayat",    2008, "Siswa", "Musik"));
        list.add(new Person("S004", "Nur Fatimah",      2010, "Siswa", "Melukis"));
        list.add(new Person("S005", "Abdullah Hakim",   2009, "Siswa", "Gaming"));
        list.add(new Person("G001", "Bu Khadijah",      1980, "Guru",  "Membaca"));
        list.add(new Person("G002", "Pak Umar Faruq",   1975, "Guru",  "Olahraga"));
        list.add(new Person("G003", "Bu Aisyah Rahma",  1985, "Guru",  "Memasak"));
        list.add(new Person("G004", "Pak Yusuf",        1978, "Guru",  "Musik"));
        list.add(new Person("T001", "Maryam Salsabila", 1990, "Staf",  "Memasak"));
        list.add(new Person("T002", "Ali Hasan",        1988, "Staf",  "Gaming"));
        list.add(new Person("T003", "Zainab Nurhaliza", 1992, "Staf",  "Melukis"));
    }

    public ArrayList<Person> getList() {
        return list;
    }

    public boolean isIdExist(String id) {
        for (Person p : list) {
            if (p.getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    public void add(Person p) {
        list.add(p);
    }

    public void remove(int index) {
        list.remove(index);
    }
}
# Penjual Bacang

```java
class SumberBunyi {
    String nama;
    boolean nyala = false;
    
    void bunyi(String bunyiDari) {
        this.nyala = true;
        if (bunyiDari.equals("mobil")){
            System.out.println("Seorang pedagang bacang bernama " + Orang.nama + " Mendengar suara tin dari mobil yang lewat");
        }
        else if (bunyiDari.equals("mesjid")){
            System.out.println("Seorang pedagang bacang bernama " + Orang.nama + " Mendengar suara adzan berkumandang dari mesjid terdekat");
        }
        else if (bunyiDari.equals("warga")){
            System.out.println("Seorang pedagang bacang bernama " + Orang.nama + " Mendengar perbincangan para warga di sekitar");
        }
    }
}

class Pendengaran {
    String nama;
    SumberBunyi mesjid;
    SumberBunyi warga;
    SumberBunyi mobil;
}

class Tempat {
    String alamat;
    Pendengaran normal;
    Pendengaran tuli;
}

class Orang {
    static String nama;
    Tempat pasar;
}

class Latihan {
    public static void main(String[] args) {
        
        Orang warga = new Orang();
        warga.nama = "Tejo";
        
        Tempat pasar = new Tempat();
        Pendengaran normal = new Pendengaran();
        SumberBunyi mobil = new SumberBunyi();
        
        normal.mobil = mobil;
        pasar.normal = normal;
        warga.pasar = pasar;
        
        warga.pasar.normal.mobil.bunyi("mobil");
        
    }
}
```
```java
class Suara {
    String jenisSuara;
    
    void dengarSuara() {
        System.out.println("Dengar suara " + jenisSuara);
    }
}

class Mobil {
    String namaMobil;
    Suara klakson;
    
    void klakson() {
        klakson.dengarSuara();
    }
}

class Warga {
    String namaWarga;
    Suara teriakan;
    
    void teriak() {
        teriakan.dengarSuara();
    }
}

class Mesjid {
    String namaMesjid;
    Suara adzan;
    
    void panggilSholat() {
        adzan.dengarSuara();
    }
}

public class PenjualBacang2 {
    String namaPenjual;
    String genderPenjual;
    int usiaPenjual;
    
    PenjualBacang2(String nama, String gender, int usia) {
        namaPenjual = nama;
        genderPenjual = gender;
        usiaPenjual = usia;
    }
    
    void mendengarSuara(Suara suara) {
        System.out.println("Penjual bacang bernama " + namaPenjual + " mendengar suara " + suara.jenisSuara);
    }

    public static void main(String[] args) {
        
        Suara klakson = new Suara();
        klakson.jenisSuara = "klakson mobil";
        
        Mobil mobil = new Mobil();
        mobil.namaMobil = "Avanza";
        mobil.klakson = klakson;
        
        Suara teriakan = new Suara();
        teriakan.jenisSuara = "teriakan warga";
        
        Warga warga = new Warga();
        warga.namaWarga = "Budi";
        warga.teriakan = teriakan;
        
        Suara adzan = new Suara();
        adzan.jenisSuara = "adzan dari mesjid";
        
        Mesjid mesjid = new Mesjid();
        mesjid.namaMesjid = "Al-Mashun";
        mesjid.adzan = adzan;
        
        PenjualBacang2 penjual = new PenjualBacang2("Siti", "Perempuan", 45);
        
        mobil.klakson();
        warga.teriak();
        mesjid.panggilSholat();
        penjual.mendengarSuara(klakson);
        
    }
}
```

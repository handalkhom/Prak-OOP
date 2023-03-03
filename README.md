# Prak-OOP
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class SumberBunyi {
    String nama;
    boolean nyala = false;
    
    void bunyi(String bunyiDari) {
        this.nyala = true;
        if (bunyiDari.equals("mobil")){
            System.out.println("Seorang pedagang bacang bernama " + Orang.nama + "Mendengar suara tin dari mobil yang lewat");}
        else if (bunyiDari.equals("mesjid")){
            System.out.println("Seorang pedagang bacang bernama " + Orang.nama + "Mendengar suara adzan berkumandang dari mesjid terdekat");}
        else if (bunyiDari.equals("warga")){
            System.out.println("Seorang pedagang bacang bernama " + Orang.nama + "Mendengar perbincangan para warga di sekitar");}
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
    String nama;
    Tempat pasar;
}

class Latihan {
    public static void main(String[] args) {
        
        Orang warga = new Orang();
        warga.nama = "Tejo";
        
        Tempat pasar = new Tempat();
        Pendengaran normal = new Pendengarann();
        SumberBunyi mobil = new SumberBunyi();
        
        dapur.mobil = mobil;
        pasar.normal = normal;
        warga.pasar = pasar;
        
        warga.pasar.normal.mobil.bunyi("mobil");
        
    }
}

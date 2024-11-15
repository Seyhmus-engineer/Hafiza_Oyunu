
import java.util.Scanner;


public class Main {

    private static Kart[][] kartlar = new Kart[4][4];
    private static boolean oyunDevam = true;

    public static void main(String[] args) {
        açılışMenüsü();
        oyunuBaşlat();
    }

    public static void açılışMenüsü() {
        System.out.println("**********************************");
        System.out.println("*          KART OYUNU           *");
        System.out.println("*  Eşleştirme oyununa hoşgeldiniz  *");
        System.out.println("**********************************");
        System.out.println("Devam etmek için Enter tuşuna basın...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void oyunuBaşlat() {
        kartlar[0][0] = new Kart('E');
        kartlar[0][1] = new Kart('A');
        kartlar[0][2] = new Kart('B');
        kartlar[0][3] = new Kart('F');
        kartlar[1][0] = new Kart('G');
        kartlar[1][1] = new Kart('A');
        kartlar[1][2] = new Kart('D');
        kartlar[1][3] = new Kart('H');
        kartlar[2][0] = new Kart('F');
        kartlar[2][1] = new Kart('C');
        kartlar[2][2] = new Kart('D');
        kartlar[2][3] = new Kart('H');
        kartlar[3][0] = new Kart('E');
        kartlar[3][1] = new Kart('G');
        kartlar[3][2] = new Kart('B');
        kartlar[3][3] = new Kart('C');

        while (oyunBittimi() == false && oyunDevam) {
            oyunTahtası();
            tahminEt();
        }

        if (!oyunDevam) {
            System.out.println("Oyun durduruldu.");
        } else {
            System.out.println("Oyun bitti. Tebrikler!");
        }
    }

    public static void tahminEt() {
        Scanner k = new Scanner(System.in);

        System.out.print("Oyunu durdurmak için 'q' ya basın, devam etmek için Enter tuşuna basın: ");
        String secim = k.nextLine();
        if (secim.equals("q")) {
            oyunDevam = false;
            return;
        }

        System.out.print("Birinci tahmin (i ve j degerlerini bir bosluklu girin): ");
        int i1 = k.nextInt();
        int j1 = k.nextInt();

        kartlar[i1][j1].setTahmin(true);
        oyunTahtası();

        System.out.print("Ikinci tahmin (i ve j degerlerini bir bosluklu girin): ");
        int i2 = k.nextInt();
        int j2 = k.nextInt();

        // Yanlış tahmin olsa bile kartı göster
        kartlar[i2][j2].setTahmin(true);
        oyunTahtası();

        if (kartlar[i1][j1].getDeger() == kartlar[i2][j2].getDeger()) {
            System.out.println("Doğru tahmin! Tebrikler.");
        } else {
            System.out.println("Yanlış tahmin. Kartlar kapatılıyor.");
            kartlar[i1][j1].setTahmin(false);
            kartlar[i2][j2].setTahmin(false);
        }
    }

    public static boolean oyunBittimi() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (kartlar[i][j].isTahmin() == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void oyunTahtası() {
        for (int i = 0; i < 4; i++) {
            System.out.println("____________________");
            for (int j = 0; j < 4; j++) {
                if (kartlar[i][j].isTahmin()) {
                    System.out.print(" |" + kartlar[i][j].getDeger() + "| ");
                } else {
                    System.out.print(" |x| ");
                }
            }
            System.out.println(" ");
        }
        System.out.println("____________________");
    }
}

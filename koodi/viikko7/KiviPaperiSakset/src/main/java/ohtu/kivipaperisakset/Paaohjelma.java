package ohtu.kivipaperisakset;

public class Paaohjelma {

    public static void main(String[] args) {
		
		KonsoliIO konsoli = new KonsoliIO();
		Pelitehdas pelityyppi = new Pelitehdas(konsoli);

        while (true) {
            konsoli.print("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

			String vastaus = konsoli.nextLine();
			vastaus = "" + vastaus.charAt(vastaus.length()-1);
						
			if(pelityyppi.hae(vastaus) ==  null) {
				break;
			} else {
				konsoli.print("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
				pelityyppi.hae(vastaus).pelaa(konsoli);
			}

        }

    }
}

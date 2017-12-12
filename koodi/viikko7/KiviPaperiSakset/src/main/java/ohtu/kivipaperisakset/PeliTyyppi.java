package ohtu.kivipaperisakset;

public abstract class PeliTyyppi implements PeliLogiikka {

	@Override
	public void pelaa(IO io) {
		Tuomari tuomari = new Tuomari();
        
        io.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = io.nextLine();
        String tokanSiirto = annaSiirto();
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.print(tuomari.toString());
            io.print("");

            io.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = io.nextLine();
            tokanSiirto = annaSiirto();

        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
		
	}
	
	private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
	
	protected abstract String annaSiirto();
	
}

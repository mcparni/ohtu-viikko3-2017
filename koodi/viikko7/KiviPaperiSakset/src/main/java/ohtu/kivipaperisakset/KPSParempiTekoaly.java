package ohtu.kivipaperisakset;


// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends PeliTyyppi {

	private TekoalyParannettu tekoaly;
	private IO io;
	
	public KPSParempiTekoaly(IO io) {
		this.io = io;
		tekoaly = new TekoalyParannettu(20);
	}
	
	@Override
	protected String annaSiirto() {
		String tokanSiirto = tekoaly.annaSiirto();
        io.print("Tietokone valitsi: " + tokanSiirto);
		return tokanSiirto;
	}


}

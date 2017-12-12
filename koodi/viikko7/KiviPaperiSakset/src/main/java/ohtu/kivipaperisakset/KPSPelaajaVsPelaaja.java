package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends PeliTyyppi {

	private IO io;
	
	public KPSPelaajaVsPelaaja(IO io) {
		this.io = io;
	}
	
	@Override
	protected String annaSiirto() {
		io.print("Toisen pelaajan siirto: ");
        String tokanSiirto = io.nextLine();
		return tokanSiirto;
	}
}
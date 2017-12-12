package ohtu.kivipaperisakset;

public class KPSTekoaly extends PeliTyyppi {

	private Tekoaly tekoaly;
	private IO io;
	
	public KPSTekoaly(IO io) {
		this.io = io;
		tekoaly = new Tekoaly();
	}
	
	@Override
	protected String annaSiirto() {
		String tokanSiirto = tekoaly.annaSiirto();
        io.print("Tietokone valitsi: " + tokanSiirto);
		return tokanSiirto;
	}
}
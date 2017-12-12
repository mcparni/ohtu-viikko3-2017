package ohtu.kivipaperisakset;

import java.util.HashMap;

public class Pelitehdas {
	private HashMap<String, PeliTyyppi> pelityypit;
	
	public Pelitehdas(IO io) {
        pelityypit = new HashMap<String, PeliTyyppi>();
        pelityypit.put("a", new KPSPelaajaVsPelaaja(io));
        pelityypit.put("b", new KPSTekoaly(io));
        pelityypit.put("c", new KPSParempiTekoaly(io));
        
    }
	
	public PeliTyyppi hae(String operaatio) {
        PeliTyyppi komento = pelityypit.get(operaatio);
        return komento;
    }
}

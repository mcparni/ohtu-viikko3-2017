
package ohtu.intjoukkosovellus;


public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        lukujono = new int[KAPASITEETTI];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
	private void tarkistaArvot(int kapasiteetti, int kasvatuskoko) {
		if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti on pienempi kuin 0");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko on pienempi kuin 0");
        }
	}
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        tarkistaArvot(kapasiteetti, kasvatuskoko);
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
	
	private void lisaaTyhjaan(int luku) {
		lukujono[0] = luku;
        alkioidenLkm++;
	}

	private void lisaaUusi(int luku) {
		lukujono[alkioidenLkm] = luku;
        alkioidenLkm++;
		if (alkioidenLkm % lukujono.length == 0) {
			int[] taulukkoOld = new int[lukujono.length];
			taulukkoOld = lukujono;
			kopioiTaulukko(lukujono, taulukkoOld);
			lukujono = new int[alkioidenLkm + kasvatuskoko];
			kopioiTaulukko(taulukkoOld, lukujono);
		}
	}
	
    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
			lisaaTyhjaan(luku);
            return true;
        }		
        if (!kuuluu(luku)) {
            lisaaUusi(luku);
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }
	
	private int alkionIndeksi(int luku) {
		int kohta = -1;
		for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                kohta = i;
                lukujono[kohta] = 0;
                return kohta;
            }
        }
		return kohta;
	}
	
	private void poistaPaikasta(int indeksi) {
		int apu;
		for (int j = indeksi; j < alkioidenLkm - 1; j++) {
			apu = lukujono[j];
			lukujono[j] = lukujono[j + 1];
			lukujono[j + 1] = apu;
		}
		alkioidenLkm--;
	}

    public boolean poista(int luku) {
        int indeksi = alkionIndeksi(luku);       
        if (indeksi != -1) {
            poistaPaikasta(indeksi);
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }
	
	private String tulostaKaikkiAlkiot() {		
		String tuotos = "{";
		for (int i = 0; i < alkioidenLkm - 1; i++) {
			tuotos += lukujono[i];
			tuotos += ", ";
		}
		tuotos += lukujono[alkioidenLkm - 1];
		tuotos += "}";
		return tuotos;
	}

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujono[0] + "}";
        } else {
            return tulostaKaikkiAlkiot();
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }
	
	public static IntJoukko yhdisteOperaatio(int[] joukkoA, int[] joukkoB) {
		IntJoukko joukko = new IntJoukko();
		for (int i = 0; i < joukkoA.length; i++) {
            joukko.lisaa(joukkoA[i]);
        }
        for (int i = 0; i < joukkoB.length; i++) {
            joukko.lisaa(joukkoB[i]);
        }
        return joukko;
	}
	
	public static IntJoukko leikkausOperaatio(int[] joukkoA, int[] joukkoB) {
		IntJoukko joukko = new IntJoukko();
		for (int i = 0; i < joukkoA.length; i++) {
            for (int j = 0; j < joukkoB.length; j++) {
                if (joukkoA[i] == joukkoB[j]) {
                    joukko.lisaa(joukkoB[j]);
                }
            }
        }
		return joukko;
	}
	
	public static IntJoukko erotusOperaatio(int[] joukkoA, int[] joukkoB) {
		IntJoukko joukko = new IntJoukko();
		for (int i = 0; i < joukkoA.length; i++) {
            joukko.lisaa(joukkoA[i]);
        }
        for (int i = 0; i < joukkoB.length; i++) {
            joukko.poista(i);
        }
		return joukko;
	}

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] joukkoA = a.toIntArray();
        int[] joukkoB = b.toIntArray();
		yhdiste = yhdisteOperaatio(joukkoA, joukkoB);
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] joukkoA = a.toIntArray();
        int[] joukkoB = b.toIntArray();
        leikkaus = leikkausOperaatio(joukkoA, joukkoB);
        return leikkaus;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] joukkoA = a.toIntArray();
        int[] joukkoB = b.toIntArray();
        erotus = erotusOperaatio(joukkoA, joukkoB);
        return erotus;
    }
        
}
package ohtu;

import javax.swing.JTextField;

public class Erotus implements Komento {
	
	private Sovelluslogiikka sovellus;
	private JTextField tuloskentta;
	private JTextField syotekentta;
	
	public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
		this.sovellus = sovellus;
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
	}

	@Override
	public void suorita() {
		int arvo = 0;
		try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
		sovellus.miinus(arvo);
		int laskunTulos = sovellus.tulos();
         
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
		
	}
	
	@Override
	public void peru() {
	
	}
	
}

package ohtu;

import javax.swing.JTextField;

public class Peru implements Komento {
	
	private Sovelluslogiikka sovellus;
	private JTextField tuloskentta;
	private JTextField syotekentta;
	
	public Peru(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
		this.sovellus = sovellus;
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
	}

	@Override
	public void suorita() {
		int tilanne = sovellus.peru();
		syotekentta.setText("");
        tuloskentta.setText("" + tilanne);
	}
	
}

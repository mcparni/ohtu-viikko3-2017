package ohtu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Sovelluslogiikka {
 
    private int tulos;
	private int edellinen;
	private LinkedList vanha;
	
	private void lisaaPeruttava() {
		vanha.add(tulos);
	}
	
	public Sovelluslogiikka() {
		vanha = new LinkedList();
		this.tulos = 0;	
	}
	
    public void plus(int luku) {
		lisaaPeruttava();
        tulos += luku;
    }
     
    public void miinus(int luku) {
		lisaaPeruttava();		
        tulos -= luku;
    }
 
    public void nollaa() {
		lisaaPeruttava();
        tulos = 0;
    }
 
    public int tulos() {
        return tulos;
    }
	
	private int tarkistaPeruttavat() {
		int tilanne = -1;
		if(vanha.size() == 0) {
			return tilanne;
		} 
		tilanne = (int)vanha.getLast();
		return tilanne;
	}
	
	public int peru() {
		System.out.println(vanha);
		int tilanne = tarkistaPeruttavat();
		if(tilanne != -1) {
			tulos = tilanne;
			vanha.removeLast();
		}
		
		return tilanne;
		
	}
	public boolean peruttavat() {
		return vanha.size() > 0 ? true : false;
	}
}
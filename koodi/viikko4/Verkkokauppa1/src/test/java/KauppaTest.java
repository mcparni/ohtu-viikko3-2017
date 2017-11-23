
import ohtu.verkkokauppa.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class KauppaTest {
	
	String asiakas = "Pekka";
	String asiakkaanTili = "12345";
	String kaupanTili = "33333-44455";
	
	@Test
	public void ostetaanYksiTuoteJotaOnVarastossa() {

		Pankki pankki = mock(Pankki.class);

		Viitegeneraattori viite = mock(Viitegeneraattori.class);

		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		
		when(varasto.saldo(1)).thenReturn(10); 
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

		Kauppa k = new Kauppa(varasto, pankki, viite);              
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1); 
		k.tilimaksu(asiakas, asiakkaanTili);

		verify(pankki).tilisiirto(eq(asiakas), eq(42), eq(asiakkaanTili), eq(kaupanTili), eq(5));   
		
	}
	@Test
	public void ostetaanKaksiEriTuotettaJoitaOnVarastossa() {

		Pankki pankki = mock(Pankki.class);

		Viitegeneraattori viite = mock(Viitegeneraattori.class);

		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		
		when(varasto.saldo(1)).thenReturn(10); 
		when(varasto.saldo(2)).thenReturn(10); 
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "koskenkorva", 8));

		Kauppa k = new Kauppa(varasto, pankki, viite);              
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.lisaaKoriin(2); 
		k.tilimaksu(asiakas, asiakkaanTili);

		verify(pankki).tilisiirto(eq(asiakas), eq(42), eq(asiakkaanTili), eq(kaupanTili), eq(13));   
		
	}
	
	@Test
	public void ostetaanKaksiSamaaTuotettaJoitaOnVarastossa() {

		Pankki pankki = mock(Pankki.class);

		Viitegeneraattori viite = mock(Viitegeneraattori.class);

		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		
		when(varasto.saldo(2)).thenReturn(10); 
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "koskenkorva", 8));

		Kauppa k = new Kauppa(varasto, pankki, viite);              
		
		k.aloitaAsiointi();
		
		k.lisaaKoriin(2); 
		k.lisaaKoriin(2); 
		k.tilimaksu(asiakas, asiakkaanTili);

		verify(pankki).tilisiirto(eq(asiakas), eq(42), eq(asiakkaanTili), eq(kaupanTili), eq(16));   
		
	}
	
	@Test
	public void ostetaanKaksiTuotettaJoistaVainToistaOnVarastossa() {

		Pankki pankki = mock(Pankki.class);

		Viitegeneraattori viite = mock(Viitegeneraattori.class);

		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		
		when(varasto.saldo(1)).thenReturn(0); 
		when(varasto.saldo(2)).thenReturn(10); 
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "koskenkorva", 8));

		Kauppa k = new Kauppa(varasto, pankki, viite);              
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.lisaaKoriin(2); 
		k.tilimaksu(asiakas, asiakkaanTili);

		verify(pankki).tilisiirto(eq(asiakas), eq(42), eq(asiakkaanTili), eq(kaupanTili), eq(8));   
		
	}
	
	@Test
	public void maksutapahtumaNollautuuUudenAloitettuaan() {

		Pankki pankki = mock(Pankki.class);

		Viitegeneraattori viite = mock(Viitegeneraattori.class);

		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		
		when(varasto.saldo(1)).thenReturn(10); 
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

		Kauppa k = new Kauppa(varasto, pankki, viite);              
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.tilimaksu(asiakas, asiakkaanTili);

		verify(pankki).tilisiirto(eq(asiakas), eq(42), eq(asiakkaanTili), eq(kaupanTili), eq(5));   
		
		
		k.aloitaAsiointi();
		k.tilimaksu(asiakas, asiakkaanTili);

		verify(pankki).tilisiirto(eq(asiakas), eq(42), eq(asiakkaanTili), eq(kaupanTili), eq(5));
		
	}
	
	@Test
	public void uusiViiteUudessaMaksutapahtumassa() {

		Pankki pankki = mock(Pankki.class);

		Viitegeneraattori viite = mock(Viitegeneraattori.class);
		
		Varasto varasto = mock(Varasto.class);
		
		when(varasto.saldo(1)).thenReturn(10); 
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		
		Kauppa k = new Kauppa(varasto, pankki, viite);   
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.tilimaksu(asiakas, asiakkaanTili);
		verify(viite, times(1)).uusi();	
		
		k.aloitaAsiointi();
		k.tilimaksu(asiakas, asiakkaanTili);
		verify(viite, times(2)).uusi();
		
	}
	
}

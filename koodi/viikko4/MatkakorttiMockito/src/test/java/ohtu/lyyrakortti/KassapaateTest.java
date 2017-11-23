
package ohtu.lyyrakortti;

import ohtu.matkakortti.Matkakortti;
import ohtu.matkakortti.Kassapaate;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KassapaateTest {
    
    Kassapaate kassa;
    Matkakortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = mock(Matkakortti.class);
    }
    
    @Test
    public void kortiltaVelotetaanHintaJosRahaaOn() {
        when(kortti.getSaldo()).thenReturn(10);
        kassa.ostaLounas(kortti);
        
        verify(kortti, times(1)).getSaldo();
        verify(kortti).osta(eq(Kassapaate.HINTA));
    }

    @Test
    public void kortiltaEiVelotetaJosRahaEiRiita() {
        when(kortti.getSaldo()).thenReturn(4);
        kassa.ostaLounas(kortti);
        
        verify(kortti, times(1)).getSaldo();
        verify(kortti, times(0)).osta(anyInt());
    }
	
	@Test
    public void kortilleLadataanPositiivinenSaldo() {
        int saldo = Kassapaate.HINTA;
		when(kortti.getSaldo()).thenReturn(saldo);
		
        kassa.lataa(kortti, saldo);
		kassa.ostaLounas(kortti);
		
        verify(kortti, times(1)).getSaldo();
		verify(kortti).osta(eq(saldo));
    }
	@Test
    public void kortilleLadataanNegatiivinenSaldo() {
        when(kortti.getSaldo()).thenReturn(0);
        kassa.lataa(kortti, -1);
		
        verify(kortti, times(0)).getSaldo();
    }
	
}

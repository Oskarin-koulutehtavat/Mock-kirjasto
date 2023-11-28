import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mockesimerkki.*;

class TilaustenKäsittelyMockitoTest {
		@Mock
		IHinnoittelija hinnoittelijaMock;
		
		@BeforeEach
		public void setup() {
			MockitoAnnotations.openMocks(this);
		}
		
		@Test
		void testaaKäsittelijäWithMockitoHinnoittelijaAlle100() {
			// Arrange
			float alkuSaldo = 1000.0f;
			float listaHinta = 30.0f;
			float alennus = 20.0f;
			float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));
			Asiakas asiakas = new Asiakas(alkuSaldo);
			Tuote tuote = new Tuote("TDD in Action", listaHinta);
			
			// Record
			Mockito.when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(alennus);
			Mockito.when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(alennus);
			
			// Act
			TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
			käsittelijä.setHinnoittelija(hinnoittelijaMock);
			käsittelijä.käsittele(new Tilaus(asiakas, tuote));
			
			// Assert
			Assertions.assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
			Mockito.verify(hinnoittelijaMock, times(2)).getAlennusProsentti(asiakas, tuote);
		}
		
		@Test
		void testaaKäsittelijäWithMockitoHinnoittelijaYli100() {
			float alkuSaldo = 1000.0f;
			float listaHinta = 300.0f;
			float alennus = 20.0f;
			float loppuSaldo = alkuSaldo - (listaHinta * (1 - (alennus + 5) / 100));
			Asiakas asiakas = new Asiakas(alkuSaldo);
			Tuote tuote = new Tuote("TDD in Action", listaHinta);
			
			// Record
			Mockito.when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(alennus);
			Mockito.when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(alennus + 5);
			
			// Act
			TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
			käsittelijä.setHinnoittelija(hinnoittelijaMock);
			käsittelijä.käsittele(new Tilaus(asiakas, tuote));
			
			// Assert
			Assertions.assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
			Mockito.verify(hinnoittelijaMock, times(2)).getAlennusProsentti(asiakas, tuote);
		}
}

package mockesimerkki;

public class FakeHinnoittelija implements IHinnoittelija {

	private float alennus;
	public FakeHinnoittelija(float alennus) {
		this.alennus = alennus;
	}
	
	public float getAlennusProsentti(Asiakas asiakas, Tuote tuote) {
		return alennus;
	}

	public void setAlennusProsentti(Asiakas asiakas, float f) {
		this.alennus = f;
		
	}

	public void aloita() {
		// TODO Auto-generated method stub
		
	}

	public void lopeta() {
		// TODO Auto-generated method stub
		
	}

}

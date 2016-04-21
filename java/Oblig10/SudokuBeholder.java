public class SudokuBeholder {
    private Brett brettet;
    private int antallLosninger = 0;

    public static final int maksAntallLagredeLosninger = 3500;

    public SudokuBeholder(Brett brettet) {
	this.brettet = brettet;
    }

    public void settInn(Rute[] losning) {
	if (++antallLosninger >= maksAntallLagredeLosninger) {
	    return;
	}
	
       
    }

    public Rute[] taUt() {
	return null;
    }

    public int hentAntallLosninger() {
	return antallLosninger();
    }
}

public class MiksturC extends TypeC  implements Mikstur {
    private int volum;
    private double virkestoff;

    public MiksturC(String navn, int id, double pris, int vol, double v) {
	super(navn, id, pris);
	volum = vol;
	virkestoff = v;
    }

    public double hentVolum() {
	return volum;
    }

    public double hentVirkestoffPerCm3() {
	return virkestoff;
    }
}

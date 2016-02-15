public class MiksturB extends TypeB  implements Mikstur {
    private int volum;
    private double virkestoff;

    public MiksturB(String navn, int id, double pris, int vanedannende, int vol, double v) {
	super(navn, id, pris, vanedannende);
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

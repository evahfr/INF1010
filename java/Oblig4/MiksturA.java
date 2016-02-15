public class MiksturA extends TypeA  implements Mikstur {
    private int volum;
    private double virkestoff;

    public MiksturA(String navn, int id, double pris, int styrke, int vol, double v) {
	super(navn, id, pris, styrke);
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

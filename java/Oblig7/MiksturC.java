public class MiksturC extends TypeC  implements Mikstur {
    private int volum;
    private double virkestoffPerCm3;
    private String info;

    public MiksturC(String navn, double pris, int vol, double vpcm3) {
	super(navn, pris);
	volum = vol;
	virkestoffPerCm3 = vpcm3;
	info = String.format("%d, %s, mikstur, c, %f, %d, %f\n", legemiddelID, navn, pris, vol, vpcm3);
    }

    public double hentVolum() {
	return volum;
    }

    public double hentVirkestoffPerCm3() {
	return virkestoffPerCm3;
    }

    public double virkestoffInnholdTot() {
	return volum*virkestoffPerCm3;
    }

    public String hentInfo() {
	return info;
    }
}

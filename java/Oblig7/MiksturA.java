public class MiksturA extends TypeA  implements Mikstur {
    private int volum;
    private double virkestoffPerCm3;
    private String info;

    public MiksturA(String navn, double pris, int styrke, int vol, double vpcm3) {
	super(navn, pris, styrke);
	volum = vol;
	virkestoffPerCm3 = vpcm3;
	info = String.format("%d, %s, mikstur, a, %f, %d, %f, %d\n", legemiddelID, navn, pris, vol, vpcm3, styrke);
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

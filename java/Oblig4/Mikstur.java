public interface Mikstur {
    /** 
     * Returnerer volumet til objektet.
     * @return volum 
     */
    public double hentVolum();

    /**
     * Returnerer objektets virkestoff per volumenhet.
     * @return virkestoff per volum enhet  
     */
    public double hentVirkestoffPerCm3();
}

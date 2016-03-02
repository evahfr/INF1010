public interface AbstraktTabell<T> {
    /**
     * Setter inn et objekt i tabellen paa en oppgitt plass (indeks).
     * @param objekt - det som skal settes inn
     * @param indeks - plassen objektet skal settes inn 
     * @return sann om operasjonen gikk bra, ellers usann
     */
    public boolean settInn(T objekt, int indeks);

    /**
     * Finne et objekt i tabellen basert på indeks.
     * @param indeks - plassen objektet skal hentes fra.
     * @return objektet
     */
    public T finnElement(int indeks);

    /**
     * Itererer over listen.
     * 
     */
    public void gaaIgjennom();
}

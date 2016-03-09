public interface AbstraktTabell<T> extends Iterable<T> {
    /**
     * Setter inn et objekt i tabellen paa en oppgitt plass (indeks).
     * @param objekt - det som skal settes inn
     * @param indeks - plassen objektet skal settes inn 
     * @return true om operasjonen gikk bra, ellers false
     */
    public boolean settInn(T objekt, int indeks);

    /**
     * Henter et objekt i tabellen basert på indeks.
     * @param indeks - plassen objektet skal hentes fra.
     * @return objektet
     */
    public T hent(int indeks);
}

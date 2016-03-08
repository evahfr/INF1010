public interface AbstraktSortertEnkelListe<T extends Comparable<T> & Lik> extends Iterable<T> {

    /**
     * Setter inn et objekt i sortert rekkefolge (minste forst) i listen.
     * @param objektet
     * @return true hvis operasjonen var vellykket, ellers false 
     */
    public boolean settInn(T element);

    /**
     * Finner et objekt ved hjelp av en String.
     * @param noekkel 
     * @return objekt 
     */
    public T finn(String noekkel);
}

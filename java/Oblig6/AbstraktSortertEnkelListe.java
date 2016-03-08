public interface AbstraktSortertEnkelListe<T extends Comparable<T> & Lik> extends Iterable<T> {

    /**
     * Setter inn et objekt i sortert rekkefoelge (minste foerst) i listen.
     * @param objektet
     * @return sann hvis operasjonen var vellykket, ellers usann 
     */
    public boolean settInn(T element);

    /**
     * Finner et objekt ved hjelp av en String.
     * @param noekkel 
     * @return objekt 
     */
    public T finn(String noekkel);
}

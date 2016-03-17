public interface AbstraktSortertEnkelListe<T extends Comparable<T> & Lik> extends Iterable<T> {

    /**
     * Setter inn et objekt i sortert rekkefoelge (minste foerst) i listen.
     * @param objektet
     * @return true hvis operasjonen var vellykket, ellers false 
     */
    public boolean settInn(T objekt);

    /**
     * Finner et objekt ved hjelp av en String.
     * Bruker her at objektet implementerer grensesnittet "Lik".
     * @param noekkel 
     * @return objektet 
     */
    public T finn(String noekkel);
}

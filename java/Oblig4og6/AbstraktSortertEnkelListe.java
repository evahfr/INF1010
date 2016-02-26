public interface AbstraktSortertEnkelListe<T> implements Comparable<T>, Lik {
    public boolean settInn(T element);
    public T finn(String noekkel);
    public void listInnhold();
}

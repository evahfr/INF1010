public interface AbstraktTabell<T> {
    public boolean settInn(T objekt, int indeks);
    public T finnElement(int indeks);
    public void gaaIgjennom();
}

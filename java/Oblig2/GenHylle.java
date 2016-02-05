interface GenHylle<E> {
    public int hylleStr();
    public boolean settInn(E ting, int plass);
    public boolean plassLedig(int plass);
    public E taUt(int plass);
}

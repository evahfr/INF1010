public class Container<E> {
    private E element;

    public void put(E element) {
	this.element = element;
    }
    
    public E take() {
	return this.element;
    }
}

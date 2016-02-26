public class SortertEnkelListe<T> implements AbstaktSortertEnkelListe<T> {
    private Node foran;

    private class Node {
	T data;
	Node neste;

	Node(T nyData) {
	    data = nyData;
	}
    }

    public boolean settInn(T element) {
	Node nyNode = new Node(element);
	
	return true;
    }

    
}

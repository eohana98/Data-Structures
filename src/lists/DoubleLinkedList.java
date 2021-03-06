package lists;

public class DoubleLinkedList<T> implements ListInterface<T> {

    private final DoubleNode<T> head;
    private final DoubleNode<T> tail;
    private int size;


    public DoubleLinkedList () {
        head = new DoubleNode<>(null);
        tail = new DoubleNode<>(null);
        head.connect(tail);
        size = 0;
    }

    @Override
    public void add(T element) {
        DoubleNode<T> node = new DoubleNode<>(element);
        DoubleNode<T> last = tail.getPrevious();

        last.connect(node);
        node.connect(tail);
        size++;
    }

    @Override
    public boolean add(T element, int index) {
        if (index < 0) {
            return false;
        } else if (index >= size) {
            add(element);
            return true;
        } else {
            DoubleNode<T> node = new DoubleNode<>(element);
            DoubleNode<T> indexNode = getNodeByIndex(index);
            indexNode.getPrevious().connect(node);
            node.connect(indexNode);
            size++;
            return true;
        }
    }

    @Override
    public void clear() {
        head.connect(tail);
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public T get(int index) {
        try {
            return getNodeByIndex(index).getData();
        } catch (NullPointerException e) {
            return null;
        }
    }

    private DoubleNode<T> getNodeByIndex(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        DoubleNode<T> curr;
        if (index > size / 2) {
            curr = tail.getPrevious();
            for (int i = size-1; i > index; i--) {
                curr = curr.getPrevious();
            }
        } else {
            curr = head.getNext();
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
        }
        return curr;
    }

    private DoubleNode<T> getNodeByElement(T element) {
        DoubleNode<T> curr = head.getNext();
        while (!curr.equals(tail)) {
            if (curr.getData().equals(element)) {
                return curr;
            }
            curr = curr.getNext();
        }
        return null;
    }

    @Override
    public int indexOf(T element) {
        int index = -1;
        DoubleNode<T> curr = head.getNext();
        for (int i = 0; i < size; i++) {
            if (curr.getData().equals(element)) {
                index = i;
                break;
            }
            curr = curr.getNext();
        }
        return index;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T removeIndex(int index) {
        try {
            DoubleNode<T> node = getNodeByIndex(index);
            T data = node.getData();
            node.getPrevious().connect(node.getNext());
            size--;
            return data;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public void remove(T element) {
        DoubleNode<T> found = getNodeByElement(element);
        if (found != null) {
            found.getPrevious().connect(found.getNext());
            size--;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder("\\ <-> ");
        DoubleNode<T> current = head.getNext();
        while (!current.equals(tail)) {
            strBuilder.append(current.getData()).append(" <-> ");
            current = current.getNext();
        }
        strBuilder.append("/");
        return strBuilder.toString();
    }


    public static void main(String[] args) {
        DoubleLinkedList<Integer> dls = new DoubleLinkedList<>();
        System.out.println(dls);
        System.out.println(dls.size());
        for(int i = 0; i < 5; i++)
            dls.add(i);
        System.out.println(dls);
        System.out.println(dls.size());
        dls.add(99, 1);
        dls.add(100, 2);
        dls.add(200, 7);
        dls.add(300, 8);
        dls.add(350, 8);
        dls.add(400, 8);
        System.out.println(dls);
        System.out.println(dls.get(3));
        System.out.println(dls.get(7));
        System.out.println(dls);
        System.out.println(dls.removeIndex(0));
        System.out.println(dls);
        System.out.println(dls.removeIndex(5));
        System.out.println(dls);
        System.out.println(dls.removeIndex(11));
        System.out.println(dls);
        System.out.println(dls.indexOf(99));
        System.out.println(dls.indexOf(1100));
        System.out.println(dls.indexOf(2));
        System.out.println(dls);
        dls.remove(99);
        System.out.println(dls);
        dls.remove(300);
        System.out.println(dls);
        dls.remove(200);
        System.out.println(dls);
    }
}

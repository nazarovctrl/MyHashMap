import java.util.Iterator;

public class MySuperList<E> implements Iterable<E> {
    private Item<E> first = null;
    private Item<E> last = null;

    /**
     * Adding value
     */

    public void add(E value) {
        Item<E> item = new Item<>(value);
        if (first == null) {
            first = item;
        } else {
            last.setNext(item);
        }
        last = item;
    }

    public void add(int index, E value) {
        if (index == 0) {
            addFirst(value);
            return;
        }
        Item<E> item = new Item<>(value);

        Item<E> temp = getItem(index - 1);
        if (temp == null) {
            return;
        }
        item.setNext(temp.getNext());
        temp.setNext(item);

    }

    public void addFirst(E value) {
        Item<E> item = new Item<>(value);
        if (first == null) {
            add(value);
        } else {
            item.setNext(first);
            first = item;
        }
    }

    public void addLast(E value) {
        add(value);
    }

    /**
     * Fetching  value
     */
    public Item<E> getItem(int index) {
        Item<E> temp = first;
        int i = 0;
        while (temp != null) {
            if (i == index) {
                return temp;
            }
            i++;
            temp = temp.getNext();
        }
        return null;
    }

    public E get(int index) {

        Item<E> temp = getItem(index);
        if (temp == null) {
            return null;
        }
        return temp.getValue();
    }

    public int indexOf(E value) {
        Item<E> temp = first;
        int i = 0;
        while (temp != null) {
            if (temp.getValue().equals(value)) {
                return i;
            }
            i++;
            temp = temp.getNext();
        }
        return -1;
    }

    /**
     * Update value
     */

    public E set(int index, E value) {
        // return old value
        Item<E> temp = getItem(index);
        if (temp == null) {
            return null;
        }
        E oldValue = temp.getValue();
        temp.setValue(value);
        return oldValue;
    }

    /**
     * Remove/delete
     */

    public E remove(int index) {
        // returns deleted value
        if (index == 0) {
            return removeFirst();
        }

        Item<E> previous = getItem(index - 1);

        if (previous == null) {
            return null;
        }

        Item<E> item = previous.getNext();
        previous.setNext(item.getNext());
        item.setNext(null);

        if (item == last) {
            last = previous;
        }

        return item.getValue();
    }

    public E removeFirst() {
        if (first == null) {
            return null;
        }
        E value = first.getValue();
        first = first.getNext();
        return value;
    }

    public Item<E> removeLast() {

        Item<E> temp = first;
        Item<E> result = null;

        while (temp != null) {
            if (temp.next.next == null) {
                result = temp.next;

                temp.next = null;
                last = temp;
                break;
            }
            temp = temp.next;
        }
        return result;
    }

    public int remove(E value) {
        // returns deleted index
        int index = 0;
        Item<E> temp = first;
        while (temp != null) {
            if (temp.value == value) {
                remove(index);
                return index;
            }
            index++;
            temp = temp.next;
        }

        return -1;
    }

    public void clear() {
    }

    /**
     * Is methods
     */

    public boolean isEmpty() {
        return true;
    }

    public boolean isContains(E value) {
        return false;
    }

    /**
     * Other methods
     */

    public int size() {
        int size = 0;
        Item<E> temp = first;
        while (temp != null) {
            size++;
            temp = temp.getNext();
        }
        return size;
    }

    public Object[] toArray() {
        return null;
    }

    public void printAll() {
        Item<E> temp = first;
        while (temp != null) {
            System.out.println(temp.getValue());
            temp = temp.getNext();
        }
    }

    public void printFirstAndLast() {
        System.out.println("first = " + first.value);
        System.out.println("last = " + last.value);
    }

    public String toString() {
        // [1,2,3,4,5]
        return "";
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Item<E> temp = first;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public E next() {
                E value = temp.value;
                temp = temp.next;
                return value;
            }
        };
    }

    /**
     * Iterating
     */


    class Item<E> {
        private E value;
        private Item<E> next;

        public Item(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Item<E> getNext() {
            return next;
        }

        public void setNext(Item<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return value + "";
        }
    }

}

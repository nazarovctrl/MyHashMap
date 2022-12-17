import java.util.Arrays;
import java.util.Iterator;

public class MyHashSet<E> implements Iterable<E> {
    Item<E>[] array = new Item[16];
    public boolean add(E value) {
        int index;
        if (value == null) {
            index = 0;
            if (array[0] != null) {
                return false;
            }
        } else {
            index = Math.abs(value.hashCode() % array.length);
            if (index == 0) {
                index = 1;
            }
        }

        if (array[index] == null) {
            Item<E> item = new Item<>(new MySuperList<>());
            array[index] = item;
        }

        if (array[index].value.indexOf(value) != -1) {
            return false;
        }

        array[index].value.add(value);

        return true;
    }

    public void increaseSize() {
        //check
        int i = 0;
        for (Item<E> item : array) {
            if (item != null) {
                i++;
            }
        }

        if (array.length / 4 * 3 <= i) {
            Item<E>[] item = new Item[(int) (array.length * 1.6)];
            System.arraycopy(array, 0, item, 0, array.length);
            array = item;
        }
    }

    public boolean contains(E value) {
        for (int i = 1; i < array.length; i++) {
            Item<E> item = array[i];
            if (item != null && item.getValue().indexOf(value) != -1) {
                return true;
            }

        }

        return false;
    }

    public boolean remove(E value) {
        if (value == null) {
            if (array[0] == null) {
                return false;
            }

            array[0] = null;
            return true;
        }

        for (int i = 1; i < array.length; i++) {
            if (array[i] != null && array[i].value.remove(value) != -1) {
                if (array[i].value.size() == 0) {
                    array[i] = null;
                }
                return true;
            }

        }

        return false;
    }

    public Object[] toArray() {
        Object[] objects = new Object[0];
        for (int i = 0, index = 0; i < array.length; i++) {
            if (array[i] == null) {
                continue;
            }

            for (E value : array[i].value) {
                if (index == objects.length) {
                    Object[] a = new Object[objects.length + 1];
                    System.arraycopy(objects, 0, a, 0, objects.length);
                    objects = a;
                }
                objects[index++] = value;
            }

        }
        return objects;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    public boolean isEmpty() {
        for (Item<E> item : array) {
            if (item != null) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        array = new Item[16];
    }

    public int size() {

        int size = 0;
        for (Item<E> item : array) {
            if (item != null) {
                size++;
            }
        }
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = 0;


            int k = 0;

            @Override
            public boolean hasNext() {
                if (array[i] != null && k == array[i].value.size()) {
                    k = 0;
                    i++;
                }

                if (i == array.length) {
                    return false;
                }

                while (array[i] == null) {
                    i++;
                    if (i == array.length) {
                        return false;
                    }
                }

                return true;
            }

            @Override
            public E next() {

                return array[i].value.get(k++);
            }
        };
    }

    class Item<E> {
        private MySuperList<E> value;

        public Item(MySuperList<E> value) {
            this.value = value;
        }

        public MySuperList<E> getValue() {
            return value;
        }

        public void setValue(MySuperList<E> value) {
            this.value = value;
        }
    }


}

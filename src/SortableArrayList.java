import java.util.Comparator;

/**
 * A resizable array implementation that extends SimpleArrayList with sorting capabilities.
 * This class provides selection sort functionality using a provided comparator.
 *
 * @author rparnian
 * @param <T> the type of elements in this list
 */
public class SortableArrayList<T> extends SimpleArrayList<T> {

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public SortableArrayList() {
        super();
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param capacity the initial capacity of the list
     */
    public SortableArrayList(int capacity) {
        super(capacity);
    }

    /**
     * Sorts the list in ascending order according to the specified comparator.
     * The sort is performed in-place using selection sort algorithm.
     *
     * @param c the comparator to determine the order of the list.
     */
    public void sort(Comparator<? super T> c) {
        for (int i = size() - 1; i > 0; i--) {
            int maxIndex = 0;
            for (int j = 1; j <= i; j++) {
                if (c.compare(get(j), get(maxIndex)) > 0) {
                    maxIndex = j;
                }
            }

            T temp = get(i);
            set(i, get(maxIndex));
            set(maxIndex, temp);
        }
    }
}
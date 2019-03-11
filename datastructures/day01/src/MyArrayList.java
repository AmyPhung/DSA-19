public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(N) - calls constructor which is O(N)
    public MyArrayList() {
        this(10); // calls the other constructor
    }

    // TODO: Runtime: O(N) - needs to allocate space for N objects
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // TODO: Runtime: O(N), O(1)* - Resizing is 1 average and adding is N worst case
    public void add(Cow c) {
        // if array is full, double size.
        if (size == elems.length) {
            Cow[] newArr = new Cow[elems.length * 2];
            System.arraycopy(elems, 0, newArr, 0, size);
            elems = newArr;
        }

        elems[size] = c;
        size++;
    }

    // TODO: Runtime: O(1) - returns saved variable
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1) - returns saved object
    public Cow get(int index) {
        if (index >= size) {
            //index out of bounds exception
            throw new IndexOutOfBoundsException(index);
        } else {
            return elems[index];
        }
    }

    // TODO: Runtime: O(N)* - N worst case for removing and N average for resizing
    public Cow remove(int index) {
        Cow rm;
        rm = elems[index];

        // shift all elements after removed index back
        for (int i=index; i<elems.length-1; i++) {
          elems[i] = elems[i + 1];
        }

        size--;

        // if array is less than 25% full, reduce to half size - don't resize to length 1
        if (size < elems.length*0.25) {
            Cow[] newArr = new Cow[elems.length/2];
            if (elems.length/2 != 1) {
                System.arraycopy(elems, 0, newArr, 0, size);
                elems = newArr;
          }
        }
        return rm;
    }

    // TODO: Runtime: O(N)* - N average for resizing and N worst case for shifting elements
    public void add(int index, Cow c) {
        if (index > size) {
          //index out of bounds exception
          throw new IndexOutOfBoundsException(index);
        }

        // if array is full, double size.
        if (size == elems.length) {
            Cow[] newArr = new Cow[elems.length*2];
            System.arraycopy(elems, 0, newArr, 0, size);
            elems = newArr;
        }
        size++;

        // move all elements after insertion
        for (int i=size; i>index; i--)  {// fix syntax
          elems[i] = elems[i-1];
        }

        elems[index] = c;
    }
}

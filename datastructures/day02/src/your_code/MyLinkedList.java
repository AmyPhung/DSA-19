package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (size == 0) {
            head = new Node(c, null, null);
            tail = head;
        } else {
            tail.next = new Node(c, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        if (size == 0) {
            head = new Node(c, null, null);
            tail = head;
        } else {
            head.prev = new Node(c, null, head);
            head = head.prev;
        }
        size++;
    }

    public Chicken get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index);
        } else {
            Node curr_node = head;
            for (int i = 0; i < index; i++) {
                curr_node = curr_node.next;
            }
            return curr_node.val;
        }
    }

    public Chicken remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index);
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size-1) {
            return removeLast();
        } else {
            Node rm_node = head;
            for (int i = 0; i < index; i++) {
                rm_node = rm_node.next;
            }
            rm_node.prev.next = rm_node.next;
            rm_node.next.prev =  rm_node.prev;
            size--;

            return rm_node.val;
        }
    }

    public Chicken removeFirst() {
        Chicken rm_chicken;

        if (size == 0) {
            throw new IndexOutOfBoundsException(0);
        } else if (size == 1) {
            rm_chicken = head.val;
            head = null;
            tail = null;
            size--;
        } else {
            rm_chicken = head.val;
            head.next.prev = null;
            head = head.next;
            size--;
        }
        return rm_chicken;
    }

    public Chicken removeLast() {
        Chicken rm_chicken;

        if (size == 0) {
            throw new IndexOutOfBoundsException(0);
        } else if (size == 1) {
            rm_chicken = head.val;
            head = null;
            tail = null;
            size--;
        } else {
            rm_chicken = tail.val;
            tail.prev.next = null;
            tail = tail.prev;
            size--;
        }
        return rm_chicken;
    }
}
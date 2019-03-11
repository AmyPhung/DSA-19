import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    private static int[] subMin(int[] Arr, int idx1, int idx2) {
        int[] rtn = new int[2]; // Returns min value, index
        int minVal = Arr[idx1];
        int minidx = idx1;

        for (int i = idx1 + 1; i <= idx2; i++) {
            if (Arr[i] < minVal) {
                minVal = Arr[i];
                minidx = i;
            }
        }
        rtn[0] = minVal;
        rtn[1] = minidx;
        return rtn;
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        int num_rmv = 0;
        int prev_idx = 0;
        int curr_idx = 0;
        List<Integer> final_arr = new ArrayList<>();

        while (curr_idx <= (A.length - 1) - k) {
            int idx2 = 0;

            idx2 = prev_idx + (k - num_rmv);

            int[] min = subMin(A, prev_idx, idx2);
            num_rmv += min[1] - prev_idx;
            prev_idx = min[1] + 1;

            final_arr.add(min[0]);
            curr_idx++;
        }

        return final_arr;
    }


    public static boolean isPalindrome(Node n) {
        if (n == null) {
            return true;
        }

        int len = findLength(n);
        if (len < 2) {
            return true;
        }

        Node midNode = findMidNode(n);
        Node tailNode = findTailNode(n);
        System.out.print("Mid:");
        System.out.println(midNode.val);

        midNode.next = reverse(midNode.next);

        for (int i = 0; i < len / 2; i++) {
            if (midNode.next.val != n.val) return false;
            midNode.next = midNode.next.next;
            n = n.next;
        }
        return true;
    }

    private static int findLength(Node n) {
        Node currNode = n;
        int len = 0;
        while (currNode.next != null) {
            currNode = currNode.next;
            len++;
        }
        return len;
    }

    private static Node findMidNode(Node n) {
        int len = findLength(n);

        int i = 1;
        Node midNode = n;
        while (i < len / 2) {
            i++;
            midNode = midNode.next;

        }
        return midNode;
    }

    private static Node findTailNode(Node n) {
        int len = findLength(n);

        int i = 1;
        Node tailNode = n;
        while (i < len) {
            i++;
            tailNode = tailNode.next;

        }
        return tailNode;
    }

    private static Node reverse(Node n) {

        Node prev = null;
        Node current = n;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }




    public static String infixToPostfix(String s) {
        // Convert string to character array
        String[] arr = s.split(" ");

        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String c : arr) {
            if (c.equals("+") || c.equals("-") || c.equals("/") || c.equals("*")) {
                stack.push(c);
            }
            else if (c.equals(")")) {
                result.add(stack.pop());
            }
            else if (c.equals("(")) {
                // do nothing
            } else { // if char is a number
                result.add(c);
            }
        }
        return String.join(" ", result);
    }
}
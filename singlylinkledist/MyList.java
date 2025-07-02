/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singlylinkledist;

/**
 *
 * @author Yourn
 */
public class MyList {

    Node head, tail;

    public MyList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(int value) {
        Node node = new Node(value);

        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + ", ");
            current = current.next;
        }
        System.out.println("");
    }

    public void addLast(int value) {
        Node node = new Node(value);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    public void addIndex(int value, int index) {
        if (index < 0) {
            return;
        }
        if (index == 0) {
            addFirst(value);
        } else {
            Node current = head;
            int pos = 0;
            while (current != null) {
                if (index - 1 == pos) {
                    break;
                }
                current = current.next;
                pos++;
            }
            if (current == null) {
                return;
            } else {
                Node node = new Node(value);
                node.next = current.next;
                current.next = node;

            }
        }
    }

    public void delLast() {
        if (isEmpty()) {
            return;
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
        tail = current;
    }

    public int delLastReturn() {
        if (isEmpty()) {
            return -1;
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        int value = current.next.value;
        current.next = null;
        tail = current;
        return value;
    }

    public void delFirst() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
    }

    public void removeAt(int index) {
        if (index < 0 || isEmpty()) {
            return;
        }

        if (index == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return;
        }

        Node current = head;
        int pos = 0;

        while (current != null && pos < index - 1) {
            current = current.next;
            pos++;
        }

        if (current == null || current.next == null) {
            return;
        }

        if (current.next == tail) {
            tail = current;
        }

        current.next = current.next.next;
    }

    public int delFirstReturn() {
        if (isEmpty()) {
            return -1;
        } else {
            int value = head.value;
            head = head.next;
            return value;
        }
    }

    public void sortFromKToH(int k, int h) {
        if (k < 0 || h < k || isEmpty()) {
            return;
        }

        Node prevK = null;
        Node start = head;
        int pos = 0;
        while (start != null && pos < k) {
            prevK = start;
            start = start.next;
            pos++;
        }

        Node end = start;
        while (end != null && pos < h) {
            end = end.next;
            pos++;
        }

        if (start == null || end == null) {
            return;
        }

        Node afterH = end.next;
        end.next = null; 

        for (Node i = start; i != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                if (i.value > j.value) {
                    int tmp = i.value;
                    i.value = j.value;
                    j.value = tmp;
                }
            }
        }

        if (prevK != null) {
            prevK.next = start;
        } else {
            head = start;
        }

        Node last = start;
        while (last.next != null) {
            last = last.next;
        }
        last.next = afterH;

        if (afterH == null) {
            tail = last;
        }
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void reverseFromKToH(int k, int h) {
        if (k < 0 || h < k || isEmpty()) {
            return;
        }

        Node prevK = null;
        Node start = head;
        int pos = 0;

        while (start != null && pos < k) {
            prevK = start;
            start = start.next;
            pos++;
        }

        if (start == null) {
            return;
        }

        Node end = start;
        while (end != null && pos < h) {
            end = end.next;
            pos++;
        }

        if (end == null) {
            return;
        }

        Node afterH = end.next;
        end.next = null;

        Node prev = null;
        Node current = start;
        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        if (prevK != null) {
            prevK.next = prev;
        } else {
            head = prev;
        }

        start.next = afterH;

        if (afterH == null) {
            tail = start;
        }
    }

    public int getValueAt(int index) {
        if (index < 0) {
            return -1;
        }

        Node current = head;
        int pos = 0;

        while (current != null) {
            if (pos == index) {
                return current.value;
            }
            current = current.next;
            pos++;
        }

        return -1;
    }

    public int indexOf(int value) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.value == value) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    public int findMax() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int max = head.value;
        Node current = head.next;

        while (current != null) {
            if (current.value > max) {
                max = current.value;
            }
            current = current.next;
        }

        return max;
    }

    public int findMin() {
        if (isEmpty()) {
            return Integer.MAX_VALUE; 
        }
        int min = head.value;
        Node current = head.next;

        while (current != null) {
            if (current.value < min) {
                min = current.value;
            }
            current = current.next;
        }

        return min;
    }

    public void swap(int k, int h) {
        if (k < 0 || h < 0 || k == h || isEmpty()) {
            return;
        }

        Node nodeK = head;
        Node nodeH = head;
        int posK = 0;
        int posH = 0;

        while (nodeK != null && posK < k) {
            nodeK = nodeK.next;
            posK++;
        }

        while (nodeH != null && posH < h) {
            nodeH = nodeH.next;
            posH++;
        }

        if (nodeK == null || nodeH == null) {
            return;
        }

        int temp = nodeK.value;
        nodeK.value = nodeH.value;
        nodeH.value = temp;
    }



}

class testSinglyLinkedList {

    public static void main(String[] args) {
        MyList myList = new MyList();
        myList.addFirst(7);
        myList.addFirst(8);
        myList.addFirst(1231);
        myList.addFirst(6);
        myList.addFirst(76);
        myList.addFirst(23);
        myList.addFirst(57634);
        myList.addFirst(6456);
        myList.addLast(346);
        myList.addIndex(534, 5);
        myList.display();
//        myList.addIndex(43, 3);
//        myList.addIndex(4331, 6);

        System.out.println(myList.getValueAt(3));
        System.out.println(myList.indexOf(1231));
        myList.removeAt(3);
        myList.display();
        System.out.println(myList.findMax());
        System.out.println(myList.findMin());
        System.out.println(myList.indexOf(myList.findMax()));

        myList.removeAt(myList.indexOf(1231));
        myList.display();
        myList.swap(3, 7);
        myList.display();
        myList.sortFromKToH(0, 0);
    }
}

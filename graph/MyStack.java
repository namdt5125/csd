/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph;

/**
 *
 * @author Yourn
 */
import java.util.LinkedList;

public class MyStack {
    LinkedList<Object> head;
    public MyStack() {
     head = new LinkedList<>();
    }
    public void push(Object x) {
     head.addFirst(x);
    }
    public boolean isEmpty() {
     return head.isEmpty();
    }
    public Object pop() {
     if(isEmpty()) return null;
     return head.removeFirst();
    }
    public Object top() {
     if(isEmpty()) return null;
     return head.getFirst();
    }
}

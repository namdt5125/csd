/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

/**
 *
 * @author Yourn
 */

import java.util.LinkedList;


public class MyQueue {
    
    LinkedList<Object> head;

    public MyQueue() {head = new LinkedList<>();}
    
    public void enqueue(Object obj) {head.addLast(obj);}
    
    public boolean isEmpty() {return head.isEmpty();}
    
    public Object dequeue() 
     {if(isEmpty()) return null;
      else return head.removeFirst();
    }
    
    public Object front() 
    {if(isEmpty()) return null;
     else return head.getFirst();
    }
    
    public void clear() {head = null;}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg1.project;

/**
 *
 * @author my
 */
public class Queue <T> {
    private Node<T> head;
    
    Queue()
    {
        head = null;
    }
    
    Queue(T value, int key)
    {
        head = new Node(value,key);
    }
    
    public boolean isEmpty()
    {
        return head==null;
    }
    
    public void enqueue(T value, int key)
    {
        Node temp = head;
        
        if(temp==null)
            head = new Node(value,key);
        else
        {
            while(temp.getNext()!=null)
                temp = temp.getNext();
            temp.setNext(new Node(value,key));
        }
    }
    
    public T dequeue()
    {
        T temp;
        temp = head.getData();
        head = head.getNext();
        return temp;
    }
    
    public T front()
    {
        return head.getData();
    }
    
    public int size()
    {
        Node temp = head;
        int size = 0;
        
        while(temp!=null)
        {
            size++;
            temp=temp.getNext();
        }
        return size;
    }
    
    public void displayItems()
    {
        if(!isEmpty())
        {
            Node temp = head;
            while(temp!=null)
            {
                System.out.print(temp.getData() + " ");
                temp = temp.getNext();
            }
            System.out.println();
        }
    }
    
}

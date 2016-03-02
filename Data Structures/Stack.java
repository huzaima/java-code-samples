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
public class Stack <T> {
    
    private Node<T> head;
    
    Stack()
    {
        head = null;
    }
    
    Stack(T value, int key)
    {
        head = new Node(value,key);
    }
    
    public boolean isEmpty()
    {
        return head==null;
    }
    
    public void push(T value, int key)
    {
        Node temp = head;
        head = new Node(value,key);
        head.setNext(temp);
    }
    
    public T pop()
    {
        T temp;
        temp = head.getData();
        head = head.getNext();
        return temp;
    }
    
    public T top()
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

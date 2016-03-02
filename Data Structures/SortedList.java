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
public class SortedList<T> extends LinkedList<T> {
    
    public boolean insertAtHead(T value, int key)
    {
        return false;
    }
    
    public boolean insertAtTail(T value, int key)
    {
        return false;
    }
    
    public boolean insertAt(int key, T value, int position)
    {
        return false;
    }
    
    public boolean insertAfter(int key, T value, Node n)
    {
        return false;
    }
    
    public boolean insertBefore(int key, T value, Node n)
    {
        return false;
    }
    
    public boolean insert(int key, T value)
    {
        Node temp = head;
        if(head==null)
        {
            head = new Node(value,key);
            size++;
            return true;
        }
        else if(key<head.getKey())
        {
            temp = head;
            head = new Node(value,key);
            head.setNext(temp);
            size++;
            return true;
        }
        else
        {
            for(int c=1;c<=size();c++,temp=temp.getNext())
                if(temp.getKey()==key)
                    return false;
                
            temp = head;
            for(int c=1;c<size() && temp.getNext().getKey()<key;c++)
                temp = temp.getNext();
            Node temp2 = temp.getNext();
            Node temp3 = new Node(value,key);
            temp.setNext(temp3);
            temp3.setNext(temp2);
            size++;
            return true;
        }
    }
}

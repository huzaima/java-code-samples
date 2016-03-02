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
public class CircularLinkedList <T> extends LinkedList <T> {
    
    public boolean insertAtHead(T value, int key)
    {
        if(head==null)
        {
            head = new Node(value,key);
            head.setNext(head);
            size++;
            return true;
        }
        else
        {
            Node temp = head;
            for(int c=1;c<=size();c++)
            {
                if(temp.getKey()==key)
                    return false;
                temp=temp.getNext();
            }
            Node temp2 = head;
            for(int c=1;c<size();c++)
                temp2 = temp2.getNext();
            temp = head;
            head = new Node(value,key);
            head.setNext(temp);
            temp2.setNext(head);
            size++;
            return true;
        }
    }
    
    public boolean insertAtTail(T value, int key)
    {
        if(head==null)
        {
            head = new Node(value,key);
            head.setNext(head);
            size++;
            return true;
        }
        else
        {
            Node temp = head;
            for(int c=1;c<=size();c++)
            {
                if(temp.getKey()==key)
                    return false;
                temp=temp.getNext();
            }
            temp = head;
            for(int c=1;c<size();c++)
                temp = temp.getNext();
            temp.setNext(new Node(value,key));
            temp.getNext().setNext(head);
            size++;
            return true;
        }
    }
    
    public boolean deleteTail()
    {
        if(head!=null)
        {
            Node temp = head;
            for(int c=1;c<size()-1;c++)
                temp = temp.getNext();
            temp.setNext(head);
            size--;
            return true;
        }
        return false;
    }
    
    public boolean deleteHead()
    {
        if(head!=null)
        {
            Node temp = head;
            head = head.getNext();
            size--;
            for(int c=1;c<=size();c++)
                temp = temp.getNext();
            temp.setNext(head);
            return true;
        }
        return false;
    }
    
    public boolean deleteAfter(int key)
    {
        if(head!=null)
        {
            if(key==head.getKey() && head.getNext()!=null)
                return deleteWithKey(head.getNext().getKey());
            else
            {
                Node temp = head;
                for(int c=1;c<size()&&key!=temp.getKey();c++)
                    temp = temp.getNext();
                
                if(temp!=null && temp.getNext()!=null)
                    return deleteWithKey(temp.getNext().getKey());
            }
        }
        return false;
    }
    
    public CircularLinkedList subList(int from, int to)
    {
        if( (from>=1 && from<to) && (to>from && to<=size()) )
        {
            Node<T> temp = head, temp2, temp3;
            for(int c=1;c<from;c++)
                temp = temp.getNext();
            
            CircularLinkedList<T> newList = new CircularLinkedList<T>();
            newList.insertAtHead(temp.getData(), temp.getKey());
            
            for(int c=from;c<to;c++)
            {
                temp = temp.getNext();
                newList.insertAtTail(temp.getData(), temp.getKey());
            }
            return newList;
        }
        return null;
    }
    
}

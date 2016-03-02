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
public class DoublyLinkedList <T> {
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    private DNode<T> head;
    private int size;
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    DoublyLinkedList()                  //default constructor
    {
        head = null;
        size = 0;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    DoublyLinkedList(T data, int key)   //constructor ith arguments
    {                                   
        head = new DNode(data,key);
        size = 1;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void clear()                 //makes DoublyLinkedList empty
    {
        if(head!=null)
        {
            head = null;
            size = 0;
        }
    }   
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean isEmpty()            //check whether DoublyLinkedlist is empty or not
    {
        return head==null;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public int size()                   //returns size of DoublyLinkedList
    {
        return size;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public DNode getHead()
    {
        return head;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    private boolean keyExists(int key)
    {
        DNode temp = head;
        
        for(int c=1;c<=size();c++)
            {
                if(temp.getKey()==key)
                    return true;
                temp=temp.getNext();
            }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean insertAtHead(T value, int key)
    {
        if(head==null)
        {
            head = new DNode(value,key);
            size++;
            return true;
        }
        else
        {
            if(!keyExists(key))
            {
                DNode temp = head;
                head = new DNode(value,key);
                head.setNext(temp);
                temp.setPrevious(head);
                size++;
                return true;
            }
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean insertAtTail(T value, int key)
    {
        if(head==null)
        {
            head = new DNode(value,key);
            size++;
            return true;
        }
        else
        {
            if(!keyExists(key))
            {
                DNode temp = head;
                temp = head;
                for(int c=1;c<size();c++)
                    temp = temp.getNext();
                DNode newNode = new DNode(value,key);
                temp.setNext(newNode);
                newNode.setPrevious(temp);
                size++;
                return true;
            }
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean insertAt(int key, T value, int position)
    {
        if(position==1)
            return insertAtHead(value,key);
        else if(position==size()+1)
            return insertAtTail(value,key);
        else if(position>1 && position<=size())
        {
            if(!keyExists(key))
            {
                DNode temp = head;
                
                for(int c=1;c<position-1;c++)
                    temp=temp.getNext();
                
                DNode temp2 = temp.getNext();
                DNode newNode = new DNode(value,key);
                temp.setNext(newNode);
                newNode.setPrevious(temp);
                newNode.setNext(temp2);
                temp2.setPrevious(newNode);
                size++;
                return true;
            }
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean insertAfter(int key, T value, DNode n)
    {
        if(n!=null)
        {
            DNode temp = head;
            int c;

            for(c=1;c<size()&&temp!=n;c++)
                temp = temp.getNext();

            
            return insertAt(key,value,++c);    
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean insertBefore(int key, T value, DNode n)
    {
        if(n!=null)
        {
            DNode temp = head;
            int c;

            for(c=1;c<size()&&temp!=n;c++)
                temp = temp.getNext();

            return insertAt(key,value,c);
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean deleteHead()
    {
        if(head!=null)
        {
            head = head.getNext();
            head.setPrevious(null);
            size--;
            return true;
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean deleteTail()
    {
        if(head!=null)
        {
            DNode temp = head;
            for(int c=1;c<size()-1;c++)
                temp = temp.getNext();
            temp.setNext(null);
            size--;
            return true;
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean deleteWithKey(int key)
    {
        if(head!=null)
        {
            if(key==head.getKey())
                return deleteHead();
            else
            {
                DNode temp = head;
                for(int c=1;c<size()&&temp.getNext().getKey()!=key;c++)
                    temp = temp.getNext();
                
                if(temp.getNext()!=null && temp.getNext().getKey()==key)
                {
                    if(temp.getNext().getNext()==null)
                        return deleteTail();
                    temp.setNext(temp.getNext().getNext());
                    temp.getNext().setPrevious(temp);
                    size--;
                }
                return true;
            }
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean deleteAfter(int key)
    {
        if(head!=null)
        {
            if(key==head.getKey() && head.getNext()!=null)
                return deleteWithKey(head.getNext().getKey());
            else
            {
                DNode temp = head;
                for(int c=1;c<size()&&key!=temp.getKey();c++)
                    temp = temp.getNext();
                
                if(temp!=null && temp.getNext()!=null)
                    return deleteWithKey(temp.getNext().getKey());
            }
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean deleteBefore(int key)
    {
        if(head!=null)
        {
            DNode temp = head;
            if(temp.getKey()!=key)
            {
                for(int c=1;c<size()&&temp.getKey()!=key;c++)
                    temp = temp.getNext();
                
                if(temp.getKey()==key)
                    return deleteWithKey(temp.getPrevious().getKey());
            }
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean remove(int index)
    {
        if(index==1)
        {
            return deleteHead();
        }
        else if(index==size())
        {
            return deleteTail();
        }
        else if(index>1 && index<size())
        {
            DNode temp = head;
            
            for(int c=1;c<index;c++)
                temp = temp.getNext();
            
            return deleteWithKey(temp.getKey());
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public T getData(int position)
    {
        if(position==1)
        {
            return head.getData();
        }
        else if(position==size())
        {
            DNode<T> temp = head;
            for(int c=1;c<size();c++)
                temp = temp.getNext();
            return temp.getData();
        }
        else if(position>1 && position<size())
        {
            DNode<T> temp = head;
            
            for(int c=1;c<position;c++)
                temp = temp.getNext();
            return temp.getData();
        }
        return null;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean searchValue(T value)
    {
        DNode<T> temp = head;
        
        for(int c=1;c<size()&&temp.getData()!=value;c++)
            temp = temp.getNext();
        
        if(temp.getData()==value)
            return true;
        
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean searchKey(int key)
    {
        DNode<T> temp = head;
        
        for(int c=1;c<size()&&temp.getKey()!=key;c++)
            temp = temp.getNext();
        
        if(temp.getKey()==key)
            return true;
        
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public DNode SearchValue(T value)
    {
        DNode<T> temp = head;
        
        for(int c=1;c<size()&&temp.getData()!=value;c++)
            temp = temp.getNext();
        
        if(temp.getData()==value)
            return temp;
        
        return null;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public DNode SearchKey(int key)
    {
        DNode<T> temp = head;
        
        for(int c=1;c<size()&&temp.getKey()!=key;c++)
            temp = temp.getNext();
        
        if(temp.getKey()==key)
            return temp;
        
        return null;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void displayItems()
    {
        if(!isEmpty())
        {
            DNode temp = head;
            for(int c=1;c<=size();c++)
            {
                System.out.print(temp.getData() + " ");
                if(c!=size)
                    temp=temp.getNext();
            }
            System.out.println();
            for(int c=1;c<=size();c++)
            {
                System.out.print(temp.getData() + " ");
                temp=temp.getPrevious();
            }
            System.out.println("\n"+"-----------------------------------------");
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public DoublyLinkedList subList(int from, int to)
    {
        if( (from>=1 && from<=size) && (to>=1 && to<=size()) && (from<=size) )
        {
            DNode<T> temp = head, temp2, temp3;
            for(int c=1;c<from;c++)
                temp = temp.getNext();
            
            DoublyLinkedList<T> newList = new DoublyLinkedList<T>();
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
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
}

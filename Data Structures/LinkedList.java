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
public class LinkedList <T>{
    
    protected Node<T> head;
    protected int size;
    
    LinkedList()
    {
        head = null;
        size = 0;
    }
    
    LinkedList(T data, int key)
    {
        head = new Node(data,key);
        size = 1;
    }
    
    public boolean clear()
    {
        if(head!=null)
        {
            head = null;
            size = 0;
            return true;
        }
        return false;
    }   
    
    public boolean isEmpty()
    {
        return head==null;
    }
    
    public int size()
    {
        return size;
    }
    
    public Node getHead()
    {
        return head;
    }
    
    public boolean insertAtHead(T value, int key)
    {
        if(head==null)
        {
            head = new Node(value,key);
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
            head = new Node(value,key);
            head.setNext(temp);
            size++;
            return true;
        }
    }
    
    public boolean insertAtTail(T value, int key)
    {
        if(head==null)
        {
            head = new Node(value,key);
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
            size++;
            return true;
        }
    }
    
    public boolean insertAt(int key, T value, int position)
    {
        if(position==1)
            return insertAtHead(value,key);
        else if(position==size()+1)
            return insertAtTail(value,key);
        else if(position>1 && position<=size())
        {
            Node temp = head;
            for(int c=1;c<=size();c++)
            {
                if(temp.getKey()==key)
                    return false;
                temp=temp.getNext();
            }
            temp = head;
            for(int c=1;c<position-1;c++)
                temp=temp.getNext();
            Node temp2 = temp.getNext();
            Node newNode = new Node(value,key);
            temp.setNext(newNode);
            newNode.setNext(temp2);
            size++;
            return true;
        }
        return false;
    }
    
    public boolean insertAfter(int key, T value, Node n)
    {
        if(n!=null)
        {
            Node temp = head;
            int c;

            for(c=1;c<size()&&temp!=n;c++)
                temp = temp.getNext();

            insertAt(key,value,++c);    
            return true;
        }
        return false;
    }
    
    public boolean insertBefore(int key, T value, Node n)
    {
        if(n!=null)
        {
            Node temp = head;
            int c;

            for(c=1;c<size()&&temp!=n;c++)
                temp = temp.getNext();

            insertAt(key,value,c);    
            return true;
        }
        return false;
    }
    
    public boolean deleteHead()
    {
        if(head!=null)
        {
            head = head.getNext();
            size--;
            return true;
        }
        return false;
    }
    
    public boolean deleteTail()
    {
        if(head!=null)
        {
            Node temp = head;
            for(int c=1;c<size()-1;c++)
                temp = temp.getNext();
            temp.setNext(null);
            size--;
            return true;
        }
        return false;
    }
    
    public boolean deleteWithKey(int key)
    {
        if(head!=null)
        {
            if(key==head.getKey())
                return deleteHead();
            else
            {
                Node temp = head;
                for(int c=1;c<size()&&temp.getNext().getKey()!=key;c++)
                    temp = temp.getNext();
                
                if(temp.getNext()!=null && temp.getNext().getKey()==key)
                {
                    temp.setNext(temp.getNext().getNext());
                    size--;
                }
                return true;
            }
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
    
    public boolean deleteBefore(int key)
    {
        if(head!=null)
        {
            Node temp = head;
            if(temp.getKey()!=key)
            {
                for(int c=1;c<size()&&temp.getNext().getKey()!=key;c++)
                    temp = temp.getNext();
                if(temp.getNext().getKey()==key)
                    return deleteWithKey(temp.getKey());
            }
        }
        return false;
    }
    
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
            Node temp = head;
            
            for(int c=1;c<index;c++)
                temp = temp.getNext();
            
            return deleteWithKey(temp.getKey());
        }
        return false;
    }
    
    public T getData(int position)
    {
        if(position==1)
        {
            return head.getData();
        }
        else if(position==size())
        {
            Node<T> temp = head;
            for(int c=1;c<size();c++)
                temp = temp.getNext();
            return temp.getData();
        }
        else if(position>1 && position<size())
        {
            Node<T> temp = head;
            
            for(int c=1;c<position;c++)
                temp = temp.getNext();
            return temp.getData();
        }
        return null;
    }
    
    public boolean searchValue(T value)
    {
        Node<T> temp = head;
        
        for(int c=1;c<size()&&temp.getData()!=value;c++)
            temp = temp.getNext();
        
        if(temp.getData()==value)
            return true;
        else
            return false;
    }
    
    public boolean searchKey(int key)
    {
        Node<T> temp = head;
        
        for(int c=1;c<size()&&temp.getKey()!=key;c++)
            temp = temp.getNext();
        
        if(temp.getKey()==key)
            return true;
        else
            return false;
    }
    
    public Node SearchValue(T value)
    {
        Node<T> temp = head;
        
        for(int c=1;c<size()&&temp.getData()!=value;c++)
            temp = temp.getNext();
        
        if(temp.getData()==value)
            return temp;
        else
            return null;
    }
    
    public Node SearchKey(int key)
    {
        Node<T> temp = head;
        
        for(int c=1;c<size()&&temp.getKey()!=key;c++)
            temp = temp.getNext();
        
        if(temp.getKey()==key)
            return temp;
        else
            return null;
    }
    
    public void displayItems()
    {
        Node temp = head;
        for(int c=1;c<=size();c++)
        {
            System.out.print(temp.getData() + " ");
            temp=temp.getNext();
        }
        System.out.println();
    }
    
    public LinkedList subList(int from, int to)
    {
        if(from>=1 && to<=size())
        {
            Node<T> temp = head, temp2, temp3;
            for(int c=1;c<from;c++)
                temp = temp.getNext();
            
            LinkedList<T> newList = new LinkedList<T>();
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
    
    public void q6()
    {
        Node temp = head, temp2=head, temp3=head, temp4=head;
        
        for(int c=1;c<=size()/2;c++)
        {
            temp2=head;
            for(int d=1;d<size()-1;d++)
                temp2 = temp2.getNext();
                
            temp3 = temp2.getNext();
            temp2.setNext(null);
            temp4 = temp.getNext();
            temp.setNext(temp3);
            temp3.setNext(temp4);
            temp = temp4;
        }
    }
}

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
public class Node <T> {
    private T value;
    private int key;
    private Node next;
    
    Node()
    {
        next = null;
    }
    
    Node(T data, int ky)
    {
        value = data;
        key = ky;
        next = null;
    }
    
    public void setData(T data)
    {
        value = data;
    }
    
    public void setKey(int ky)
    {
        key = ky;
    }
    
    public void setNext(Node n)
    {
        next = n;
    }
    
    public T getData()
    {
        return value;
    }
    
    public int getKey()
    {
        return key;
    }
    
    public Node getNext()
    {
        return next;
    }
}

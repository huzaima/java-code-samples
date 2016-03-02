/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assignment.pkg1.project;

/**
 *
 * @author k132024
 */
public class BST<T> extends BinaryTree<T> {
    
    public BST() 
    {
        root = null;
    }
    
    public BST(T value, int key)
    {
        root = new DNode(value,key);
    }
    
    public DNode getRoot()
    {
        return root;
    }
    
    public void insert(T value, int key) 
    {
        if (root==null)
        {
            root = new DNode(value,key);
            return;
        }
        else 
        {
            DNode parent, temp = root;
            
            while(true)
            {
                parent = temp;
                if (key<temp.getKey()) 
                {
                    temp = temp.getPrevious();
                    
                    if (temp == null) 
                    {
                        parent.setPrevious(new DNode(value,key));
                        return;
                    }
                }
                else 
                {
                    temp = temp.getNext();
                    if (temp == null) 
                    {
                        parent.setNext(new DNode(value,key));
                        return;
                    }
                }
            }
        }
    }
    
    public void delete(int x)
    {
        DNode temp = Search(x);

        if(temp.getPrevious()==null && temp.getNext()==null)
        {
            DNode temp2 = parent(temp);
            if(temp==temp2.getPrevious())
                temp2.setPrevious(null);
            else
                temp2.setNext(null);
        }
        else if(temp.getPrevious()==null || temp.getNext()==null)
        {
            DNode temp2 = parent(temp);
            if(temp==temp2.getPrevious())
                if(temp.getPrevious()!=null)
                    temp2.setPrevious(temp.getPrevious());
                else
                    temp2.setPrevious(temp.getNext());
            else
                if(temp.getPrevious()!=null)
                    temp2.setNext(temp.getPrevious());
                else
                    temp2.setNext(temp.getNext());
        }
        else
        {
            DNode min = minimum(temp.getNext());

            if(min==parent(min).getPrevious())
                parent(min).setPrevious(null);
            else
                parent(min).setNext(null);

            min.setPrevious(temp.getPrevious());
            min.setNext(temp.getNext());

            if(temp!=root)
            {
                DNode temp2 = parent(temp);
                if(temp==temp2.getPrevious())
                    parent(temp).setPrevious(min);
                else
                    parent(temp).setNext(min);
            }
            else
            {
                root = min;
            }
        }
    }
    
    public boolean search(int key)
    {
        DNode temp = root;
        
        while (temp!=null)
        {
            if(key<temp.getKey())
                temp = temp.getPrevious();
            else if (key>temp.getKey())
                temp = temp.getNext();
            else
                return true;
        }
        return false;
    }
    
    public DNode Search(int key)
    {
        DNode temp = root;
        
        while (temp!=null)
        {
            if(key<temp.getKey())
                temp = temp.getPrevious();
            else if (key>temp.getKey())
                temp = temp.getNext();
            else
                return temp;
        }
        return null;
        
    }

    public T minimum()
    {
        DNode<T> temp = root;

        while(temp.getPrevious()!=null)
            temp=temp.getPrevious();
        return temp.getData();
    }
    
    public DNode minimum(DNode<T> temp)
    {
        while(temp.getPrevious()!=null)
            temp=temp.getPrevious();
        return temp;
    }

    public T maximum()
    {
        DNode<T> temp = root;

        while(temp.getNext()!=null)
            temp=temp.getNext();
        return temp.getData();
    }
    
    public T maximum(DNode<T> temp)
    {
        while(temp.getNext()!=null)
            temp=temp.getNext();
        return temp.getData();
    }
     
    public DNode parent(DNode temp)
    {
        if(temp!=root)
        {
            DNode temp2 = root, temp3=null;
            
            while (temp2!=null)
            {
                if (temp.getKey() < temp2.getKey())
                {
                    temp3=temp2;
                    temp2 = temp2.getPrevious();
                }
                else if (temp.getKey() > temp2.getKey())
                {
                    temp3=temp2;
                    temp2 = temp2.getNext();
                }
                else
                    return temp3;
            }
        }
        return null;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void merge(BST t2)
    {
        if(!t2.isEmpty())
        {
            DNode<T> temp;
            Queue<DNode> q = new Queue();
            int c = 0;
            q.enqueue(t2.root, c++);
            
            while(!q.isEmpty())
            {
                temp = q.dequeue();
                
                insert(temp.getData(),temp.getKey());
                
                if(temp.getPrevious()!=null)
                    q.enqueue(temp.getPrevious(), c++);
                if(temp.getNext()!=null)
                    q.enqueue(temp.getNext(), c++);
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public BST copy()
    {
        if(!isEmpty())
        {
            BST b = new BST();
            DNode<T> temp;
            Queue<DNode> q = new Queue();
            int c = 0;
            q.enqueue(root, c++);
            
            while(!q.isEmpty())
            {
                temp = q.dequeue();
                
                b.insert(temp.getData(),temp.getKey());
                
                if(temp.getPrevious()!=null)
                    q.enqueue(temp.getPrevious(), c++);
                if(temp.getNext()!=null)
                    q.enqueue(temp.getNext(), c++);
            }
            return b;
        }
        return null;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assignment.pkg1.project;
/**
 *
 * @author k132024
 */
public class AVL <T> extends BST <T> {
    
     public AVL() 
     {
         root = null;
     }
     public AVL(T data, int key)
     {
     root = new DNode(data,key);
     }

     public DNode getRoot()
     {
         return root;
     }

     public void insert(T value, int key) 
     {
         DNode newNode = new DNode(value,key);
         if (root == null)
         {
             root = newNode;
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
                         parent.setPrevious(newNode);
                         break;
                     }
                 }
                 else 
                 {
                     temp = temp.getNext();
                     if (temp == null) 
                     {
                         parent.setNext(newNode);
                         break;
                     }
                 }
             }
         }
         
         if(unbalancedNode(newNode)!=null)
             balanceTree(newNode);
     }

     
     private void balanceTree(DNode newNode)
     {
         DNode temp = unbalancedNode(newNode);
         while(temp!=null)
         {
             if(heightFactor(temp)==2)
             {
                 if(heightFactor(temp.getNext())==1)
                     rotateLeft(temp);
                 else if(heightFactor(temp.getNext())==-1)
                     doubleRightLeftRotation(temp);
             }
             else if(heightFactor(temp)==-2)
             {
                 if(heightFactor(temp.getPrevious())==-1)
                     rotateRight(temp);
                 else if(heightFactor(temp.getPrevious())==1)
                     doubleLeftRightRotation(temp);
             }
             temp = unbalancedNode(newNode);
         }
         
     }     
     
     private int heightFactor(DNode temp)
     {
         return height(temp.getNext()) - height(temp.getPrevious());
     }
     
     private DNode unbalancedNode(DNode newNode)
     {
         DNode temp = newNode;
         while(temp!=null)
         {
             int heightFactor = height(temp.getNext()) - height(temp.getPrevious());
             if(heightFactor==2 || heightFactor==-2)
                 return temp;
             temp = parent(temp);
         }
         
         return null;
     }
     
     private DNode rotateLeft(DNode temp)
     {
         if(temp==root)
         {
            DNode temp2 = temp.getNext();
            temp.setNext(temp2.getPrevious());
            temp2.setPrevious(temp);
            root = temp2;
            return temp2;
         }
         else
         {
            DNode temp2 = parent(temp);
            DNode temp3 = temp.getNext();
            if(temp2.getNext()==temp)
                temp2.setNext(temp3);
            else if(temp2.getPrevious()==temp)
                temp2.setPrevious(temp3);
            temp.setNext(temp3.getPrevious());
            temp3.setPrevious(temp);
            return temp3;
         }
     }
     
     private DNode rotateRight(DNode temp)
     {
         if(temp==root)
         {
            DNode temp2 = temp.getPrevious();
            temp.setPrevious(temp2.getNext());
            temp2.setNext(temp);
            root = temp2;
            return temp2;
         }
         else
         {
            DNode temp2 = parent(temp);
            DNode temp3 = temp.getPrevious();
            if(temp2.getPrevious()==temp)
                temp2.setPrevious(temp3);
            else if(temp2.getNext()==temp)
                temp2.setNext(temp3);
            temp.setPrevious(temp3.getNext());
            temp3.setNext(temp);
            return temp3;
         }
     }
     
     private void doubleRightLeftRotation(DNode temp)
     {
         temp.setNext(rotateRight(temp.getNext()));
         rotateLeft(temp);
     }
     
     private void doubleLeftRightRotation(DNode temp)
     {
         temp.setPrevious(rotateLeft(temp.getPrevious()));
         rotateRight(temp);
     }
     
    public DNode parent(DNode temp)
    {
        if(temp!=root)
        {
        DNode temp2 = root, temp3=null;
         boolean found = false;
         while (temp2!=null && !found)
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
                 found = true;
         }
         return temp3;
        }
        return null;
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
            balanceTree(temp2);
        }
        else if(temp.getPrevious()==null || temp.getNext()==null)
        {
            DNode temp2 = parent(temp);
            if(temp==temp2.getPrevious())
            {
                if(temp.getPrevious()!=null)
                    temp2.setPrevious(temp.getPrevious());
                else
                    temp2.setPrevious(temp.getNext());
                balanceTree(temp2.getPrevious());
            }
            else
            {
                if(temp.getPrevious()!=null)
                    temp2.setNext(temp.getPrevious());
                else
                    temp2.setNext(temp.getNext());
                balanceTree(temp2.getNext());
            }
        }
        else
        {
            DNode min = minimum(temp.getNext());
            DNode parentOfMin = parent(min);    //saved for case of root deletion

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
                balanceTree(min);
            }
            else
            {
                root = min;
                balanceTree(min);
            }
        }
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
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void merge(AVL t2)
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
    public AVL copy()
    {
        if(!isEmpty())
        {
            AVL b = new AVL();
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
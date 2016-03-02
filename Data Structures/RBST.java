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
public class RBST<T> {
    
    private RBSTNode<T> root;
    
    RBST()
    {
        root = null;
    }
    
    RBST(T data, int key)
    {
        root = new RBSTNode<T>(data,key);
    }
    
    public RBSTNode getRoot()
    {
        return root;
    }
    
    public boolean isEmpty()
    {
        return root==null;
    }
    
    public void insert(T value, int key) 
    {
        RBSTNode newNode = new RBSTNode(value,key);

        if (root == null)
        {
            root = newNode;
        }
        
        else 
        {
            RBSTNode parent, temp = root;

            while(true)
            {
                parent = temp;
                if (key<temp.getKey()) 
                {
                    temp = temp.getLeft();
                    if (temp == null) 
                    {
                        parent.setLeft(newNode);
                        break;
                    }
                }
                else 
                {
                    temp = temp.getRight();
                    if (temp == null) 
                    {
                        parent.setRight(newNode);
                        break;
                    }
                }
            }
        }

        heapify(newNode);
    }
    
    public void delete(int key)
    {
        RBSTNode temp = Search(key);
        
        if(temp.getLeft()==null && temp.getRight()==null)
        {
            if(temp==parent(temp).getLeft())
                parent(temp).setLeft(null);
            else
                parent(temp).setRight(null);
        }
        else if(temp.getLeft()==null || temp.getRight()==null)
        {
            RBSTNode child;
            if(temp.getLeft()!=null)
                child = temp.getLeft();
            else
                child = temp.getRight();
            
            if(temp==parent(temp).getLeft())
                parent(temp).setLeft(child);
            else
                parent(temp).setRight(child);
        }
        else
        {
            while(temp.getLeft()!=null && temp.getRight()!=null)
            {
                if(temp.getLeft().getPriority()<temp.getRight().getPriority())
                    rotateRight(temp);
                else
                    rotateLeft(temp);
            }
            if(temp==parent(temp).getLeft())
                parent(temp).setLeft(null);
            else
                parent(temp).setRight(null);
        }
    }
    
    public RBSTNode parent(RBSTNode temp)
    {
        if(temp!=root)
        {
            RBSTNode temp2 = root, temp3=null;
            
            while (temp2!=null)
            {
                if (temp.getKey() < temp2.getKey())
                {
                    temp3=temp2;
                    temp2 = temp2.getLeft();
                }
                else if (temp.getKey() > temp2.getKey())
                {
                    temp3=temp2;
                    temp2 = temp2.getRight();
                }
                else
                    return temp3;
            }
        }
        return null;
    }
    
    private void heapify(RBSTNode current)
    {
        while(current!=root && current.getPriority()<parent(current).getPriority())
        {
            if(current==parent(current).getLeft())
                rotateRight(parent(current));
            else if(current==parent(current).getRight())
                rotateLeft(parent(current));
        }
    }
    
    private void rotateLeft(RBSTNode temp)
    {
        if(temp==root)
        {
           RBSTNode temp2 = temp.getRight();
           temp.setRight(temp2.getLeft());
           temp2.setLeft(temp);
           root = temp2;
        }
        else
        {
           RBSTNode temp2 = parent(temp);
           RBSTNode temp3 = temp.getRight();
           if(temp2.getRight()==temp)
               temp2.setRight(temp3);
           else if(temp2.getLeft()==temp)
               temp2.setLeft(temp3);
           temp.setRight(temp3.getLeft());
           temp3.setLeft(temp);
        }
    }

    private void rotateRight(RBSTNode temp)
    {
        if(temp==root)
        {
           RBSTNode temp2 = temp.getLeft();
           temp.setLeft(temp2.getRight());
           temp2.setRight(temp);
           root = temp2;
        }
        else
        {
           RBSTNode temp2 = parent(temp);
           RBSTNode temp3 = temp.getLeft();
           if(temp2.getLeft()==temp)
               temp2.setLeft(temp3);
           else if(temp2.getRight()==temp)
               temp2.setRight(temp3);
           temp.setLeft(temp3.getRight());
           temp3.setRight(temp);
        }
    }
    
    public void inorder(RBSTNode n)
    {
        if (n != null)
        {
            inorder(n.getLeft());
            System.out.print(n.getData() + " ");
            inorder(n.getRight());
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void preorder(RBSTNode n)
    {
        if (n != null)
        {
            System.out.print(n.getData() + " ");
            preorder(n.getLeft());
            preorder(n.getRight());
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void postorder(RBSTNode n)
    {
        if (n != null)
        {
            postorder(n.getLeft());
            postorder(n.getRight());
            System.out.print(n.getData() + " ");
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void levelOrder()
    {
        RBSTNode temp = root;
        Queue <RBSTNode> s = new Queue();
        Queue <RBSTNode> q = new Queue();
        int c = 0;
        
        q.enqueue(temp, ++c);
         
        while(!q.isEmpty())
        {
            temp = q.dequeue();
            s.enqueue(temp, ++c);
            
            if(temp.getLeft()!=null)
                q.enqueue(temp.getLeft(),++c);
            if(temp.getRight()!=null)
                q.enqueue(temp.getRight(),++c);
        }
         
        while(!s.isEmpty())
        {
            temp = s.dequeue();
            System.out.print(temp.getData() + "-" + temp.getPriority() + " ");
        }
        System.out.println("");
     }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public int totalNodes()
    {
        return totalNodes(root);
    }
    
    public int totalNodes(RBSTNode x) 
    {
        if (x == null) 
            return 0;
        else if(x.getLeft()==null && x.getRight()==null)
            return 1;
        else
            return 1 + totalNodes(x.getLeft()) + totalNodes(x.getRight());
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public int height()
    {
        return height(root);
    }
    
    public int height(RBSTNode x)
    {
        if (x == null) 
            return -1;
        return 1 + Math.max( height(x.getLeft()), height(x.getRight()) );
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean search(int key)
    {
        RBSTNode temp = root;
        
        while (temp!=null)
        {
            if(key<temp.getKey())
                temp = temp.getLeft();
            else if (key>temp.getKey())
                temp = temp.getRight();
            else
                return true;
        }
        return false;
    }
    
    public RBSTNode Search(int key)
    {
        RBSTNode temp = root;
        
        while (temp!=null)
        {
            if(key<temp.getKey())
                temp = temp.getLeft();
            else if (key>temp.getKey())
                temp = temp.getRight();
            else
                return temp;
        }
        return null;
        
    }
    
    public RBSTNode minimum()
    {
        RBSTNode<T> temp = root;

        while(temp.getLeft()!=null)
            temp=temp.getLeft();
        return temp;
    }
    
    public RBSTNode minimum(RBSTNode<T> temp)
    {
        while(temp.getLeft()!=null)
            temp=temp.getLeft();
        return temp;
    }

    public RBSTNode maximum()
    {
        RBSTNode<T> temp = root;

        while(temp.getRight()!=null)
            temp=temp.getRight();
        return temp;
    }
    
    public RBSTNode maximum(RBSTNode<T> temp)
    {
        while(temp.getRight()!=null)
            temp=temp.getRight();
        return temp;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void merge(RBST t2)
    {
        if(!t2.isEmpty())
        {
            RBSTNode<T> temp;
            Queue<RBSTNode> q = new Queue();
            int c = 0;
            q.enqueue(t2.root, c++);
            
            while(!q.isEmpty())
            {
                temp = q.dequeue();
                
                insert(temp.getData(),temp.getKey());
                
                if(temp.getLeft()!=null)
                    q.enqueue(temp.getLeft(), c++);
                if(temp.getRight()!=null)
                    q.enqueue(temp.getRight(), c++);
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public RBST copy()
    {
        if(!isEmpty())
        {
            RBST b = new RBST();
            RBSTNode<T> temp;
            Queue<RBSTNode> q = new Queue();
            int c = 0;
            q.enqueue(root, c++);
            
            while(!q.isEmpty())
            {
                temp = q.dequeue();
                
                b.insert(temp.getData(),temp.getKey());
                
                if(temp.getLeft()!=null)
                    q.enqueue(temp.getLeft(), c++);
                if(temp.getRight()!=null)
                    q.enqueue(temp.getRight(), c++);
            }
            return b;
        }
        return null;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
}

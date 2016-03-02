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
public class BinaryTree <T> {
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    protected DNode<T> root;
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    BinaryTree()
    {
        root = null;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    BinaryTree(T value, int key)
    {
        root = new DNode(value,key);
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public int totalNodes()
    {
        return totalNodes(root);
    }
    
    private int totalNodes(DNode x) 
    {
        if (x == null) 
            return 0;
        else if(x.getPrevious()==null && x.getNext()==null)
            return 1;
        else
            return 1 + totalNodes(x.getPrevious()) + totalNodes(x.getNext());
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public int height()
    {
        return height(root);
    }
    
    public int height(DNode x)
    {
        if (x == null) 
            return -1;
        return 1 + Math.max( height(x.getPrevious()), height(x.getNext()) );
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean isEmpty()
    {
        return root==null;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void inorder(DNode n)
    {
        if (n != null)
        {
            inorder(n.getPrevious());
            System.out.print(n.getData() + " ");
            inorder(n.getNext());
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void preorder(DNode n)
    {
        if (n != null)
        {
            System.out.print(n.getData() + " ");
            preorder(n.getPrevious());
            preorder(n.getNext());
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void postorder(DNode n)
    {
        if (n != null)
        {
            postorder(n.getPrevious());
            postorder(n.getNext());
            System.out.print(n.getData() + " ");
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void levelOrder()
    {
        if(!isEmpty())
        {
            DNode temp = root;
            Queue <DNode> s = new Queue<DNode>();
            Queue <DNode> q = new Queue<DNode>();
            int c = 0;

            q.enqueue(temp, ++c);

            while(!q.isEmpty())
            {
                temp = q.dequeue();
                s.enqueue(temp, ++c);

                if(temp.getPrevious()!=null)
                    q.enqueue(temp.getPrevious(),++c);
                if(temp.getNext()!=null)
                    q.enqueue(temp.getNext(),++c);
            }

            while(!s.isEmpty())
            {
                temp = s.dequeue();
                System.out.print(temp.getData() + " ");
            }
            System.out.println("");
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void insert(T value, int key)
    {
        if(root==null)
            root = new DNode(value,key);
        
        else
        {
            DNode temp = root;
            Queue <DNode> q = new Queue<DNode>();
            Queue <DNode> s = new Queue<DNode>();
            int c = 0, size = 0;

            q.enqueue(temp, ++c);

            while(!q.isEmpty())
            {
                temp = q.dequeue();
                if(temp.getKey()==key)
                    return;
                s.enqueue(temp, ++c);

                if(temp.getPrevious()!=null)
                    q.enqueue(temp.getPrevious(),++c);
                if(temp.getNext()!=null)
                    q.enqueue(temp.getNext(),++c);
            }

            while(!s.isEmpty())
            {
                temp = s.dequeue();

                if(temp.getPrevious()==null)
                {
                    temp.setPrevious(new DNode(value,key));
                    return;
                }
                else if(temp.getNext()==null)
                {
                    temp.setNext(new DNode(value,key));
                    return;
                }
            }
        }
     }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void delete()
    {
        if(!isEmpty())
        {
            if(root.getPrevious()==null && root.getNext()==null)
                root = null;

            else
            {
                DNode temp, temp2 = root;
                int c = 0;
                Queue<DNode> q = new Queue();
                q.enqueue(root, c++);

                while(!q.isEmpty())
                {
                    temp = q.dequeue();

                    if(temp.getPrevious()!=null)
                        q.enqueue(temp.getPrevious(), c++);
                    else
                    {
                        temp2.setNext(null);
                        return;
                    }
                    if(temp.getNext()!=null)
                        q.enqueue(temp.getNext(), c++);
                    else
                    {
                        temp.setPrevious(null);
                        return;
                    }

                    temp2 = temp;
                }
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void merge(BinaryTree t2)
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
    public BinaryTree copy()
    {
        if(!isEmpty())
        {
            BinaryTree b = new BinaryTree();
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
    public int min()
    {
        DNode<T> temp = root;
        Queue <DNode> s = new Queue<DNode>();
        Queue <DNode> q = new Queue<DNode>();
        int c = 0;
        int min = temp.getKey();
        
        q.enqueue(temp, ++c);
         
        while(!q.isEmpty())
        {
            temp = q.dequeue();
            if(min>temp.getKey())
                min = temp.getKey();
                
            
            if(temp.getPrevious()!=null)
                q.enqueue(temp.getPrevious(),++c);
            if(temp.getNext()!=null)
                q.enqueue(temp.getNext(),++c);
        }
        return min;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public int max()
    {
        DNode<T> temp = root;
        Queue <DNode> s = new Queue<DNode>();
        Queue <DNode> q = new Queue<DNode>();
        int c = 0;
        int max = temp.getKey();
        
        q.enqueue(temp, ++c);
         
        while(!q.isEmpty())
        {
            temp = q.dequeue();
            if(max<temp.getKey())
                max = temp.getKey();
                
            
            if(temp.getPrevious()!=null)
                q.enqueue(temp.getPrevious(),++c);
            if(temp.getNext()!=null)
                q.enqueue(temp.getNext(),++c);
        }
        return max;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean search(int key)
    {
        DNode<T> temp = root;
        Queue <DNode> s = new Queue<DNode>();
        Queue <DNode> q = new Queue<DNode>();
        int c = 0;
        
        q.enqueue(temp, ++c);
         
        while(!q.isEmpty())
        {
            temp = q.dequeue();
            if(key==temp.getKey())
                return true;
                
            
            if(temp.getPrevious()!=null)
                q.enqueue(temp.getPrevious(),++c);
            if(temp.getNext()!=null)
                q.enqueue(temp.getNext(),++c);
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public DNode Search(int key)
    {
        DNode<T> temp = root;
        Queue <DNode> s = new Queue<DNode>();
        Queue <DNode> q = new Queue<DNode>();
        int c = 0;
        
        q.enqueue(temp, ++c);
         
        while(!q.isEmpty())
        {
            temp = q.dequeue();
            if(key==temp.getKey())
                return temp;
                
            
            if(temp.getPrevious()!=null)
                q.enqueue(temp.getPrevious(),++c);
            if(temp.getNext()!=null)
                q.enqueue(temp.getNext(),++c);
        }
        return null;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean isLeaf(int key)
    {
        DNode temp = Search(key);
        if(temp!=null && temp.getPrevious()==null && temp.getNext()==null)
            return true;
        return false;
    }
}

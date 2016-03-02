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
public class RedBlackTree<T> {
    
    private RBNode<T> root;
    RedBlackTree()
    {
        root = null;
    }
    
    RedBlackTree(T data, int key)
    {
        root = new RBNode<T>(data,key);
        root.setColor(color.black);
    }
    
    public RBNode getRoot()
    {
        return root;
    }
    
    public boolean isEmpty()
    {
        return root==null;
    }
    
    public void insert(T value, int key) 
    {
        RBNode newNode = new RBNode(value,key);
        newNode.setColor(color.red);

        if (root == null)
        {
            root = newNode;
            root.setColor(color.black);
        }
        else 
        {
            RBNode parent, temp = root;

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

        balanceTree(newNode);
    }
    
    public void balanceTree(RBNode current)
    {
        RBNode uncle;
        current.setColor(color.red);
        
        while(current!=root && (isRed(current) && isRed(parent(current))))
        {
            if(parent(current)==parent(parent(current)).getLeft())
                uncle = parent(parent(current)).getRight();
            else
                uncle = parent(parent(current)).getLeft();

            if(isRed(uncle))
                case1(current,uncle);
            else
                case2(current);
        }
        root.setColor(color.black);
    }
    
    private void case1(RBNode current, RBNode uncle)
    {
        parent(current).setColor(color.black);
        uncle.setColor(color.black);
        parent(parent(current)).setColor(color.red);
        current = parent(parent(current));
    }
    
    private void case2(RBNode current)
    {
        if(current==parent(current).getRight() &&
           parent(current)==parent(parent(current)).getLeft())
        {
            current = parent(current);
            rotateLeft(current);
        }
        else if(current==parent(current).getLeft() &&
           parent(current)==parent(parent(current)).getRight())
        {
            current = parent(current);
            rotateRight(current);
        }
        case3(current);
    }
    
    private void case3(RBNode current)
    {
        color c = parent(current).getColor();
        parent(current).setColor(parent(parent(current)).getColor());
        parent(parent(current)).setColor(c);
        
        if(current==parent(current).getLeft())
        {
            rotateRight(parent(parent(current)));
        }
        else
        {
            rotateLeft(parent(parent(current)));
        }
    }
    
    private boolean isRed(RBNode temp)
    {
        if(temp==null)
            return false;
        else
        {
            return temp.getColor()==color.red;
        }
    }
    
    private boolean isBlack(RBNode temp)
    {
        if(temp==null)
            return true;
        else
        {
            return temp.getColor()==color.black;
        }
    }
    
    private void rotateLeft(RBNode temp)
    {
        if(temp==root)
        {
           RBNode temp2 = temp.getRight();
           temp.setRight(temp2.getLeft());
           temp2.setLeft(temp);
           root = temp2;
        }
        else
        {
           RBNode temp2 = parent(temp);
           RBNode temp3 = temp.getRight();
           if(temp2.getRight()==temp)
               temp2.setRight(temp3);
           else if(temp2.getLeft()==temp)
               temp2.setLeft(temp3);
           temp.setRight(temp3.getLeft());
           temp3.setLeft(temp);
        }
    }

    private void rotateRight(RBNode temp)
    {
        if(temp==root)
        {
           RBNode temp2 = temp.getLeft();
           temp.setLeft(temp2.getRight());
           temp2.setRight(temp);
           root = temp2;
        }
        else
        {
           RBNode temp2 = parent(temp);
           RBNode temp3 = temp.getLeft();
           if(temp2.getLeft()==temp)
               temp2.setLeft(temp3);
           else if(temp2.getRight()==temp)
               temp2.setRight(temp3);
           temp.setLeft(temp3.getRight());
           temp3.setRight(temp);
        }
    }
    
    public RBNode parent(RBNode temp)
    {
        if(temp!=root)
        {
            RBNode temp2 = root, temp3=null;
            
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
    
    public void inorder(RBNode n)
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
    public void preorder(RBNode n)
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
    public void postorder(RBNode n)
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
        RBNode temp = root;
        Queue <RBNode> s = new Queue();
        Queue <RBNode> q = new Queue();
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
            char a;
            if(temp.getColor()==color.black)
                a = 'b';
            else
                a = 'r';
            System.out.print(temp.getData() + "" + a + " ");
        }
        System.out.println("");
     }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public int totalNodes()
    {
        return totalNodes(root);
    }
    
    public int totalNodes(RBNode x) 
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
    
    public int height(RBNode x)
    {
        if (x == null) 
            return -1;
        return 1 + Math.max( height(x.getLeft()), height(x.getRight()) );
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public boolean search(int key)
    {
        RBNode temp = root;
        
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
    
    public RBNode Search(int key)
    {
        RBNode temp = root;
        
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
    
    public RBNode minimum()
    {
        RBNode<T> temp = root;

        while(temp.getLeft()!=null)
            temp=temp.getLeft();
        return temp;
    }
    
    public RBNode minimum(RBNode<T> temp)
    {
        while(temp.getLeft()!=null)
            temp=temp.getLeft();
        return temp;
    }

    public RBNode maximum()
    {
        RBNode<T> temp = root;

        while(temp.getRight()!=null)
            temp=temp.getRight();
        return temp;
    }
    
    public RBNode maximum(RBNode<T> temp)
    {
        while(temp.getRight()!=null)
            temp=temp.getRight();
        return temp;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void merge(RedBlackTree t2)
    {
        if(!t2.isEmpty())
        {
            RBNode<T> temp;
            Queue<RBNode> q = new Queue();
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
    public RedBlackTree copy()
    {
        if(!isEmpty())
        {
            RedBlackTree b = new RedBlackTree();
            RBNode<T> temp;
            Queue<RBNode> q = new Queue();
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

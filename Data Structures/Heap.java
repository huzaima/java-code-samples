/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heaaaaapppppp;

/**
 *
 * @author my
 */
public class Heap {
    private int[] a;
    private int size;
    
    Heap()
    {
        a = new int[20];
        size=0;
    }
    
    Heap(int heapSize)
    {
        a = new int[heapSize];
        size = 0;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public void insert(int x)
    {
        a[size++] = x;
        int temp = 0;
        int positionChild = size-1;
        int positionParent = (int)(Math.ceil(positionChild/2.0)-1);
        
        while(positionChild>0 && a[positionChild]<a[positionParent])
        {
            temp = a[positionChild];
            a[positionChild] = a[positionParent];
            a[positionParent] = temp;
            positionChild = (int)(Math.ceil(positionChild/2.0)-1);
            positionParent = (int)(Math.ceil(positionChild/2.0)-1);
        }
    }
    
    public int delete()
    {
        int returnValue = a[0];
        a[0] = a[size-1];
        size--;
        int temp;
        int positionParent = 0;
        int leftChild, rightChild;
        
        while(2*positionParent+1<size || 2*positionParent+2<size)
        {
            rightChild = a[positionParent] - a[2*positionParent+2];
            leftChild = a[positionParent] - a[2*positionParent+1];

            if(leftChild>rightChild)// && a[positionParent]>a[2*positionParent+1])
            {
                temp = a[positionParent];
                a[positionParent] = a[2*positionParent+1];
                a[2*positionParent+1] = temp;
                positionParent = 2*positionParent+1;
            }
            else// if(a[positionParent]>a[2*positionParent+2])
            {
                temp = a[positionParent];
                a[positionParent] = a[2*positionParent+2];
                a[2*positionParent+2] = temp;
                positionParent = 2*positionParent+2;
            }
        }
        return returnValue;
    }
    
    public void print()
    {
        for(int c=0;c<size;c++)
            System.out.print(a[c] + " ");
        System.out.println();
    }
}

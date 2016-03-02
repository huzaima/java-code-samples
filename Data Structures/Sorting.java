/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author my
 */
public class Sorting {
    
    public static void swap(int[] a, int p, int q)
    {
        int temp = a[p];
        a[p] = a[q];
        a[q] = temp;
    }
    
    public static int partition(int[] a, int p, int q)
    {
        for(int c=0;c<a.length;c++)
            System.out.print(a[c] + " ");
        System.out.println("with start= " + p + " and end= " + q);
        
        int pivot = a[q];
        int j, i = q;
        
        for(j=q-1;j>=p;j--)
        {
            if(pivot<a[j])
            {
                i--;
                swap(a, i, j);
            }
        }
        swap(a, i, q);
        return i;
    }
    
    public static void quickSort(int[] a, int p, int q)
    {
        if(p<q)
        {
            int r = partition(a,p,q);
            
            quickSort(a,p,r-1);
            quickSort(a,r+1,q);
        }
    }
    
    public static void merge(int[] a, int p, int q)
    {
        int[] temp = new int[q+1];
        int mid = (p+q)/2;
        int i = p, j = mid+1, k = 0;
        
        while(i<=mid && j<=q)
        {
            if(a[i]<a[j])
                temp[k++] = a[i++];
            else
                temp[k++] = a[j++];
        }
        
        while(i<=mid)
            temp[k++] = a[i++];
        while(j<=q)
            temp[k++] = a[j++];
        
        k--;
        System.out.println(k);
        while(k>=0)
        {
            a[k] = temp[k];
            System.out.print(a[k] + " ");k--;
        }
        System.out.println("");
        
        for(int c=0;c<a.length;c++)
            System.out.print(a[c] + " ");
        System.out.println("\n-------------------------------------------------");
        
    }
    
    public static void mergeSort(int[] a, int p, int q)
    {
        if(p<q)
        {
            int mid = (p+q)/2;
            mergeSort(a,p,mid);
            mergeSort(a,mid+1,q);
            merge(a,p,q);
        }
    }
    
    public static int max(int[] a)
    {
        int  max = a[0];
        for(int c=1;c<a.length;c++)
            if(a[c]>max)
                max = a[c];
        return max;
    }
    
    public static int length(int a)
    {
        int length = 0;
        
        while(a>0)
        {
            a /= 10;
            length++;
        }
        return length;
    }
    
    public static void radixSort(int[] a)
    {
        int mod = 10;
        int div = 1;
        Queue[] q = new Queue[10];
        for(int c=0;c<10;c++)
            q[c] = new LinkedList<Integer>();
        
        for(int c=1;c<=length(max(a));c++)
        {
            for(int d=0;d<a.length;d++)
            {
                int temp = (a[d]%mod)/div;
                q[temp].add(a[d]);
            }
            for(int d=0, e=0;d<a.length;d++)
            {
                while(!q[d].isEmpty())
                    a[e++] = (int) q[d].remove();
            }
            
            mod *= 10;
            div *= 10;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package q2.arraysorting;

/**
 *
 * @author my
 */
public class ArraySorting {
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void bubbleSort(int[] a)
    {
        int c,d,temp,n=a.length;
        
        for(c=0;c<n;c++)
        {
            for(d=1;d<n;d++)
                if(a[d]<a[d-1])
                {
                    temp=a[d];
                    a[d]=a[d-1];
                    a[d-1]=temp;
                }
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public void bubbleSortWithFlag(int[] a)
    {
        int c,d,temp,n=a.length;
        boolean check=true;
        
        for(c=0;c<n&&check;c++)
        {
            check=false;
            for(d=1;d<n;d++)
                if(a[d]<a[d-1])
                {
                    temp=a[d];
                    a[d]=a[d-1];
                    a[d-1]=temp;
                    check=true;
                }
        }
    }
    
    public void bubbleSortCocktailVersion(int[] a)
    {
        int c,d,temp,n=100000;
        
        for(c=1;c<=n/2;c++)
        {
            for(d=c;d<=n-c;d++)
            {
                if(a[d]<a[d-1])
                {
                    temp = a[d];
                    a[d] = a[d-1];
                    a[d-1] = temp;
                }
            }
            
            for(int e=n-c;e>=c;e--)
            {
                if(a[e]<a[e-1])
                {
                    temp = a[e];
                    a[e] = a[e-1];
                    a[e-1] = temp;
                }    
            }
        }    
    }
    
    public void insertionSort(int[] a)
    {
        int c,d,temp,n=17;
        
        for(c=1;c<n;c++)
        {
            temp=a[c];
            for(d=c-1;d>=0&&temp<a[d];d--)
                a[d+1]=a[d];
            if(c!=d+1)
                a[d+1]=temp;
        }
    }
    
    public void selectionSort(int[] a)
    {
        int c,d,temp,location,n=11;
        
        for(c=0;c<n-1;c++)
        {
            location=0;
            for(d=1;d<n-c;d++)
                if(a[d]>a[location])
                    location=d;
            if(location!=d-1)
            {
                temp = a[d-1];
                a[d-1] = a[location];
                a[location] = temp;
            }
        }
    }
    
    public void selectionSortMinMaxSwap(int[] a)
    {
        int c,d,temp,min,max,n=100000,e=n-1;
        
        for(c=0;c<=n/2;c++,e--)
        {
            min=max=c;
            for(d=c+1;d<n-c;d++)
            {
                if(a[d]<a[min])
                    min=d;
                else if(a[d]>a[max])
                    max=d;
            }
            
            temp = a[c];
            a[c] = a[min];
            a[min] = temp;
            
            temp = a[max];
            a[max] = a[e];
            a[e] = temp;
            
        }
        
    }
    
}

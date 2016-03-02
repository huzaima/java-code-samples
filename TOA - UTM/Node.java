/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toa.project;

/**
 *
 * @author my
 */
public class Node {
    
    private String read, write, move, moveToState;
    
    Node()
    {
    }
    
    Node(String r, String w, String m, String mTS)
    {
        read = r;
        write = w;
        move = m;
        moveToState = mTS;
    }
    
    public String getRead()
    {
        return read;
    }
    
    public String getWrite()
    {
        return write;
    }
    
    public String getMove()
    {
        return move;
    }
    
    public String getMoveToState()
    {
        return moveToState;
    }
    
    public void print()
    {
        System.out.print(read + "->" + write + ",");
        if(move.contentEquals("1"))
            System.out.print("L");
        else
            System.out.print("R");
        System.out.println(" to " + moveToState);
    }
}

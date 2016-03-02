/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toa.project;

import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author my
 */
public class UTM {

    private String machineDefinition;
    private final String inputReadOnly;//read-only tape
    private final String initialState;
    private final String finalState;
    private Vector<String> input;//contains encoded contents of read-only tape

    UTM() 
    {
        /*
         * This is a constructor and it'll take input from user following things:
         * 1) Machine Definition
         * 2) Initial State
         * 3) Final State
         * 4) Input
         * It'll then encode machine definition and input
         */
        
        Scanner in = new Scanner(System.in);

        System.out.println("Enter input in following format stateNumber,read,write,move,moveToState (use $ for blank symbol)");
        System.out.println("Consider this definition of {a,b}* and folllow this pattern.");
        System.out.println("1,a,a,R,1-1,b,b,R,1-1,$,$,L,2");
        System.out.print("Enter Definition Now: ");
        machineDefinition = in.nextLine();

        System.out.print("Enter Initial State (Example: 1): ");
        initialState = convertToUnary(in.nextLine());

        System.out.print("Enter Final State (Example: 5): ");
        finalState = convertToUnary(in.nextLine());

        System.out.print("Enter Input (Example: abbab or 10010): ");
        inputReadOnly = in.nextLine();
        
        encodeMachine();//encoding machine
        encodeTape();//encoding read-only input tape and copying it to read-write tape
        System.out.println(input.toString());//encoded input
    }
    
    public void simulateTM()
    {
        /*
         * This function is responsible to simulate TM which is given in 
         * definition on the input provided
         */
        
        int currentHead;
        currentHead = 1;//head pointer at start
        String read = input.elementAt(currentHead);//read
        String currentState = initialState;
        Node temp = transitionValue(currentState,read,machineDefinition);
        
        if(temp==null)
        {
            System.out.println("No Possible Transition at Inital State");
            return;
        }
        
        while(transitionAvailable(currentState,read,machineDefinition) && !temp.getMoveToState().contentEquals(finalState))//transition available and not on final state
        {
            temp = transitionValue(currentState,read,machineDefinition);//retrieve read,write,move,moveToState
            temp.print();

            input.setElementAt(temp.getWrite(), currentHead);//write on tape
            
            if(temp.getMove().contentEquals("1"))//move left
            {
                if(currentHead>0)
                    currentHead--;
                else
                    input.insertElementAt("$", 0);
            }
            else if (temp.getMove().contentEquals("11"))//move right
            {
                if(currentHead<input.size()-1)
                    currentHead++;
                else
                {
                    input.insertElementAt("$", input.size());
                    currentHead++;
                }
            }
            currentState = temp.getMoveToState();//update current state after moving to state
            read = input.elementAt(currentHead);//read new input from tape
            
            System.out.println(input.toString() + "\n");//print tape
        }
        
        if(temp != null && temp.getMoveToState().contentEquals(finalState))//if on final state
            System.out.println("String Accepted");
        else
            System.out.println("Reject/Halt on state " + temp.getMoveToState());//halt
    }
    
    private boolean transitionAvailable(String currentState, String read, String md)
    {
        /* 
         * This finction checks in machine definition whether the transition is 
         * available for whatever is input from input string and on which state 
         * we are. Returns true if there is transition available, false otherwise.
         */
        
        String toCheck = "-".concat(currentState).concat(",").concat(read).concat(",");
        return md.contains(toCheck);
    }
    
    private Node transitionValue(String currentState, String read, String md)//md = machine definition
    {
        /* 
         * This function retrieves the value from machine definition of what to 
         * write, where to move, and to which state, based on current state and 
         * input on current state (arguments).
         */
        
        String toCheck = "-".concat(currentState).concat(",").concat(read).concat(",");
        int c = md.indexOf(toCheck);
        if(transitionAvailable(currentState,read,md) && c != -1)
        {
            int d = ++c; 
            String write, move, moveToState;
            while(md.charAt(d)!=',')
                d++;
            c = ++d;
            while(md.charAt(d)!=',')
                d++;
            c = ++d;
            while(md.charAt(d)!=',')
                d++;
            write = md.substring(c, d);
            c = ++d;
            while(md.charAt(d)!=',')
                d++;
            move = md.substring(c, d);
            c = ++d;
            while(d<md.length() && md.charAt(d)!='-')
                d++;
            moveToState = md.substring(c, d);
            
            Node temp = new Node(read,write,move,moveToState);
            return temp;
        }
        return null;
    }

    private void encodeMachine() 
    {
        String temp = "";
        int c = 0;
        int d = c+1;
        
        while(c<machineDefinition.length() && d<machineDefinition.length())
        {
            temp = temp.concat("-");
            while(machineDefinition.charAt(d)!=',')
                d++;
            temp = temp.concat(convertToUnary(machineDefinition.substring(c, d))).concat(",");
            c = ++d;
            while(machineDefinition.charAt(d)!=',')
                d++;
            temp = temp.concat(convertToUnary(machineDefinition.substring(c, d))).concat(",");
            c = ++d;
            while(machineDefinition.charAt(d)!=',')
                d++;
            temp = temp.concat(convertToUnary(machineDefinition.substring(c, d))).concat(",");
            c = ++d;
            while(machineDefinition.charAt(d)!=',')
                d++;
            temp = temp.concat(convertToUnary(machineDefinition.substring(c, d))).concat(",");
            c = ++d;
            while(d<machineDefinition.length() && machineDefinition.charAt(d)!='-')
                d++;
            temp = temp.concat(convertToUnary(machineDefinition.substring(c, d)));
            c = ++d;
        }
        machineDefinition = temp;
    }
    
    private void encodeTape()
    {
        input = new Vector();
        
        int d = 0;
        String a;
        
        for (int c=0;c<inputReadOnly.length();c++) 
        {
            a = Character.toString(inputReadOnly.charAt(c));
            
            if(a.contentEquals("a"))
                input.add("1");
            else if(a.contentEquals("b"))
                input.add("11");
            else if(a.contentEquals("c"))
                input.add("111");
            else if(a.contentEquals("X"))
                input.add("1111");
            else if(a.contentEquals("Y"))
                input.add("11111");
            else if(a.contentEquals("Z"))
                input.add("111111");
            else if(a.contentEquals("T"))
                input.add("1111111");
            else
            {
                int x = Integer.parseInt(a);
                String unary = "";
                if(x>0)
                    while (x-- > 0) 
                        unary = unary.concat("1");
                input.add(unary.concat("1"));
            }
        }
        input.insertElementAt("$", 0);
        input.insertElementAt("$", input.size());
    }

    private String convertToUnary(String a) 
    {
        if((!a.contentEquals("R") && !a.contentEquals("L") && Character.isLetter(a.charAt(0))) || a.contentEquals("$"))
        {
            if(a.contentEquals("a"))
                return "1";
            else if(a.contentEquals("b"))
                return "11";
            else if(a.contentEquals("c"))
                return "111";
            else if(a.contentEquals("X"))
                return "1111";
            else if(a.contentEquals("Y"))
                return "11111";
            else if(a.contentEquals("Z"))
                return "111111";
            else if(a.contentEquals("T"))
                return "1111111";
            else if(a.contentEquals("$"))
                return "$";
        }
        else if (!a.contentEquals("R") && !a.contentEquals("L"))
        {
            int x = Integer.parseInt(a);
            String unary = "";
            while (x-->0)
                unary = unary.concat("1");
            return unary.concat("1");
        }
        else
        {
            if(a.contentEquals("L"))
                return "1";
            else
                return "11";
        }
        return null;
    }

}

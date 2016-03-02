/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toa.project;
import java.util.Scanner;
/**
 *
 * @author my
 */
public class TOAProject {

    /**
     * @param args the command line arguments
     */
    
    /*
     * few machine definitions
     */
    
    /*
     * increment (initial state = 1, final stae = 4):
     * "1,0,0,R,1-1,1,1,R,1-1,$,$,L,2-2,0,1,R,3-2,1,1,L,2-2,$,1,R,5-5,1,0,R,5-5,$,$,L,4-3,1,0,R,3-3,$,$,L,4"
     */
    
    /*
     * a^n b^n (n>0) (initial state = 1, final state = 5):
     * "1,a,X,R,2-1,Y,Y,R,4-2,a,a,R,2-2,Y,Y,R,2-2,b,Y,L,3-3,a,a,L,3-3,Y,Y,L,3-3,X,X,R,1-4,Y,Y,R,4-4,$,$,L,5"
     */
    
    /*
     * a^n b^n (n>=0) (initial state = 1, final state = 5):
     * "1,a,X,R,2-1,Y,Y,R,4-1,$,$,L,5-2,a,a,R,2-2,Y,Y,R,2-2,b,Y,L,3-3,a,a,L,3-3,Y,Y,L,3-3,X,X,R,1-4,Y,Y,R,4-4,$,$,L,5"
     */

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        
        UTM utm = new UTM();
        
        System.out.print("Do You Want To Start TM Simulation?(Y/N) ");
        String choice = in.nextLine();
        
        if(choice.contentEquals("y") || choice.contentEquals("Y"))
            utm.simulateTM();  
    }
}

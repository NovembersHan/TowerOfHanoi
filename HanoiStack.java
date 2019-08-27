/*
 * File: HanoiStack.java
 * Author: Han
 */

import java.util.Stack;

public class HanoiStack {
	Stack<Integer> s; // source pole
	Stack<Integer> d; // destination pole
	Stack<Integer> a; // auxiliary pole
	Stack<Integer> pole1; // one active pole for moving disk
	Stack<Integer> pole2; // the other active pole for moving disk
	int index1; // the index of pole1
	int index2; // the index of pole2
	
	/**
	 * Function to decide total moves and which two poles are active poles for each move
	 * @param ndisks the number of total disks
	 * @param src the pole index of source pole
	 * @param dest the pole index of destination pole
	 */
	public void hanoi(int ndisks, int src, int dest) {
		@SuppressWarnings("unchecked")
		Stack<Integer>[] poles = new Stack[3]; //create the array for pole stacks
		poles[0] = new Stack<Integer>(); //create the stack for pole 0
		poles[1] = new Stack<Integer>(); //create the stack for pole 1
		poles[2] = new Stack<Integer>(); //create the stack for pole 2
		s = poles[src]; // initial source pole
		d = poles[dest]; // initial destination pole
		a = poles[3-src-dest]; // initial auxiliary pole
		
		// total moves of n disks are 2^n - 1 steps
		int total_moves = (int) (Math.pow(2, ndisks) - 1);
		
		// push ndisks of disks to source pole, with the largest disk numbered 1 and the smallest as ndisks
		for (int i = 1; i <= ndisks; i++) {
			s.push(i);
		}
        
		/**
		 * For an even number of disks:
			make the legal move between pegs A and B (in either direction),
			make the legal move between pegs A and C (in either direction),
			make the legal move between pegs B and C (in either direction),
			repeat until complete.
			
		 * For an odd number of disks:
			make the legal move between pegs A and C (in either direction),
			make the legal move between pegs A and B (in either direction),
			make the legal move between pegs B and C (in either direction),
			repeat until complete.
		 */
		for (int i = 1; i <= total_moves; i++) { 
            if (i % 3 == 1) {
            	pole1 = s;
            	index1 = src;
            	if (ndisks % 2 == 0) { 
            		pole2 = a;
            	    index2 = 3-src-dest;
            	} else {
            		pole2 = d;
            		index2 = dest;
            	}
            	moveDisk();             	
            }
       
            else if (i % 3 == 2) { 
            	pole1 = s;
            	index1 = src;
            	if (ndisks % 2 == 0) { 
            		pole2 = d;
                	index2 = dest;
            	} else {
            		pole2 = a;
                	index2 = 3-src-dest;
            	}
            	moveDisk(); 
            }
            
            else if (i % 3 == 0) {
            	pole1 = a;
            	pole2 = d;
            	index1 = 3-src-dest;
            	index2 = dest;
            	moveDisk(); 
           }
        } 

		
	}
	
	/**
	 * Function to decide a disk should be taken from which pole of two active poles
	 */
	public void moveDisk() {
        // if pole1 is empty, push the disk on the top of pole2 to pole1
		if (pole1.empty()) {
        	pole1.push(pole2.pop());
        	System.out.println("Move disk from pole "+index2 + " to pole "+index1);
        }
        // if pole2 is empty, push the disk on the top of pole2 to pole1
        else if (pole2.empty()) {
        	pole2.push(pole1.pop());
        	System.out.println("Move disk from pole "+index1 + " to pole "+index2);
        } else {
    		// compare the disk integer on each pole, put the smaller disk(larger integer) to the other pole
        	int pole1TopDisk = pole1.pop(); 
            int pole2TopDisk = pole2.pop();
            if (pole1TopDisk > pole2TopDisk) {
            	pole2.push(pole2TopDisk);
            	pole2.push(pole1TopDisk);
            	System.out.println("Move disk from pole "+index1 + " to pole "+index2);
            } else {
            	pole1.push(pole1TopDisk);
            	pole1.push(pole2TopDisk);
            	System.out.println("Move disk from pole "+index2 + " to pole "+index1);
            }
        }
	}
	
	/**
	 * Test with 1, 2, 3, and 4 disks from pole0 to pole2.
	 */
    public static void main(String[] args) {
    	HanoiStack hs = new HanoiStack();
    	System.out.println("Tower of Hanoi: 1 disk");
    	hs.hanoi(1, 0, 2);
		
    	System.out.println("\nTower of Hanoi: 2 disks");
    	hs.hanoi(2, 0, 2);

    	System.out.println("\nTower of Hanoi: 3 disks");
    	hs.hanoi(3, 0, 2);
    	
    	System.out.println("\nTower of Hanoi: 4 disks");
    	hs.hanoi(4, 0, 2);
    }

}


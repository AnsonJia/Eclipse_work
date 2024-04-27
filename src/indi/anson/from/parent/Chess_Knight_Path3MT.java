package indi.anson.from.parent;


import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Chess_Knight_Path3MT extends Thread{ //tread here
	// common vars
	private int[][] board;
	private int [] pastMove;
	private int count;
	private int [] lastPos;
	private int [] nextPos;
	private int starter;
	public boolean found = false;
	
	//initialize
	public Chess_Knight_Path3MT(int[][] board, int [] pastmove, int count, int [] lastPos, int [] nextPos, int starter){
		//fix array refrencing 
		this.board = new int [board.length][];
		for (int i = 0; i<board.length; i++) {
			this.board[i] = board[i].clone();
		}
		//printArr(this.board);
		//System.out.println();
		
		this.pastMove = pastmove.clone();
		this.count = count;
		this.lastPos = lastPos.clone();
		this.nextPos = nextPos.clone();
		this.starter = starter;
	}
	
	//run
	public void run() {// started decided by the main
		//System.out.println("Thread: "+ getName()); 
		boolean moved = true;
		int [][] knight = {{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1}};
		
		//////////////////////////////////////////////////////// tester for the first split when MT
		nextPos[0] = lastPos[0]+knight[starter][0];
		nextPos[1] = lastPos[1]+knight[starter][1];
		
		// check if valid location
		if (nextPos[0]>=0 && nextPos[0]<8 && nextPos[1]>=0 && nextPos[1]<8 && board[7-nextPos[1]][nextPos[0]]==-1) {
			//add to arrs
			count++;
			board[7-nextPos[1]][nextPos[0]] = count;
			pastMove[count] = starter;
			lastPos[0] = nextPos[0];
			lastPos[1] = nextPos[1];
		}
		//// if started is not valid, count stays at 29, doesnt enter the while loop and thread dies
		
		
		//temp condition return to norm later//
		while (count>=30 && count<62){ // set diff condition////////////////////////////////
			int [] ret = loop(moved, board, pastMove, count, lastPos, nextPos, knight);
			count = ret[0];
			moved = ret[1] == 1;
		
		} //outside while loop
		//end/////////////////////////////////////
		if (count==62) {
			System.out.println("end");
			System.out.println(new Date());
			found = true;
		}
	}
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int horPos;
		int verPos;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Input the x position of the knight");
		horPos = scanner.nextInt();
		
		System.out.println("Input the y position of the knight");
		verPos = scanner.nextInt();
		
		scanner.close();

		int [][] knight = {{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1}};
		int [] pastMove = new int[64];
		
		int[][] board = {
				{-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1},
				};
		
		//setup
		int count = 0;
		board[7-verPos][horPos] = count;
		pastMove[count] = -1;

		boolean moved = true;
		
		int [] lastPos = {horPos, verPos};
		int [] nextPos  = new int[2];
		System.out.println(new Date());
		
		
		
		//main loop 
		while (count>=0 && count<30){ // set diff condition///////////////////////
			
			// Do MT when 30 have been placed
			if (count == 29) {
				// do MT
				Chess_Knight_Path3MT t1 = new Chess_Knight_Path3MT(board, pastMove, count, lastPos, nextPos, 0);
				Chess_Knight_Path3MT t2 = new Chess_Knight_Path3MT(board, pastMove, count, lastPos, nextPos, 1);
				Chess_Knight_Path3MT t3 = new Chess_Knight_Path3MT(board, pastMove, count, lastPos, nextPos, 2);
				Chess_Knight_Path3MT t4 = new Chess_Knight_Path3MT(board, pastMove, count, lastPos, nextPos, 3);
				Chess_Knight_Path3MT t5 = new Chess_Knight_Path3MT(board, pastMove, count, lastPos, nextPos, 4);
				Chess_Knight_Path3MT t6 = new Chess_Knight_Path3MT(board, pastMove, count, lastPos, nextPos, 5);
				Chess_Knight_Path3MT t7 = new Chess_Knight_Path3MT(board, pastMove, count, lastPos, nextPos, 6);
				Chess_Knight_Path3MT t8 = new Chess_Knight_Path3MT(board, pastMove, count, lastPos, nextPos, 7);
				t1.start();
				t2.start();
				t3.start();
				t4.start();
				t5.start();
				t6.start();
				t7.start();
				t8.start();
				
				try {
					t1.join();
					t2.join();
					t3.join();
					t4.join();
					t5.join();
					t6.join();
					t7.join();
					t8.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (t1.found == true || t2.found == true ||t3.found == true ||t4.found == true ||t5.found == true ||t6.found == true ||t7.found == true ||t8.found == true) {
					Chess_Knight_Path3MT [] list = {t1,t2,t3,t4,t5,t6,t7,t8};
					for (Chess_Knight_Path3MT i: list) {
						if (i.found==true) {
							printArr(i.board);
							System.out.println();
						}
					}
					break;
				}else {
					moved = false;
				}
			}
			
			
			
			int [] ret = loop(moved, board, pastMove, count, lastPos, nextPos, knight);
			count = ret[0];
			moved = ret[1] == 1;
			
		}
		
	}
	
	
	static int [] loop(boolean moved, int[][] board, int [] pastMove, int count, int [] lastPos, int [] nextPos, int [][] knight) {
		int checkMove = 0;
		
		//////////////////////step back condition ////////////////////////////
		if (moved == false) {
			// set location to -1 (empty)
			board[7-lastPos[1]][lastPos[0]] = -1;
			
			//set last pos back
			lastPos[0] -= knight[pastMove[count]][0];
			lastPos[1] -= knight[pastMove[count]][1];
			
			// advance to the next knight movement option
			checkMove = pastMove[count]+1;
			
			count--;
		}
		
		///////////////////////normal condition///////////////////////////
		for (int i = checkMove; i<knight.length; i++) {
			// advance next position
			nextPos[0] = lastPos[0]+knight[i][0];
			nextPos[1] = lastPos[1]+knight[i][1];
			
			// check if valid location
			if (nextPos[0]>=0 && nextPos[0]<8 && nextPos[1]>=0 && nextPos[1]<8 && board[7-nextPos[1]][nextPos[0]]==-1) {
				//add to arrs
				count++;
				board[7-nextPos[1]][nextPos[0]] = count;
				pastMove[count] = i;
				lastPos[0] = nextPos[0];
				lastPos[1] = nextPos[1];
				moved = true;
				
				break;
			}
			moved = false;
		}
		
		if (moved==true) {
			int [] ret = {count, 1};
			return ret;
		}
		int [] ret = {count, 0};
		return ret;
	}
	
	
	
	
	
	
	
	static void printArr(int[][] board) {
		for (int[] i : board) {
			System.out.print("\n");
			for (int j : i) {
				if (j == -1) {
					System.out.print("# ");
				}
				else {
					System.out.print(Integer.toString(j)+" ");
				}
				if (j<10) {
					System.out.print(" ");
				}
			}
		}
	}

}

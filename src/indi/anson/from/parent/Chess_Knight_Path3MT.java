package indi.anson.from.parent;


import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Chess_Knight_Path3MT extends Thread{
	// common vars
	private int[][] board;
	private int [] pastmove;
	private int count;
	
	//initialize
	public Chess_Knight_Path3MT(int[][] board, int [] pastmove, int count){
		this.board = board;
		this.pastmove = pastmove;
		this.count = count;
	}
	
	//run
	public void run() {
		System.out.println("test");
		System.out.println(board);
		System.out.println(pastmove);
		System.out.println(count);
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
		
		//main loop to include MT//////////////////////////////////////////////////////////////////////////////
		while (count>=0 && count<63){ //************** temp change revert norm**********//
			int checkMove = 0;
			//step back condition (possible condition to start MT)
			if (moved == false) {
				// set location of -1 (empty)
				board[7-lastPos[1]][lastPos[0]] = -1;
				
				//set last pos back
				lastPos[0] -= knight[pastMove[count]][0];
				lastPos[1] -= knight[pastMove[count]][1];
				
				// advance the knight selection option
				checkMove = pastMove[count]+1;
				
				count--;
			}
			
			//////////normal condition
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
			
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		System.out.println("end");
		printArr(board);
		System.out.println(new Date());
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

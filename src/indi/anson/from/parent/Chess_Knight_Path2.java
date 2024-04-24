package indi.anson.from.parent;

//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
//import java.util.concurrent.TimeUnit;

public class Chess_Knight_Path2 {

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
		
		
		int [][] knight = {{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1}};
		int [] pastMove = new int[64];
		//ArrayList <Integer> pastMove = new ArrayList<Integer>();
		
		//setup
		int count = 0;
		board[7-verPos][horPos] = count;
		pastMove[count] = -1;
		//count++;
		
		boolean moved = true;
		
		
		int [] lastPos = {horPos, verPos};
		int [] nextPos  = new int[2];
		System.out.println(new Date());
		//main loop//////////////////////////////////////////////////////////////////////////////
		while (count>=0 && count<63){
		//while (moved) {
			int checkMove = 0;
			//step back condition
			if (moved == false) {
				
				//System.out.println(board[7-lastPos[1]][lastPos[0]]);
				//System.out.println(Arrays.toString(lastPos));
				board[7-lastPos[1]][lastPos[0]] = -1;
				//System.out.println(count);
				//System.out.println((pastMove[count]));
				//System.out.println(Arrays.toString(pastMove));
				lastPos[0] -= knight[pastMove[count]][0];
				lastPos[1] -= knight[pastMove[count]][1];
				//System.out.println(Arrays.toString(lastPos));
				//
				//System.out.println(checkMove);
				checkMove = pastMove[count]+1;
				//System.out.println(checkMove);
				//System.out.println();
				count--;
				//break;
			}
			
			//////////////////////
			
			
			
			//testing
			
			//System.out.println(Arrays.toString(lastPos));
			//System.out.println();
			//
			
			//////////normal condition
			for (int i = checkMove; i<knight.length; i++) {
				nextPos[0] = lastPos[0]+knight[i][0];
				nextPos[1] = lastPos[1]+knight[i][1];
				
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
			
			/*try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
		}
		
		System.out.println("end");
		printArr(board);
		System.out.println(new Date());
		//System.out.println(Arrays.toString(lastPos));
		//System.out.println(Arrays.toString(pastMove));
		//System.out.println(pastMove[count]);
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
			//System.out.println(Arrays.toString(i));
		}
	}

}

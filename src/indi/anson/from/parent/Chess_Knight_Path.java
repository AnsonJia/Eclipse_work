package indi.anson.from.parent;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Chess_Knight_Path {

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
		
		String[][] board = {
				{"# ","# ","# ","# ","# ","# ","# ","# "},
				{"# ","# ","# ","# ","# ","# ","# ","# "},
				{"# ","# ","# ","# ","# ","# ","# ","# "},
				{"# ","# ","# ","# ","# ","# ","# ","# "},
				{"# ","# ","# ","# ","# ","# ","# ","# "},
				{"# ","# ","# ","# ","# ","# ","# ","# "},
				{"# ","# ","# ","# ","# ","# ","# ","# "},
				{"# ","# ","# ","# ","# ","# ","# ","# "},
				};

		
		
		int [] start = {horPos, verPos};
		int [][] knight = {{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1}};
		ArrayList<int[]> pastPos = new ArrayList<int[]>();
		ArrayList <Integer> moveSelect = new ArrayList<Integer>();
		pastPos.add(start);
		
		
		boolean moved = true;
		int count = 0;//////////
		board[7-verPos][horPos] = Integer.toString(count)+" ";/////////
		
		
		System.out.println(new Date());////////
		
		while (!pastPos.isEmpty()&&!(pastPos.size() == 64)) {
			if (moved) {
				count++;
			}else {
				count--;
			}
			moved = tryPath(count, board, pastPos, knight, moveSelect, moved);
			
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("end");
		
		printPath(pastPos);
		System.out.println(new Date());
		
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	static boolean tryPath(int count, String[][] board, ArrayList<int[]> pastPos, int [][] moves, ArrayList <Integer> pastMoves, boolean moved) {
	
		int checkMove = 0;
		
		if (moved == false) {
			checkMove = pastMoves.get(pastMoves.size()-1)+1;
			pastMoves.remove(pastMoves.size()-1);
			int [] remove = pastPos.get(pastPos.size()-1);////////
			board[7-remove[1]][remove[0]] = "# ";///////////
			pastPos.remove(pastPos.size()-1);
			
			
			//System.out.println("going back");

		}
		//printArr(board);
		printPath(pastPos);
		System.out.println();
		
		if (!pastPos.isEmpty()) {
			int currX = pastPos.get(pastPos.size()-1)[0];
			int currY = pastPos.get(pastPos.size()-1)[1];
			
			for (int i = checkMove; i<moves.length; i++) {
				int [] checkNextPos = {currX+moves[i][0], currY+moves[i][1]};
				if (checkValid(checkNextPos[0]) && checkValid(checkNextPos[1]) && !FoundArrInLst(board, checkNextPos)) {
					pastPos.add(checkNextPos.clone());
					pastMoves.add(i);
					
					board[7-checkNextPos[1]][checkNextPos[0]] = Integer.toString(count);
					
					if (count<10) {
						board[7-checkNextPos[1]][checkNextPos[0]] += " ";
					}
		
					//System.out.println(count);
					
					return true;
				}
			}
		}
		return false;

	}
	//endMain/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	static void printArr(String[][] board) {
		for (String[] i : board) {
			System.out.println(Arrays.toString(i));
		}
	}
	
	static void printPath(ArrayList<int[]> pastPos) {
		int temp [] = new int [2];
		for (int i = 0; i<8; i++) {
			temp[1]=7-i;
			System.out.print("\n");
			for (int j = 0; j<8; j++) {
				temp[0]=j;
				int pos = IndexOfArrInLst(pastPos, temp);
				if (pos!= -1) {
					System.out.print(pos+" ");
					if (pos<10) {
						System.out.print(" ");
					}
				}else {
					System.out.print("#  ");
				}
			}
		}
	}
	
	static boolean checkValid(int nextPos) {
		if (nextPos < 8 && nextPos >= 0) {
			return true;
		}
		return false;
	}
	
	static int IndexOfArrInLst(ArrayList<int[]> lst, int [] arr) {
		for (int i = 0; i<lst.size(); i++) {
			if (Arrays.equals(lst.get(i), arr)) {
				return i;
			}
		}
		return -1;
	}
	
	static boolean FoundArrInLst(String[][] board, int [] arr) {
		if (board[7-arr[1]][arr[0]].equals("# ")) {
			return false;
		}
		return true;
		
	}
	

}

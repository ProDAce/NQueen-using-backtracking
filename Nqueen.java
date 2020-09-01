package algorithms;

import java.util.*;
public class Nqueen {
	static int board[][];	//Board where queen is marked by 1
	static int pos[];		//Position(column) of ith queen
	static int n;			//Number of queens or board size
	static boolean flag=false;	//Checks if solution is available
	
	//Constructor to initialize
	Nqueen()
	{
		Scanner inp=new Scanner(System.in);
		System.out.print("Input number of queens: ");
		n=inp.nextInt();
		board = new int [n][n];
		pos = new int [n];		
		inp.close();
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
				board[i][j]=0;
		}
		solveQueen(0,0);
	}
	
	//Safe position checking
	boolean issafe(int queen, int col)
	{
		/*We are not checking horizontal(row) because each queen is 
		being put in different rows*/
		
		//Checking the column for any other queen 
		for(int i=queen-1;i>=0;i--)
		{
			if(board[i][col]==1)
				return false;
		}
		
		//Checking left diagonal for any other queen
		for(int i=queen-1, j=col-1;i>=0 && j>=0;i--,j--)
		{
			if(board[i][j]==1)
				return false;
		}
		
		//Checking right diagonal for any other queen
		for(int i=queen-1, j=col+1;i>=0 && j<n;i--,j++)
		{
			if(board[i][j]==1)
				return false;
		}
		
		return true;
	}
	
	void solveQueen(int queen, int col)	//Method to place the queens
	{
		if(queen==-1)	//Check if backtracking is possible or not
		{
			return;
		}
		if(queen==n)	//Checking if all queens are being placed or not
		{
			flag=true;
		}
		else if(col<n)	//Checking each column until suitable location is found
		{
			if(issafe(queen, col))	//Checking if the place is safe or not
			{
				board[queen][col]=1;
				pos[queen]=col;
				solveQueen(queen+1,0);
			}
			else	//If not safe we will check next column
				solveQueen(queen,col+1);
		}
		else	//If no suitable location is found then this block is 
		{		//executed and backtracking occurs
			if(queen>0)
			{
				board[queen-1][pos[queen-1]]=0;
				solveQueen(queen-1,pos[queen-1]+1);
			}
			solveQueen(-1,0);
		}
		return;
	}
	
	void printBoard()	//Method to print the board if solution is possible
	{
		if(flag)
		{
			System.out.println("The solution:-\n");
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<n;j++)
					System.out.print(board[i][j]+"  ");
				System.out.println();
			}
		}
		else
			System.out.println("\nThere are no solution");
		
	}
	public static void main(String[] args) {
		Nqueen obj=new Nqueen();
		obj.printBoard();
	}
}

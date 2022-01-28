//To write a program for 8 queens problem without fixing constraints 

import java.util.*; 

public class Main 

{ 

  public static void main (String[]args) 

  { 

    Scanner in = new Scanner (System.in); 

      System.out.println ("Enter N value "); 

    int n = in.nextInt (); 

    if (n < 4) 

      { 

System.out.println ("There are no Solutions for N = " + n); 

System.exit (0); 

      } 

    System.out.println ("Possible Solutions for N =" + n + " are"); 

    char[][] board = new char[n][n]; 

    fillboard (board); 

    solveN0ueen (0, board); 

  } 

  public static void fillboard (char[][]board) 

  { 

    for (int i = 0; i < board.length; i++) 

      { 

for (int j = 0; j < board[0].length; j++) 

  { 

    board[i][j] = '1'; 

  } 

      } 

  } 

  public static void display (char[][]board) 

  { 

    for (int i = 0; i < board.length; i++) 

      { 

for (int j = 0; j < board[0].length; j++) 

  { 

    System.out.print (board[i][j] + " "); 

  } 

System.out.println (); 

      } 

    System.out.println ("_______"); 

  } 

  public static void solveN0ueen (int r, char[][]board) 

  { 

    if (r == board.length) 

      { 

display (board); 

return; 

      } 

    for (int c = 0; c < board[0].length; c++) 

      { 

if (isSafe (r, c, board)) 

  { 

    board[r][c] = '0'; 

    solveN0ueen (r + 1, board); 

    board[r][c] = '1'; 

  } 

      } 

  } 

  public static boolean isSafe (int r, int c, char[][]board) 

  { 

    for (int i = r, j = c; i >= 0 && j >= 0; i--, j--) 

      { 

if (board[i][j] == '0') 

  { 

    return false; 

  } 

      } 

    for (int i = r; i >= 0; i--) 

      { 

if (board[i][c] == '0') 

  { 

    return false; 

  } 

      } 

    for (int i = r, j = c; i >= 0 && j < board[0].length; i--, j++) 

      { 

if (board[i][j] == '0') 

  { 

    return false; 

  } 

      } 

    return true; 

  } 

} 
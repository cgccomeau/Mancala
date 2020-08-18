import java.util.Scanner;

public class MancalaRunner
{
  public static void main (String[] args)
  {
    Mancala board = new Mancala();
    Scanner scan = new Scanner(System.in);
    int col;
    System.out.println(" How to Play: ");
    System.out.println(" 1. There are four pebbles in each hole. ");
    System.out.println(" 2. Each player can only select pebbles from their own row. ");
    System.out.println(" 3. Once a hole is selected, one pebble is put into each succesive hole until the supply is exhausted. ");
    System.out.println(" 4. If the final pebble chosen from a hole lands in your own bank, you get another turn. ");
    System.out.println(" 5. If the final pebble lands in en empty hole, then all of the pebbles directly across go into the current player's bank. ");
    System.out.println(" 6. Finally, the game ends if one row is completely exhausted.All of the pebbles in the opposing player's row goes into the opposing player's bank ");
    System.out.println(" Como Jugar: " );
    System.out.println(" 1. Hay cuatro guijarros en cada parte. ");
    System.out.println(" 2. Cada jugador puede elijir partes que estan en su columna. ");
    System.out.println(" 3. Cuando elije una parte, cada guijarro va a estar en partes sucesivos. ");
    System.out.println(" 4. Si el guijarro final elijido esta en su banco, el juagodor va a jugar de nuevo. ");
    System.out.println(" 5. Si el guijarro final esta en una parte que no tiene nada guijarro, todos los guijarros en el opuesto columna van a estar en su banco. " );
    System.out.println(" 6. Finalmnete, si una fila no tiene nada guijarro, todos los guijarros en el opuesto fila van a estar en el banco del opuesto jugador. " );
    System.out.println(board);
    
    while (!board.checkEnd())
    {
      System.out.println("Please enter the column position which you want to move, between 1-6.");
      col = scan.nextInt();
      board.yourMove(1, col);
      System.out.println();
      System.out.println(board);
      System.out.println();
            
      while (!board.checkEnd() && board.getGoAgain())
      {
        System.out.println("Yay! You get to go again. Please enter the column position of your move.");
        col = scan.nextInt();
        board.yourMove(1, col);
        System.out.println(board);
        System.out.println();
      }
      
      System.out.println("This is the board after the computer's move");
      System.out.println();
      board.oppMove();
      System.out.println(board);
      System.out.println();
      
      while(!board.checkEnd() && board.getGoAgain())
      {
        System.out.println("The computer gets to go again. This is the board after the computer's move.");
        System.out.println();
        board.oppMove();
        System.out.println(board);
        System.out.println();        
      }
    }
    
    board.finalCollect();
    System.out.println("Sweeping the board...");
    System.out.println();
    System.out.println(board);
    System.out.println();
    System.out.println(board.findWinner());
    
    scan.close();
  }
}

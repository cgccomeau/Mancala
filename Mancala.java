public class Mancala
{
  private int[][] game = new int [2][8];
  private int yourBank = 0;
  private int oppBank = 0;
  private boolean goAgain = false;
  
  
  // Creates Mancala board with 4 stones in each element
  public Mancala ()
  {
    for (int i = 0; i < game.length; i++)
     for (int j = 1; j < 7; j++)
      game[i][j] = 4;    
  }
    
  // Executes player move based on input
  // Precondition: row is always 1
  public void yourMove(int row, int col)
  {
    int spaces = game[row][col];
    boolean go = true;
    int start = col + 1; 
    goAgain = false;
    
    // Outer loop allows for move continuation if needed
    while (spaces > 0)
    {
      if (go)
      {
        game[row][col] = 0;
        go = false;
      }

      // Starts on bottom row
      while (start < 7 && spaces > 0)
      {
        game[row][start]++;
        spaces--;
        start++;
      }      
    
      // Deposits stone into player's bank
      // Checks if player can go again
      if (spaces > 0)
      {
        yourBank++;
        spaces--;
        if (spaces == 0)
          goAgain = true;
        else
          goAgain = false;
      }
      
      // Adjusts parameters to opp's row
      if (spaces > 0)
      {
        row = 0;
        start = 6;
      }
      
      // Loops through opp's row
      while (start > 0 && spaces > 0)
      {
        game[row][start]++;
        start--;
        spaces--;
      }
    
      // Adjusts parameters back to own row if needed
      if (spaces > 0)
      {
        row = 1;
        start = 1;
      }
    }
    
    // Doesn't run through code if move ends in bank
    if (!goAgain)
    {
      // Adjusts parameters for additional stone collection
      if (row == 0 && start == 7)
        row = 1;
      else if (row == 1 && start == 1)
        row = 0;
      else if (row == 1)
        start--;
      else
        start++;
    
    
      // Checks and executes additional stone collection
      if (game[row][start] == 1)
      {
        if (row == 0)
          yourBank += game[1][start] + 1;
        else
          yourBank += game[0][start] + 1;
        game[0][start] = 0;
        game[1][start] = 0;
      }
    }
  }    
  
  // Executes Opp move based on Math.random()
  // Precondition: row is always 0
  public void oppMove()
  {
    int row = 0;
    int col = (int)(Math.random()*6 + 1);
    int spaces = game[row][col];
    goAgain = false;
    
    // Ensures opp doesn't choose empty slot
    while (spaces == 0)
    {
      col = (int)(Math.random()*6 + 1);
      spaces = game[row][col];
    }
    
    boolean go = true;
    int start = col - 1;
    
    while (spaces > 0)
    {
      if (go)
      {
        game[row][col] = 0;
        go = false;
      }
      
      // Starts on opp's row
      while (start > 0 && spaces > 0)
      {
        game[row][start]++;
        start--;
        spaces--;
      }
      
      // Deposits stone into opp's bank
      // Checks if opp can go again
      if (spaces > 0)
      {
        oppBank++;
        spaces--;
        if (spaces == 0)
          goAgain = true;
        else
          goAgain = false;
      }

      
      // Adjusts parameters to player's row
      if (spaces > 0)
      {
        row = 1;
        start = 1;
      }
      
      // Loops through player's row
      while (start < 7 && spaces > 0)
      {
        game[row][start]++;
        spaces--;
        start++;
      }
      
      // Adjusts parameters back to opp's row if needed
      if (spaces > 0)
      {
        row = 0;
        start = 6;
      }
    }
    
    // Doesn't run through code if move ends in bank
    if (!goAgain)
    {
      // Adjusts parameters for additional stone collection
      if (row == 0 && start == 7)
        row = 1;
      else if (row == 1 && start == 1)
        row = 0;
      else if (row == 1)
        start--;
      else
        start++;
    
    
      // Checks and executes additional stone collection
      if (game[row][start] == 1)
      {
        if (row == 0)
          oppBank += game[1][start] + 1;
        else
          oppBank += game[0][start] + 1;
        game[0][start] = 0;
        game[1][start] = 0;
      }
    }
  }
  
  
  // Accesses player's bank value  
  public int getYourBank()
  {
    return yourBank;
  }
  
  // Accesses opp's bank value
  public int getOppBank()
  {
    return oppBank;
  }
  
  // Accesses if player or opp can go again
  public boolean getGoAgain()
  {
    return goAgain;
  }
    
  // Checks if game has ended
  public boolean checkEnd()
  {
    boolean end1 = true;
    boolean end2 = true;
    for (int i = 1; i < 7; i++)
    {
      if (game[0][i] != 0)
        end1 = false;
      if (game[1][i] != 0)
        end2 = false;
    }
    return end1 || end2;
  }
  
  // Executes final collection once game has ended
  public void finalCollect()
  {
    int col = 1;
    if (checkEnd())
    {
      while (col < 7)
      {
        yourBank += game[1][col];
        oppBank += game[0][col];
        game[1][col] = 0;
        game[0][col] = 0;
        col++;
      }
    }
    
  }
  
  // Determines winner of game
  public String findWinner()
  {
    String result = "";
    if (getYourBank() > getOppBank())
      result = "Congrats, you won!";
    else if (getYourBank() == getOppBank())
      result = "We have a tie!";
    else
      result = "Egregious! You lost.";
    return result;
  }
  
  // Prints Mancala board values
  public String toString()
  {
    String result = "" + getOppBank() + "\n";
    for (int i = 0; i < 2; i++)
    {
      for (int j = 1; j < 7; j++)
        result += game[i][j] + "\t";
      result += "\n";
    }
    result += getYourBank();
    return result;
  }
}
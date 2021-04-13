import java.util.*;
import java.lang.Math;

public class Module2
{
   public static void main(String[] args)
   {
      int userBet = 0;
      ThreeString pullString = new ThreeString();
      //main game loop
      while((userBet = getBet()) != 0)
      {
         //sets the users pull
         pullString = pull();
         //checks if there are more pulls
         if(pullString.saveWinnings(userBet * getPayMultiplier(pullString)))
         {
            //if there's more pulls, display pull dialogue
            display(pullString, userBet * getPayMultiplier(pullString));
         }
         else
         {
            //if no more pulls, display winnings dialogue and end
            System.out.println("Thanks for playing!\n" + 
                  pullString.toStringWinnings());
            System.exit(0);
         }
      }
      //user exited game loop with 0, display winnings dialogue
      System.out.println("Thanks for playing!\n" + 
            pullString.toStringWinnings());
   }
   //prompts user for bet and validates data, 0 < bet < 100
   public static int getBet()
   {
      int bet = -1;
      Scanner scan = new Scanner(System.in);
      while(bet < 0 || bet > 100)
      {
         System.out.println("How much would you like to bet "
               + "(1 - 100) or 0 to quit? ");
         bet = scan.nextInt();
      }
      return bet;
   }
   //method returns random string
   public static String randString()
   {
      int randomNumber = (int)(Math.random() * 1000);
      //space is generated 50% of the time
      if (randomNumber >= 0 && randomNumber <= 500 )
         return "space";
      //cherries is generated 25% of the time
      else if (randomNumber >= 501 && randomNumber <= 750)
         return "cherries";
      //BAR is generated 12.5% of the time
      else if (randomNumber >= 751 && randomNumber <= 875)
         return "BAR";
      //7 is generated 12.5% of the time
      else if (randomNumber >= 876 && randomNumber <= 1000)
         return "7";
      return null;
   }
   //method for users pull to be assigned the three strings
   public static ThreeString pull()
   {
      ThreeString obj = new ThreeString();
      obj.setString1(randString());
      obj.setString2(randString());
      obj.setString3(randString());
      return obj;
   }
   //gets play multiplier for three strings
   public static int getPayMultiplier(ThreeString thePull)
   {
      String str1 = thePull.getString1();
      String str2 = thePull.getString2();
      String str3 = thePull.getString3();
      //cherries  [not cherries]  [any] returns x5 multiplier
      if (str1.equals("cherries") && !str2.equals("cherries"))
         return 5;
      //cherries  cherries  [not cherries] returns x15 multiplier
      else if (str1.equals("cherries") && str2.equals("cherries")
            && !str3.equals("cherries"))
         return 15;
      //cherries  cherries  cherries returns x30 multiplier
      else if (str1.equals("cherries") && str2.equals("cherries")
            && str3.equals("cherries"))
         return 30;
      //BAR  BAR  BAR returns x50 multiplier
      else if (str1.equals("BAR") && str2.equals("BAR")
            && str3.equals("BAR"))
         return 50;
      //7  7  7 returns x100 multiplier
      else if (str1.equals("7") && str2.equals("7")
            && str3.equals("7"))
         return 100;
      //any other combo of strings returns 0 multiplier
      else
         return 0;
   }
   //displays pull dialogue
   public static void display (ThreeString thePull, int winnings)
   {
      System.out.println("whirrrrrr .... and your pull is ...");
      System.out.println(thePull.toString());
      if(winnings > 0)
         System.out.println("Congratulations, you win: " + winnings);
      else
         System.out.println("Sorry, you lose.");
   }
}

class ThreeString
{
   public static final int MAX_LEN = 20;
   public static final int MAX_PULLS = 40;
   private String string1, string2, string3;
   private static int numPulls;
   private static int[] pullWinnings = new int[MAX_PULLS];
   public ThreeString()
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }
   //checks if string is valid
   boolean validString(String str)
   {
      if (!str.equals("") && str.length() <= MAX_LEN )
         return true;
      else
         return false;
   }
   //setters for the three strings
   public boolean setString1(String str)
   {
      if(validString(str) == true)
      {
         string1 = str;
         return true;
      }
      else
         return false;
      
   }
   public boolean setString2(String str)
   {
      if(validString(str) == true)
      {
         string2 = str;
         return true;
      }
      else
         return false;
   }
   public boolean setString3(String str)
   {
      if(validString(str) == true)
      {
         string3 = str;
         return true;
      }
      else
         return false;
   }
   //getters for the three strings
   public String getString1()
   {
      return string1;
   }
   public String getString2()
   {
      return string2;
   }
   public String getString3()
   {
      return string3;
   }
   //returns three strings of pull in one string
   public String toString()
   {
      return string1 + "   " + string2 + "   " + string3;
   }
   //saves winnings to array
   public boolean saveWinnings(int winnings)
   {
      //checks if number of pulls exceeds array size
      if(numPulls >= MAX_PULLS)
         return false;
      //if there are pulls left, save the winnings to array
      else
      {
         pullWinnings[numPulls] = winnings;
         numPulls += 1;
         return true;
      }
   }
   //creates a string to display the winnings dialogue
   public String toStringWinnings()
   {
      int total = 0;
      String winnings = "Your individual winnings were:\n";
      for(int i = 0; i < numPulls; i++)
      {
         total += pullWinnings[i];
         winnings = winnings + " " + Integer.toString(pullWinnings[i]);
      }
      winnings = winnings + "\nYour total winnings were: $" + 
            Integer.toString(total);
      return winnings;
   }
}
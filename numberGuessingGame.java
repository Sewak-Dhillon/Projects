import java.util.Scanner;
import javax.swing.*;

public class numberGuessingGame {
    public static String guessNumber(int user,int computer,int count)
    {
        String str = "";
        if(computer%2 == 0)
            str = "even";
        else
            str = "odd";
        if(user < 0 || user > 100)
            return "Invalid input.";
        else if (user < computer)
            return "Enter a higher value.\nHint : It is a "+str+" number.";
        else if(user > computer)
            return "Enter a lower value.\nHint : It is a "+str+" number.";
        else
            return "Correct!\nTotal guesses : " + count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JFrame jf = new JFrame("Number Guessing Game");
        jf.setSize(420,420);
        jf.setVisible(true);
        int user = 0,count = 0;
        int computer = (int)(Math.random()*100) + 1; // It will generate some random number between 1-100.
        while (user != computer)
        {
            count++;
            String response = JOptionPane.showInputDialog(jf,
            "Enter a guess between 1 and 100", "Number Guessing Game",3);

            user = Integer.parseInt(response);

            JOptionPane.showMessageDialog(jf, ""+ guessNumber(user, computer, count),"Alert",JOptionPane.WARNING_MESSAGE);


//            My implementation.

//            user = sc.nextInt();
//            String str = guessNumber(user,computer,count);
//            System.out.println(str);
        }
    }
}

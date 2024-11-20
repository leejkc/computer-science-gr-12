import java.util.Scanner;

public class MANcalculator {
    public static void main(String[] args){
        System.out.println("");
        System.out.println("");
        // spacing 
        System.out.println("M.A.N. Calculator!");
        Scanner sc = new Scanner(System.in);

        System.out.print("first term coefficent: ");
        String firstTerm = sc.nextLine();
        System.out.print("second term coefficent: ");
        String secondTerm = sc.nextLine();
        System.out.print("third term coefficent: ");
        String thirdTerm = sc.nextLine();

        sc.close();

        try {
            // parse to check if int
            int first = Integer.parseInt(firstTerm);
            int second = Integer.parseInt(secondTerm);
            int third = Integer.parseInt(thirdTerm);

            // if int put into method
            int m = first*third; // what muliplies to
            int a = second; // what adds to

            int divisor = 1;
            while (true){
                int result = m/divisor;
                int guess = result + divisor;
                if (guess == a){
                    System.out.println(result);
                    System.out.println(divisor);

                    System.out.println("");
                    System.out.println("");        
                    break;
                }
                if (divisor > m){
                    System.out.println("Your trinomial has no result!");

                    System.out.println("");
                    System.out.println("");        
                    return;
                }
                divisor++;
            }
            return;
        } catch (NumberFormatException _) { // underscore because not calling e (unused lambda)
            System.out.println("Please enter a int representing the\ncoefficent for each term");

            System.out.println("");
            System.out.println("");
            return;
        }
    }
}

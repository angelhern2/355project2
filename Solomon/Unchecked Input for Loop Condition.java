import java.util.Scanner;

public class CWE606 {
    public void loopBasedOnInput(int n) {
        for (int i = 0; i < n; i++) { // No check on input
            System.out.println("Iteration " + (i + 1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int userInput = scanner.nextInt(); // Unchecked input
        new CWE606().loopBasedOnInput(userInput); // Could cause excessive looping
    }
}

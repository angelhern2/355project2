public class CWE484 {
    public void checkGrade(char grade) {
        switch (grade) {
            case 'A':// Missing break causes unintended fall-through
                System.out.println("Excellent");
                case 'B':// Missing break causes unintended fall-through
                System.out.println("Excellent");
            case 'C': 
                System.out.println("Good");
                break;
            default:
                System.out.println("Needs improvement");
                break;
        }//If A or B is chosen the results will also print the statement below due to the lack of break statements
    }
}

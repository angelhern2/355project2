public class CWE248 {
    public void riskyOperation() {
        throw new RuntimeException("Something went wrong!");
    }

    public static void main(String[] args) {
        new CWE248().riskyOperation(); // Uncaught exception
    }
}

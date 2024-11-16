public class CWE1108 {
    static int globalCounter = 0; // Overused global variable

    public void incrementCounter() {
        globalCounter++;
    }

    public void resetCounter() {
        globalCounter = 0;
    }
}
//Makes it hard to manage and debug the variable if needed since it is used across multiple methods

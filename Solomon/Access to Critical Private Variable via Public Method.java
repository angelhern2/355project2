public class CWE767 {
    private int criticalValue = 42;

    public int getCriticalValue() {
        return criticalValue; // Returns the private variable directly
    }

    public void setCriticalValue(int criticalValue) {
        this.criticalValue = criticalValue; // Modifies the private variable directly
    }

    public static void main(String[] args) {
        CWE767 obj = new CWE767();
        obj.setCriticalValue(0); // Public access to modify private data
        System.out.println("Critical Value: " + obj.getCriticalValue());
    }
}

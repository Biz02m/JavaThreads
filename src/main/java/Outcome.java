import java.util.HashMap;
import java.util.Map;

public class Outcome {
    private HashMap<Integer,Boolean> Outcomes;

    public Outcome(){
        this.Outcomes = new HashMap<>();
    }
    
    public void printMe(){
        System.out.println("In the outcome is: ");
        for (Map.Entry<Integer,Boolean> entry : Outcomes.entrySet()) {
            int value = entry.getKey();
            boolean bol = entry.getValue();
            System.out.println("Number: " + value + (bol ? " is prime number" : " is not a prime number"));
        }
    }

    public synchronized void put(Integer value, Boolean isPrime){
        this.Outcomes.put(value,isPrime);
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Outcome {
    public class Out{
        private int liczba;
        private List<Integer> dzielniki;

        public Out(int liczba){
            this.liczba = liczba;
        }

        public int getLiczba() {
            return liczba;
        }

        public void setLiczba(int liczba){
            this.liczba = liczba;
        }
    }
    private List<Out> outcomes;

    public Outcome(){
        this.outcomes = new ArrayList<>();
    }
    
    public void printMe(){
        System.out.println("In the outcome is: ");
        for (Out o : this.outcomes
             ) {
            System.out.println("Number: "+ o.liczba + " ma dzielniki: " + o.dzielniki);
        }
        //System.out.println("Number: " + value + (bol ? " is prime number" : " is not a prime number"));

    }

    public synchronized void put(Integer liczba, List<Integer> dzielniki){
        Out out = new Out(liczba);
        out.dzielniki = dzielniki;
        this.outcomes.add(out);
    }
}
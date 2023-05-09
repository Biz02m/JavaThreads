import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Outcome {
    public class Out{
        private Long liczba;
        private List<Long> dzielniki;

        public Out(Long liczba){
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

    public void printToFile(){
        try {
            FileWriter myWriter = new FileWriter("dzielniki.txt");

            for (Out o: this.outcomes
                 ) {
                myWriter.write("Liczba: "+ o.liczba + " ma dzielniki: " + o.dzielniki + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public synchronized void put(Long liczba, List<Long> dzielniki){
        Out out = new Out(liczba);
        out.dzielniki = dzielniki;
        long currentThreadId = Thread.currentThread().getId();
        System.out.println(currentThreadId + ": dodaje wynik: " + liczba);
        this.outcomes.add(out);
    }
}
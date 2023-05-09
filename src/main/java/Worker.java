import java.util.ArrayList;
import java.util.List;

public class Worker implements Runnable{
    private Resource resource;
    private Outcome outcome;

    public Worker(Resource resource, Outcome outcome){
        this.resource = resource;
        this.outcome = outcome;
    }

    public static boolean isPrime(int number) throws InterruptedException {
        // Liczby mniejsze lub równe 1 nie są pierwsze
        if (number <= 1) {
            return false;
        }

        // Sprawdzanie dzielników
        for (int i = 2; i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new InterruptedException();
//            }
        }

        // Jeśli nie znaleziono dzielników, to liczba jest pierwsza
        return true;
    }

    public static List<Integer> getDzielniki(int number) throws InterruptedException{
        List<Integer> dzielniki = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                dzielniki.add(i);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new InterruptedException();
            }
        }

        return dzielniki;
    }

    @Override
    public void run(){
        long currentThreadId = Thread.currentThread().getId();
        System.out.println("Thread with ID has started: " + currentThreadId);
        int value;
        boolean isP;
        List<Integer> dzielniki;
        while(true){
            try{
                value = resource.take();
            }
            catch (InterruptedException ex){
                break;
            }
            try{
                dzielniki = getDzielniki(value);
                //isP = isPrime(value);
            }catch (InterruptedException ex){
                //System.out.println("pobralem i zwracam wartosc do listy: " + value);
                resource.put(value);
                break;
            }

            System.out.println();
            outcome.put(value,dzielniki);
        }
    }

}

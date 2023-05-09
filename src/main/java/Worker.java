import java.util.ArrayList;
import java.util.List;

public class Worker implements Runnable{
    private Resource resource;
    private Outcome outcome;

    public Worker(Resource resource, Outcome outcome){
        this.resource = resource;
        this.outcome = outcome;
    }

    public static List<Long> getDzielniki(Long number) throws InterruptedException{
        List<Long> dzielniki = new ArrayList<>();

        if(number < 1){
            dzielniki.add(0l);
            return dzielniki;
        }

        for (Long i = 1l; i <= number; i++) {
            if (number % i == 0) {
                dzielniki.add(i);
            }
            try {
                Thread.sleep(0);
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
        Long value;
        boolean isP;
        List<Long> dzielniki;
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
                System.out.println("pobralem i zwracam wartosc do listy: " + value);
                resource.put(value);
                break;
            }

            System.out.println();
            outcome.put(value,dzielniki);
        }
    }

}

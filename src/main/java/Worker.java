import java.util.ArrayList;
import java.util.List;

public class Worker implements Runnable{
    private Resource resource;
    private Outcome outcome;
    public boolean isWorking;

    public Worker(Resource resource, Outcome outcome){
        this.resource = resource;
        this.outcome = outcome;
        isWorking = true;
    }

    public List<Long> getDivisors(Long number){// throws InterruptedException{
        List<Long> divisors = new ArrayList<>();

        if(number < 1){
            divisors.add(0l);
            return divisors;
        }

        for (Long i = 1l; i <= number; i++) {
            if (number % i == 0) {
                divisors.add(i);
            }
        }

        return divisors;
    }

    @Override
    public void run(){
        long currentThreadId = Thread.currentThread().getId();
        System.out.println("Thread with ID has started: " + currentThreadId);
        Long value = -1l;
        List<Long> divisors;

        while(isWorking){
            try{
                value = resource.take();
            }
            catch (InterruptedException ex){
                isWorking = false;
            }

            if(value >= 0) {
                divisors = getDivisors(value);
                outcome.put(value, divisors);
            }
            value = -1l;
        }
    }

}

import java.util.ArrayList;
import java.util.List;

public class Worker implements Runnable{
    private final Resource resource;
    private final Outcome outcome;
    public boolean isWorking;

    public Worker(Resource resource, Outcome outcome){
        this.resource = resource;
        this.outcome = outcome;
        isWorking = true;
    }

    public List<Long> getDivisors(Long number) throws InterruptedException{
        List<Long> divisors = new ArrayList<>();

        if(number < 1){
            divisors.add(0L);
            return divisors;
        }

        for (Long i = 1L; i <= number; i++) {
            if (number % i == 0) {
                divisors.add(i);
            }
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                isWorking = false;
            }
        }



        return divisors;
    }

    @Override
    public void run(){
        long currentThreadId = Thread.currentThread().getId();
        System.out.println("Thread with ID has started: " + currentThreadId);
        Long value = -1L;
        List<Long> divisors = new ArrayList<>();

        while(isWorking){
            try{
                value = resource.take();
            }
            catch (InterruptedException ex){
                isWorking = false;
            }

            if(value >= 0) {
                try {
                    divisors = getDivisors(value);
                    if(!isWorking){
                        throw new InterruptedException();
                    }
                }
                catch (InterruptedException e){
                    isWorking = false;
                }
                outcome.put(value, divisors);
            }
            value = -1L;
        }
    }

}

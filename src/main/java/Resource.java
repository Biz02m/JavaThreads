import java.util.LinkedList;

public class Resource {
    private LinkedList<Long> Numbers;

    public Resource(){
        this.Numbers = new LinkedList<Long>();
    }

    public synchronized Long take() throws InterruptedException{
        long currentThreadId = Thread.currentThread().getId();
        while(Numbers.isEmpty()){
            System.out.println(currentThreadId + ": lista jest pusta, czekam na inputy");
            wait();
        }
        Long retVal = Numbers.getLast();
        System.out.println(currentThreadId + ": pobralem: "+ retVal);
        Numbers.removeLast();
        return retVal;
    }

    public synchronized void put(Long value){
        this.Numbers.addFirst(value);
        notifyAll();
    }

    public synchronized void printMe(){
        System.out.println("In the list remains: ");
        for (Long num : Numbers) {
            System.out.println(num);
        }
    }
}

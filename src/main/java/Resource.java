import java.util.LinkedList;

public class Resource {
    private LinkedList<Long> Numbers;

    public Resource(){
        this.Numbers = new LinkedList<>();
    }

    public LinkedList<Long> getNumbers(){
        return this.Numbers;
    }

    public synchronized Long take() throws InterruptedException{
        while(Numbers.isEmpty()){
            wait();
        }
        Long retVal = Numbers.getLast();
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

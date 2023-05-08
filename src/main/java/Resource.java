import java.util.LinkedList;

public class Resource {
    private LinkedList<Integer> Numbers;

    public Resource(){
        this.Numbers = new LinkedList<Integer>();
    }

    public synchronized Integer take() throws InterruptedException{
        while(Numbers.isEmpty()){
            System.out.println("lista jest pusta, czekam na inputy");
            wait();
        }
        System.out.println("znaleziono input, odpalamy");
        int retVal = Numbers.getLast();
        Numbers.removeLast();
        return retVal;
    }

    public synchronized void put(Integer value){
        this.Numbers.addFirst(value);
        notifyAll();
    }

    public synchronized void printMe(){
        System.out.println("In the list remains: ");
        for (int num : Numbers) {
            System.out.println(num);
        }
    }
}

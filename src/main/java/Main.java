import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Resource resource;
    public static Outcome outcome;
    public static LinkedList<Thread> workers;
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        String op;
        Integer tmp;
        workers = new LinkedList<>();
        resource = new Resource();
        outcome = new Outcome();


        while(true){
            System.out.println("type in : new | quit | input | random entries");
            op = scan.nextLine();
            switch (op){
                case "new":
                    System.out.println("Type in a number of threads to be created:");
                    tmp = scan.nextInt();
                    startNewThreads(tmp);
                    break;
                case "quit":
                    quit();
                    break;
                case "input":
                    System.out.println("Type in numbers to be processed:");
                    inputs();
                    break;
                case "random entries":
                    System.out.println("Type in: number of inputs | origin | bounds");
                    int noOfInpts = scan.nextInt();
                    int origin = scan.nextInt();
                    int bounds = scan.nextInt();
                    randomInputs(noOfInpts,origin,bounds);
            }
        }
    }

    public static void inputs(){
        String op;
        Integer tmp;
        while(true){
            op = scan.nextLine();
            if(op.equals("q"))
                break;
            try {
                tmp = Integer.parseInt(op);
            }catch (NumberFormatException ex){
                continue;
            }
            resource.put(tmp);
        }
    }

    public static int quit(){
        for (Thread th : workers) {
            th.interrupt();
        }
        for (Thread th : workers) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        resource.printMe();
        outcome.printMe();
        return 0;
    }
    
    public static void startNewThreads(int noOfThs){
        for(int i = 0; i < noOfThs; i++){
            Thread thread = new Thread(new Worker(resource,outcome));
            thread.start();
            workers.add(thread);
        }
    }

    public static void randomInputs(int numberOfInputs, int origin, int bound){
        Random rand = new Random();
        for(int i = 0; i<numberOfInputs;i++){
            resource.put(rand.nextInt(origin, bound));
        }
    }
}


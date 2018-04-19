package lab4_5;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Lab4_5 {

    public static void main(String[] args) throws IOException{
        Scanner terminalInput = new Scanner(System.in);
        String fileName;


       // File file = new File ("file.txt");
        PrintWriter out = new PrintWriter ("src\\lab4_5\\output\\out");
        out.println("sdf");

        Scanner in = null;
        do {
           // fileName = terminalInput.nextLine();
            fileName = "in1";
            File path = new File("src\\lab4_5\\input\\" + fileName);
            try {
                in = new Scanner(path);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("NO SUCH FILE IN DIRECTORY src/lab4_5/input");
                e.printStackTrace();
            }
        } while (!fileName.equals("stop"));
        if (in == null)
            System.exit(0);
        final Scanner lin = in;
        Lock pauseMonitor = new ReentrantLock();
        ExecutorService mainPool = Executors.newFixedThreadPool(4);

        Runnable checkingStatusTask = () -> {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    String terminalCommand;
                    synchronized (terminalInput) {
                        terminalCommand = terminalInput.nextLine();
                    }
                    if (terminalCommand.equals("exit")){
                        pauseMonitor.lock();
                        mainPool.shutdown();
                        out.close ();
                        Thread.currentThread().interrupt();
                    }
                    if (terminalCommand.equals("pause")){
                        pauseMonitor.lock();
                        while (true){
                            try {
                                String terminalWaitFlag = terminalInput.nextLine();
                                if (terminalWaitFlag == "go"){
                                    break;
                                }
                            }
                            catch (NoSuchElementException ex){
                                try {
                                    Thread.sleep(100);
                                }
                                catch (InterruptedException exx){
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                        pauseMonitor.unlock();
                    }
                }
                catch (NoSuchElementException e){
                    try {
                        synchronized (System.out){
                            System.out.println("www");
                        }
                        Thread.sleep(100);
                    }
                    catch (InterruptedException intEx){
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        Thread checkingService = new Thread(checkingStatusTask);
        
        Runnable factorTask = () -> {
            while (true) {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        long val;
                        synchronized (lin) {
                            val = lin.nextLong();
                        }
                        LongFactorization fctr = new LongFactorization(val);
                        synchronized (out) {
                            out.println(fctr.toString());
                        }
                        synchronized (System.out){
                            System.out.println("DONE!");
                        }
                    }
                    if (pauseMonitor.tryLock()) {
                        pauseMonitor.wait();
                    }
                    else {
                        break;
                    }
                }
                catch (InterruptedException intEx) {
                }
            }
        };

        checkingService.start();
       /* Thread taskMaker = new Thread(factorTask);
        taskMaker.start();*/
        Future <?> res= mainPool.submit(factorTask);

        out.close ();
    }


}

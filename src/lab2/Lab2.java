package lab2;

import java.io.FileNotFoundException;
import java.util.TreeMap;

public class Lab2 {
    public static void main(String[] args) throws FileNotFoundException, InstantiationException, IllegalAccessException{
        StatisticMultiset <Double> set = new StatisticMultiset( Double.class);
        Class aa = Integer.class;
        set.addNum(1.0);
        set.addNum(1.0);
        set.addNum(2.0);
        set.addNum(3.0);
        set.addNum(3.0);
        System.out.println("GetMax: " + set.getMax());
        System.out.println("GetMin: " + set.getMin());
        System.out.println("GetAvg: " + set.getAvg());
        System.out.println("GetCU: " + set.getCountUnder(2.0));
        System.out.println("GetCU: " + set.getCountUnder(3.0));
        System.out.println("GetCU: " + set.getCountUnder(1.0));
        System.out.println("GetCA: " + set.getCountAbove(2.0));
        System.out.println("GetCA: " + set.getCountAbove(1.0));
        System.out.println("GetCA: " + set.getCountAbove(3.0));
        set.addNumsFromFile("a.in");
        System.out.println("GetCA: " + set.getCountAbove(3.0));
    }
}




package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticMultiset <T extends Number> implements Statistical, Cloneable {

    private TreeMap<T, Integer> data;
    private int size;
    private BigDecimal sum;
    private Class keyClass;
    private boolean hasChanged;
    private T maxLastRes;
    private T minLastRes;
    private T lastThreshold;
    private Integer aboveLastRes;
    private Integer underLastRes;
    private Double avgLastRes;

    public StatisticMultiset(Class keys) {
        data = new TreeMap<>();
        sum = new BigDecimal(0);
        keyClass = keys;
        hasChanged = false;
    }

  /*  public StatisticMultiset(Collection<Double> inputData){
        this();
        this.addNums(inputData);
    }*/

    /*private StatisticMultiset(int a, Collection<T> inputData) {
        Map<T, Integer> newMap = inputData.<T>stream().collect(Collectors.toMap(
                Function.identity(),
                i -> 1,
                Integer::sum));
        data = new TreeMap<T, Integer>(newMap);
        size = inputData.size();
        sum = new BigDecimal(inputData.stream().reduce(0.0,  (T i, T j) -> (Class<T>;(Double)i + (Double) j) ));
        /*BigDecimal curSum = new BigDecimal(0);
        for (Double curDouble: inputData){
            curSum.add(new BigDecimal(curDouble));
        }
        sum = new BigDecimal(
                inputData.stream().reduce().;
    }*/


    public void addNumsFromFile(String fileName) throws FileNotFoundException {
        File path = new File("src\\lab2\\input\\" + fileName);
        Scanner in = new Scanner(path);
        if (this.keyClass == Integer.class){
            addIntsFromIOStream(in);
            return;
        }
        if (this.keyClass == Double.class){
            addDoublesFromIOStream(in);
            return;
        }
        //ex
    }

    private void addNumsFromIOStream (Scanner input, Object val){
        System.out.println("wrong type");
    }

    @SuppressWarnings("unchecked")
    private void addIntsFromIOStream (Scanner input){
        while (input.hasNextInt()) {
            this.addNum((T)(Integer)input.nextInt());
        }
    }

    @SuppressWarnings("unchecked")
    private void addDoublesFromIOStream (Scanner input){
        while (input.hasNextDouble()){
            this.addNum((T)(Double)input.nextDouble());
        }
    }

    /*****************************************/

    private void update(Number newValue) {
        size++;
        sum = sum.add(new BigDecimal(newValue.doubleValue()));
    }

    @SuppressWarnings("unchecked")
    public void addNum(T newValue) {
        hasChanged = true;
        update(newValue);
        T num =  newValue;
        Integer v = data.putIfAbsent(num, 1);
        if (v == null) {
            return;
        }
        data.put(num, v + 1);
    }

   /* public void addNums(Collection <Double> inputData){
        for (Double curDouble : inputData){
            this.addNum(curDouble);
        }
    }*/

    @Override
    public T getMax() {
        if (!hasChanged && maxLastRes != null) return maxLastRes;
        hasChanged = false;
        maxLastRes = data.lastKey();
        return maxLastRes;
    }

    @Override
    public T getMin() {
        if (!hasChanged && minLastRes != null) return minLastRes;
        hasChanged = false;
        minLastRes = data.firstKey();
        return minLastRes;
    }

    @Override
    public double getAvg() {
        if (!hasChanged && avgLastRes != null) return avgLastRes;
        hasChanged = false;
        avgLastRes = sum.divide(new BigDecimal(size), MathContext.DECIMAL64).doubleValue();
        return avgLastRes;
    }


    public int getCountUnder(T threshold) {
        if (!hasChanged && underLastRes != null && lastThreshold.equals(threshold)) return underLastRes;
        hasChanged = false;
        lastThreshold = threshold;
        T upperBound = data.lowerKey(threshold);
        if (upperBound == null) return 0;
        int res = 0;
        Map<T, Integer> subRange = data.subMap(data.firstKey(), true, upperBound, true);
        for (Map.Entry<T, Integer> entry : subRange.entrySet()) {
            res += entry.getValue();
        }
        underLastRes = res;
        return res;
    }


    public int getCountAbove(T threshold) {
        if (!hasChanged && aboveLastRes != null && lastThreshold.equals(threshold)) return aboveLastRes;
        hasChanged = false;
        lastThreshold = threshold;
        T lowerBound = data.higherKey(threshold);
        if (lowerBound == null) return 0;
        int res = 0;
        Map<T, Integer> subRange = data.subMap(lowerBound, true, data.lastKey(), true);
        for (Map.Entry<T, Integer> entry : subRange.entrySet()) {
            res += entry.getValue();
        }
        aboveLastRes = res;
        return res;
    }
}

abstract class StatMLTSet<T> implements Statistical, Collection<T>, Cloneable, Set<T> {

}
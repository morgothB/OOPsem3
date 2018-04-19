package lab4_5;

import java.util.LinkedList;
import java.util.List;

public class LongFactorization implements Cloneable{
    private List <Long> dividers;
    private boolean primeFlag;
    private long longValue;

    protected LongFactorization(){}

    public LongFactorization(long val){
        longValue = val;
        if (val <= 0){
            primeFlag = false;
            return;
        }
        List <Long> newDivList = new LinkedList<>();
        FactorLib.factorize(val, newDivList);
        dividers = newDivList;
        longValue = val;
        primeFlag = (dividers.size() == 2 );
    }

    public LongFactorization(List <Long> newValues){
        dividers.addAll(newValues);
        if (dividers == null){
            primeFlag = false;
            longValue = 0;
            return;
        }
        primeFlag = dividers.size() <= 2;
        longValue = newValues.stream().reduce(Long::sum).get();
    }

    public List <Long> getDividers (){
        List <Long> res = new LinkedList<>();
        res.addAll(dividers);
        return res;
    }

    public long getValue (){
        return longValue;
    }

    public boolean isPreme (){
        return primeFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;
        LongFactorization val = (LongFactorization) o;
        return this.primeFlag == val.primeFlag
                && this.longValue == val.longValue
                && this.dividers.equals(val.dividers);
    }

    @Override
    public LongFactorization clone(){
        LongFactorization res = new LongFactorization();
        res.longValue = this.longValue;
        res.dividers.addAll(this.dividers);
        res.primeFlag = this.primeFlag;
        return res;
    }



    @Override
    public String toString() {
        if (dividers != null) {
            return dividers.toString();
        }
        else {
            return "Value is zero";
        }
    }
}

package testingfiles;

public class TestClass implements Comparable<TestClass>{
    public int val;

    public static int compareTo (TestClass a, TestClass b){
        return a.val > b.val ? -1 : ((a.val == b.val) ? 0 : 1);
    }

    @Override
    public int compareTo(TestClass o) {
        return this.val > o.val ? -1 : ((this.val == o.val) ? 0 : 1);
    }
}


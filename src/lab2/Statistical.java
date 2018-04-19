package lab2;

interface Statistical <T>{

  //  void  addNum(Number num);
   // void  addNum(Collection<? extends Number> inputData);
    //void  AddNum( const std::vector<int>& numbers );
    // void  AddNum( const std::list<int>& numbers );
    //void  AddNums( const StatisticMultiset& a_stat_set );
    //void  addNumsFromFile(String fileName);

    T getMax();

    T getMin();

    double getAvg();

   // int getCountUnder(T threshold);

  //  int getCountAbove(T threshold);
}

package npComplate2CAP;

import java.util.ArrayList;

public class SolutionTSP {
    private ArrayList <ArrayList <Float>> toTSP;
    private Integer numRows;

    private ArrayList <ArrayList <Integer>> salary;
    private ArrayList <Integer> cost;
    private ArrayList <Integer> result;
    private ExactSolution nextSet;

    public SolutionTSP(ArrayList <ArrayList <Float>> TSP) {
        nextSet = new ExactSolution();
        toTSP = TSP;
        numRows = toTSP.get(0).size()/2;
        salary = new ArrayList <ArrayList <Integer>>();
        cost = new ArrayList <Integer>();
        for (int i = 0; i < numRows; i++)
            cost.add(i);
        salary.add(nextSet.add(cost));
        salary.get(0).remove(0);
        ArrayList <Integer>  result = new ArrayList<Integer>();
    }

    public ArrayList<Integer> minTSP(){
        float min=100000;
        ArrayList <Integer>  a = new ArrayList<Integer>();

        a=nextSet.add(salary.get(0));
        while (nextSet.NextSet(a, numRows-1)) {
            salary.add(nextSet.add(a));
        }
        min = count(min);
        while (nextSet.NextSet(cost, numRows)){
            min = count(min);
        }
        return result;
    }

    public float count(float min){
        float count;
        for (int j=0; j<salary.size();j++){
            count = toTSP.get(0).get(cost.get(0)+numRows);
            ArrayList <Integer>  count_result = new ArrayList<Integer>();
            count_result.add(0);
            count_result.add(cost.get(0));
            for(int i=1; i<numRows;i++) {
                count += toTSP.get(salary.get(j).get(i-1)).get(cost.get(i)+numRows);
                count_result.add(salary.get(j).get(i-1));
                count_result.add(cost.get(i));
            }
            if(min>count) {
                min=count;
                result = nextSet.add(count_result);
            }
        }
        return min;
    }
}

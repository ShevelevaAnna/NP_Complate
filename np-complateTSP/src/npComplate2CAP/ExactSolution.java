package npComplate2CAP;
import java.util.*;
import java.lang.*;

public class ExactSolution {
    private Integer numRows;

    private ArrayList <ArrayList <Integer>> costs;
    private ArrayList <ArrayList <Integer>> salary;

    public ExactSolution(ArrayList <ArrayList <Integer>> theCost, ArrayList <ArrayList <Integer>> theSalary) {
        costs = theCost;
        salary = theSalary;
        numRows = costs.size();
    }

    public ExactSolution() {
    }

    public ArrayList <Integer> solution() {
        ArrayList <Integer>  a = new ArrayList<Integer>(numRows);
        ArrayList <ArrayList <Integer>> minSalary = new ArrayList<ArrayList<Integer>>();
        ArrayList <Integer> minCost;
        int min, count=0;

        for (int i = 0; i < numRows; i++)
            a.add(i);
        for(int j=0; j<numRows; j++)
            count += salary.get(j).get(a.get(j));
        min=count;
        minSalary.add(add(a));
        count = 0;

        while (NextSet(a, numRows))
        {
            for(int j=0; j<numRows; j++)
                count += salary.get(j).get(a.get(j));
            if (count == min)
                minSalary.add(add(a));
            if(count < min) {
                min=count;
                minSalary = new ArrayList<ArrayList<Integer>>();
                minSalary.add(add(a));
            }
            count = 0;
        }
        if(minSalary.size()==1) minCost=minSalary.get(0);
        else minCost=minCost(minSalary);
        return  minCost;
    }
    public ArrayList <Integer> add (ArrayList <Integer> a){
        ArrayList <Integer>  a1 = new ArrayList<Integer>();
        for (int i=0; i<a.size(); i++) a1.add(a.get(i));
        return a1;
    }
    public ArrayList <Integer> minCost(ArrayList <ArrayList <Integer>> minSalary){
        ArrayList <Integer> min_cost = new ArrayList<Integer>();
        int min = 100000, count=0;
        for (int i=0; i<minSalary.size();i++)
        {
            for(int j=0; j<minSalary.get(0).size();j++)
                count += salary.get(j).get(minSalary.get(i).get(j));
            if(count < min) {
                min_cost = add(minSalary.get(i));
                min=count;
            }
            count = 0;
        }
        return min_cost;
    }
    public void swap(ArrayList<Integer> a, int i, int j)
    {
        int s = a.get(i);
        a.add(i, a.get(j));
        a.remove(i+1);
        a.add(j,s);
        a.remove(j+1);
    }
    public boolean NextSet(ArrayList<Integer> a, int n)
    {
        int j = n - 2;
        while (j != -1 && a.get(j) >= a.get(j + 1)) j--;
        if (j == -1)
            return false;
        int k = n - 1;
        while (a.get(j) >= a.get(k)) k--;
        swap(a, j, k);
        int l = j + 1, r = n - 1;
        while (l<r) swap(a, l++, r--);
        return true;
    }
}
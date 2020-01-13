package npComplate2CAP;

import java.util.ArrayList;

public class ReductionToTSP {
    private Integer numRows;

    private ArrayList <ArrayList<Integer>> costs;
    private ArrayList <ArrayList <Integer>> salary;

    public ReductionToTSP(ArrayList <ArrayList <Integer>> theCost, ArrayList <ArrayList <Integer>> theSalary) {
        costs = theCost;
        salary = theSalary;
        numRows = costs.get(0).size();
    }

    public ArrayList <ArrayList<Float>> toTSP (){
        ArrayList <ArrayList<Float>> toTSP = new ArrayList<ArrayList<Float>>();
        ArrayList <Float>  strToTSP;
        for (int i=0; i<2*numRows; i++){
            strToTSP = new ArrayList<Float>(numRows);
            if(i<numRows)
                for (int j=0; j<2*numRows; j++){
                    if(j<numRows) strToTSP.add((float)100000);
                    else strToTSP.add((float)salary.get(i).get(j-numRows)+(float)0.1/costs.get(i).get(j-numRows));
                }
            else
                for (int j = 0; j < 2 * numRows; j++) {
                    if (j < numRows) strToTSP.add((float) 0);
                    else strToTSP.add((float) 100000);
                }
            toTSP.add(strToTSP);
        }

        return toTSP;
    }
}

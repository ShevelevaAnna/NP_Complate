import npComplate2CAP.ExactSolution;
import npComplate2CAP.ReductionToTSP;
import npComplate2CAP.SolutionTSP;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        int numberOfTasks = 8;
        for(int test = 0; test<10;test++){
            ArrayList <ArrayList <Integer>> costMatrix = new ArrayList <ArrayList <Integer>>();
            ArrayList <Integer>  cost;

            ArrayList <ArrayList <Integer>> salaryMatrix = new ArrayList <ArrayList <Integer>>();
            ArrayList <Integer>  salary;

            final Random random = new Random();

            // Создание задачи
            for(int i=0; i<numberOfTasks;i++){
                cost = new ArrayList<Integer>();
                salary = new ArrayList<Integer>();
                for (int j=0; j<numberOfTasks;j++){
                    cost.add(random.nextInt(100) + 1);
                    salary.add(random.nextInt(100) + 1);
                }
                costMatrix.add(add(cost));
                salaryMatrix.add(add(salary));
            }

            // решение 2-ЗОН
            ExactSolution CAP=new ExactSolution(costMatrix,costMatrix);
            ArrayList<Integer> result_2CAP = CAP.solution();
            String str_2CAP="";
            for(int i=0; i<result_2CAP.size();i++)
                str_2CAP += result_2CAP.get(i)+" ";

            // перевод в TSP
            ReductionToTSP toTSP = new ReductionToTSP(costMatrix,costMatrix);
            ArrayList<ArrayList<Float>> result_toTSP = toTSP.toTSP();

            // решение TSP
            SolutionTSP TSP=new SolutionTSP(result_toTSP);
            ArrayList<Integer> result_TSP = TSP.minTSP();
            String str_TSP="";
            for(int i=0; i<result_TSP.size();i++)
                str_TSP += result_TSP.get(i)+" ";

            // Запись в файл
            try(FileWriter writer = new FileWriter("document/solution_"+numberOfTasks+".txt", true)) {
                writer.write(str_2CAP);
                writer.append('\n');
                writer.write(str_TSP);
                writer.append('\n');
                writer.append('\n');
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public static ArrayList <Integer> add (ArrayList <Integer> a){
        ArrayList <Integer>  a1 = new ArrayList<Integer>();
        for (int i=0; i<a.size(); i++) a1.add(a.get(i));
        return a1;
    }
}

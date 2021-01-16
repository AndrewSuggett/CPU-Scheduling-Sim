import java.util.concurrent.TimeUnit;
import java.util.*;
public class CPU
{
    private Algorithms algorithm;
    private int cycles = 1;
    private double averageWaitTime;
    private double averageTurnAroundTime;
    private double averageResponseTime;
    
    public CPU(Algorithms a)
    {
        algorithm = a;
    }

    public void loop()
    {

        while(!algorithm.isEmpty())
        {
            //Waits one second 
            try{
                TimeUnit.SECONDS.sleep(1);
            }
            catch(Exception e)
            {
                System.out.println("Error:" + e);
            }
            
            //Get next proccess according to algorthim
            algorithm.nextProccess();
        }

        //Calculates the averages for wait time, turnaround and response time
        setAverages();
        
        System.out.println(showAverages());
    }

    public String getAverages()
    {
        String str = "\n" + algorithm.algorthimName() + ": " + showAverages() + "\n-------------------";
        return str;
    }
    
    
    //Prints out the averages
    private String showAverages()
    {

        String str = String.format("\nAverage wait time: %.02f, Average TurnAroundTime: %.02f, Average Response Time: %.02f",averageWaitTime, averageTurnAroundTime, averageResponseTime);
        return str;
    }

    private void setAverages()
    {
        
        ArrayList<Procces> finishedProcces = algorithm.getFinishedProcces();
        double totalWaitTime = 0.0;
        double totalTurnAroundTime = 0.0;
        double totalResponseTime = 0.0;
        
        //Gets the total
        for(int i = 0; i < finishedProcces.size(); i++)
        {
            Procces p = finishedProcces.get(i);
            System.out.println(p);
            totalTurnAroundTime += p.getTurnaroundTime();
            totalWaitTime += p.getWaitTime();
            totalResponseTime += p.getResponseTime();
        
        }

        //Calculates the average
        averageWaitTime = totalWaitTime / finishedProcces.size();
        averageTurnAroundTime = totalTurnAroundTime / finishedProcces.size();
        averageResponseTime = totalResponseTime / finishedProcces.size();
    }

}
import java.util.concurrent.TimeUnit;
import java.util.*;

import javax.swing.GroupLayout.SequentialGroup;

public class RoundRobin implements Algorithms
{
    private ArrayList<Procces> roundRobin = new ArrayList();
    private int numberOfProccess = 0;
    private String algorthimName = "Round Robin";
    
    private final int QUANTUM = 3; //Amount of time each proccess runs for until switching
    private int cycles = 1;
    
    //Used to tell what proccess is running
    private Procces currentProcces;
    private int currentProccesIndex = 0;
    
    private ArrayList<Procces> finishedProcess = new ArrayList();

    
    public RoundRobin (ArrayList<Procces> array)
    {
        for(int i = 0; i < array.size(); i++)
        {
            Procces p = new Procces(array.get(i));
            roundRobin.add(p);
            numberOfProccess ++;
        }
    
    }

    public Procces nextProccess()
    {
        int counter = 0;
        
        
        currentProcces = roundRobin.get(currentProccesIndex);
        
        //Used to return the proccess that is going to run
        Procces returnProcces = currentProcces;
        int oldIndex = currentProccesIndex;

        //Loops for the quantum time or until the proccess is done running
        while(currentProcces.burstTimeLeft() > 0 && counter < QUANTUM)
        {
            
            //Sets the response time of the proccess if the proccess has never been on the cpu
            if(currentProcces.getIntiBurstTime() == currentProcces.burstTimeLeft())
            {
                //Repsonse time is the wait time before the procces first gets time on the cpu
                currentProcces.setResponseTime(currentProcces.getWaitTime());
            }
            
            
            //Increase all wait time of proccess not running
            increaseWaitTime();

            //Reduce the burst time of the procces running
            currentProcces.reduceBurstTime();
            
            //Used to wait one second between a cycle for readability
            if(counter >= 1)
            {
                try{
                    TimeUnit.SECONDS.sleep(1);
                }
                catch(Exception e)
                {
                    System.out.println("Error:" + e);
                }
            }
            
            counter++;
            System.out.println("CPU Cycle " + cycles + ": Name: " + currentProcces.getName() + " Burst time Left: " + currentProcces.burstTimeLeft());
            cycles++;
        } 

        //Sets the currentProcces to the next procces to run
        getNextProcces();
        
        //Removes the current procces
        if(returnProcces.burstTimeLeft() <= 0)
        {
            Procces p = roundRobin.remove(oldIndex);
            finishedProcess.add(p);
            numberOfProccess -= 1;
        }

        //Makes sures the indes of the current procces is correct.
        currentProccesIndex = roundRobin.indexOf(currentProcces);

        return returnProcces;
          
    }

    public int numberOfProccess()
    {
        return numberOfProccess;
    }

    public boolean isEmpty()
    {
        if(numberOfProccess <= 0)
            return true;
        return false;
    }

    public int size()
    {
        return numberOfProccess();
    }

    public ArrayList<Procces> getFinishedProcces()
    {
        return finishedProcess;
    }

    public int getCycles()
    {
        return cycles;
    }

    public String algorthimName()
    {
        return algorthimName;
    }

    private void increaseWaitTime()
    {
        //Increase the wait time of all procces that aren't running
        for(int i = 0; i < roundRobin.size(); i++)
        {
            if(i != currentProccesIndex)
            {
                Procces p = roundRobin.get(i);
                p.increaseWaitTime();
            }
        }
        
    }

    private void getNextProcces()
    {
        //Gets the next proccess to run
        if(size() > 1)
        {
            currentProccesIndex += 1;
            
            if(currentProccesIndex >= size()) //If the index is greater than the size of the array go back to the start
            {
                currentProccesIndex = 0;
            }
            currentProcces = roundRobin.get(currentProccesIndex); 
        }
    }


}

import java.util.*;
public class FCFS implements Algorithms
{
    private LinkedList<Procces> fifo = new LinkedList();
    private int numberOfProccess = 0;
    
    private ArrayList<Procces> finishedProcess = new ArrayList();
    
    private int cycles = 1;
    
    private String algorthimName = "First Come First Serve";

    
    public FCFS (ArrayList<Procces> array)
    {
        //Fills linked list of proccess fifo
        for(int i = 0; i < array.size(); i++)
        {
            Procces p = new Procces(array.get(i));
            fifo.addLast(p);
            numberOfProccess++;
        }
    }

    public Procces nextProccess()
    {
            
            //If more than one proccess in the list then we increase each of the wait times of the other proccess by one
            if(fifo.size() > 1) 
            {
                increaseWaitTime();
            }
        
            Procces first = fifo.getFirst(); //Get first proccess in list
            first.reduceBurstTime();          //Reduce the proccess burst time by one
            
            if(first.burstTimeLeft() <= 0)       //If the burst time is zero remove proccess
            {
                
                first.setResponseTime(first.getWaitTime());
                first = fifo.removeFirst();
                
                finishedProcess.add(first); //Add process to array of finshedProcces

                numberOfProccess -= 1;
            }

            System.out.println("CPU Cycle " + cycles + ": Name: " + first.getName() + " Burst time Left: " + first.burstTimeLeft());
            cycles++;
            return first;


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
        for(int i = 1; i < fifo.size(); i++)
        {
            Procces p = fifo.get(i);
            p.increaseWaitTime();
        }
    }
}

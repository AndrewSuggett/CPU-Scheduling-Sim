import java.util.*;

public class Priority implements Algorithms
{
    private LinkedList<Procces> priorityList = new LinkedList();
    private int numberOfProccess = 0;
    private ArrayList<Procces> finishedProcess = new ArrayList();
    
    private int cycles = 1; //Keeps track of how many cycles it takes to finsh all proccess
    private String algorthimName = "Priority";

    
    public Priority (ArrayList<Procces> array)
    {
        
        Collections.sort(array, new SortbyPriority()); //Sorts the array by prioirty, lower the number higher the priority
        
        for(int i = 0; i < array.size(); i++)
        {
            Procces p = new Procces(array.get(i));
            priorityList.addLast(p);
            numberOfProccess++;
        }
    }

    public Procces nextProccess()
    {
            
              //If more than one proccess in the list then we increase each of the wait times of the other proccess by one
            if(priorityList.size() > 1) 
            {
                increaseWaitTime();
            }
        
            Procces first = priorityList.getFirst(); //Get first proccess in list
            first.reduceBurstTime();          //Reduce the proccess burst time by one
            if(first.burstTimeLeft() <= 0)       //If the burst time is zero remove proccess
            {
                
                first.setResponseTime(first.getWaitTime());
                first = priorityList.removeFirst();
                finishedProcess.add(first);
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
        for(int i = 1; i < priorityList.size(); i++)
        {
            Procces p = priorityList.get(i);
            p.increaseWaitTime();
        }
    }
    
    private class SortbyPriority implements Comparator<Procces> 
    { 
        public int compare(Procces a, Procces b) 
        { 
            return a.getPriority() - b.getPriority(); 
        } 
    }

    
}
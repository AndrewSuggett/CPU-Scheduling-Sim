import java.util.*;
public class SJF implements Algorithms
{
    private LinkedList<Procces> shortestList = new LinkedList();
    private int numberOfProccess = 0;
    private ArrayList<Procces> finishedProcess = new ArrayList();
    private int cycles = 1;
    private String algorthimName = "Shortest Job First";

    
    public SJF (ArrayList<Procces> array)
    {
        
        Collections.sort(array, new SortbyBurst()); //Sorts array by shortest time first

        for(int i = 0; i < array.size(); i++)
        {
            Procces p = new Procces(array.get(i));
            shortestList.addLast(p);
            numberOfProccess++;
        }
    }

    public Procces nextProccess()
    {
            
              //If more than one proccess in the list then we increase each of the wait times of the other proccess by one
            if(shortestList.size() > 1) 
            {
                increaseWaitTime();
            }
        
            Procces first = shortestList.getFirst(); //Get first proccess in list
            first.reduceBurstTime();          //Reduce the proccess burst time by one
            if(first.burstTimeLeft() <= 0)       //If the burst time is zero remove proccess
            {
                
                first.setResponseTime(first.getWaitTime());
                first = shortestList.removeFirst();
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
        for(int i = 1; i < shortestList.size(); i++)
        {
            Procces p = shortestList.get(i);
            p.increaseWaitTime();
        }
    }

    private class SortbyBurst implements Comparator<Procces> 
    { 
        public int compare(Procces a, Procces b) 
        { 
            return a.getIntiBurstTime() - b.getIntiBurstTime(); 
        } 
    } 
}

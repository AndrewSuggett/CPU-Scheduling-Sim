/*
    Name: Andrew Suggett
    Student #: 180169

    This is made to simulate cpu scheduling. Using FCFS, Priority, Round Robin and SJF.

    It assumes that all proccess arrive at the same time.

*/
import java.util.*;
public class CPUSchedulingMain
{
    public static void main(String[] args)
    {

        final int BURSTTIMERANGE = 10;
        final int PRIORITYRANGE = 3;

        ArrayList<Procces> array = new ArrayList();
        Random rand = new Random();

        //Creates three proccess with name p1, p2, p3 and random burstime and priority
        Procces p1 = new Procces("p1", rand.nextInt(BURSTTIMERANGE) + 1, rand.nextInt(PRIORITYRANGE));
        Procces p2 = new Procces("p2", rand.nextInt(BURSTTIMERANGE) + 1, rand.nextInt(PRIORITYRANGE));
        Procces p3 = new Procces("p3", rand.nextInt(BURSTTIMERANGE) + 1, rand.nextInt(PRIORITYRANGE));
        Procces p4 = new Procces("p4", rand.nextInt(BURSTTIMERANGE) + 1, rand.nextInt(PRIORITYRANGE));
        Procces p5 = new Procces("p5", rand.nextInt(BURSTTIMERANGE) + 1, rand.nextInt(PRIORITYRANGE));

        array.add(p1);
        array.add(p2);
        array.add(p3);
        array.add(p4);
        array.add(p5);

        //Prints out the all of proccess
        for(int i = 0; i < array.size(); i++)
        {
            Procces p = array.get(i);
            System.out.println("Name: " + p.getName() + " Total Burst Time: " + p.getIntiBurstTime() + " Priority: " + p.getPriority());
        }

        System.out.println("\n");

        //Creates each of the algorthims with the same proccess
        FCFS f = new FCFS(array);
        Priority p = new Priority(array);
        RoundRobin r = new RoundRobin(array);
        SJF s = new SJF(array);

        //Prints out the alogrithms running
        //First come first Serve
        System.out.println(f.algorthimName());
        System.out.println("----------------------");
        CPU c1 = new CPU(f);
        c1.loop();
        System.out.println("\n");

        //Priority
        System.out.println(p.algorthimName());
        System.out.println("----------------------");
        CPU c2 = new CPU(p);
        c2.loop();
        System.out.println("\n");

        //Shorest Job First
        System.out.println(s.algorthimName());
        System.out.println("----------------------");
        CPU c3 = new CPU(s);
        c3.loop();
        System.out.println("\n");

        //Round Robin
        System.out.println(r.algorthimName());
        System.out.println("----------------------");
        CPU c4 = new CPU(r);
        c4.loop();
        System.out.println("\n");

        //Prints out the averages of each alogrithm to compare
        System.out.println("Averages");
        System.out.println("---------------");
        System.out.println(c1.getAverages());
        System.out.println(c2.getAverages());
        System.out.println(c3.getAverages());
        System.out.println(c4.getAverages());


    }

}
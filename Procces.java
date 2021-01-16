//Class used to simulate a proccess
public class Procces
{
      private int burstTime;
      private int initBurstTime;
      private int waitTime = 0;
      private String name = "";
      private int turnAroundTime;
      private int responseTime;
      private int priority = 0;

      public Procces()
      {
          burstTime = 10;
      }

      public Procces(Procces p)
      {
          this.name = p.getName();
          this.burstTime = p.getIntiBurstTime();
          this.priority = p.getPriority();
          initBurstTime = burstTime;
          turnAroundTime = burstTime;
      }

      public Procces(String n, int burst)
      {
            name = n;
            burstTime = burst;
            turnAroundTime = burst;
            initBurstTime = burst;
      }

      public Procces(String n, int burst, int prior)
      {
            name = n;
            burstTime = burst;
            turnAroundTime = burst;
            initBurstTime = burst;
            priority = prior;

      }

      public int burstTimeLeft()
      {
          return burstTime;
      }

      public void reduceBurstTime()
      {
          //Reduce run time by one
          burstTime -= 1;
      }
      public void reduceBurstTime(int n)
      {
          //Reduce run time by specifed amount
          burstTime -= n;
      }

      public void increaseWaitTime()
      {
          waitTime += 1;
          turnAroundTime += 1;
      }
      public void setWaitTime(int n)
      {
            waitTime = n;
      }

      public int getWaitTime()
      {
          return waitTime;
      }

      public void setResponseTime(int time)
      {
            responseTime = time;
      }

      public int getResponseTime()
      {
          return responseTime;
      }

      public String toString()
      {
          String str = "Name: " + name + " TurnAroundTime :" + turnAroundTime + " WaitTime :" + waitTime + " ResponseTime: " + responseTime;
          return str;
      }

      public int getTurnaroundTime()
      {
          return turnAroundTime;
      }

      public String getName()
      {
          return name;
      }

      public int getIntiBurstTime()
      {
          return initBurstTime;
      }

      public int getPriority()
      {
            return priority;
      }

      public void setPriority(int p)
      {
        priority = p;
      }



}

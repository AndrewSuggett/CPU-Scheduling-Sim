
import java.util.*;
interface Algorithms
{
    public Procces nextProccess(); //Next proccess in Alogrthim
    public int numberOfProccess(); //Returns number of Proccess
    public  ArrayList<Procces> getFinishedProcces(); //List of finished proccess
    public int getCycles(); //Number of times the alorithm takes to completed all proccess
    public String algorthimName();


    public boolean isEmpty();
    public int size();

}
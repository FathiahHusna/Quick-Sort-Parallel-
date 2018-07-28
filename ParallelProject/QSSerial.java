
/**
 * FATHIAH HUSNA BINTI FIRDAUS 2016647044
 * NUR ALYA EZRIN BINTI MOHD SHAFIE 2016644988
 * CS2304A
 * QUICKSORT SERIAL
 */
import java.util.*;
import java.util.Random;
public class QSSerial
{
    static void swap(int[] data, int i, int j)
    {
            int tmp=data[i];
            data[i]=data[j];
            data[j]=tmp;
    }
        
    static int partition(int[] data, int start, int end)
    {
        if(start==end)
           return start;
        int pivot=data[end];
        int s=start-1;
        for(int i=start;i<end;i++)
            if(data[i]<=pivot)
                 swap(data,++s,i);
        swap(data,++s,end);
        return s;
    }
    
    static void quickSort(int[] data, int start, int end) 
    {
        if (end<=start)
            return;
        int s=partition(data,start,end);
        quickSort(data,start,s-1);
        quickSort(data,s+1,end);
    }
        
    static int[] randomList(int n,int k) 
    {
        Random random=new Random();
        int[] data=new int[n];
        for(int i=0;i<data.length;i++)
            data[i]=random.nextInt(k);
        return data;
    }
    
    public static void shuffleArray(int[] data) 
    {
        int n = data.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(data, i, change);
        }
    }
    
    public static void main(String[] args)
    {
        //int[] data={3,1,2,8,5,6};
        Scanner in = new Scanner(System.in);
        System.out.print("\nTotal input: ");
        int num = in.nextInt();
        int[] data = new int[num];
        
        //generate random numbers
        /*for (int i = 0; i < data.length; i++)
            data[i] = (int)(Math.random()*1000+1);*/
            
        //generate list of numbers
        for (int i = 0; i < data.length; i++)
            data[i] = i;

        System.out.println("List sblm random: ");
        for (int i = 0; i < data.length; i++)
        {
            System.out.print(data[i] + " ");
        }
        
        //arrange in random
        shuffleArray(data);
       
        //print unsorted numbers
        System.out.println("\nList 1 (before sorted): ");
        for (int i = 0; i < data.length; i++)
        {
            System.out.print(data[i] + " ");
        }
        
        //start the execution time
        double startTime = System.nanoTime();
        
        //start the sort
        quickSort(data,0,data.length-1);
        
        System.out.println("\n\nQUICK SORT IN SERIAL");
        //print sorted numbers
        System.out.println("List 2 (after sorted): ");
        for(int i:data)
            System.out.format("%d ",i);
            System.out.format("\n");
            int n=10000000;
            quickSort(randomList(n,1000000),0,n-1);
            

        //end of execution time
        double endTime = System.nanoTime();
        //calculate execution time
        double totalTime = (endTime - startTime) ;
        System.out.print("\nTotal execution time in nanoseconds: " + totalTime);
    }   
}

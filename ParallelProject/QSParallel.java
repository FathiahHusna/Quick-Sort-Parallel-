
/**
 * FATHIAH HUSNA BINTI FIRDAUS 2016647044
 * NUR ALYA EZRIN BINTI MOHD SHAFIE 2016644988
 * CS2304A
 * QUICKSORT PARALLEL
 */
import java.io.Console;
import java.util.Random;
import java.util.*;
public class QSParallel implements Runnable
{
    int[] data;
    int start,end;
    
        QSParallel(int[] data,int start,int end) 
        {
            this.data=data;
            this.start=start;
            this.end=end;
        }
        
        //implements the run method and override 
        //If this thread was constructed using a separate Runnable run object, then that Runnable object's run method is called
        public void run()
        {
            quickSort(this.data,this.start,this.end);
        }
        
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
            for (int i = 0; i < n; i++) 
            {
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
            //quickSort(data,0,data.length-1);
            
            System.out.println("\n\nQUICK SORT IN PARALLEL");
            //print the sorted numbers
            System.out.println("List 2 (after sorted): ");
            /*for(int i:data)
                System.out.format("%d ",i);
            System.out.format("\n");*/
            for (int i=0; i<data.length; i++)
                System.out.print(data[i] + " ");
            int n=10000000;
            data=randomList(n,1000000);
            
            //data=data,start=0,end=n-1
            int s=partition(data,0,n-1);
            
            //parallel decomposition
            
            //with two decomposition
            Thread t1=new Thread(new QSParallel(data,0,s-1));
            Thread t2=new Thread(new QSParallel(data,s+1,data.length-1));
            
            /*int a = (s-1)/2;
            int b = a+1;
            int c = (data.length-1)/2;
            int d = c+1;
            //with four decomposition
            Thread t1=new Thread(new QSParallel(data,0,a));
            Thread t2=new Thread(new QSParallel(data,b,s-1));
            Thread t3=new Thread(new QSParallel(data,s+1,c));
            Thread t4=new Thread(new QSParallel(data,d,data.length-1));*/
            
            //threads simultaneously execute
            //Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
            t1.start();
            t2.start();
            /*t3.start();
            t4.start();*/
            //conquer
            double startTimejoin = System.nanoTime();
            double endTime1=0, endTime2=0, endTime3=0, endTime4=0; 
            try {
                //Waits for this thread to die.
                t1.join();
                //endTime1 = System.nanoTime();
                t2.join();
                //endTime2 = System.nanoTime();
                //t3.join();
                //endTime3 = System.nanoTime();
                //t4.join();
                //endTime4 = System.nanoTime();
            }catch(InterruptedException e){
                System.out.println(e);
            }
            
            System.out.println("Thread1: " + t1); 
            System.out.println("Thread2: " + t2); 
            
            //end of execution time
            double endTime = System.nanoTime();
            //calculate the execution time
            double totalTime = endTime - startTime;
            /*double totalTime1 = endTime1 - startTimejoin;
            double totalTime2 = endTime2 - startTimejoin;
            double totalTime3 = endTime3 - startTimejoin;
            double totalTime4 = endTime4 - startTimejoin;*/
            
            
                
            System.out.print("\nTotal execution time in nanoseconds: " + totalTime);
            /*System.out.print("\nTotal execution time t1: " + totalTime1);
            System.out.print("\nTotal execution time t2: " + totalTime2);
            System.out.print("\nTotal execution time t3: " + totalTime3);
            System.out.print("\nTotal execution time t4: " + totalTime4);*/
        }
}

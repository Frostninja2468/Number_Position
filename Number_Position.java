import java.util.*;
class Number_Position
{
    static int[] rnAr;
    static int[] gnAr;
    static int cd=0, cp=0, no_len=0, cont=1;
    static Scanner s = new Scanner(System.in);
    static Random r = new Random();
        
    public static void main(String args[])    
    {
        String tab;
        boolean is_inv = false;
        
        //Entering no. length
        System.out.print("Enter the length of no. : ");
        do
        {
            is_inv = false;
            no_len=s.nextInt();
            if(no_len>=9 || no_len<0)
            {
                System.out.print("Try again\t\t: ");
                is_inv = true;
            }
        }while(is_inv);
        
        //setting tab
        if(no_len<=2)
            tab = "\t\t|";
        else
            tab = "\t|";
        s.nextLine();
        rnAr = new int[no_len];
        gnAr = new int[no_len];
        rn_gen();
        System.out.print("\nEnter a no. and if you want to exit, enter 0\t\t");
        int gue_no;
        System.out.print("\n|Sn |No.\t|Cd|Cp|\t\t");
        
        do
        {
            gue_no=s.nextInt();
            cd=0;
            cp=0;
            extr(gue_no);
            if(gue_no==1111)
            {
                for(int i = 0; i<no_len;i++)
                    System.out.print(rnAr[i]);
                System.out.print("\t\t\t\t");
                continue;
            }
            if(check(gue_no))
                continue;
            if(gue_no==0)
                break;
            cd_cp();
            System.out.format( "|%2d |%d%s%d |%d |\t\t",cont,gue_no,tab,cd,cp);
            cont++;
        }
        while(!(cd==cp && cd==no_len) || (gue_no==0));
        if(cd==cp && cd==no_len)
            System.out.println("congratulations you won in " + (cont-1) + " tries");
        else if(gue_no==0)
        {
            System.out.print("Oh, you lost, the no. was ");
            for(int i = 0; i<no_len;i++)
                System.out.println(rnAr[i]);
        }
    }
        
    public static void rn_gen()//generates the random no. to guess
    {
        boolean is_duplicate;
        for(int i = 0 ; i<no_len ; i++)
        {
            int temp_rnd, c=0, v=10;
            if(i==0)
            {
                c=1;
                v=9;
            }
            else
            {
                c=0;
                v=10;
            }
            do
            {
                temp_rnd = r.nextInt(v) + c;
                is_duplicate = false;
                for(int j = 0 ; j<i ; j++)
                {
                    if(rnAr[j]==temp_rnd)
                    {
                        is_duplicate = true;
                        break;
                    }    
                    
                }
            }
            while(is_duplicate);
            rnAr[i]=temp_rnd;
        }
    }
    
    public static void cd_cp()//checking for correction
    {
        int i=0;
        int j=0;
        for(i = 0 ; i<no_len ; i++)
        {
            for(j = 0 ; j<no_len ; j++)
            {
                if(gnAr[i]==rnAr[j])
                {
                    cd++;
                    if(gnAr[i]==rnAr[i])
                    {
                        cp++;
                        continue;
                    }
                }
            }
        }
    }
    
    public static void extr(int gn)//extract the digits of gn to the ArrayList gnAr
    {
        for(int i = no_len-1, j = 0; i>=0 ; i--, j++)
        {
            int a = (int)Math.floor((gn/Math.pow(10,i))%10);
            gnAr[j]=a;
        }
    }
    public static boolean check(int gn)//checking if the input is correct
    {
        boolean is_inv = false;
        
        duplicate_loop:
        for(int i = 0; i < no_len ; i++)
        {
            if(gn==0)
                break;
            
            if(gn<Math.pow(10,(no_len-1)))
            {
                is_inv = true;
                System.out.print("No. is too small\t\t");
                break duplicate_loop;
                
            }
            
            if(gn>Math.pow(10,(no_len)))
            {
                is_inv = true;
                System.out.print("No. is too big\t\t\t");
                break duplicate_loop;
            }
            
            for(int j = 0 ; j<=i ; j++)
            {
                if(i==j)
                    continue;
                if(gnAr[i]==gnAr[j])
                {
                    System.out.print("No duplicate digits allowed\t");
                    is_inv = true;
                    break duplicate_loop;
                }
            }
        }
        return is_inv;
    }
}

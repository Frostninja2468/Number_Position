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
            if(no_len>9 || no_len<1)
            {
                System.out.print("Try again\t\t: ");
                is_inv = true;
            }
        }while(is_inv);

        //setting the necessary variables
        if(no_len<=2)
            tab = "\t\t|";
        else
            tab = "\t|";
        s.nextLine();
        rnAr = new int[no_len];
        gnAr = new int[no_len];

        if(no_len == 4)
            rn_gen_4();
        else
            rn_gen_not_4();

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

    public static void rn_gen_4()//
    {
        int wa5, wi5, won, ta5 = 0, ti5 = 0, ton = 0, rd = r.nextInt(6);
        if(rd  == 0)
        {
            wa5 = 1;
            wi5 = 2;
            won = 1;
        }
        else if(rd == 1)
        {
            wa5 = 2;
            wi5 = 1;
            won = 1;
        }
        else if(rd == 2)
        {
            wa5 = 1;
            wi5 = 1;
            won = 2;
        }
        else if(rd == 3)
        {
            wa5 = 0;
            wi5 = 2;
            won = 2;
        }
        else if(rd == 4)
        {
            wa5 = 2;
            wi5 = 0;
            won = 2;
        }
        else
        {
            wa5 = 2;
            wi5 = 2;
            won = 0;
        }
        System.out.println("Random" + rd);

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
                boolean a5 = true, i5 = true, on = true;
                temp_rnd = r.nextInt(v) + c;
                System.out.println(temp_rnd);
                
                if(temp_rnd >= 1 && temp_rnd <= 4)
                    a5 = ta5 <= wa5;
                if(temp_rnd >= 5 && temp_rnd <= 8)
                    i5 = ti5 <= wi5;
                if(temp_rnd == 0 || temp_rnd == 9)
                    on = ton <= won;
                
                is_duplicate = false;
                if(a5 && i5 &&  on)
                {
                    for(int j = 0 ; j<i ; j++)
                    {
                        if(rnAr[j]==temp_rnd)
                        {
                            is_duplicate = true;
                            break;
                        }    
                    }
                }
            }
            while(is_duplicate);
            System.out.println("selected : " + temp_rnd);
            rnAr[i] = temp_rnd;
            if(rnAr[i] >=1 && rnAr[i] <= 4)
                ta5++;
            else if(rnAr[i] >= 5 && rnAr[i] <= 8)
                ti5++;
            else
                ton++;
        }
            System.out.println(ta5 + " " + ti5 + " " + ton);
    }
        
    public static void rn_gen_not_4()//generates the random no. that is not 4 digits long to guess
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
            System.out.println(temp_rnd);

            rnAr[i] = temp_rnd;
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
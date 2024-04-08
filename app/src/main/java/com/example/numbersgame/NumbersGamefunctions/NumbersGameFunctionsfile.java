package com.example.numbersgame.NumbersGamefunctions;

import java.util.Arrays;

public class NumbersGameFunctionsfile {
   static int number;
    public NumbersGameFunctionsfile(int num) {
         number =num;
        System.out.println("armstrong");

    }


    public static int arm(){
        System.out.println("main called");

        int result=0;
    int sum=0;
    int temp = number;    //153
    while (temp > 0) { // 153>0 yes
        int rem = temp % 10; // 153%10=       rem=3
        sum = sum + (rem * rem * rem);  // 0+(3*3*3)=27    sum=27
        temp = temp / 10;
        //  153/10=15    temp=15
    }
    sum = sum + (temp * temp * temp);
        System.out.println("main called 1");

        if (sum == number) { //
        System.out.println("The number given " + number + " is ArmStrong number");
        result= 1;
    } else {
        System.out.println("The number given " + number + " is not ArmStrong number");
        result=0;
    }
    return result;
//compares sum with the number
}

    public static String prime(){
        int i=1;
        String prim = "";
        int count=0; //count t collect divisor coz prime no has 2 divisor eg,7 has 1,7 it is used to store no of divisor
        while ( i <= number){ //num=7 i=1 1<=7
            if (number%i==0){ //7%1==0 means 7 dividing 1 has rem 0 then they got 1 divisor
                count++; //count=1
            }
            i++; //oterwise 1 goes to 2 to 7
        }
        if (count==2){
            System.out.println(number+ "  prime number");
            prim=(number+" is A Prime Number");
        }
        else {
            System.out.println(number+"  not prime number ");
            prim=(number+" is Not A Prime Number");
        }


        return prim ; }

        public int[] Fibonacci(){
            int[] myArray = new int[number];
            int n1 = 0, n2 = 1, n3, j;
            System.out.print(n1 + " " + n2);//printing 0 and 1
            myArray[0]=0;
            myArray[1]=1;

            for (j = 2; j < number ; j++)
            {
                n3 = n1 + n2;// 0+1=1   1+1=2
                System.out.print(" " + n3);//1 2
                myArray[j]=n3;
                n1 = n2; //0 1
                n2 = n3;//1  2
            }

        return myArray;}

        public String Palindrome(){
        int n=number;
        String boo="";
            int r,sum=0,temp;

            temp=n;
            while(n>0){
                r=n%10;  //getting remainder
                sum=(sum*10)+r;
                n=n/10;
            }

            if(temp==sum)  {//454==454
                System.out.println("palindrome number ");
                boo= number+" Is A Palindrome !!!"; }
            else{
                System.out.println("not palindrome");
                boo= number+" Is Not A Palindrome !!!";}
        return boo;
        }

        public String  Perfect(){
            int num=number;
            int i=1;
            int sum=0;
            String boo="";
            while (i <= num / 2) {
                if (num % i == 0) {
                    //calculates the sum of factors
                    sum = sum + i;
                } //end of if
                // after each iteration, increments the value of variable i by 1
                i++;
            } //end of while
            if (sum == num) {
                //prints if sum and n are equal
                System.out.println(num + " is a perfect number.");
                boo=+number+" is A Perfect Number !!";
            } //end of if
            else{
                //prints if sum and n are not equal
                System.out.println(num + " is not a perfect number.");
                boo=number+" is Not A Perfect Number !!";
            }

        return boo;
    }

        public String Factorial(double D_number){
            int fc=1;
            double num=D_number;

            while (number>0){
                fc=fc*number;
                number--;
            }
            System.out.println("the factorial of is "+fc);
            return "The Factorial of "+num+" is : "+fc;
    }
}

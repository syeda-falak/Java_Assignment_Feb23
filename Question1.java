import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question1 {
    public static void main(String args[]){


        String s;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number: ");
        s = scanner.nextLine();

        BigInteger n = new BigInteger(s);
        List<BigInteger> list = new ArrayList<>();
        var temp = n;

        for(var i=n; i.compareTo(new BigInteger("0"))!=0; ){
            if(checkPrime(i)){
                list.add(i);
                i = n.subtract(i);
                n= i;
            }
            else i= i.subtract(new BigInteger("1"));
        }
        System.out.println(list);
    }

    private static boolean checkPrime(BigInteger num){

        if(num.compareTo(new BigInteger("1"))==0)
        {
            return true;
        }
        for(BigInteger i= BigInteger.valueOf(2);  i.compareTo(num.divide(new BigInteger("2")))<0; i=i.add(new BigInteger("1")))
        {
            if((num.mod(i)).compareTo(new BigInteger("0")) == 0)
                return  false;
        }
        return true;
    }
}

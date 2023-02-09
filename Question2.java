
import java.util.*;

public class Question2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++) {
            a[i]=sc.nextInt();
        }
        int first[]=new int[n/2];
        int second[]=new int[n/2];
        int sum1=0,sum2=0;
        Arrays.sort(a);
        int x=0,y=0;
        first[x++]=a[n-1];
        sum1=a[n-1];
        second[y++]=a[n-2];
        sum2=a[n-2];
        for(int i=n-3;i>=0;i--) {
            if(sum1>sum2) {
                if(y<=n/2) {
                    sum2=sum2+a[i];
                    second[y]=a[i];
                    y++;
                }
            }
            else {
                if(x<=n/2) {
                    sum1=sum1+a[i];
                    first[x]=a[i];
                    x++;
                }
            }
        }
        int i=0;
        x=0;
        y=0;
        while(x<n/2) {
            a[i++]=second[x++];
        }
        while(y<n/2) {
            a[i++]=first[y++];
        }
        for(int k=0;k<n;k++) {
            System.out.print(a[k]+" ");
        }
        System.out.println();
        sc.close();
    }


}


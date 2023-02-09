import java.util.*;

public class Question1 {
    static boolean[][] dp;

    private static void display(ArrayList<Integer> p) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<p.size();i++) {
            sb.append(p.get(i)+"+");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }

    static void printSubsetsRec(int arr[], int i, int sum, ArrayList<Integer> p) {
        if (i == 0 && sum != 0 && dp[0][sum]) {
            p.add(arr[i]);
            display(p);
            System.exit(0);
            p.clear();
            return;
        }
        if (i == 0 && sum == 0) {
            display(p);
            System.exit(0);
            p.clear();
            return;
        }
        if (dp[i - 1][sum]) {
            ArrayList<Integer> b = new ArrayList<>();
            b.addAll(p);
            printSubsetsRec(arr, i - 1, sum, b);
        }
        if (sum >= arr[i] && dp[i - 1][sum - arr[i]]) {
            p.add(arr[i]);
            printSubsetsRec(arr, i - 1, sum - arr[i], p);
        }
    }

    static void printAllSubsets(int arr[], int n, int sum) {
        if (n == 0 || sum < 0)
            return;

        dp = new boolean[n][sum + 1];
        for (int i = 0; i < n; ++i) {
            dp[i][0] = true;
        }

        if (arr[0] <= sum)
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; ++i)
            for (int j = 0; j < sum + 1; ++j)
                dp[i][j] = (arr[i] <= j) ? (dp[i - 1][j] || dp[i - 1][j - arr[i]]) : dp[i - 1][j];
        if (dp[n - 1][sum] == false) {
            System.out.println("There are no subsets with" + " sum " + sum);
            return;
        }
        ArrayList<Integer> p = new ArrayList<>();
        printSubsetsRec(arr, n - 1, sum, p);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = generatePrimes(n);
        System.out.println(list);
        int a[]=new int[list.size()];
        for(int i=0;i<list.size();i++) {
            a[i]=list.get(i);
        }
        printAllSubsets(a,list.size(), n);
        sc.close();
    }


    public static List<Integer> generatePrimes(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        for (int i = 3; i <= n; i++) {

            if (checkPrime(i)) {
                list.add(i);
            }

        }
        return list;
    }

    private static boolean checkPrime(int x) {
        if (x % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(x); i += 2) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}



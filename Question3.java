import java.util.*;

public class Question3 {
    public static int evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        Stack<Integer> values = new Stack<Integer>();
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i] == ' ')
                continue;

            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer sbuf = new StringBuffer();

                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.toString()));

                i--;
            }

            else if (tokens[i] == '(')
                ops.push(tokens[i]);

            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                ops.push(tokens[i]);
            }
        }
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        return values.pop();
    }
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    public static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a mathematical expression: ");
        String expression = sc.nextLine();
        String str=expression.substring(0, expression.lastIndexOf('='));
        StringBuilder sb=new StringBuilder(str);
        int total=Integer.parseInt(expression.substring(expression.indexOf('=')+1));
        String resStr=Solution(str,sb,total);
        resStr+=expression.substring(expression.lastIndexOf('='));
        System.out.println(resStr);
//		System.out.println(evaluate(str));
        sc.close();
    }
    private static String Solution(String str, StringBuilder sb,int res) {
        // TODO Auto-generated method stub
        int op=0,cl=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='(') {
                op++;
            }
            else if(str.charAt(i)==')') {
                cl++;
            }
        }
        boolean open=false,close=false;

        if(Math.abs(cl-op)==1) {
            if(cl>op) {
                open=true;
            }
            else {
                close=true;
            }
        }
        for(int i=0;i<str.length();i++) {
            if(close && op!=cl) {
                sb=new StringBuilder(str);
                if(sb.charAt(i)!='('  && sb.charAt(i)!='+' && sb.charAt(i)!='-' && sb.charAt(i)!='*' && sb.charAt(i)!='/') {
                    sb.insert(i+1, ')');
//					System.out.println(sb);
                    try {
                        int temp=evaluate(sb.toString());
                        if(temp==res) {
                            return sb.toString();
                        }
                    }
                    catch(Exception e) {

                    }
                }
            }
            if(open && op!=cl) {
                sb=new StringBuilder(str);
                if(sb.charAt(i)!=')'  && sb.charAt(i)!='+' && sb.charAt(i)!='-' && sb.charAt(i)!='*' && sb.charAt(i)!='/') {
                    sb.insert(i, '(');
                    System.out.println(sb);
                    try {
                        int temp=evaluate(sb.toString());
                        if(temp==res) {
                            return sb.toString();
                        }
                    }
                    catch(Exception e) {

                    }
                }
            }
        }
        return null;
    }
}


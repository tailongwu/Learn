import java.util.Scanner;
import java.util.Stack;

class Evaluate {

    Stack<String> ops = new Stack<>();//操作符栈
    Stack<Double> vals = new Stack<>();//操作数栈

    public double doEvaluate(Scanner sca) {
        while (sca.hasNext()) {
            //扫描输入缓存区
            String s = sca.next();
            if (s.equals("=")) {
                //"="则跳出循环
                break;
            }
            if (s.equals("(")) {
                //如果是左括号，什么也不干
            }else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                System.out.println("操作符" + s + "入栈了");
                ops.push(s);
            }else if (s.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) {
                    v = vals.pop() + v;
                }else if (op.equals("-")) {
                    v = vals.pop() - v;
                }else if (op.equals("*")) {
                    v = vals.pop() * v;
                }else if (op.equals("/")) {
                    v = vals.pop() / v;
                }
                vals.push(v);
                System.out.println("运算结果" + v + "入栈了");
            }else {
                vals.push(Double.parseDouble(s));
                System.out.println("操作数" + Double.parseDouble(s) + "入栈了");
            }
        }
        return vals.pop();
    }

    public static void main(String[] args) {
        Evaluate eva = new Evaluate();

        System.out.println("请输入一个算术表达式，字符之间请以空格隔开，例如： （ 1.2 + 2.3 ） = ");
        Scanner s = new Scanner(System.in);
        double result = eva.doEvaluate(s);
        System.out.println("最终运算结果是：" + result);
    }
}
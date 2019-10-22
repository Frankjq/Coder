## 224. Basic Calculator

> Implement a basic calculator to evaluate a simple expression string.
> The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

operation:  +, -, ()

#### stack, saing sign and result

```java
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int sign = 1;
        int operand = 0;
        
        for(int i = 0; i< s.length(); i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                operand =  operand * 10 + (int)(ch-'0'); 
            }
            else if(ch == '+'){
                result += sign * operand;
                operand = 0;
                sign = 1;
            }
            else if(ch == '-'){
                result += sign * operand;
                operand = 0;
                sign = -1;
            }
            else if(ch == '('){
                stack.push(result);
                stack.push(sign);
                
                operand = 0;
                result = 0;
                sign = 1;
                
            }
            else if(ch == ')'){
                result += sign * operand;
                result *= stack.pop();
                result += stack.pop();
                operand = 0;
            }
        }
        
        return result + operand * sign;  
    }
}
```

## 227. Basic Calculator II

> Implement a basic calculator to evaluate a simple expression string.
operation: + - * /

#### stack save all number

```java
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int operand = 0;
        char sign = '+';
        for(int i = 0; i< s.length(); i++){
            char ch = s.charAt(i);
            if(ch == ' ' && i!=s.length()-1) continue;
            if(Character.isDigit(ch)){
                operand = operand * 10 + (int)(ch-'0');
            }
            
            if(!Character.isDigit(ch) || i == s.length()-1){
                if(sign == '+'){
                    stack.push(operand);
                }else if(sign == '-'){
                    stack.push(operand*(-1));
                }
                else if(sign == '*'){
                    stack.push(stack.pop()*operand);
                }
                else if(sign == '/'){
                    stack.push(stack.pop()/operand);
                }
                
                sign = ch;
                operand = 0;
            }
        }
        
        int res = 0;
       for(int num: stack){
            res += num;
        }
        
        return res;
        
    }
}
```

## 772. Basic Calculator III

operation + - * / ()

#### stack

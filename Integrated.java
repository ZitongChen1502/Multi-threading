public class Integrated extends Thread{
    String[]in;
    String[]convert;
    String[]out;
    public Integrated(String[]input){
        in = input;
        convert = new String[in.length];
        out=new String[in.length];
    }
    public void run() {
        System.out.println("Start converting to postfix.-single");
        for (int i = 0; i < in.length; i++) {
            convert[i]=ShuntingYard(in[i]);
        }
        System.out.println("Conversion to postfix completed.-single");
        System.out.println("Start calculation.-single");
        for (int i = 0; i < convert.length; i++) {
            out[i]=calculate(convert[i]);
        }
        System.out.println("Calculation completed.-single");
        System.out.println("Start printing results.-single");
        for (int i = 0; i < in.length; i++) {
            System.out.println(out[i]+"-single");
        }
        System.out.println("Printing results completed.-single");

    }
    public String ShuntingYard(String formula){
        QueueLL Queue = new QueueLL();
        StackLL Stack = new StackLL();
        String number = "0123456789";
        String digit;
        Node n;
        for(int i =0; i<formula.length();i++){
            digit = formula.substring(i,i+1);
            boolean isNum = false;
            for(int j=0; j<10;j++){
                if(digit.equals(number.substring(j,j+1))){
                    isNum = true;
                    break;
                }
            }
            if(isNum){
                n = new Node(digit);
                Queue.enqueue(n);
            }
            else {
                if (Stack.isEmpty())
                {
                    Stack.push(new Node(digit));
                }
                else{
                    if (digit.equals("(")) {
                        Stack.push(new Node(digit));
                    }
                    else if(Stack.peek().equals("(")) {
                        Stack.push(new Node(digit));
                    }
                    else if (digit.equals(")")) {
                        String move = (String) Stack.peek();
                        while (move!=null && move.equals("(")==false) {
                            Queue.enqueue(new Node(move));
                            Stack.pop();
                            move = (String) Stack.peek();
                        }
                        Stack.pop();
                    } else if (digit.equals("*") || digit.equals("/")) {
                        Stack.push(new Node(digit));
                    }
                    else {
                        String move = (String) Stack.peek();
                        while (move!=null && !move.equals("(")) {
                            Queue.enqueue(new Node(move));
                            Stack.pop();
                            move = (String) Stack.peek();
                        }
                        Stack.push(new Node(digit));
                    }
                }
            }

        }
        while(!Stack.isEmpty()){
            Queue.enqueue(new Node(Stack.peek()));
            Stack.pop();
        }
        return Queue.display();

    }

    public String calculate(String postFix){
        StackLL stack = new StackLL();
        String nums = "0123456789";
        for(int i =0; i<postFix.length();i++){
            String digit = postFix.substring(i,i+1);
            boolean isNum = false;
            for(int j=0; j<10;j++){
                if(digit.equals(nums.substring(j,j+1))){
                    isNum = true;
                    break;
                }
            }
            if(isNum){
                stack.push(new Node(postFix.substring(i,i+1)));
            }
            else{
                double a = Double.parseDouble(stack.pop().getData().toString());
                double b = Double.parseDouble(stack.pop().getData().toString());
                if(postFix.substring(i,i+1).equals("+")){
                    String value = Double.toString(a+b);
                    stack.push(new Node(value));
                }
                else if(postFix.substring(i,i+1).equals("-")){
                    String value = Double.toString(b-a);
                    stack.push(new Node(value));
                }
                else if (postFix.substring(i,i+1).equals("/")){
                    String value = Double.toString(b/a);
                    stack.push(new Node(value));
                }
                else{
                    String value = Double.toString(a*b);
                    stack.push(new Node(value));
                }
            }
        }
        return stack.display();
    }


}

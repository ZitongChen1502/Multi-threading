public class Main {
    //This app is a multi-layer multi-thread calculator in which the input is a
    // set of normal math formula and the export is the results.
    //I let the multi-threaded version run along side with the single-threaded version
    // to determine their running speed. When the program finishes, there will
    //be a note"Printing results completed" + "-single/multi".
   // If -multi appears before single(single-thread continues to run after
    //multi-thread finished, we see that in this app multi-threading increases
    //the efficiency of the program.
    //P.S. I used wait() and a lock instead of a join() method in the multi-thread,
    // because there are 3 threads in the multi-threading class.
    //One for inport, one for convert to postfix, and one for calculation.
    //A lock is much easier to manage for me.
    //Also, I commented out the printing things. However, if you would love
    //to see an insane code run-through, feel free to add back!
    //Enjoy!
    public static void main(String[] args) {
        Thread single =new Thread(new Runnable() {
            @Override
            public void run() {
                String[] formula = new String[1000000];
                System.out.println("Start inputting.-single");
                for (int i = 0; i < 1000000; i++) {
                    formula[i] = "(3+5)/(2+2)+2";
                }
                System.out.println("Inputting completed.-single");
                String[] result = new String[1000000];
                Integrated singleT = new Integrated(formula);
        singleT.start();
        }
    });
        Thread multi = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] formula = new String[1000000];
                String[] converted = new String[1000000];
                Object Lock = new Object();
                Thread cre = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        synchronized (Lock) {
                            System.out.println("Start inputting.-multi");
                            for (int i = 0; i < 1000000; i++) {
                                formula[i] = "(3+5)/(2+2)+2";
                            }
                            System.out.println("Inputting completed.-multi");
                        }
                    }
                });
                Thread con = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (Lock) {
                            System.out.println("Start converting to postfix.-multi");
                            for (int i = 0; i < formula.length; i++) {
                                while (formula[i] == null) {
                                    try {
                                        Lock.wait();
                                    } catch (InterruptedException e) {
                                        System.out.println(e);
                                    }
                                }
                                Lock.notify();
                                converted[i] = ShuntingYard(formula[i]);

                            }
                            System.out.println("Conversion to postfix completed.-multi");
                        }
                    }
                });
                Thread cal = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (Lock) {
                            String[] out = new String[1000000];
                            System.out.println("Start calculation.-multi");
                            for (int i = 0; i < converted.length; i++) {
                                while (converted[i] == null) {
                                    try {
                                        Lock.wait();
                                    } catch (InterruptedException e) {
                                        System.out.println(e);
                                    }
                                }
                                Lock.notify();
                                out[i] = calculate(converted[i]);
                            }
                            System.out.println("Calculation completed.-multi");
                            System.out.println("Start printing results.-multi");
                            for (int i = 0; i < out.length; i++) {
                                System.out.println(out[i]+"-multi");
                            }
                            System.out.println("Printing results completed.-multi");
                        }
                    }
                });
                cre.start();
                con.start();
                cal.start();
            }
        });
        single.start();
        multi.start();
    }
    public static String ShuntingYard(String formula){
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
    public static String calculate(String postFix){
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

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by slav9nin on 16.05.2015.
 */
public class SearchPalindrome extends JFrame{

    private final JLabel label;
    private Palindrome result;
    SearchPalindromeWork worker = new SearchPalindromeWork();

    private ArrayList<Palindrome> palindromes = new ArrayList<>();

    public SearchPalindrome() {
        super("SearchPalindrome");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        final JPanel panel = new JPanel(new SpringLayout());
        label = new JLabel("Palindrome will be here!!!");
//        label.setPreferredSize(new Dimension(250,250));
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);
        add(panel);

        setBounds(300,300, 300, 300);
        setVisible(true);

        worker.execute();
        label.setText("Wait for a worker do its job...");
    }

    public Palindrome getResult() {
        return result;
    }

    private String reverse(String source) {
        char[] arr = source.toCharArray();
        int N = arr.length;
        for( int i = 0; i < N/2; i++) {
            char temp = arr[i];
            arr[i] = arr[N-i-1];
            arr[N-i-1] = temp;
        }
        return new String(arr);
    }

    private void searchPalindromePrimitive() {
        int n1 = 999, n2 = 999;

        for (int i = n1; i >= 100; i--) {
            for (int j = n2; j >= 100; j--) {
                int num = i*j;
                String res = String.valueOf(num);
                String isPalindrome = reverse(res);
                if (isPalindrome.equals(res)) {
                    Palindrome p = new Palindrome(num, i, j);
                    palindromes.add(p);
                }
            }
        }

        Palindrome result = new Palindrome(0, 0, 0);
        for(Palindrome p : palindromes) {
            if(p.palindrome > result.palindrome) {
                result.setNumber1(p.getNumber1());
                result.setNumber2(p.getNumber2());
                result.setPalindrome(p.getPalindrome());
            }
        }

        this.result = result;

        StringBuilder output = new StringBuilder();
        output.append("Palindrome: ").append(result.getPalindrome())
                .append("\n").append("Number 1 : ")
                .append(result.getNumber1()).append("\n")
                .append("Number 2: ").append(result.getNumber2());
        label.setText(output.toString());
    }

    private void searchPalindromeSmart() {
        int n1 = 999, n2 = 999;

        for (int i = n1; i >= 900; i--) {
            for (int j = n2; j >= 100; j--) {
                int num = i*j;
                String res = String.valueOf(num);
                String isPalindrome = reverse(res);
                if (isPalindrome.equals(res)) {
                    Palindrome p = new Palindrome(num, i, j);
                    palindromes.add(p);
                }
            }
        }

        Palindrome result = new Palindrome(0, 0, 0);
        for(Palindrome p : palindromes) {
            if(p.palindrome > result.palindrome) {
                result.setNumber1(p.getNumber1());
                result.setNumber2(p.getNumber2());
                result.setPalindrome(p.getPalindrome());
            }
        }

        this.result = result;

        StringBuilder output = new StringBuilder();
        output.append("Palindrome: ").append(result.getPalindrome())
                .append("\n").append("Number 1 : ")
                .append(result.getNumber1()).append("\n")
                .append("Number 2: ").append(result.getNumber2());
        label.setText(output.toString());
    }

    private class Palindrome {

        private long palindrome;
        private int number1, number2;
        public Palindrome(long palindrome, int num1, int num2) {
            this.palindrome = palindrome;
            this.number1 = num1;
            this.number2 = num2;
        }

        public long getPalindrome() {
            return palindrome;
        }

        public int getNumber2() {
            return number2;
        }

        public int getNumber1() {
            return number1;
        }

        public void setPalindrome(long palindrome) {
            this.palindrome = palindrome;
        }

        public void setNumber1(int number1) {
            this.number1 = number1;
        }

        public void setNumber2(int number2) {
            this.number2 = number2;
        }
    }

    private class SearchPalindromeWork extends SwingWorker<String,String> {

        @Override
        protected String doInBackground() throws Exception {
            //searchPalindromePrimitive();
            searchPalindromeSmart();
            publish("Palindrome is found");
            return String.valueOf(SearchPalindrome.this.getResult().palindrome);
        }

        @Override
        protected void done() {
            StringBuilder output = new StringBuilder();
            output.append("Palindrome: ").append(result.getPalindrome())
                    .append("\n").append("Number 1 : ")
                    .append(result.getNumber1()).append("\n")
                    .append("Number 2: ").append(result.getNumber2());
            label.setText(output.toString());
        }

        @Override
        protected void process(List<String> chunks) {
            label.setText(chunks.get(0));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SearchPalindrome();
            }
        });
    }
}

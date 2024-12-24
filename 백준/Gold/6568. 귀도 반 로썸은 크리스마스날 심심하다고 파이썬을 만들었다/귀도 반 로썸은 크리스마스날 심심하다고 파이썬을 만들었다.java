import java.io.*;

public class Main {
    static int[] memo;
    static int pc, adder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        memo = new int[32];
        while (true) {
            pc = 0;
            adder = 0;
            for (int n = 0; n < 32; n++) {
                if ((input = br.readLine()) == null) {
                    System.out.println(sb);
                    return;
                }
                memo[n] = Integer.parseInt(input, 2);
            }
            tc:
            while (true) {
                int cmd = memo[pc] >> 5;
                int val = memo[pc] & 31;

                switch (cmd) {
                    case 0:
                        memo[val] = adder;
                        pc++;
                        break;
                    case 1:
                        adder = memo[val];
                        pc++;
                        break;
                    case 2:
                        if (adder == 0) pc = val;
                        else pc++;
                        break;
                    case 3:
                        pc++;
                        break;
                    case 4:
                        adder = (adder + 255) % 256;
                        pc++;
                        break;
                    case 5:
                        adder = (adder + 1) % 256;
                        pc++;
                        break;
                    case 6:
                        pc = val;
                        break;
                    case 7:
                        sb.append(String.format("%8s", Integer.toBinaryString(adder)).replace(' ', '0')).append("\n");
                        break tc;
                }
                pc %= 32;
            }
        }
    }
}
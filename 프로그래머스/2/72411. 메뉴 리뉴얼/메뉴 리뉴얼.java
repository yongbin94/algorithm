import java.io.*;
import java.util.*;

class Solution {
    static int[] O, C, max;
    static List<Integer>[] list;
    public String[] solution(String[] orders, int[] course) {
        max = new int[27];
        list = new ArrayList[27];
        for(int i = 0; i <= 26; i++)
            list[i] = new ArrayList<>(); 
        O = new int[orders.length];
        C = course;
        for(int i = 0; i < orders.length; i++) {
            int bit = 0;
            for(char c : orders[i].toCharArray())
                bit += 1 << (c - 'A');
            O[i] = bit;
        }
        System.out.println(26);
        recur(0,0, 0);
        
        int size = 0, idx = 0;
        for(List<Integer> l : list)
            size += l.size();
        
        String[] answer = new String[size];
        for(List<Integer> l : list)
            for(int bit : l) {
                StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < 26; i++) {
                        if(((bit >> i) & 1) == 1)
                            sb.append((char)('A' + i));
                }
                answer[idx++] = sb.toString();
            }
        Arrays.sort(answer);
        return answer;
    }
    static void recur(int cnt, int start, int bit) {
        for(int c : C) {
            if(c == cnt) {
                int tmp = 0;
                for(int o : O) {
                    if((bit & o) == bit)
                        tmp++;
                }
                if(tmp >= 2 && max[cnt] <= tmp) {
                    if(max[cnt] < tmp) {
                        max[cnt] = tmp;
                        list[cnt] = new ArrayList<>();
                    }
                    list[cnt].add(bit);
                }
                if(c == C[C.length - 1])
                    return;
                break;
            }
        }
        for(int i = start; i < 26; i++) 
            recur(cnt + 1, i + 1, bit + (1 << i));
    }
}
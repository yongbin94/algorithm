class Solution {
    public String solution(String new_id) {
        String answer = "";
        char[] A = new_id.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : A) {
            if (ch >= 'A' && ch <= 'Z')
                ch = (char) (ch - 'A' + 'a');
            
            if (!(ch >= 'a' && ch <= 'z') && !(ch >= '0' && ch <= '9') && !(ch == '-' || ch == '_' || ch == '.'))
                continue;
            
            if(ch == '.'  && (sb.length() == 0 || sb.charAt(sb.length() - 1) == '.'))
                continue;
            
            sb.append(ch);
        }
        
        if(sb.length() > 15) {
            sb.delete(15, sb.length());
        }
        
        for (int i = sb.length() - 1; i >= 0; i--) {
            if(sb.charAt(i) == '.')
                sb.deleteCharAt(i);
            else
                break;
        }
        
        if (sb.length() == 0)
            sb.append('a');
        
        while(sb.length() <= 2)
            sb.append(sb.charAt(sb.length() - 1));
        
        return sb.toString();
    }
}
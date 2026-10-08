class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb=new StringBuilder();
        Stack<Character> st=new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='('){
                if(st.size()>0){
                    sb.append(c);
                }
                st.push(c);
            }else{
                st.pop();
                if(st.size()>0){
                    sb.append(c);
                }
            }
        }
        return new String(sb);
    }
}
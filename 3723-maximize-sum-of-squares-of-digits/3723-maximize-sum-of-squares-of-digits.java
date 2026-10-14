class Solution {
    public String maxSumOfSquares(int num, int sum) {
        int drevantor=num;
        if(sum>9*num || sum<1){
            return "";
        }
        int[] digits=new int[num];

        int remaining=sum;
        for(int i=0;i<num;i++){
            digits[i]=Math.min(9,remaining);
            remaining-=digits[i];
        }
        StringBuilder res=new StringBuilder();
        for(int digit:digits){
            res.append(digit);
        }
        return res.toString();
    }
}
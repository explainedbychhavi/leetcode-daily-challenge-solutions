class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
       // int Ax1=rec1[0];
       // int Ay1=rec1[1];
       // int Ax2=rec1[2];
       // int Ay2=rec1[3];
       // int Bx1=rec2[0];
       // int By1=rec2[1];
       // int Bx2=rec2[2];
       // int By2=rec2[3];
        // if(Ax1==Ax2 || Ay1==Ay2 || Bx1==Bx2 || By1==By2) return false;
        return!(rec1[2]<=rec2[0] || rec1[3]<=rec2[1] || rec1[0]>=rec2[2] || rec1[1]>=rec2[3]);
    }
}
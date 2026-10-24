class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int startAlice = getDays(arriveAlice);
        int endAlice = getDays(leaveAlice);
        int startBob = getDays(arriveBob);
        int endBob = getDays(leaveBob);
        int start = Math.max(startAlice, startBob);
        int end = Math.min(endAlice, endBob);
        if (start > end) {
            return 0;
        }
        return end - start + 1;
    }
    public int getDays(String date) {
        int[] days = {31, 28, 31, 30, 31, 30,31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));  // 08-15 --227
        int total = day;
        for (int i = 0; i < month - 1; i++) {
            total += days[i];
        }
        return total;
    }
}

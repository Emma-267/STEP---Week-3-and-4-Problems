//Risk Threshold Binary Lookup
public class RiskThresholdSearch {

    public static int floor(int[] sorted, int target) {
        int low = 0, high = sorted.length - 1;
        int floor = Integer.MIN_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sorted[mid] <= target) {
                floor = sorted[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return floor == Integer.MIN_VALUE ? -1 : floor;
    }

    public static int ceiling(int[] sorted, int target) {
        int low = 0, high = sorted.length - 1;
        int ceil = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sorted[mid] >= target) {
                ceil = sorted[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ceil == Integer.MAX_VALUE ? -1 : ceil;
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};

        int floor30 = floor(risks, 30);
        int ceil30 = ceiling(risks, 30);
        System.out.println("Binary floor(30): " + floor30 + ", ceiling: " + ceil30);
    }
}
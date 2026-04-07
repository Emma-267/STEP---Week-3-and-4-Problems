import java.util.*;

//Account ID Lookup in Transaction Logs
public class TransactionSearch {

    public static int linearSearchFirst(String[] logs, String target) {
        for (int i = 0; i < logs.length; i++)
            if (logs[i].equals(target)) return i;
        return -1;
    }

    public static int linearSearchLast(String[] logs, String target) {
        for (int i = logs.length - 1; i >= 0; i--)
            if (logs[i].equals(target)) return i;
        return -1;
    }

    public static int binarySearch(String[] logs, String target) {
        Arrays.sort(logs);
        int low = 0, high = logs.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static int countOccurrences(String[] logs, String target) {
        int count = 0;
        for (String log : logs)
            if (log.equals(target)) count++;
        return count;
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};

        int first = linearSearchFirst(logs, "accB");
        int last = linearSearchLast(logs, "accB");
        System.out.println("Linear first accB: index " + first);
        System.out.println("Linear last accB: index " + last);

        int idx = binarySearch(Arrays.copyOf(logs, logs.length), "accB");
        int count = countOccurrences(logs, "accB");
        System.out.println("Binary accB: index " + idx + ", count=" + count);
    }
}
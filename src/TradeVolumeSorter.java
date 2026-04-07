import java.util.*;

//Historical Trade Volume Analysis
public class TradeVolumeSorter {

    static class Trade {
        String id;
        int volume;

        public Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    public static void mergeSort(List<Trade> trades) {
        if (trades.size() <= 1) return;
        int mid = trades.size() / 2;
        List<Trade> left = new ArrayList<>(trades.subList(0, mid));
        List<Trade> right = new ArrayList<>(trades.subList(mid, trades.size()));
        mergeSort(left);
        mergeSort(right);
        merge(trades, left, right);
    }

    private static void merge(List<Trade> trades, List<Trade> left, List<Trade> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).volume <= right.get(j).volume) trades.set(k++, left.get(i++));
            else trades.set(k++, right.get(j++));
        }
        while (i < left.size()) trades.set(k++, left.get(i++));
        while (j < right.size()) trades.set(k++, right.get(j++));
    }

    public static void quickSortDesc(List<Trade> trades, int low, int high) {
        if (low < high) {
            int pi = partition(trades, low, high);
            quickSortDesc(trades, low, pi - 1);
            quickSortDesc(trades, pi + 1, high);
        }
    }

    private static int partition(List<Trade> trades, int low, int high) {
        int pivot = trades.get(high).volume;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (trades.get(j).volume >= pivot) {
                i++;
                Collections.swap(trades, i, j);
            }
        }
        Collections.swap(trades, i + 1, high);
        return i + 1;
    }

    public static void main(String[] args) {
        List<Trade> trades = Arrays.asList(
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        );
        List<Trade> mergeList = new ArrayList<>(trades);
        mergeSort(mergeList);
        System.out.println("MergeSort: " + mergeList);

        List<Trade> quickList = new ArrayList<>(trades);
        quickSortDesc(quickList, 0, quickList.size() - 1);
        System.out.println("QuickSort (desc): " + quickList);

        int totalVolume = trades.stream().mapToInt(t -> t.volume).sum();
        System.out.println("Total volume: " + totalVolume);
    }
}
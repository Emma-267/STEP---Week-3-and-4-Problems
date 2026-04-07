import java.util.*;

//Portfolio Return Sorting
public class PortfolioSorter {

    static class Asset {
        String name;
        double returnRate;
        double volatility;

        public Asset(String name, double returnRate, double volatility) {
            this.name = name;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        @Override
        public String toString() {
            return name + ":" + returnRate + "%";
        }
    }

    public static void mergeSort(List<Asset> assets) {
        if (assets.size() <= 1) return;

        int mid = assets.size() / 2;
        List<Asset> left = new ArrayList<>(assets.subList(0, mid));
        List<Asset> right = new ArrayList<>(assets.subList(mid, assets.size()));

        mergeSort(left);
        mergeSort(right);
        merge(assets, left, right);
    }

    private static void merge(List<Asset> assets, List<Asset> left, List<Asset> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).returnRate <= right.get(j).returnRate) assets.set(k++, left.get(i++));
            else assets.set(k++, right.get(j++));
        }
        while (i < left.size()) assets.set(k++, left.get(i++));
        while (j < right.size()) assets.set(k++, right.get(j++));
    }

    public static void quickSortDesc(List<Asset> assets, int low, int high) {
        if (low < high) {
            int pi = partition(assets, low, high);
            quickSortDesc(assets, low, pi - 1);
            quickSortDesc(assets, pi + 1, high);
        }
    }

    private static int partition(List<Asset> assets, int low, int high) {
        Asset pivot = assets.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (assets.get(j).returnRate > pivot.returnRate ||
                    (assets.get(j).returnRate == pivot.returnRate && assets.get(j).volatility < pivot.volatility)) {
                i++;
                Collections.swap(assets, i, j);
            }
        }
        Collections.swap(assets, i + 1, high);
        return i + 1;
    }

    public static void main(String[] args) {
        List<Asset> portfolio = Arrays.asList(
                new Asset("AAPL", 12, 20),
                new Asset("TSLA", 8, 30),
                new Asset("GOOG", 15, 25)
        );

        List<Asset> mergeList = new ArrayList<>(portfolio);
        mergeSort(mergeList);
        System.out.println("MergeSort: " + mergeList);

        List<Asset> quickList = new ArrayList<>(portfolio);
        quickSortDesc(quickList, 0, quickList.size() - 1);
        System.out.println("QuickSort (desc+volatility): " + quickList);
    }
}
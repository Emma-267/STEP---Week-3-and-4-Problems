import java.util.*;

//Client Risk Score Ranking
public class ClientRiskSorter {

    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        public Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return name + ":" + riskScore;
        }
    }

    public static void bubbleSortByRisk(List<Client> clients) {
        int n = clients.size();
        int swaps = 0;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (clients.get(j).riskScore > clients.get(j + 1).riskScore) {
                    Collections.swap(clients, j, j + 1);
                    swaps++;
                }
        System.out.println("Bubble (asc): " + clients + " , Swaps: " + swaps);
    }

    public static void insertionSortDescRisk(List<Client> clients) {
        int n = clients.size();
        for (int i = 1; i < n; i++) {
            Client key = clients.get(i);
            int j = i - 1;
            while (j >= 0 &&
                    (clients.get(j).riskScore < key.riskScore ||
                            (clients.get(j).riskScore == key.riskScore &&
                                    clients.get(j).accountBalance < key.accountBalance))) {
                clients.set(j + 1, clients.get(j));
                j--;
            }
            clients.set(j + 1, key);
        }
        System.out.println("Insertion (desc): " + clients);
    }

    public static void topNHighRisk(List<Client> clients, int n) {
        System.out.print("Top " + n + " risks: ");
        for (int i = 0; i < Math.min(n, clients.size()); i++) {
            System.out.print(clients.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Client> clients = Arrays.asList(
                new Client("clientC", 80, 1000),
                new Client("clientA", 20, 500),
                new Client("clientB", 50, 2000)
        );

        bubbleSortByRisk(new ArrayList<>(clients));
        insertionSortDescRisk(new ArrayList<>(clients));
        topNHighRisk(clients, 3);
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type number of clasters: ");
        int k = scanner.nextInt();

        // Load observations from a CSV file
        List<Observation> observations = loadSet("data/iris.csv");
        List<Cluster> clusters = new ArrayList<>();

        // Initialize clusters with the first k observations
        for (int i = 0; i < k; i++) {
            clusters.add(new Cluster(observations.get(i)));
        }

        // Assign the remaining observations to random clusters
        for (int i = k; i < observations.size(); i++) {
            clusters.get(new Random().nextInt(clusters.size())).addObservation(observations.get(i));
        }

        boolean centroidChanged = true;
        while(centroidChanged) {
            Map<Cluster, double[]> centroids = new HashMap<>();
            centroidChanged = false;

            // Calculate centroids for each cluster
            for (Cluster cluster : clusters) {
                cluster.getDistances();
                centroids.put(cluster, cluster.getCentroid());
            }

            // Assign each observation to the closest cluster
            for (Observation observation : observations) {
                centroids = centroids.entrySet().stream()
                        .sorted((c1, c2) -> {
                            if (observation.getDistance(c1.getValue()) <= observation.getDistance(c2.getValue())) return -1;
                            else return 1;
                        })
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (oldV, newV) -> oldV, LinkedHashMap::new));

                Cluster closest = centroids.entrySet().iterator().next().getKey();

                if (closest.getObservations().contains(observation)) continue;
                else{
                    for (Cluster cluster : clusters) {
                        cluster.getObservations().remove(observation);
                    }
                    closest.addObservation(observation);
                    centroidChanged = true;
                }
            }
            System.out.println();
        }

        // Print the resulting clusters
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("\nGroup " + (i + 1) + ": ");
            for (Observation observation : clusters.get(i).getObservations())
                System.out.println(observation.toString());
        }

    }

    // Load observations from a CSV file
    private static ArrayList<Observation> loadSet(String fileName) {
        ArrayList<Observation> dataSet = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double[] vector = new double[values.length - 1];

                for (int i = 0; i < values.length - 1; i++) {
                    vector[i] = Double.parseDouble(values[i]);
                }
                dataSet.add(new Observation(vector));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSet;
    }
}

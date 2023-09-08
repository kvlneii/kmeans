import java.util.ArrayList;
import java.util.List;

class Cluster {
    List<Observation> observations = new ArrayList<>();
    public Cluster(Observation observation){
        addObservation(observation);
    }
    public List<Observation> getObservations() {
        return observations;
    }
    public void addObservation(Observation observation) {
        observations.add(observation);
    }

    // Calculate the centroid of the cluster
    public double[] getCentroid(){
        double[] sum = new double[observations.get(0).getCoordinates().length];
        for (Observation observation : observations) {
            for (int j = 0; j < observation.getCoordinates().length; j++) {
                sum[j] += observation.getCoordinates()[j];
            }
        }
        for (int i = 0; i < sum.length; i++) {
            sum[i] /= observations.size();
        }

        return sum;
    }

    // Calculate the sum of distances from the centroid for the observations in the cluster
    public void getDistances(){
        double sum = 0;
        double[] centroid = getCentroid();
        for (Observation observation : observations) {
            for (int i = 0; i < observation.getCoordinates().length; i++) {
                sum += Math.pow((centroid[i] - observation.getCoordinates()[i]), 2);
            }
        }
        System.out.println("Sum of distances => " + sum + ", group size: " + observations.size());
    }
}
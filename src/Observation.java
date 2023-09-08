public class Observation {
    private double[] coordinates;
    public Observation(double[] coordinates) {
        this.coordinates = coordinates;
    }
    public double[] getCoordinates() {
        return coordinates;
    }

    // Calculate the Euclidean distance between this observation and a centroid
    public double getDistance(double[] centroid) {
        double sum = 0;
        for (int i = 0; i < coordinates.length; i++) {
            sum += Math.pow((centroid[i] - coordinates[i]), 2);
        }

        return sum;
    }

    // Convert the observation to a string
    public String toString(){
        StringBuilder text = new StringBuilder();
        for (Double d : coordinates){
            text.append(d).append(",");
        }
        return text.toString();
    }
}
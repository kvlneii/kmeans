# K-Means Clustering Algorithm
This program implements the K-Means clustering algorithm to group data from a CSV file into k clusters. The program calculates and displays information about the cluster assignments and the sum of squared distances within clusters after each iteration of the algorithm.

## Implementation Details
* The program uses the K-Means clustering algorithm to group observations into clusters.
* It initializes clusters with the first k observations.
* The remaining observations are assigned to random clusters.
* It iteratively updates cluster assignments and calculates the sum of squared distances within clusters until convergence.
* The results, including cluster assignments and sum of squared distances, are displayed after each iteration.


## Input Data
The program loads observations from a CSV file. Each row in the CSV file represents an observation, with each column containing attribute values. The program is designed to be flexible and work with data of varying dimensions.


## How to Use
To use this program, follow these steps:
1) Clone this repository or download the source code files.
2) Compile the Java code. You can use the following command in your terminal or command prompt:
    ```bash
    javac Main.java
4) Run the compiled program:
    ```bash
    java Main
5) Follow the on-screen prompts to specify the number of clusters (k).

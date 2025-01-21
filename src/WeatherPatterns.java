import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author YOUR NAME HERE
 */

public class WeatherPatterns {


    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        int size = temperatures.length;
        ArrayList<Integer>[] adjacencyList = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            adjacencyList[i] = new ArrayList<Integer>();
        }
        // For each temp, look through all temps before it
        // If a temp before it is less than the current temperature, add it to the current temp's adjacency list
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                // If the current temp is less than the temp at i, add the edge to adjacency list
                if (temperatures[j] < temperatures[i]) {
                    adjacencyList[i].add(j);
                }
            }
        }
        int longest = 0;
        int[] longestPaths = new int[size];
        // Loop through each temp and recurse to find the longest path to that temp
        for (int i = 0; i < size; i++) {
            int longestPathTo = longestPathTo(adjacencyList, i, longestPaths);
            // Update the longest total path
            longest = Math.max(longest, longestPathTo);
        }
        return longest;
    }
    public static int longestPathTo(ArrayList<Integer>[] adjacencyList, int vertex, int[] longestPaths) {
        // Obtain the connections of the current vertex
        ArrayList<Integer> connections = adjacencyList[vertex];
        int len = 0;
        for (int connectingVertex : connections) {
            // Check if the longest path to the connecting vertex is already known
            if (longestPaths[connectingVertex] != 0) {
                // Use the known longest path and take the max
                len = Math.max(len, longestPaths[connectingVertex]);
            }
            // Otherwise, recurse to find the longest path to the current vertex
            else {
                len = Math.max(len, longestPathTo(adjacencyList, connectingVertex, longestPaths));
            }
        }
        // Add 1 for edge to current vertex
        return len + 1;
    }
}

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
        // Create a map of path lengths for efficient lookups
        int[] longestPaths = new int[size];
        // Loop through each temp and recurse to find the longest path to that temp
        for (int i = 0; i < size; i++) {
            int longestPathTo = longestPathTo(adjacencyList, i, longestPaths);
            // Update the global longest increasing trend
            longest = Math.max(longest, longestPathTo);
        }
        return longest;
    }

    /**
     * Given a vertex, finds the longest path to that vertex by recursively finding the longest paths to all
     * the adjacent vertices
     * @param adjacencyList The list of all adjacent vertices to the current vertex
     * @param vertex The index of the current vertex
     * @param longestPaths The map of known path lengths using memoization
     * @return The length of the longest path to the current vertex
     */
    public static int longestPathTo(ArrayList<Integer>[] adjacencyList, int vertex, int[] longestPaths) {
        // Check if the longest path to the connecting vertex is already known
        if (longestPaths[vertex] != 0) {
            return longestPaths[vertex];
        }
        int longestPath = 0;
        // Obtain the connections of the current vertex
        ArrayList<Integer> connections = adjacencyList[vertex];
        for (int connectingVertex : connections) {
            // Find the longest path to each adjacent vertex
            int pathLength = longestPathTo(adjacencyList, connectingVertex, longestPaths);
            // Update our total longest path length to the given vertex
            longestPath = Math.max(longestPath, pathLength);
        }
        // Add longest path to current vertex to our map, including step to move to current vertex
        longestPaths[vertex] = longestPath + 1;
        // Return longest path to current vertex
        return longestPath + 1;
    }
}

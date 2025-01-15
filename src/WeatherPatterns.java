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
        int[] runs = new int[size];
        // Set the run length of the first index to 1
        runs[0] = 1;
        int maxRun = Integer.MIN_VALUE;
        for (int i = 1; i < size; i++) {
            int max = Integer.MIN_VALUE;
            // Loop through each index before the current one
            // Find the max run of the indices before
            for (int j = 0; j < i; j++) {
                // If the current temp is greater than temp at i, move past it
                if (temperatures[j] > temperatures[i]) {
                    break;
                }
                // If the current run is greater than the max run, update the max run before current
                if (runs[j] > max) {
                    max = runs[j];
                }
            }
            int currentRun = max + 1;
            runs[i] = currentRun;
            maxRun = Math.max(currentRun, maxRun);

        }
        return maxRun;
    }
}

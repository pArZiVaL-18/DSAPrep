public class _0042_trapping_rainwater {

    // Calculates the total amount of trapped rainwater given the elevation map
    public static int trapRainWater(int elevationMap[]) {
        int n = elevationMap.length;
        // Calculate the maximum height to the left of each bar
        int leftMax[] = new int[n];
        leftMax[0] = elevationMap[0];
        for(int i = 1; i < n; i++) {
            leftMax[i] = Math.max(elevationMap[i], leftMax[i - 1]);
        }

        // Calculate the maximum height to the right of each bar
        int rightMax[] = new int[n];
        rightMax[n - 1] = elevationMap[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(elevationMap[i], rightMax[i + 1]);
        }

        int totalTrappedWater = 0;
        // Calculate trapped water at each index
        for(int i = 0; i < n; i++) {
            // Water level is determined by the shorter of the two boundaries
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            // Trapped water is water level minus the height at current index
            totalTrappedWater += waterLevel - elevationMap[i];
        }
        return totalTrappedWater;
    }

    public static void main(String args[]){
        int elevationMap1[] = {4, 2, 0, 6, 3, 2, 5};
        int elevationMap2[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}; // Additional test case
        System.out.println("Trapped water (test case 1): " + trapRainWater(elevationMap1));
        System.out.println("Trapped water (test case 2): " + trapRainWater(elevationMap2));
    }
}
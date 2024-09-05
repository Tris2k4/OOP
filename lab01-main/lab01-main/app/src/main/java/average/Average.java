package average;

public class Average {
    /**
     * Returns the average of an array of numbers
     *
     * @param nums the array of integer numbers
     * @return the average of the numbers
     */
    public float computeAverage(int[] nums) {
        float result = 0;
        // Add your code
        for (int num : nums) {
            result += num;
        }
        return result / nums.length;
    }

    public static void main(String[] args) {
        // Add your code
        int[] arr = {1, 2, 3, 4, 5, 6};
        Average average = new Average();
        System.out.println("The average is " + average.computeAverage(arr));
    }
}

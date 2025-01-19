package Stockly.src.components;

import java.util.Arrays;

public class MergeSort {

    public static String[] mergeSortByNumber(String[] unsortedArr) {
        // Return array when all data are in their own array
        if (unsortedArr.length <= 1) {
            return unsortedArr;
        }

        // Split array
        int middle = unsortedArr.length / 2;
        String[] leftHalf = Arrays.copyOfRange(unsortedArr, 0, middle);
        String[] rightHalf = Arrays.copyOfRange(unsortedArr, middle, unsortedArr.length);

        // Recursively sort both halves
        String[] sortedLeft = mergeSortByNumber(leftHalf);
        String[] sortedRight = mergeSortByNumber(rightHalf);

        // Merge the sorted halves
        return mergeByNumber(sortedLeft, sortedRight);
    }

    // Compare and merge array by number
    public static String[] mergeByNumber(String[] left, String[] right) {
        String[] result = new String[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (extractNumber(left[i]) <= extractNumber(right[j])) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    // Extract number from string
    private static double extractNumber(String str) {
        String numberStr = str.replaceAll("[^\\d.]", "");
        if (numberStr.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(numberStr);
    }


    public static String[] mergeSort(String[] unsortedArr) {
        // Return array when all data are in their own array
        if (unsortedArr.length <= 1) {
            return unsortedArr;
        }
    
        // Split array
        int middle = unsortedArr.length / 2;
        String[] leftHalf = Arrays.copyOfRange(unsortedArr, 0, middle);
        String[] rightHalf = Arrays.copyOfRange(unsortedArr, middle, unsortedArr.length);
    
        // Recursively sort both halves
        String[] sortedLeft = mergeSort(leftHalf);
        String[] sortedRight = mergeSort(rightHalf);
    
        // Merge the sorted halves
        return merge(sortedLeft, sortedRight);
    }
    
    // Compare and merge array
    public static String[] merge(String[] left, String[] right) {
        String[] result = new String[left.length + right.length];
        int i = 0, j = 0, k = 0;
    
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
    
        while (i < left.length) {
            result[k++] = left[i++];
        }
    
        while (j < right.length) {
            result[k++] = right[j++];
        }
    
        return result;
    }
}

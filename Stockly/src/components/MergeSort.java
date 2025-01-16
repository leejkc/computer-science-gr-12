package Stockly.src.components;

import java.util.Arrays;

public class MergeSort {

    public static int[] mergeSort(int[] unsortedArr) {
        //return array when all data are in it own array
        if (unsortedArr.length <= 1) {
            return unsortedArr;
        }

        //split array
        int middle = unsortedArr.length / 2;
        int[] leftHalf = Arrays.copyOfRange(unsortedArr, 0, middle);
        int[] rightHalf = Arrays.copyOfRange(unsortedArr, middle, unsortedArr.length);

        //move to sort method
        int[] sortedLeft = mergeSort(leftHalf);
        int[] sortedRight = mergeSort(rightHalf);

        return merge(sortedLeft, sortedRight);
    }

    //compare and merge array
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
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

import java.util.Arrays;

class Sort{
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
}


public class ExapleMergeSort {
    public static void main(String[] args) {
        int[] demo= new int[5];
        demo[0]=6;
        demo[1]=3;
        demo[2]=7;
        demo[3]=9;
        demo[4]=2;
        int[] sortedDemo=Sort.mergeSort(demo);

        for (int i = 0; i < sortedDemo.length; i++) {
            if(i==0){
                System.out.print("sorted array "+sortedDemo[i]);
            }
            else{
                System.out.print(","+sortedDemo[i]);
            }
        }
    }
}
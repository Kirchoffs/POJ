import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int len = Integer.parseInt(br.readLine());;
        while (len > 0) {
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            System.out.println(solve(arr));
            len = Integer.parseInt(br.readLine());
        }
    }

    private static long solve(int[] arr) {
        int[] helper = new int[arr.length];
        return mergeSort(arr, helper, 0, arr.length - 1);
    }

    private static long mergeSort(int[] arr, int[] helper, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        long res = mergeSort(arr, helper, left, mid) + mergeSort(arr, helper, mid + 1, right);
        res += merge(arr, helper, left, mid, right);

        return res;
    }

    private static long merge(int[] arr, int[] helper, int left, int mid, int right) {
        for (int index = left; index <= right; index++) {
            helper[index] = arr[index];
        }
        int leftIndex = left, rightIndex = mid + 1;
        int index = left;
        long res = 0;
        while (leftIndex <= mid) {
            if (rightIndex > right || helper[leftIndex] <= helper[rightIndex]) {
                arr[index++] = helper[leftIndex++];
                // Another way
                // res += rightIndex - (mid + 1);
            } else {
                arr[index++] = helper[rightIndex++];
                res += mid - leftIndex + 1;
            }
        }
        return res;
    }
}

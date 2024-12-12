import java.util.*;
import java.io.*;
/**
 * Inversion Counting
 * MergeSort를 통해 구한다.
 */

public class Main{

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int length;

    public static long merge(int[] seq, int low, int mid, int high, int[] aux){
        // 앞 절반 복사 
        for (int i=low, j=0; i<= mid; i++, j++ ){
            aux[j] = seq[i];
        }

        long res = 0;
        int cnt =0;
        int i = mid + 1, j=0, k=low;
        while (i<= high && j <=mid - low){
            // 앞 배열이 더 작거나 같은 경우 뒷배열에서 복사된 만큼 카운트 증가
            if (aux[j] <= seq[i]){
                seq[k++] = aux[j++];
                res += cnt;
            }else{ 
                seq[k++] = seq[i++];
                cnt++;
            }
        }

        // 아직 앞배열에서 숫자가 남아있다면 마저 채운다.
        while (j <= mid -low){
            seq[k++] = aux[j++];
            res+= cnt;
        }
        return res;
    }

    public static long recSort(int[] seq, int low, int high, int aux[]){
        long res = 0;
        if (low < high){
            int mid = (low + high)/ 2;
            res += recSort(seq, low, mid, aux);
            res += recSort(seq, mid + 1, high, aux);
            res += merge(seq, low, mid, high, aux);
        }
        return res;
    }
    public static long mergeSort(int[] seq, int n){
        int[] aux = new int[n];
        return recSort(seq, 0, n-1, aux);
    }

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        length =Integer.parseInt(st.nextToken());
        int[] seq = new int[length];

        st = new StringTokenizer(br.readLine().trim());
        for (int i=0; i<length; i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(mergeSort(seq, length));
    }
}
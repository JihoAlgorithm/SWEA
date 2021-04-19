package swea3238;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = read();
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            long N = Long.parseLong(st.nextToken());
            long R = Long.parseLong(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            bw.write("#" + t + " " + nCr(N, R, P) + "\n");
        }

        bw.close();

    }

    private static long nCr(long N, long R, int P) {

        long res, F[] = new long[P];
        res = F[0] = 1;
        for (int i = 1; i < P; i++) F[i] = i * F[i - 1] % P;

        while (N != 0 || R != 0) {

            int n = (int) (N % P);
            int r = (int) (R % P);

            if (n < r) {
                res = 0;
                break;
            }

            res = res * F[n] % P;
            res = res * power((F[n - r] * F[r]) % P, P - 2, P) % P;

            N /= P;
            R /= P;

        }

        return res;

    }

    private static long power(long x, int y, int P) {

        long res = 1;

        while (y > 0) {
            if ((y & 1) == 1) res = res * x % P;
            y >>= 1;
            x = x * x % P;
        }

        return res;

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;

    }

}
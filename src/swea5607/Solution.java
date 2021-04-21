package swea5607;

public class Solution {

    private static final int MAX_SIZE = 1000000, P = 1234567891;

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();

        long[] F = new long[MAX_SIZE + 1];
        F[0] = F[1] = 1;

        int T = read(), bigN = 2;
        for (int t = 1; t <= T; t++) {

            int N = read(), R = read();
            if (bigN < N) {
                F(bigN, N, F);
                bigN = N;
            }

            long A = (F[N] * power((F[N - R] * F[R]) % P, P - 2)) % P;
            sb.append('#').append(t).append(' ').append(A).append('\n');

        }

        System.out.print(sb);

    }

    private static void F(int l, int r, long[] F) {

        for (int i = l; i <= r; i++)
            if (F[i] == 0) F[i] = (i * F[i - 1]) % P;

    }

    private static long power(long x, int y) {

        long res = 1;

        while (y > 0) {

            if ((y & 1) != 0) res = (res * x) % P;
            y >>= 1;
            x = (x * x) % P;

        }

        return res;

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;

    }

}
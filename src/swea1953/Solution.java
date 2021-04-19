package swea1953;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

class Solution {

    private static final int[] dx = {-1, 0, 0, 1}; // 방향벡터
    private static final int[] dy = {0, -1, 1, 0}; // 상좌우하
    private static final int PIPE = 986016240, BIT = 8, MASK = 255, QLEN = 200;

    public static void main(String[] args) throws Exception {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = read();
        int[] queue = new int[200];
        long[] visited = new long[50];

        for (int t = 1; t <= T; t++) {

            int N = read(), M = read(), R = read(), C = read(), L = read();
            int[][] map = new int[N][M];

            for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = System.in.read() & 15;
                System.in.read(); // WhiteSpace
            } System.in.read(); }

            for (int i = 0; i < N; i++) visited[i] = 0;

            queue[0] = (R << BIT) | C;
            visited[R] |= 1L << C;

            bw.write("#" + t + " " + bfs(map, queue, visited, N, M, L));
            bw.newLine();

        }

        bw.close();

    }

    private static int bfs(int[][] map, int[] queue, long[] visited, int N, int M, int L) {

        int res = 1, time = 1, offer = 0, poll = -1;

        while (time++ < L) {

            int size = abs(offer, poll);

            while (size-->0) {

                if (++poll == QLEN) poll = 0;
                int x = queue[poll] >> BIT;
                int y = queue[poll] & MASK;
                int p = PIPE >> (map[x][y] << 2);

                for (int i = 0; i < 4; i++) {

                    if ((p & (1 << i)) == 0) continue;

                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
                    if (map[nx][ny] == 0 || (visited[nx] & (1L << ny)) != 0) continue;
                    if (((PIPE >> (map[nx][ny] << 2)) & (1 << (3 - i))) == 0) continue;

                    if (++offer == QLEN) offer = 0;
                    queue[offer] = (nx << BIT) | ny;
                    visited[nx] |= 1L << ny;
                    res++;

                }

            }

        }

        return res;

    }

    private static int abs(int a, int b) { return (a -= b) < 0 ? ~a + 1 : a; }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

}
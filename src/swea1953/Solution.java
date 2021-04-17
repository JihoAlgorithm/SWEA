package swea1953;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

class Solution {

    private static final int[] dx = {-1, 0, 0, 1}; // 방향벡터
    private static final int[] dy = {0, -1, 1, 0}; // 상좌우하
    private static final int PIPE = 986016240, BIT = 8, MASK = 255;

    public static void main(String[] args) throws Exception {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        LinkedList<Integer> queue = new LinkedList<>();

        int T = read();

        for (int t = 1; t <=  T; t++) {

            int N = read(), M = read(), R = read(), C = read(), L = read();
            int[][] map = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = System.in.read() - 48; System.in.read();
                } System.in.read();
            }

            queue.clear();
            queue.offer((R << BIT) | C);
            long[] visited = new long[N];
            visited[R] |= 1L << C;

            bw.write("#" + t + " " + bfs(map, queue, visited, N, M, L));
            bw.newLine();

        }

        bw.close();

    }

    private static int bfs(int[][] map, LinkedList<Integer> queue, long[] visited, int N, int M, int L) {

        int res = 1, time = 1;

        while (time++ < L) {

            int size = queue.size();

            while (size-->0) {

                int x = queue.poll();
                int y = x & MASK;
                x >>= BIT;
                int p = PIPE >> (map[x][y] << 2);

                for (int i = 0; i < 4; i++) {

                    if ((p & (1 << i)) == 0) continue;

                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
                    if ((visited[nx] & (1L << ny)) != 0 || map[nx][ny] == 0) continue;
                    if (((PIPE >> (map[nx][ny] << 2)) & (1 << (3 - i))) == 0) continue;

                    queue.offer((nx << BIT) | ny);
                    visited[nx] |= 1L << ny;
                    res++;

                }

            }

        }

        return res;

    }

    private static int read() throws Exception {
        int c, n = System.in.read() - 48;
        while ((c = System.in.read()) > 32) n = 10 * n + c - 48;
        if (c == 13) System.in.read();
        return n;
    }
}

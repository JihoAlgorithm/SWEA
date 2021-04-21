package swea1249;

import java.util.LinkedList;

public class Solution {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        int T = read();
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> queue = new LinkedList<>();

        for (int t = 1; t <=T; t++) {

            int N = read();
            int[][] time = new int[N][N];
            int[][] map = new int[N][N];

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++)
                    map[x][y] = System.in.read() - 48;

                System.in.read(); // White Space
                System.in.read(); // WhiteSpace

            }

            int res = bfs(map, time, queue, N);

            sb.append('#').append(t).append(' ').append(res).append('\n');

        }

        System.out.print(sb);

    }

    private static int bfs(int[][] map, int[][] time, LinkedList<Integer> queue, int N) {

        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;

        int res = Integer.MAX_VALUE;

        queue.clear();
        queue.offer(0);
        queue.offer(0);

        while (!queue.isEmpty()) {

            int x = queue.poll();
            int y = queue.poll();

            if (x == N - 1 && y == N - 1)
                res = min(res, time[x][y]);

            if (res <= time[x][y]) continue;

            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx == N || ny == N) continue;

                if (!visited[nx][ny] || time[nx][ny] > time[x][y] + map[nx][ny]) {

                    time[nx][ny] = time[x][y] + map[nx][ny];
                    visited[nx][ny] = true;
                    queue.offer(nx);
                    queue.offer(ny);

                }

            }

        }

        return res;

    }

    private static int min(int a, int b) {
        return a < b ? a : b;
    }

    /**
     * Input Process
     * @see https://blog.naver.com/jihogrammer/222281999239
     */
    private static int read() throws Exception {

        int c, N = System.in.read() - 48;
        while ((c = System.in.read()) > 32) N = 10 * N + c - 48;

        if (c == 13) System.in.read();

        return N;

    }

}

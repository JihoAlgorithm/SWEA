package swea5643;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        int T = read();

        for (int t = 1; t <= T; t++) {

            int N = read();
            int M = read();

            ArrayList<ArrayList<Integer>> high = new ArrayList<>(N);
            ArrayList<ArrayList<Integer>> low  = new ArrayList<>(N);
            for (int i = 0; i <= N; i++) {
                high.add(new ArrayList<>());
                low.add(new ArrayList<>());
            }
            for (int i = 0; i < M; i++) {
                int l = read();
                int h = read();
                high.get(l).add(h);
                low.get(h).add(l);
            }

            int[] count = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                dfs(high, i, count, new boolean[N + 1]);
                dfs(low, i, count, new boolean[N + 1]);
            }

            int cnt = 0;
            for (int i = 1; i <= N; i++) if (count[i] == N -1) cnt++;

            sb.append('#').append(t).append(' ').append(cnt).append('\n');

        }

        System.out.print(sb);

    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph, int i, int[] count, boolean[] visited) {

        visited[i] = true;
        ArrayList<Integer> adjList = graph.get(i);

        for (Integer v : adjList) {
            if (visited[v]) continue;
            count[v]++;
            visited[v] = true;
            dfs(graph, v, count, visited);
        }

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;

    }

}
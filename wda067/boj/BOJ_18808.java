import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 스티커 붙이기 / 골드3
https://www.acmicpc.net/problem/18808
 */
public class BOJ_18808 {

    private static int N, M, K;
    private static int[][] paper;
    private static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());  // 스티커의 개수

        paper = new int[N][M];

        List<int[][]> stickers = new ArrayList<>();

        // 스티커 정보
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[R][C];

            for (int j = 0; j < R; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < C; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            stickers.add(sticker);
        }

        // 스티커 붙이기
        for (int s = 0; s < K; s++) {
            int[][] cur = stickers.get(s);
            flag = false;  // 현재 스티커 붙힌 여부

            for (int d = 0; d < 4 && !flag; d++) {
                if (d != 0) {  // 처음이 아닌 경우
                    cur = rotate(cur);  // 스티커 회전
                }

                // 회전된 상태에서 전체 탐색
                for (int i = 0; i < N && !flag; i++) {
                    for (int j = 0; j < M && !flag; j++) {
                        check(i, j, cur);
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (paper[i][j] == 1) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    private static void check(int r, int c, int[][] sticker) {
        int stickerR = sticker.length;     // 스티커 가로 길이
        int stickerC = sticker[0].length;  // 스티커 세로 길이

        // 범위 체크
        if (r + stickerR > N || c + stickerC > M) {
            return;
        }

        // 스티커 판단
        for (int i = r; i < stickerR + r; i++) {
            for (int j = c; j < stickerC + c; j++) {
                // 이미 붙어있는 경우 스톱
                if (sticker[i - r][j - c] == 1 && paper[i][j] == 1) {
                    return;
                }
            }
        }

        // 스티커 붙힘
        for (int i = r; i < stickerR + r; i++) {
            for (int j = c; j < stickerC + c; j++) {
                if (sticker[i - r][j - c] == 1) {
                    paper[i][j] = 1;
                }
            }
        }

        flag = true;
    }

    // 2차원 배열 90도 회전
    private static int[][] rotate(int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        int[][] rotated = new int[c][r];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                rotated[j][r - 1 - i] = sticker[i][j];
            }
        }

        return rotated;
    }
}

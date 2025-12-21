import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[r][c];

            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < c; l++) {
                    sticker[j][l] = Integer.parseInt(st.nextToken());
                }
            }

            for (int dir = 0; dir < 4; dir++) {
                if (tryAttach(sticker)) {  // 붙일 수 있는지 시도
                    break;  // 붙였으면 다음 스티커로
                }
                sticker = rotate(sticker);  // 시계 방향 90도 회전
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    static boolean tryAttach(int[][] sticker) {
        int stickerR = sticker.length;
        int stickerC = sticker[0].length;

        // 노트북의 모든 시작 위치 시도
        for (int i = 0; i <= n - stickerR; i++) {
            for (int j = 0; j <= m - stickerC; j++) {
                // 이 위치에서 붙일 수 있는지 체크
                boolean canAttach = true;
                for (int r = 0; r < stickerR; r++) {
                    for (int c = 0; c < stickerC; c++) {
                        if (sticker[r][c] == 1 && board[i + r][j + c] == 1) {
                            canAttach = false;
                            break;
                        }
                    }
                    if (!canAttach) break;
                }

                // 붙일 수 있으면 붙이기
                if (canAttach) {
                    for (int r = 0; r < stickerR; r++) {
                        for (int c = 0; c < stickerC; c++) {
                            if (sticker[r][c] == 1) {
                                board[i + r][j + c] = 1;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    static int[][] rotate(int[][] sticker) {
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
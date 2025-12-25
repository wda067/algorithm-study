import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int r, c;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static ArrayList<int[]> firePath = new ArrayList<>();
    static ArrayList<int[]> movePath = new ArrayList<>();
    static char[][] board;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'F') {
                    firePath.add(new int[] {i, j});
                }
                else if (board[i][j] == 'J') {
                    movePath.add(new int[] {i, j, 1});
                }
            }
        }

        int time = 0;
        while (true) {
            time++;

            // 지훈이 탈출 시도
            int state = isEscape();
            
            // 탈출 성공
            if (state != -1) break;
            
            // 더 이상 이동할 좌표 없으면 탈출 불가
            if (movePath.isEmpty()) {
                time = -1;
                break;
            }
            
            // 불 확산
            fireSpread();
            
            // 지훈이 이동
            move();
        }
        System.out.println(time == -1 ? "IMPOSSIBLE" : time);
    }

    static void move() {
        ArrayList<int[]> nextMovePath = new ArrayList<>();
        for (int[] path : movePath) {
            int cx = path[0];
            int cy = path[1];
            int distance = path[2];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                // 벽, 불 체크
                if (board[nx][ny] == '#' || board[nx][ny] == 'F') continue;

                if (board[nx][ny] == '.') {
                    board[nx][ny] = 'J';
                    nextMovePath.add(new int[] {nx, ny, distance + 1});
                }
            }
        }
        // 초기화
        movePath.clear();

        // 다음 좌표로 갱신        
        movePath = nextMovePath;
    }

    static void fireSpread() {
        ArrayList<int[]> nextFirePath = new ArrayList<>();
        for (int[] path : firePath) {
            int cx = path[0];
            int cy = path[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위, 벽, 불 체크
                if (nx < 0 || nx >= r || ny < 0 || ny >= c || board[nx][ny] == '#' || board[nx][ny] == 'F') continue;
                
                board[nx][ny] = 'F';
                nextFirePath.add(new int[] {nx, ny});
            }
        }
        // 초기화
        firePath.clear();
        
        // 다음 좌표로 갱신        
        firePath = nextFirePath;
    }

    static int isEscape() {
        for (int[] path : movePath) {
            int cx = path[0];
            int cy = path[1];
            int distance = path[2];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) return distance;
            }
        }
        return -1;
    }
}
package boj;

import java.io.*;
import java.util.*;

public class BOJ_18808 {

	static class Sticker {
		int row;
		int col;
		int[][] map;

		public Sticker(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public void setMap(int[][] map) {
			this.map = map;
		}
	}

	static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 노트북 세로
		int M = Integer.parseInt(st.nextToken()); // 노트북 가로
		board = new int[N][M];
		int K = Integer.parseInt(st.nextToken()); // 스티커 개수
		List<Sticker> stickers = new ArrayList<>();

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			Sticker sticker = new Sticker(row, col);
			int[][] map = new int[sticker.row][sticker.col];
			for (int j = 0; j < row; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < col; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			sticker.setMap(map);
			stickers.add(sticker);
		}

		for (Sticker s : stickers) {
			Sticker cur = s;
			for (int rot = 0; rot < 4; rot++) {
				if (tryAttach(cur, N, M)){
					break;
				}
				cur = rotate(cur);
			}
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1)
					answer++;
			}
		}
		System.out.println(answer);

	}

	static boolean tryAttach(Sticker s, int N, int M) {
		for (int x = 0; x <= N - s.row; x++) {
			for (int y = 0; y <= M - s.col; y++) {
				if (canPlace(s, x, y)) {
					for (int i = 0; i < s.row; i++) {
						for (int j = 0; j < s.col; j++) {
							if (s.map[i][j] == 1)
								board[x + i][y + j] = 1;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	static boolean canPlace(Sticker s, int x, int y) {
		for (int i = 0; i < s.row; i++) {
			for (int j = 0; j < s.col; j++) {
				if (s.map[i][j] == 1 && board[x + i][y + j] == 1)
					return false;
			}
		}
		return true;
	}

	public static Sticker rotate(Sticker s) {
		int[][] map = s.map;
		int row = s.row;
		int col = s.col;
		int[][] tmp = new int[col][row];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tmp[j][row - 1 - i] = map[i][j];
			}
		}
		Sticker sticker = new Sticker(col, row);
		sticker.setMap(tmp);
		return sticker;
	}
}
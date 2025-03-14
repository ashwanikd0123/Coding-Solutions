import java.util.HashSet;

public class SudokuSolver {

    static final char EMPTY = '.';
    int n;
    HashSet<Character>[] row = new HashSet[9];
    HashSet<Character>[] col = new HashSet[9];
    HashSet<Character>[] block = new HashSet[9];
    char[] options;

    int blockIdx(int r, int c) {
        int blockR = r / 3;
        int blockC = c / 3;
        return blockR * 3 + blockC;
    }

    void init(char[][] board) {
        options = new char[n];
        for (int i = 0; i < n; i++) {
            row[i] = new HashSet<Character>();
            col[i] = new HashSet<Character>();
            block[i] = new HashSet<Character>();
            options[i] = (char)('1' + i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == EMPTY) {
                    continue;
                }
                row[i].add(board[i][j]);
                col[j].add(board[i][j]);

                int idx = blockIdx(i, j);
                block[idx].add(board[i][j]);
            }
        }
    }

    boolean dfs(char[][] board, int r, int c) {
        if (r == n && c == 0) {
            return isValidSudoku(board);
        }

        int nextR = (c == n - 1) ? r + 1 : r;
        int nextC = (c == n - 1) ? 0 : c + 1;

        if (board[r][c] != EMPTY) {
            return dfs(board, nextR, nextC);
        }

        int bIdx = blockIdx(r, c);

        for (char ch : options) {

            if (row[r].contains(ch) ||
                    col[c].contains(ch) ||
                    block[bIdx].contains(ch)) {
                continue;
            }

            row[r].add(ch);
            col[c].add(ch);
            block[bIdx].add(ch);
            board[r][c] = ch;
            if (dfs(board, nextR, nextC)) {
                return true;
            }
            row[r].remove(ch);
            col[c].remove(ch);
            block[bIdx].remove(ch);
        }

        board[r][c] = EMPTY;

        return false;
    }

    public void solveSudoku(char[][] board) {
        n = board.length;
        init(board);
        dfs(board, 0, 0);
    }

    boolean isValidSudoku(char[][] board) {
        int n = board.length;
        HashSet<Character> set = new HashSet<Character>();
        // check rows
        for (int i = 0; i < n; i++) {
            set.clear();
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (set.contains(board[i][j])) {
                    return false;
                }
                set.add(board[i][j]);
            }
        }
        // check cols
        for (int i = 0; i < n; i++) {
            set.clear();
            for (int j = 0; j < n; j++) {
                if (board[j][i] == '.') {
                    continue;
                }
                if (set.contains(board[j][i])) {
                    return false;
                }
                set.add(board[j][i]);
            }
        }
        // check blocks
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                set.clear();
                for (int k = i * 3; k < (i + 1) * 3; k++) {
                    for (int l = j * 3; l < (j + 1) * 3; l++) {
                        if (board[k][l] == '.') {
                            continue;
                        }
                        if (set.contains(board[k][l])) {
                            return false;
                        }
                        set.add(board[k][l]);
                    }
                }
            }
        }
        return true;
    }
}

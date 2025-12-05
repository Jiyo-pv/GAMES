import java.util.Scanner;

public class SlidingPuzzle {

    static void swap(int[][] a, int pos_r, int pos_c, int row, int col) {
        int temp = a[row][col];
        a[row][col] = a[pos_r][pos_c];
        a[pos_r][pos_c] = temp;
    }

    static boolean checkStatus(int[][] a) {
        int status = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (a[i][j] != status % 16) {
                    return false;
                }
                status++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] a = new int[4][4];

        int[][] easy = {
                {1, 2, 7, 3},
                {5, 6, 11, 4},
                {9, 10, 15, 8},
                {13, 14, 12, 0}
        };

        int[][] normal = {
                {1, 2, 3, 4},
                {5, 6, 8, 7},
                {9, 10, 12, 11},
                {14, 15, 13, 0}
        };

        int[][] hard = {
                {5, 9, 11, 10},
                {13, 6, 2, 3},
                {1, 7, 14, 4},
                {8, 12, 15, 0}
        };

        System.out.println("\t\tSLIDING PUZZLE");
        System.out.println("Choose difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Normal");
        System.out.println("3. Hard");
        System.out.print("-> ");
        int mode = sc.nextInt();

        System.out.print("Enter number of players: ");
        int n = sc.nextInt();

        String[] name = new String[n];
        int[] moves = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of player " + (i + 1) + ": ");
            name[i] = sc.next();
        }

        sc.nextLine(); // clear newline

        for (int player = 0; player < n; player++) {

            // Copy selected puzzle
            if (mode == 1) {
                for (int i = 0; i < 4; i++)
                    System.arraycopy(easy[i], 0, a[i], 0, 4);
            } else if (mode == 2) {
                for (int i = 0; i < 4; i++)
                    System.arraycopy(normal[i], 0, a[i], 0, 4);
            } else {
                for (int i = 0; i < 4; i++)
                    System.arraycopy(hard[i], 0, a[i], 0, 4);
            }

            int pos_r = 3, pos_c = 3;
            char ch;

            do {
                // Clear screen (portable way)
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("\t\tSLIDING PUZZLE");
                System.out.println("Player: " + name[player]);

                // Print board
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        System.out.print((a[i][j] == 0 ? "0" : a[i][j]) + "\t");
                    }
                    System.out.println();
                }

                System.out.println("Move: w=up s=down a=left d=right 0=exit");
                System.out.print("-> ");
                ch = sc.nextLine().charAt(0);

                switch (ch) {
                    case 'w':
                        if (pos_r > 0) {
                            swap(a, pos_r, pos_c, pos_r - 1, pos_c);
                            pos_r--;
                            moves[player]++;
                        }
                        break;

                    case 's':
                        if (pos_r < 3) {
                            swap(a, pos_r, pos_c, pos_r + 1, pos_c);
                            pos_r++;
                            moves[player]++;
                        }
                        break;

                    case 'a':
                        if (pos_c > 0) {
                            swap(a, pos_r, pos_c, pos_r, pos_c - 1);
                            pos_c--;
                            moves[player]++;
                        }
                        break;

                    case 'd':
                        if (pos_c < 3) {
                            swap(a, pos_r, pos_c, pos_r, pos_c + 1);
                            pos_c++;
                            moves[player]++;
                        }
                        break;

                    case '0':
                        System.out.println("Game aborted.");
                        return;

                    default:
                        continue;
                }

                if (checkStatus(a)) {
                    System.out.println("Puzzle Solved!");
                    break;
                }

            } while (ch != '0');
        }

        // Show results
        System.out.println("\n_____________________________");
        System.out.println("         SCORE BOARD");
        System.out.println("-----------------------------");
        System.out.println("Player Name      Moves");
        System.out.println("-----------------------------");

        int winner = 0;
        for (int i = 1; i < n; i++) {
            if (moves[i] < moves[winner])
                winner = i;
        }

        for (int i = 0; i < n; i++) {
            System.out.println(name[i] + "\t   " + moves[i] +
                    ((i == winner) ? " (winner)" : ""));
        }
    }
}

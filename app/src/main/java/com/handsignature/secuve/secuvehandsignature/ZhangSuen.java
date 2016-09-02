package com.handsignature.secuve.secuvehandsignature;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by quddn on 2016-09-02.
 */
public class ZhangSuen {
    public static String[][] image;
    public static int[][] pixels;
    public static Bitmap bit;

    final static int[][] nbrs = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1},
            {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}};

    final static int[][][] nbrGroups = {{{0, 2, 4}, {2, 4, 6}}, {{0, 2, 6},
            {0, 4, 6}}};

    static List<Point> toWhite = new ArrayList<>();
    static char[][] grid;

    ZhangSuen(Bitmap bit) {
        this.bit = bit;
        pixels = new int[bit.getHeight()][bit.getWidth()];
        // bitmap -> int array
        for (int i = 0; i < bit.getHeight(); ++i)
            for (int j = 0; j < bit.getWidth(); ++j)
                pixels[i][j] = bit.getPixel(j, i);

        for (int i = 0; i < bit.getHeight(); ++i) {
            for (int j = 0; j < bit.getWidth(); ++j) {
                if (pixels[i][j] == -1) // white
                    pixels[i][j] = 0;
                else // black
                    pixels[i][j] = 1;
            }
        }
        grid = new char[bit.getHeight()][bit.getWidth()];
        for (int i = 0; i < bit.getHeight(); ++i) {
            for (int j = 0; j < bit.getWidth(); ++j) {
                if (pixels[i][j] == 0) // white
                    grid[i][j] = ' ';
                else // black
                    grid[i][j] = '#';
            }
        }
    }

    static void thinImage() {
        boolean firstStep = false;
        boolean hasChanged;

        do {
            hasChanged = false;
            firstStep = !firstStep;

            for (int r = 1; r < grid.length - 1; r++) {
                for (int c = 1; c < grid[0].length - 1; c++) {

                    if (grid[r][c] != '#')
                        continue;

                    int nn = numNeighbors(r, c);
                    if (nn < 2 || nn > 6)
                        continue;

                    if (numTransitions(r, c) != 1)
                        continue;

                    if (!atLeastOneIsWhite(r, c, firstStep ? 0 : 1))
                        continue;

                    toWhite.add(new Point(c, r));
                    hasChanged = true;
                }
            }

            for (Point p : toWhite)
                grid[p.y][p.x] = ' ';
            toWhite.clear();

        } while (firstStep || hasChanged);

        printResult();
    }

    static int numNeighbors(int r, int c) {
        int count = 0;
        for (int i = 0; i < nbrs.length - 1; i++)
            if (grid[r + nbrs[i][1]][c + nbrs[i][0]] == '#')
                count++;
        return count;
    }

    static int numTransitions(int r, int c) {
        int count = 0;
        for (int i = 0; i < nbrs.length - 1; i++)
            if (grid[r + nbrs[i][1]][c + nbrs[i][0]] == ' ') {
                if (grid[r + nbrs[i + 1][1]][c + nbrs[i + 1][0]] == '#')
                    count++;
            }
        return count;
    }

    static boolean atLeastOneIsWhite(int r, int c, int step) {
        int count = 0;
        int[][] group = nbrGroups[step];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < group[i].length; j++) {
                int[] nbr = nbrs[group[i][j]];
                if (grid[r + nbr[1]][c + nbr[0]] == ' ') {
                    count++;
                    break;
                }
            }
        return count > 1;
    }

    static void printResult() {
        for (char[] row : grid)
            System.out.println(row);
    }

    static Bitmap getBitmap() {
        for (int i = 0; i < bit.getHeight(); ++i) {
            for (int j = 0; j < bit.getWidth(); ++j) {
                if (grid[i][j] == '#') // black
                    pixels[i][j] = -16777216;
                else // white
                    pixels[i][j] = -1;
            }
        }
        int [] re = new int[bit.getHeight()*bit.getWidth()];
        int cnt = 0;
        for (int i = 0; i < bit.getHeight(); ++i) {
            for (int j = 0; j < bit.getWidth(); ++j) {
                re[cnt++] = pixels[i][j];
            }
        }
        bit = Bitmap.createBitmap(re, 0, bit.getWidth(), bit.getWidth(), bit.getHeight(), Bitmap.Config.RGB_565);
        return bit;
    }
}

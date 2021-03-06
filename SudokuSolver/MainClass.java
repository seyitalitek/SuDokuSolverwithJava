package SudokuSolver;

import java.text.DecimalFormat;

public class MainClass {

    public static void main(String[] args) {

        Table tablo1 = new Table();

        tablo1.setEleman(0, 0, 8);
        tablo1.setEleman(1, 2, 3);
        tablo1.setEleman(2, 1, 7);
        tablo1.setEleman(1, 3, 6);
        tablo1.setEleman(2, 4, 9);
        tablo1.setEleman(2, 6, 2);
        tablo1.setEleman(3, 1, 5);
        tablo1.setEleman(3, 5, 7);
        tablo1.setEleman(4, 4, 4);
        tablo1.setEleman(4, 5, 5);
        tablo1.setEleman(4, 6, 7);
        tablo1.setEleman(5, 3, 1);
        tablo1.setEleman(5, 7, 3);
        tablo1.setEleman(6, 2, 1);
        tablo1.setEleman(6, 8, 8);
        tablo1.setEleman(6, 7, 6);
        tablo1.setEleman(7, 2, 8);
        tablo1.setEleman(7, 3, 5);
        tablo1.setEleman(7, 7, 1);
        tablo1.setEleman(8, 1, 9);
        tablo1.setEleman(8, 6, 4);

        System.out.println(tablo1);
        long time = System.nanoTime();
        if (tablo1.solve()) {
            System.out.println(tablo1);
        } else {
            System.out.println("Invalid Sudoku");
        }

        time = System.nanoTime() - time;
        DecimalFormat formatter = new DecimalFormat("#0.00");
        System.out.println("  Elapsed Time: "
                + formatter.format(time / 1_000_000_000D) + " seconds\n");

    }

}

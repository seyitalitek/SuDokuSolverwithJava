package com.solver.Sudoku;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainClass {

    public static boolean solve(Table tablo1) {
        tablo1.updateUnsolved();
        ArrayList<Table.Element> list;
        list = tablo1.getUnsolved();
        if (!list.isEmpty()) {
            list.get(0).buildPossibles();
        }
        //System.out.println(tablo1.numberOfUnsolved);
        if (list.isEmpty()) {
            //System.out.print("son ");
            return true;
        } else if (list.
                get(0).getPossibles().isEmpty()) {
            return false;
        } else {

            for (Object possible : list.
                    get(0).getPossibles()) {
                list.get(0).setValue((int) possible);
                if (solve(tablo1) == false) {
                    list.get(0).setValue(0);
                } else {
                    return true;
                }

            }
            return false;
        }

    }

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
        solve(tablo1);
        System.out.println(tablo1);
        time = System.nanoTime() - time;
        DecimalFormat formatter = new DecimalFormat("#0.00");
        System.out.println("  Elapsed Time: " + 
                      formatter.format(time/1_000_000_000D) + " seconds\n");

    }

}

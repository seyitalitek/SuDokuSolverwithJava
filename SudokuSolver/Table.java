package SudokuSolver;

import java.util.ArrayList;
import java.util.Collections;

public class Table {

    protected final Element[][] dizi = new Element[9][9];
    protected int numberOfUnsolved;

    Table() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                dizi[i][j] = new Element(i, j, 0);
            }
        }
    }

    public void setEleman(int i, int j, int neu) {
        if ((i < 9 && i >= 0) && (j < 9 && j >= 0) && (neu < 10 && neu >= 0)) {
            dizi[i][j].setValue(neu);
        }
    }
    @Override
    public String toString() {
        int satno = 0;

        String ausgabe = "\n\n        0 1 2   3 4 5   6 7 8   (j)\n";
        ausgabe += "        | | |   | | |   | | |\n";

        for (Element[] is : dizi) {
            ausgabe += "   " + satno + " -- ";
            int sutno = 0;
            for (Element i : is) {
                if (sutno == 2 || sutno == 5) {
                    if (i.getValue() == 0) {
                        ausgabe += " " + " | ";
                    } else {
                        ausgabe += +i.getValue() + " | ";
                    }

                } else {
                    if (i.getValue() == 0) {
                        ausgabe += " " + " ";
                    } else {
                        ausgabe += +i.getValue() + " ";
                    }
                }

                ++sutno;
            }
            if (satno == 2 || satno == 5) {
                ausgabe = ausgabe + "\n        ---------------------\n";
            } else {
                ausgabe = ausgabe + "\n";
            }
            satno++;
        }

        ausgabe += "\n  (i)\n";

        return ausgabe;
    }

    public void updateUnsolved() {
        numberOfUnsolved = 0;
        for (Element[] elemans : dizi) {
            for (Element eleman : elemans) {
                if (eleman.getValue() == 0) {
                    ++numberOfUnsolved;
                }
            }
        }
    }

    public ArrayList<Element> getUnsolved() {

        ArrayList<Element> unsolveds = new ArrayList<>();
        for (Element[] elemans : dizi) {
            for (Element eleman : elemans) {
                if (eleman.getValue() == 0) {
                    unsolveds.add(eleman);
                }
            }
        }

        unsolveds.forEach(unsolved -> {
            unsolved.buildPossibles();
        });

        Collections.sort(unsolveds);

        return unsolveds;
    }

    public class Element implements Comparable<Element> {

        private final int[] digits = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        private final int row;
        private final int column;
        private final int smallsq;
        private int value;
        private ArrayList<Integer> possibles = new ArrayList<>();
        protected int nOfPossibles = 0;

        Element(int i, int j, int neu) {
            //this.ind = i * 9 + j;
            this.row = i;
            this.column = j;
            this.value = neu;
            this.smallsq = (this.column / 3) + (this.row / 3) * 3;
        }


        public int getValue() {
            return this.value;
        }

        public void setValue(int neu) {

            if (neu < 10 && neu >= 0) {
                this.value = neu;
            }

        }

        public void buildPossibles() {

            possibles = new ArrayList<>();

            ArrayList<Integer> rowothers = new ArrayList<>();
            ArrayList<Integer> columnothers = new ArrayList<>();
            ArrayList<Integer> smallsqothers = new ArrayList<>();
            for (Element e : dizi[this.row]) {
                rowothers.add(e.getValue());
            }

            for (int i = 0; i < 9; i++) {
                columnothers.add(dizi[i][this.column].getValue());
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (dizi[i][j].smallsq == this.smallsq) {
                        smallsqothers.add(dizi[i][j].getValue());
                    }

                }
            }

            ArrayList<Integer> endliste = new ArrayList<>();

            endliste.addAll(rowothers);
            endliste.addAll(columnothers);
            endliste.addAll(smallsqothers);
            //endliste.removeAll(Collections.singleton(this.getValue()));

            for (Integer integer : digits) {
                if (!(endliste.contains(integer))) {
                    possibles.add(integer);
                }
            }

            nOfPossibles = possibles.size();

        }

        public ArrayList getPossibles() {
            return this.possibles;
        }
        


        @Override
        public int compareTo(Element o) {
            return ((o.nOfPossibles == this.nOfPossibles) ? 0 : 
                    ((o.nOfPossibles < this.nOfPossibles) ? 1 : -1));
        }
    }

}

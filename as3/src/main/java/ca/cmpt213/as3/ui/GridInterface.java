package ca.cmpt213.as3.ui;

import ca.cmpt213.as3.logic.TokimonGrid;

public class GridInterface {

    public static void showGrid(TokimonGrid npc) {

        char letter = 'A';

        System.out.print(" "); // column numeration
        for (int i = 1; i <= 10; i++) {
            if (i < 10)
                System.out.print("  " + i);
            else
                System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 1; i <= 10; i++) {
            System.out.print(letter++); // row numeration
            for (int j = 1; j <= 10; j++) {
                System.out.print("  ~");
            }
            System.out.println();
        }

    }

}

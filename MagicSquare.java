import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MagicSquare implements MagicSquareInterface {
    private int dimension;
    private String fileName;
    private int[][] magicSquare;
    private int magicNumber;
    private boolean isMagicSquare;

    public MagicSquare(String fileName) {
        this.fileName = fileName;
        File scanFile = new File(fileName);
        try {
            int dimension = 0;
            Scanner fileScan = new Scanner(scanFile);
            while (fileScan.hasNextLine()) {
                dimension++;
            }
            this.dimension = dimension;
            int[][] magicSquare = readMatrix(fileName);
            this.magicSquare = magicSquare;
            this.magicNumber = (int) ((dimension * (Math.pow((double) dimension, 2.0) + 1)) / 2);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound");

        }

    }

    public MagicSquare(String filename, int dimension) {
        this.dimension = dimension;
        this.fileName = filename;
        this.magicNumber = (int) ((dimension * (Math.pow((double) dimension, 2.0) + 1)) / 2);
        this.magicSquare = new int[dimension][dimension];

        int row = dimension - 1;
        int col = (int) (dimension / 2);

        int oldRow;
        int oldCol;

        for (int i = 1; i < Math.pow((int) dimension, 2.0); i++) {
            magicSquare[row][col] = i;

            oldRow = row;
            oldCol = col;

            row++;
            col++;

            if (row == dimension) {
                row = 0;
            }
            if (col == dimension) {
                col = 0;
            }
            if (magicSquare[row][col] != 0) {
                row = oldRow;
                col = oldCol;
                row--;
            }

        }

        writeMatrix(magicSquare, fileName);
    }

    private int[][] readMatrix(String filename) {
        File squareFile = new File(filename);
        int[][] magicsquare = new int[dimension][dimension];
        int row = 0;

        try {
            Scanner fileScan = new Scanner(squareFile);
            while (fileScan.hasNextLine()) {
                int col = 0;
                String line = fileScan.nextLine();
                Scanner lineScan = new Scanner(line);
                final String DELIMITERS = " ";
                lineScan.useDelimiter(DELIMITERS);
                while (lineScan.hasNext()) {
                    magicsquare[row][col] = Integer.parseInt(lineScan.next());
                    col++;

                }
                lineScan.close();
                row++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound");

        }
        return magicsquare;
    }

    private void writeMatrix(int[][] matrix, String filename) {

        try {
            File file = new File(filename);
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            for (int i = 0; i < dimension; i++) {
                pw.println();
                for (int j = 0; j < dimension; j++) {
                    String value = String.valueOf(magicSquare[i][j]);
                    pw.print(value);
                }
            }
            pw.close();

        } catch (IOException e) {
            System.out.println("IOException");
        }

    }

    public boolean isMagicSquare() {
        boolean magicB = true;
        
}
    }

    public int[][] getMatrix() {
        int[][] returnMatrix = magicSquare;
        return returnMatrix;
    }

    public String toString() {

    }

}
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MagicSquare implements MagicSquareInterface {

    private int dimension;
    private int[][] magicSquare;
    
   

    /**
     * 
     * @param fileName name of the file to be read
     * @throws FileNotFoundException
     */
    public MagicSquare(String fileName) throws FileNotFoundException {
        
        int[][] magicSquare = readMatrix(fileName);
        this.magicSquare = magicSquare;
        int magicNumber = (int) ((dimension * (Math.pow((double) dimension, 2.0) + 1)) / 2);

    }

    /**
     * 
     * @param filename  name of file to be created
     * @param dimension size of the MagicSquare
     * @throws IOException
     */

    public MagicSquare(String filename, int dimension) throws IOException {

        if (dimension % 2 == 1) {
            this.dimension = dimension;
            
            int magicNumber = (int) ((dimension * (Math.pow((double) dimension, 2.0) + 1)) / 2);
            this.magicSquare = new int[dimension][dimension];

            int row = dimension - 1;
            int col = dimension / 2;

            int oldRow;
            int oldCol;

            for (int i = 1; i <= (dimension * dimension); i++) {
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
            writeMatrix(magicSquare, filename);
        }

    }

    //Reads file given by the filename parameter and stores the contents in a new array, then returns the array

    private int[][] readMatrix(String filename) throws FileNotFoundException {
        File squareFile = new File(filename);

        int row = 0;

        Scanner fileScan = new Scanner(squareFile);
        dimension = Integer.parseInt(fileScan.nextLine());
        magicSquare = new int[dimension][dimension];
        while (fileScan.hasNextLine()) {
            int col = 0;
            String line = fileScan.nextLine();
            Scanner lineScan = new Scanner(line);

            while (lineScan.hasNext()) {
                magicSquare[row][col] = Integer.parseInt(lineScan.next());
                col++;

            }
            lineScan.close();
            row++;
        }
        fileScan.close();

        return magicSquare;
    }

    //Takes in a 2d array matrix and a new filename, then creates a file using the contents of the array

    private void writeMatrix(int[][] matrix, String filename) throws IOException {

        File file = new File(filename);
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        pw.print(dimension);
        for (int i = 0; i < dimension; i++) {
            pw.println();
            for (int j = 0; j < dimension; j++) {
                int value = magicSquare[i][j];
                pw.print(value);
                pw.print(" ");
            }
        }
        pw.close();

    }

    // Determines if this MagicSquare object is a magicsquare
    public boolean isMagicSquare() {
        boolean isMagicSquare = true;
        int magicNumber = (int) ((dimension * (Math.pow((double) dimension, 2.0) + 1)) / 2);

        for (int i = 0; i < dimension; i++) {
            int test = 0;

            for (int j = 0; j < dimension; j++) {
                test += magicSquare[i][j];

            }
            if (test != magicNumber) {
                isMagicSquare = false;
            }

        }

        int testRight = 0;

        for (int i = 0; i < dimension; i++) {
            testRight += magicSquare[i][i];

        }
        if (testRight != magicNumber) {
            isMagicSquare = false;
        }

        int testLeft = 0;

        for (int i = dimension - 1; i >= 0; i--) {
            testLeft += magicSquare[i][i];

        }

        if (testLeft != magicNumber) {
            isMagicSquare = false;
        }

        int dimenCount = 0;
        for (int i = 0; i <= (dimension * dimension); i++) {
            dimenCount += i;
        }

        int counter = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                counter += magicSquare[i][j];
            }
        }

        if (!(dimenCount == counter)) {
            isMagicSquare = false;
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 1; j < dimension; j++) {
                if (magicSquare[i][j] == magicSquare[i][j - 1]) {
                    isMagicSquare = false;
                }
            }
        }

        return isMagicSquare;

    }

    //preserves encapsulation by copying the magicsquare into a new array, then returning that array
    public int[][] getMatrix() {
        int[][] returnMatrix = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                returnMatrix[i][j] = magicSquare[i][j];
            }

        }
        return returnMatrix;
    }

    //prints the dimension, the entire array, and determines if the object is a magicsquare
    public String toString() {
        String returnString = "";
        returnString += "The Matrix\n";

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                returnString += String.valueOf(magicSquare[i][j]);
                returnString += " ";
            }
            returnString += "\n";
        }

        if (this.isMagicSquare()) {
            returnString += "\nis a magic square";
        } else {
            returnString += "\nis not a magic square";
        }

        

        return returnString;
    }

}
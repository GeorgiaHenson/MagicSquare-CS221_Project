import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MagicSquare implements MagicSquareInterface {
    private int dimension;
    private String fileName;
    private int[][] magicSquare;
    private int magicNumber;

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

        int row = dimension-1;
        int col = (int)(dimension/2);

        int oldRow;
        int oldCol;

        for (int i = 1; i < Math.pow((int)dimension, 2.0);i++){
            magicSquare[row][col] = i;

            oldRow = row;
            oldCol = col;

            row++;
            col++;

            if (row == dimension){
                row = 0;
            }
            if(col == dimension){
                col = 0;
            }

            
        }
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

    private void writeMatrix(int[][] matrix, String filename){
        
        try{
        PrintWriter pw = new PrintWriter(new File(filename));

        }

    }
}
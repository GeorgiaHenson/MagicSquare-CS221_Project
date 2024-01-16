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
    

    public MagicSquare(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        int[][] magicSquare = readMatrix(fileName);
        this.magicSquare = magicSquare;
        this.magicNumber = (int) ((dimension * (Math.pow((double) dimension, 2.0) + 1)) / 2);
       


    }

    public MagicSquare(String filename, int dimension) {
       
       if(dimension %2 == 1){
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
    
        
    }

    private int[][] readMatrix(String filename) throws FileNotFoundException {
        File squareFile = new File(filename);
        
        int row = 0;

        // try {
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
        // } catch (FileNotFoundException e) {
        //     System.out.println("FileNotFound");

        // }
        return magicSquare;
    }

    private void writeMatrix(int[][] matrix, String filename) {

        try {
            File file = new File(filename);
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            pw.println(dimension);
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

    @Override
    public boolean isMagicSquare() {
        this.isMagicSquare = true;
        
        

        for (int i = 0; i < dimension; i++) {
            int test = 0;
            int test2 = 0;
            for (int j = 0; j < dimension; j++) {
                test += magicSquare[i][j];
                test2 += magicSquare[j][i];
            }
            if (test != magicNumber && test2 == magicNumber) {
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

        for (int i = dimension-1; i > 0; i--) {
            testLeft += magicSquare[i][i];

        }
        if (testLeft != magicNumber) {
            isMagicSquare = false;
        }

        int dimenCount = 0;
        for(int i = 0; i < (dimension*dimension); i++){
            dimenCount+= i;
        }

        int counter = 0;
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                counter+= magicSquare[i][j];
            }
        }

        if(!(dimenCount == counter)){
            isMagicSquare = false;
        }

        
        return isMagicSquare;

    }

    @Override
    public int[][] getMatrix() {
       int[][] returnMatrix = magicSquare;
       return returnMatrix;
    }


    public String toString(){
        String returnString = "";
        returnString += "The Matrix\n";

        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                returnString += String.valueOf(magicSquare[i][j]);
                returnString += " ";
            }
            returnString += "\n";
        }

        if(this.isMagicSquare()){
            returnString += "\nis a magic square";
        }
        else{
            returnString += "\nis not a magic square";
        }
        
        returnString += "\n" +magicNumber;

        return returnString;
    }

}
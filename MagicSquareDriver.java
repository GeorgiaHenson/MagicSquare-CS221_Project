import java.io.IOException;

import java.util.Scanner;

public class MagicSquareDriver {

    /**
     * 
     * @param args
     * @throws NumberFormatException
     * @throws IOException
     * @author GeorgiaHenson
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
        if (args.length > 0) {
            switch (args[0]) {
                case "-create":

                    MagicSquare newSquare = new MagicSquare(args[1], Integer.parseInt(args[2]));
                    System.out.println(newSquare);

                    break;

                case "-check":
                    MagicSquare newMgSquare = new MagicSquare(args[1]);
                    System.out.println(newMgSquare);
                    break;

                default:
                    System.out.println("Wrong order of commands");
                    break;

            }
            System.out.println("Goodbye");

        }
    }
}
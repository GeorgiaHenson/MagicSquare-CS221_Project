import java.io.FileNotFoundException;

public class MagicSquareDriver{
    
    
     public static void main(String[] args) throws FileNotFoundException {
       switch(args[0]){
            case "-create":
                if(Integer.parseInt(args[2]) %2 != 0){
                    MagicSquare newSquare = new MagicSquare(args[1],Integer.parseInt(args[2]));
                    System.out.println(newSquare);

                }
                else{
                    System.out.println("Dimension must be odd");
                }
                break;

            case "-check":
                MagicSquare newSquare = new MagicSquare(args[1]);
                System.out.println(newSquare);
                break;

            default:
                System.out.println("Wrong order of commands");
                break;
            
                   }
                System.out.println("Goodbye");            

    }
}
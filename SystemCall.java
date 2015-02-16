import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.PrintWriter;


/**
 *  This program prompts the user for input, expecting the input to be the 
 *  name of a file, and writes the contents of the specified file into another
 *  file. The program is meant to be ran with the program 'strace' in order 
 *  to see the various system calls that the program must make in order to 
 *  perform the operations. 
 *
 *  @author Jeremiah Griffin
 *  @version 2/12/2015
 **/

 public class SystemCall{
    /**
     * Scanner for reading input and output file names.
     */
    private Scanner scanIt;

    /**
     * String to store name of input file.
     */
    private String input;

    /**
     * String to store name of ouput file.
     */
    private String output;


   /**
    * Constructor for SystemCall object.
    */
    public SystemCall(){
        scanIt = new Scanner(System.in);
        input = "";
        output = "";
        
        

    }
    /**
     * Prompts the user for input and output file. If the input file does not
     * exist, the program aborts with appropriate message. Likewise, if the 
     * output file already exists, the program aborts with the appropriate 
     * message. If both of those checks pass, the input file is read and 
     * copied into the output file with an appropriate success message showing
     * after the file is done being copied.
     */
    public void prompt() throws IOException{
        // Words to be copied and written to new file.
        String words = "";

        // Prompt user for input file.
        System.out.println("Please enter the name of a valid input file...");
        input = scanIt.next();

        //Prompt user for output file.
        System.out.println("Please enter the name of a valid output file...");
        output = scanIt.next();

        // Create new File instances for both input and output files.
        File inputFile = new File(input);
        File outputFile = new File(output);

        // Check to see if input file exists.
        if(inputFile.exists() == false){
            System.out.println("File "+ inputFile + " does not exist.");
            System.exit(1);                      
        }

        // Check to see if output file doesn't exist.
        if(outputFile.exists() == true){
            System.out.println("File " + outputFile +" already exists.");
            System.exit(1);

        }
        try{
            // Creating a file reader.
            FileReader reader = new FileReader(inputFile);
            // Creating a buffer reader.
            BufferedReader buffRead = new BufferedReader(reader);
            // Creating a file writer. 
            FileWriter writer = new FileWriter(outputFile);
            // Creating a buffer writer.
            BufferedWriter buffWrite = new BufferedWriter(writer);
            // Read and write will there are lines to be copied.
            while(buffRead.ready() == true){
                words = buffRead.readLine();
                buffWrite.write(words);
                buffWrite.newLine();

            }
            // Clean and close out buffer.
            buffWrite.flush();
            buffWrite.close();      
       }catch(FileNotFoundException e){
            System.out.println("File not found");

       }
       
        System.out.println("Success! Your new output file is finished.");

    }

    /**
     *  Main method creates a SystemCall object and runs the method prompt().
     *  @param args - command line arguments.
     */
    public static void main(String[] args){
        /**
         * SystemCall object created in order to create process.
         */
        SystemCall driver = new SystemCall();
        try{
            // Start the program by prompting the user.
            driver.prompt();
        }catch(IOException e ){
            System.out.println("Program could not run, please check the input"
            + " and output values" +
            " that you entered.");

        }

    }
 }

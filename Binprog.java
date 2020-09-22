import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * main class
 * 
 * @author omaralshikh, ahmad malik
 * @version 7/21/2020
 *
 */
public class Binprog {

    /**
     * This is the entry point of the application
     * 
     * @param args
     *            Command line arguments
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException {

        /* This is our tree */
        BinTree tree = new BinTree();
        /* No argument passed */
        if (args[0] == null) {
            System.out.println("No Argument Given");
        }
        /*
         * Call the readFile method with the passed input file which contains
         * the
         * expressions to be calculated
         */
        else {
            readFile(args[0], tree);
        }

    }


    /**
     * method to read and process contents of file
     * 
     * @param inputFileName
     *            file to be read
     * @param tree
     *            tree to be made
     * @throws FileNotFoundException
     *             thrown if no file is found
     */
    public static void readFile(String inputFileName, BinTree tree)
        throws FileNotFoundException {
        /* File Object which will be used to scan the given file */
        File file = new File(inputFileName);
        /* Scanner Object which will be used to scan the opened file */
        Scanner scanner = new Scanner(file);

        /* Loop through the opened file to read each line */
        while (scanner.hasNextLine()) {
            /*
             * Scan the next line in the file and store it into the String
             * fileLine
             */
            String fileLine = scanner.nextLine();

            /*
             * If the fileLine scanned with its whitespace's trimmed out is not
             * empty we
             * process the line
             */
            if (!fileLine.trim().isEmpty()) {
                /* Send the fileLine to be processed */
                processLine(fileLine, tree);

            } // end if

        } // end while
        /* Close our scanner after scanning our file */
        scanner.close();
    } // end main helper


    /**
     * method to process each string in a line
     * 
     * @param fileLine
     *            process each line
     * @param tree
     *            tree to be made
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public static void processLine(String fileLine, BinTree tree) {

        /* This cleans the line and then splits it into Strings in an array */
        String[] splitFileLine = fileLine.replaceAll("(^\\s+|\\s+$)", "").split(
            "\\s+");

        /* If the command is insert */
        if (splitFileLine[0].contentEquals("insert")) {
            /* Print the entered line */
            System.out.println(">" + fileLine);

            /* Parse the x and y values of the city to be inserted */
            int x = Integer.parseInt(splitFileLine[1]);
            int y = Integer.parseInt(splitFileLine[2]);

            /*
             * Check to see if the x and y values are valid i.e they lays within
             * the region
             */
            if (x >= 0 && x < 1024 && y >= 0 && y < 1024) {
                /* Call the insert method with the checked x and y values */
                tree.insert(x, y, splitFileLine[3]);
            }
            /* Else print this error method */
            else {
                System.out.println("The city can not be insterted.");
            }

        }

        /* If the command is remove */
        else if (splitFileLine[0].contentEquals("remove")) {
            /*
             * Print the entered line and call the trees remove method to remove
             * a city in the tree with the parsed Int values
             */
            System.out.println(">" + fileLine);
            /*
             * If removal from the tree is not successful then print a message
             */
            if (!tree.remove(Integer.parseInt(splitFileLine[1]), Integer
                .parseInt(splitFileLine[2]))) {
                /* the message to be printed if the removal is not successful */
                tree.printMsg();
            }

        }

        /* If the command is find */
        else if (splitFileLine[0].contentEquals("find")) {
            /*
             * Print the entered line and call the trees find method to find a
             * city in the tree with the parsed Int values
             */
            System.out.println(">" + fileLine);
            tree.find(Integer.parseInt(splitFileLine[1]), Integer.parseInt(
                splitFileLine[2]));

        }

        /* If the command is regionsearch */
        else if (splitFileLine[0].contentEquals("regionsearch")) {

            /*
             * Print the entered line and call the trees region search method to
             * search the tree with the parsed Int values
             */
            System.out.println(">" + fileLine);
            tree.regionSearch(Integer.parseInt(splitFileLine[1]), Integer
                .parseInt(splitFileLine[2]), Integer.parseInt(splitFileLine[3]),
                Integer.parseInt(splitFileLine[4]));

        }

        /* If the command is print */
        else if (splitFileLine[0].contentEquals("print")) {
            /*
             * Print the entered line and call the trees print method to print
             * the tree
             */
            System.out.println(">" + fileLine);
            tree.printTree();
        }

        /* If no correct command is found */
        else {
            /* Print the entered line and then a message */
            System.out.println(">" + fileLine);
            System.out.println("ERROR! Unrecognized command:" + fileLine);

        }
    }
}

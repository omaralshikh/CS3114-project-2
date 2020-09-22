import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import student.TestCase;

/**
 * tests class for main
 * 
 * @author omaralshikh ahmadmalik
 * @version 8/4/2020
 */
public class BinprogTest extends TestCase {

    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();


    /**
     * An artificial test to get initial coverage for the main method. Delete or
     * modify this test.
     * 
     * @throws FileNotFoundException
     */

    public void testMain() throws FileNotFoundException {
        Binprog dum = new Binprog();
        System.setOut(new PrintStream(outContent));

        assertNotNull(dum);
        Binprog.main(new String[1]);
        assertEquals(outContent.toString(), "No Argument Given\n");
        outContent.reset();

        String[] args = new String[] { "P2InputSample.txt" };
        Binprog.main(args);
        assertEquals(">insert 37 80 Blacksburg\n>"
            + "insert 38 122 San_Francisco\n>" + "insert 41 96 Omaha\n"
            + ">remove 38 122\n>" + "find 38 122\n"
            + "Record could not be printed.  " + "It does not exist.\n>"
            + "remove 38 122\n" + "Record could not be removed. "
            + "It does not exist.\n>" + "find 41 96\n" + "Omaha 41 96\n"
            + ">regionsearch 30 70 20 50\n" + "Blacksburg 37 80\n"
            + "Omaha 41 96\n" + "13 Nodes visited.\n>"
            + "print\nI, 0, 0, 1024, 1024\n" + "  I, 0, 0, 512, 1024\n"
            + "    I, 0, 0, 512, 512\n" + "      I, 0, 0, 256, 512\n"
            + "        I, 0, 0, 256, 256\n" + "          I, 0, 0, 128, 256\n"
            + "            I, 0, 0, 128, 128\n"
            + "              I, 0, 0, 64, 128\n"
            + "                E, 0, 0, 64, 64\n"
            + "                I, 0, 64, 64, 64\n"
            + "                  E, 0, 64, 32, 64\n"
            + "                  I, 32, 64, 32, 64\n"
            + "                    Blacksburg, 37, 80\n"
            + "                    Omaha, 41, 96\n"
            + "              E, 64, 0, 64, 128\n"
            + "            E, 0, 128, 128, 128\n"
            + "          E, 128, 0, 128, 256\n"
            + "        E, 0, 256, 256, 256\n" + "      E, 256, 0, 256, 512\n"
            + "    E, 0, 512, 512, 512\n" + "  E, 512, 0, 512, 1024\n>"
            + "I am the very model of a modern Major-General\n"
            + "ERROR! Unrecognized command:I am the very model "
            + "of a modern Major-General\n" + ">insert 1026 1026\n"
            + "The city can not be insterted.\n" + ">insert 0 1026\n"
            + "The city can not be insterted.\n" + ">insert 1026 0\n"
            + "The city can not be insterted.\n" + ">insert -1 -1\n"
            + "The city can not be insterted.\n" + ">insert 5 -5\n"
            + "The city can not be insterted.\n", outContent.toString());

    }

}

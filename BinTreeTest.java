
/**
 * 
 */
import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * test class for bintree
 * 
 * @author omaralshikh ahmadmalik
 * @version 8/4/2020
 */
public class BinTreeTest extends TestCase {

    private BinTree tree;
    private BinTree tree2;
    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();


    /**
     * set up method
     */
    public void setUp() {
        tree = new BinTree();
        tree2 = new BinTree();
        System.setOut(new PrintStream(outContent));

    }


    /**
     * test the remove method
     */
    public void testRemove() {
        BinTree emptyT = new BinTree();
        assertFalse(tree.remove(5, 5));
        tree.insert(512, 512, "bburg");
        tree.insert(100, 800, "sterling");

        assertFalse(tree.remove(900, 900));
        assertTrue(tree.remove(100, 800));
        tree.insert(800, 100, "ashburn");

        assertTrue(tree.remove(512, 512));

        assertFalse(emptyT.remove(512, 512));

        emptyT.insert(512, 512, "bburg");
        assertTrue(emptyT.remove(512, 512));

        emptyT.insert(502, 512, "bburg");
        assertFalse(emptyT.remove(512, 512));

        tree2.insert(512, 512, "bburg");
        tree2.insert(100, 800, "sterling");

        assertFalse(tree2.remove(900, 900));
        tree2.insert(800, 100, "ashburn");

        assertTrue(tree2.remove(512, 512));

    }


    /**
     * test the find method
     */
    public void testFind() {
        tree.insert(512, 512, "bburg");
        tree.insert(100, 800, "sterling");
        tree.insert(800, 100, "ashburn");
        tree.find(512, 512);
        assertEquals("bburg 512 512\n", outContent.toString());
        outContent.reset();

        tree.find(100, 800);
        assertEquals("sterling 100 800\n", outContent.toString());
        outContent.reset();

        tree.find(800, 100);
        assertEquals("ashburn 800 100\n", outContent.toString());
        outContent.reset();

        tree.find(841, 100);
        assertEquals("Record could not be printed.  It does not exist.\n",
            outContent.toString());
        outContent.reset();

    }


    /**
     * tests the region search method
     */
    public void testRegionSearch() {
        tree.insert(512, 512, "bburg");
        tree.insert(100, 800, "sterling");
        tree.insert(800, 100, "ashburn");
        tree.regionSearch(0, 700, 200, 200);
        assertEquals("sterling 100 800\n3 Nodes visited.\n", outContent
            .toString());
        outContent.reset();

        tree.regionSearch(2000, 700, 200, 200);
        assertEquals("The specified region is outside the known world.\n",
            outContent.toString());
        outContent.reset();

        tree.regionSearch(200, 7000, 200, 200);
        // need case outisde of region

    }


    /**
     * tests the input file entirely
     */
    public void testPrintTree() {

        tree2.insert(37, 80, "B");
        tree2.insert(38, 122, "SF");
        tree2.insert(41, 96, "OM");
        tree2.remove(38, 122);
        tree2.find(38, 122);
        tree2.remove(38, 122);
        tree2.find(41, 96);
        tree2.regionSearch(30, 70, 20, 50);
        tree2.printTree();
        assertEquals("Record could not be printed.  It does not exist.\n"
            + "OM 41 96\n" + "B 37 80\n" + "OM 41 96\n" + "13 Nodes visited.\n"
            + "I, 0, 0, 1024, 1024\n" + "  I, 0, 0, 512, 1024\n"
            + "    I, 0, 0, 512, 512\n" + "      I, 0, 0, 256, 512\n"
            + "        I, 0, 0, 256, 256\n" + "          I, 0, 0, 128, 256\n"
            + "            I, 0, 0, 128, 128\n"
            + "              I, 0, 0, 64, 128\n"
            + "                E, 0, 0, 64, 64\n"
            + "                I, 0, 64, 64, 64\n"
            + "                  E, 0, 64, 32, 64\n"
            + "                  I, 32, 64, 32, 64\n"
            + "                    B, 37, 80\n"
            + "                    OM, 41, 96\n"
            + "              E, 64, 0, 64, 128\n"
            + "            E, 0, 128, 128, 128\n"
            + "          E, 128, 0, 128, 256\n"
            + "        E, 0, 256, 256, 256\n" + "      E, 256, 0, 256, 512\n"
            + "    E, 0, 512, 512, 512\n" + "  E, 512, 0, 512, 1024\n",
            outContent.toString());
        outContent.reset();
        BinTree tree3;
        tree3 = new BinTree();
        tree3.printTree();
        assertEquals("E, 0, 0, 1024, 1024\n", outContent.toString());
        outContent.reset();
        tree3.insert(200, 200, "bb");
        tree3.insert(200, 200, "cc");
        tree3.printTree();
        assertEquals(
            "I, 0, 0, 1024, 1024\n  bb, 200, 200\n  E, 512, 0, 512, 1024\n",
            outContent.toString());
        outContent.reset();
        tree3.remove(200, 200);
        tree3.remove(200, 200);
        tree3.insert(200, 100, "bb");
        tree3.insert(200, 200, "cc");
        tree3.printTree();
        BinTree tree4;
        tree4 = new BinTree();

        tree4.insert(37, 80, "aa");
        tree4.insert(37, 122, "bb");
        tree4.insert(1023, 80, "cc");
        tree4.insert(600, 800, "dd");
        tree4.insert(952, 205, "ee");
        tree4.insert(369, 852, "ff");
        tree4.insert(360, 985, "gg");
        tree4.insert(652, 25, "hh");
        tree4.insert(369, 852, "ff");
        tree4.find(37, 80);
        tree4.find(37, 122);
        tree4.find(1023, 80);
        tree4.find(600, 800);
        tree4.find(952, 205);
        tree4.find(369, 852);
        tree4.find(360, 985);
        tree4.find(652, 25);
        tree4.find(369, 852);
        tree4.regionSearch(0, 0, 1023, 1023);
        tree4.regionSearch(-5, -5, 2, 2);
        tree4.regionSearch(100, -5, 2, 2);
        tree4.regionSearch(100, 100, 2, 2);
        tree4.regionSearch(100, 300, 500, 100);
        tree4.regionSearch(1000, 1000, 100, 100);

        tree4.remove(37, 80);
        tree4.remove(37, 122);
        tree4.remove(1023, 80);
        tree4.remove(600, 800);
        tree4.remove(952, 205);
        tree4.remove(369, 852);
        tree4.remove(360, 985);
        tree4.remove(652, 25);
        tree4.remove(369, 852);

    }


    /**
     * tests the printed mssg
     */
    public void testPrintMsg() {
        tree.printMsg();
        assertEquals(outContent.toString(),
            "Record could not be removed. It does not exist.\n");
        outContent.reset();
    }

}

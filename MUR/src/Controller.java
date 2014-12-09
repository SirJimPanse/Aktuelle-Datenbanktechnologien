import java.awt.Point;

public class Controller {

    /**
     * Algorithm for calculating the minimal bounding rectangle for a set of
     * given points.
     * 
     * @author Tino Landmann
     * @param args
     */

    public static void main(String[] args) {
	int[][] testList = { { 1, 5 }, { 2, 2 }, { 2, 7 }, { 4, 4 }, { 5, 13 },
		{ 6, 1 }, { 7, 4 }, { 9, 6 }, { 10, 5 } };
	int[][] mur = buildMur(testList);

	System.out.print("points: ");
	for (int[] p : testList) {
	    System.out.print("(" + p[0] + " " + p[1] + ")");
	}

	System.out.println("");
	System.out.print("minimal bounding rectangle: ");
	for (int i = 0; i < mur.length; i++) {
	    System.out.print("(" + mur[i][0] + ", " + mur[i][1] + ") ");
	}
    }

    /**
     * Defines minimal bounding rectangle from a list of points. For this, the
     * method checks what are the min and max coordinates. After this the method
     * is using the buildRectangleFromEdgePoints to get the corresponding edge
     * points.
     * 
     * @param int[][]
     * @return int[][]
     */
    public static int[][] buildMur(int[][] testList) {
	int[][] mur = new int[4][2];
	int maxX = testList[0][0];
	int minX = testList[0][0];
	int maxY = testList[0][1];
	int minY = testList[0][1];

	for (int i = 1; i < testList.length; i++) {
	    if (testList[i][0] > maxX) {
		maxX = testList[i][0];
	    }
	    if (testList[i][0] < minX) {
		minX = testList[i][0];
	    }
	    if (testList[i][1] > maxY) {
		maxY = testList[i][1];
	    }
	    if (testList[i][1] < minY) {
		minY = testList[i][1];
	    }
	}

	mur = buildRectangleFromEdgePoints(minX, maxX, minY, maxY);

	return mur;
    }

    /**
     * Defines edge points of the rectangle, based on the given max and min
     * coordinates.
     * 
     * @param int
     * @param int
     * @param int
     * @param int
     * @return int[][]
     */
    public static int[][] buildRectangleFromEdgePoints(int minX, int maxX,
	    int minY, int maxY) {
	int[] p1 = { minX, minY };
	int[] p2 = { minX, maxY };
	int[] p3 = { maxX, maxY };
	int[] p4 = { maxX, minY };

	return new int[][] { p1, p2, p3, p4 };
    }

}

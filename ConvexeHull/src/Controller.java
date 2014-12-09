import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class Controller {

  /**
   * @author Tino Landmann
   * @param args
   * 
   *        Algorithm to determine the convex hull of a set of points, by using the graham-scan
   *        algorithm.
   */

  public static void main(String[] args) {
    ArrayList<Point> points = new ArrayList<Point>();
    points.add(new Point(1, 5));
    points.add(new Point(2, 2));
    points.add(new Point(2, 7));
    points.add(new Point(4, 4));
    points.add(new Point(5, 13));
    points.add(new Point(6, 1));
    points.add(new Point(7, 4));
    points.add(new Point(9, 6));
    points.add(new Point(10, 5));
    
    System.out.print("points: ");
    for (Point p : points){
      System.out.print("(" + p.x + " " + p.y + ")");
    }

    Stack<Point> convexHull = grahamScan(points);
    
    System.out.println("");
    System.out.print("convex hull: ");
    for (Point p : convexHull) {
      System.out.print("(" + p.x + ", " + p.y + ")");
    }
  }


  /**
   * Method which performs the graham scan algorithm. For each point in the list is checked whether
   * these is left or right of the previous vector. If the point is on the left side, push the point
   * on the stack and check the next point. Otherwise delete the first element of the stack and try
   * this point with a vector before.
   * 
   * @param ArrayList<Point>
   * @return Stack<Point>
   */
  public static Stack<Point> grahamScan(ArrayList<Point> points) {
    Stack<Point> ch = new Stack<Point>();

    points = sortPointsByAngle(getMinimalYPoint(points), points);

    ch.push(points.get(0));
    ch.push(points.get(1));
    // Push first and second point to the stack, this will be the first vector.

    int index = 2;
    while (index < points.size()) {
      if (checkLeftOrRight(ch.get(ch.size() - 2), ch.get((ch.size() - 1)), points.get(index))) {
        ch.push(points.get(index));
        index += 1;
      } else {
        ch.pop();
      }
    }
    return ch;
  }


  /**
   * Checks which point have the smallest Y-coordinate. If the Y-coordinate of two points is the
   * same, check if the X-coordinate is smaller an then use the point with the smaller one.
   * 
   * @param ArrayList<Point>
   * @return Point
   */
  public static Point getMinimalYPoint(ArrayList<Point> points) {
    Point minY = points.get(0);

    for (Point p : points) {
      if (p.getY() < minY.getY()) {
        minY = p;
      } else if (p.getY() == minY.getY()) {
        if (p.getX() < minY.getX()) {
          minY = p;
        }

      }
    }
    return minY;
  }


  /**
   * Sorts a list of points by their angle. If the angle is the same, check the distance. The point
   * with shorter distance will have the priority.
   * 
   * @param Point
   * @param ArrayList<Point>
   * @return ArrayList<Point>
   */
  public static ArrayList<Point> sortPointsByAngle(Point minY, ArrayList<Point> points) {
    ArrayList<Point> sortedPoints = new ArrayList<Point>();
    sortedPoints.add(minY);


    while (!points.isEmpty()) {
      double actAngle = getAngle(minY, points.get(0));
      double actDistance = getDistance(minY, points.get(0));
      Point actPoint = points.get(0);

      for (Point p : points) {
        double angle = getAngle(minY, p);
        double distance = getDistance(minY, p);

        if (angle < actAngle) {
          actAngle = angle;
          actPoint = p;
        } else if (angle == actAngle) {
          if (distance < actDistance) {
            actAngle = angle;
            actDistance = distance;
            actPoint = p;
          }

        }

      }

      if (!sortedPoints.contains(actPoint)) {
        sortedPoints.add(actPoint);
      }
      points.remove(actPoint);
    }

    return sortedPoints;
  }


  /**
   * Calculates the angle between two given points.
   * 
   * @param Point
   * @param Point
   * @return double
   */
  public static double getAngle(Point p1, Point p2) {
    return Math.atan2((p2.getY() - p1.getY()), (p2.getX() - p1.getX()));
  }


  /**
   * Calculates the distance between two given points.
   * 
   * @param Point
   * @param Point
   * @return double
   */
  public static double getDistance(Point p1, Point p2) {
    return Math.sqrt((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p2.getY() - p1.getY())
        * (p2.getY() - p1.getY()));
  }


  /**
   * Calculates the size of a triangle of three given points by using cross product. If the size is
   * bigger then 0, the third point is on the left side of the vector of point 1 and point 2.
   * Otherwise it is on the vector (== 0) or on the right side (< 0).
   * 
   * @param Point
   * @param Point
   * @param Point
   * @return boolean
   */
  public static boolean checkLeftOrRight(Point p1, Point p2, Point p3) {
    double size = ((p2.x - p1.x) * (p3.y - p1.y)) - ((p3.x - p1.x) * (p2.y - p1.y));
    if (size > 0) {
      return true;
    }
    return false;
  }
}

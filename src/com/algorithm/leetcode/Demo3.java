package com.algorithm.leetcode;

/**
 * 查找一系列点在一条直线上的最大个数
 */
public class Demo3 {

    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public int maxPoints(Point[] points) {
        if (points == null || points.length < 3)
            return points.length;
        int res = 0;
        for (int i = 1; i < points.length; i++) {
            int count = 0;
            int a = points[i].x;
            int b = points[i].y;
            int dx = a - points[i - 1].x;
            int dy = b - points[i - 1].y;
            if (dx == 0 && dy == 0) {
                for (int j = 0; j < points.length; j++) {
                    if (points[j].x == a && points[j].y == b) {
                        count++;
                    }
                }
            } else {
                for (int j = 0; j < points.length; j++) {
                    if ((points[j].x - a) * dy == (points[j].y - b) * dx) {
                        count++;
                    }
                }
            }
            res = Math.max(res, count);
        }
        return res;
    }

    public static void main(String[] args) {
//        Point p1 = new Point(84,250);
        Point p2 = new Point(0, 0);
        Point p3 = new Point(1, 0);
        Point p4 = new Point(0, -70);
        Point p5 = new Point(0, -70);
        Point p6 = new Point(1, -1);
//        Point p7 = new Point(21,10);
//        Point p8 = new Point(42,90);
//        Point p9 = new Point(-42,-230);
        Demo3 d = new Demo3();
        Point[] points = {p2, p3, p4, p5, p6};
        System.out.println(d.maxPoints(points));
    }
}

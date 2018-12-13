import java.util.ArrayList;
import java.util.Collections;

public class collisionDetector {
    ArrayList<String[]> meshData;
    ArrayList<String[]> sphereData;
    int numberTriangles;
    int numberSpheres;
    double radius;
    public collisionDetector(ArrayList<String[]> meshData, ArrayList<String[]> sphereData, int numberTriangles, int numberSpheres, double radius){
        this.meshData = meshData;
        this.sphereData = sphereData;
        this.numberTriangles = numberTriangles;
        this.numberSpheres = numberSpheres;
        this.radius = radius;
    }

    ArrayList<String[]> getTriangleData(ArrayList<String[]> meshData, int index){
        if(index >= numberTriangles){
            System.out.print("Out of index, # of triangles");
            System.exit(-1);
        }
        ArrayList<String[]> result = new ArrayList<>();
        for(int i = 3*index; i < 3*index + 3; i++){
            String[] temp = meshData.get(i);
            //System.out.printf("%d Mesh data is %s %s %s\n", index, temp[0], temp[1], temp[2]);
            result.add(temp);
        }
        return result;
    }

    String[] getSphereData(ArrayList<String[]> sphereData, int index){
        if(index >= numberSpheres){
            System.out.print("Out of index, # of spheres");
            System.exit(-1);
        }
        String[] result = sphereData.get(index);
        //System.out.printf("%d Sphere data is %s %s %s\n", index, result[0], result[1], result[2]);
        return result;
    }

    boolean sphereTriangleIntersection(ArrayList<String[]> triangleData, String[] sphereData){
        ArrayList<double[]> triangle = new ArrayList<>();
        for(int i = 0; i < triangleData.size(); i++){
            String[] temp;
            temp = triangleData.get(i);
            double p1 = Double.parseDouble(temp[0]);
            double p2 = Double.parseDouble(temp[1]);
            double p3 = Double.parseDouble(temp[2]);
            double[] xyzTriangle = {p1, p2, p3};
            triangle.add(xyzTriangle);
        }

        // XYZ of one sphere
        double p1 = Double.parseDouble(sphereData[0]);
        double p2 = Double.parseDouble(sphereData[1]);
        double p3 = Double.parseDouble(sphereData[2]);
        //double[] sphere = {p1, p2, p3};

        // XYZ of one triangle
        double x1 = triangle.get(0)[0];
        double y1 = triangle.get(0)[1];
        double z1 = triangle.get(0)[2];

        double x2 = triangle.get(1)[0];
        double y2 = triangle.get(1)[1];
        double z2 = triangle.get(1)[2];

        double x3 = triangle.get(2)[0];
        double y3 = triangle.get(2)[1];
        double z3 = triangle.get(2)[2];

        double d1 = Math.pow((x1 - p1), 2.0) + Math.pow((y1 - p2), 2.0) + Math.pow((z1 - p3), 2.0);
        double d2 = Math.pow((x2 - p1), 2.0) + Math.pow((y2 - p2), 2.0) + Math.pow((z2 - p3), 2.0);
        double d3 = Math.pow((x3 - p1), 2.0) + Math.pow((y3 - p2), 2.0) + Math.pow((z3 - p3), 2.0);

        d1 = Math.sqrt(d1);
        d2 = Math.sqrt(d2);
        d3 = Math.sqrt(d3);
        /*
        // simple check method
        if(d1 <= radius || d2<= radius || d3 <= radius){
            return true;
        }
        else {
            return false;
        }
        // end simple check method
        */
        // true check method
        double l = (y2 - y1)*(z3 - z1) - (y3 - y1)*(z2 - z1);
        double m = (z2 - z1)*(x3 - x1) - (z3 - z1)*(x2 - x1);
        double n = (x2 - x1)*(y3 - y1) - (x3 - x1)*(y2 - y1);
        double t = (l*(x1 - p1) + m*(y1 - p2) + n*(z1 - p3))/(l*l + m*m + n*n);

        double X = p1 + t*l;
        double Y = p2 + t*m;
        double Z = p3 + t*n;

        double abs1 = Math.abs((X - x3)*y2 + (x2 - X)*y3 + (x3 - x2)*Y);
        double abs2 = Math.abs((Y - y3)*x1 + (y3 - y1)*X + (y1 - Y)*x3);
        double abs3 = Math.abs((y2 - Y)*x1 + (Y - y1)*x2 + (y1 - y2)*X);
        double absTotal = abs1 + abs2 + abs3;
        double tol = 0.01;
        double distance;
        if((1.0 - tol)*Math.abs(n) < absTotal && (1.0 + tol)*Math.abs(n) > absTotal){
            double dx = Math.pow((X - p1), 2.0);
            double dy = Math.pow((Y - p2), 2.0);
            double dz = Math.pow((Z - p3), 2.0);
            distance = Math.sqrt(dx + dy + dz);
        }
        else{
            double[] P = {p1, p2, p3};
            double[] A = {x1, y1, z1};
            double[] B = {x2, y2, z2};
            double[] C = {x3, y3, z3};
            double distPAB = point2SegDist(P, A, B);
            double distPAC = point2SegDist(P, A, C);
            double distPBC = point2SegDist(P, B, C);
            ArrayList<Double> distPABC = new ArrayList<>();
            distPABC.add(distPAB);
            distPABC.add(distPAC);
            distPABC.add(distPBC);
            distance = Collections.min(distPABC);
        }

        if(distance <= radius){
            return true;
        }
        else{
            return false;
        }
        // end true check method
    }

    double point2SegDist(double[] P, double[] A, double[] B){
        double x1 = A[0];
        double y1 = A[1];
        double z1 = A[2];

        double x2 = B[0];
        double y2 = B[1];
        double z2 = B[2];

        double p1 = P[0];
        double p2 = P[1];
        double p3 = P[2];

        double dotProduct = (p1 - x1)*(x2 - x1) + (p2 - y1)*(y2 - y1) + (p3 - z1)*(z2 - z1);
        double d2 = Math.pow((x2 - x1), 2.0) + Math.pow((y2 - y1), 2.0) + Math.pow((z2 - z1), 2.0);
        if(dotProduct <= 0.0){
            double dx = Math.pow((p1 - x1), 2.0);
            double dy = Math.pow((p2 - y1), 2.0);
            double dz = Math.pow((p3 - z1), 2.0);
            double distance = Math.sqrt(dx + dy + dz);
            return distance;
        }
        if(dotProduct >= d2){
            double dx = Math.pow((p1 - x2), 2.0);
            double dy = Math.pow((p2 - y2), 2.0);
            double dz = Math.pow((p3 - z2), 2.0);
            double distance = Math.sqrt(dx + dy + dz);
            return distance;
        }
        double r = dotProduct / d2;
        double cx = x1 + (x2 - x1)*r;
        double cy = y1 + (y2 - y1)*r;
        double cz = z1 + (z2 - z1)*r;

        double dx = Math.pow((p1 - cx), 2.0);
        double dy = Math.pow((p2 - cy), 2.0);
        double dz = Math.pow((p3 - cz), 2.0);
        double distance = Math.sqrt(dx + dy + dz);
        return distance;
    }
}

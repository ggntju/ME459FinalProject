import java.util.ArrayList;

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

        // simple check method
        if(d1 <= radius || d2<= radius || d3 <= radius){
            return true;
        }
        else {
            return false;
        }
        // end simple check method
    }

}

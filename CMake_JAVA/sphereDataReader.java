import java.io.*;
import java.util.ArrayList;
public class sphereDataReader {
    /**
     * \class sphereDataReader
     * \brief sphereDataReader class reads the sphere data from an input file called "spheres.input"
     * @param sphereData is an ArrayList<String[]> stores the data from the file
     * @param radius is a double that stores the radius of the spheres
     * @param numberSpheres is an int that stores the number of spheres
     */
    ArrayList<String[]> sphereData = new ArrayList<>();
    double radius;
    int numberSpheres;

    /**
     * \protected readSphereData() reads the sphere data
     * @return nothing
     */
    void readSphereData() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("spheres.input"));
            reader.readLine();  // read first line
            radius = Double.parseDouble(reader.readLine());
            System.out.printf("Radius of Spheres is %f\n", radius);
            numberSpheres = Integer.parseInt(reader.readLine());
            System.out.printf("Number of Spheres is %d\n", numberSpheres);
            String line;

            while((line = reader.readLine()) != null){
                String item[] = line.split(",");
                sphereData.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * \protected outputSphereData() outputs the mesh data for testing
     * @return nothing
     */

    void outputSphereData(){
        int n = sphereData.size();
        System.out.printf("Number of points is %d\n", n);
        for(int i = 0; i < n; i++){
            String[] temp;
            temp = sphereData.get(i);
            double p1 = Double.parseDouble(temp[0]);
            double p2 = Double.parseDouble(temp[1]);
            double p3 = Double.parseDouble(temp[2]);
            System.out.printf("Sphere data is %f, %f, %f\n", p1, p2, p3);
        }
    }
}

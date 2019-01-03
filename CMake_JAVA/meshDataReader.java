import java.io.*;
import java.util.ArrayList;

public class meshDataReader {
    /**
     * \class meshDataReader
     * \brief meshDataReader class reads the mesh data from an input file called "mesh.input"
     * @param meshData is an ArrayList<String[]> stores the data from the file
     * @param numberTriangles is an int stores the number of triangles
     */
    ArrayList<String[]> meshData = new ArrayList<>();
    int numberTriangles;

    /**
     * \protected readMeshData() reads the mesh data
     * @return nothing
     */
    void readMeshData() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("mesh.input"));
            numberTriangles = Integer.parseInt(reader.readLine());
            System.out.printf("Number of Triangles is %d\n", numberTriangles);
            String line;

            while((line = reader.readLine()) != null){
                String item[] = line.split(",");
                meshData.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * \protected outputMeshData() outputs the mesh data for testing
     * @return nothing
     */

    void outputMeshData(){
        int n = meshData.size();
        System.out.printf("Number of points is %d\n", n);
        for(int i = 0; i < n; i++){
            String[] temp;
            temp = meshData.get(i);
            double p1 = Double.parseDouble(temp[0]);
            double p2 = Double.parseDouble(temp[1]);
            double p3 = Double.parseDouble(temp[2]);
            System.out.printf("Mesh data is %f, %f, %f\n", p1, p2, p3);
        }
    }

}

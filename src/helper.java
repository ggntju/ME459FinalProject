import java.io.*;
import java.util.ArrayList;


public class helper {
    /**
     * \class helper
     * \brief helper class is used for writing data into output file
     * @param collisionPair is an ArrayList<int[]> stores the data for output also a constructor input
     */
    ArrayList<int[]> collisionPair;

    public helper(ArrayList<int[]> collisionPair){
        this.collisionPair = collisionPair;
    }

    /**
     * \protected writeToFile() function writes the data into a file called "collision_detection.out"
     * @return nothing
     * @throws IOException
     */
    void writeToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("collision_detection.out"));
        writer.write("s,t" + "\n");
        for(int[] pair: collisionPair){
            writer.write(pair[0] + "," + pair[1] + "\n");
        }
        writer.close();
    }
}

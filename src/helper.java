import java.io.*;
import java.util.ArrayList;

public class helper {
    ArrayList<int[]> collisionPair;

    public helper(ArrayList<int[]> collisionPair){
        this.collisionPair = collisionPair;
    }

    void writeToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"));
        writer.write("s,t" + "\n");
        for(int[] pair: collisionPair){
            writer.write(pair[0] + "," + pair[1] + "\n");
        }
        writer.close();
    }
}

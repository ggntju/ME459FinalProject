import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<int[]> collisionPair = new ArrayList<>();
        meshDataReader mDR = new meshDataReader();
        mDR.readMeshData();
        //mDR.outputMeshData();
        sphereDataReader sDR = new sphereDataReader();
        sDR.readSphereData();
        //sDR.outputSphereData();

        collisionDetector cD = new collisionDetector(mDR.meshData, sDR.sphereData, mDR.numberTriangles, sDR.numberSpheres, sDR.radius);
        //cD.getSphereData(sDR.sphereData, 0);
        //cD.getTriangleData(mDR.meshData, 0);
        int numberTriangles = cD.numberTriangles;
        int numberSpheres = cD.numberSpheres;

        long tStart = System.currentTimeMillis();
        for(int i = 0; i < numberTriangles; i++){
            for(int j = 0; j< numberSpheres; j++){
                boolean flag = cD.sphereTriangleIntersection(cD.getTriangleData(mDR.meshData, i), cD.getSphereData(sDR.sphereData, j));
                if(flag){
                    int[] pair = {i,j};
                    collisionPair.add(pair);
                }
            }
        }
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double runningTime = tDelta / 1000.0;
        System.out.printf("%d collision pairs\n", collisionPair.size());
        System.out.printf("Finish in %f seconds\n", runningTime);
        //System.out.printf("%d,%d", collisionPair.get(200)[0], collisionPair.get(200)[1]);
        helper hp = new helper(collisionPair);
        try{
            hp.writeToFile();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}

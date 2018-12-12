
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        meshDataReader mDR = new meshDataReader();
        mDR.readMeshData();
        mDR.outputMeshData();
        sphereDataReader sDR = new sphereDataReader();
        sDR.readSphereData();
        sDR.outputMeshData();
    }

}

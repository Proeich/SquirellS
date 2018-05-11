package Utils;

import java.io.BufferedReader;
import java.io.FileReader;

class BoardConfig {

    //to be written to File
    private Vector2 size;
    private int wallCount;

    public BoardConfig(){
        try {
            readFromFile();
        } catch (Exception e) {
            size = new Vector2(10,10);
            wallCount = 20;
        }
    }

    public void writeToFile(){
    }

    private void readFromFile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("Test"));
        StringBuilder sb = new StringBuilder();
        String k = br.readLine();
        size = new Vector2(Integer.parseInt(k.split(", ")[0]),Integer.parseInt(k.split(", ")[1]));
        k = br.readLine();
        wallCount = Integer.parseInt(k);
        br.close();
    }

    public int getWallCount() {
        return wallCount;
    }

    public void setWallCount(int wallCount) {
        this.wallCount = wallCount;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

}

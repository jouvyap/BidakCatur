package bravostudio.testbukalapak;

/**
 * Created by jouvyap on 8/11/16.
 */
public class BidakModel {
    String type;
    int x_pos;
    int y_pos;

    public BidakModel(String type, int x_pos, int y_pos) {
        this.type = type;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

    public String toString(){
        return type + ", x:" + x_pos + ", y:" + y_pos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }
}

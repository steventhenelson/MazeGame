
public class Node {
    protected int Room;
    protected boolean Exit;
    protected Node Left;
    protected Node Right;
    protected Node Back;

    public Node() {
        Room = 0;
        Exit = false;
        Left = null;
        Right = null;
        Back = null;
    }

    public Node(int number, boolean value) {
        Room = number;
        Exit = value;
        Left = null;
        Right = null;
        Back = null;
    }

    public int getRoom() {
        return Room;
    }

    public void setRoom(int room) {
        Room = room;
    }

    public boolean getExit() {
        return Exit;
    }

    public void setExit(boolean exit) {
        Exit = exit;
    }
    public Node getLeft() {
        return Left;
    }

    public void setLeft(Node left) {
        Left = left;
    }

    public Node getRight() {
        return Right;
    }

    public void setRight(Node right) {
        Right = right;
    }

    public Node getBack() {
        return Back;
    }

    public void setBack(Node back) {
        Back = back;
    }
}

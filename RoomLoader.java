import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class RoomLoader extends AbstractRoomLoader {

    private Map<Integer, Room> rooms;

    public RoomLoader() {
        this.rooms = new HashMap<>();
    }

    @Override
    public void load() {
        Room room1 = new Room("Room 1", "This is room 1 description");
        Room room2 = new Room("Room 2", "This is room 2 description");
        Room room3 = new Room("Room 3", "This is room 3 description");

        Door door1 = Door.RED;
        Door door2 = Door.GREEN;

        room1.addDoor(door1, room2);
        room2.addDoor(door2, room3);

        setStart(room1);
        setEnd(room3);

        addRoom(room1);
        addRoom(room2);
        addRoom(room3);
    }

    @Override
    public Room getStart() {
        return start;
    }

    @Override
    public Room getEnd() {
        return end;
    }

    @Override
    public void serialize(String fileName) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
            System.out.println("RoomLoader has been serialized to =>" + fileName);
        } catch (IOException ex) {
            System.out.println("IOException is caught => " + ex);
        }
    }

    @Override
    public AbstractRoomLoader deserialize(String fileName) {
        AbstractRoomLoader rL = null;
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            rL = (AbstractRoomLoader) in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been deserialized  from file " + fileName);
            System.out.println("Start = " + rL.getStart() + ", end = " + rL.getEnd());
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Exception is caught => " + ex);
        }
        return rL;
    }

    private void addRoom(Room room) {
        rooms.put(room.getID(), room);
    }

    private void setStart(Room start) {
        this.start = start;
    }

    private void setEnd(Room end) {
        this.end = end;
    }
}



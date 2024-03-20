public class DumbBot {

    private Room currRoom;
    private AbstractRoomLoader loader;
    private String str;

    public void load() {
        loader = new RoomLoader();
        // loader.load();
        // loader.deserialize("Test.ser");
        currRoom = loader.getStart();
        str = "[" + currRoom.getID() + "]";

    }

    public void run() {
        while (!currRoom.equals(loader.getEnd())) {
            Door rDoor = pickRandomDoor();
            str += " -> " + rDoor;
            currRoom = currRoom.enter(rDoor);
            str += " -> [" + currRoom.getID() + "]";
        }
        System.out.println("Found the End! " + Room.getNumMoves() + " steps");
        System.out.println(str);
    }

    private Door pickRandomDoor() {
        int i = 0;
        int rand = (int) (Math.random() * currRoom.getDoors().size());
        for (Door d : currRoom.getDoors()) {
            if (i == rand)
                return d;
            i++;
        }
        return null; // This should not occur unless currRoom has no doors

    }

    public static void main(String[] args) {
        DumbBot bot = new DumbBot();
        bot.load();
        bot.run();
    }

}
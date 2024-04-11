import java.util.*;

public class ThetaStarBot
{
    private Room startRoom;
    private Room endRoom;
    private PriorityQueue<Room> openList;
    private Set<Room> closedList;
    private Map<Room, Room> parentMap;
    private AbstractRoomLoader loader;

    public ThetaStarBot(AbstractRoomLoader loader)
    {
        this.loader = loader;
        this.loader.load();
        this.startRoom = loader.getStart();
        this.endRoom = loader.getEnd();
        this.openList = new PriorityQueue<>(Comparator.comparingInt(this::computeCost));
        this.closedList = new HashSet<>();
        this.parentMap = new HashMap<>();
    }

    public List<Door> findShortestPathThetaStar()
    {
        openList.add(startRoom);
        parentMap.put(startRoom, null);

        while (!openList.isEmpty())
        {
            Room currentRoom = openList.poll();

            if (currentRoom.equals(endRoom))
            {
                return reconstructPath();
            }

            closedList.add(currentRoom);

            for (Door door : currentRoom.getDoors())
            {
                Room neighborRoom = currentRoom.enter(door);
                if (neighborRoom != null && !closedList.contains(neighborRoom))
                {
                    int tentativeGScore = getGScore(currentRoom) + 1;
                    if (!openListContains(neighborRoom) || tentativeGScore < getGScore(neighborRoom))
                    {
                        parentMap.put(neighborRoom, currentRoom);
                        openList.offer(neighborRoom);
                    }
                }
            }
        }

        return null;
    }

    private boolean openListContains(Room room)
    {
        return openList.contains(room);
    }

    private int getGScore(Room room)
    {
        return parentMap.containsKey(room) ? getGScore(parentMap.get(room)) + 1 : 0;
    }

    private int computeCost(Room room)
    {
        int distanceCost = heuristicCost(room, endRoom);
        int lineOfSightCost = hasLineOfSight(parentMap.get(room), room) ? 0 : Integer.MAX_VALUE;
        return getGScore(room) + distanceCost + lineOfSightCost;
    }

	private boolean hasLineOfSight(Room from, Room to)
	{
		if (from == null)
		{
			return false;
		}

		Set<Room> visited = new HashSet<>();
		Queue<Room> queue = new LinkedList<>();
		queue.offer(from);

		while (!queue.isEmpty())
		{
			Room current = queue.poll();
			visited.add(current);

			if (current.equals(to))
			{
				return true;
			}

			for (Door door : current.getDoors())
			{
				Room nextRoom = current.enter(door);
				if (nextRoom != null && !visited.contains(nextRoom))
				{
					queue.offer(nextRoom);
				}
			}
		}

	return false;
	}


    private List<Door> reconstructPath()
    {
        List<Door> path = new ArrayList<>();
        Room current = endRoom;
        while (current != null) {
            Room parent = parentMap.get(current);
            if (parent != null) {
                for (Map.Entry<Door, Room> entry : parent.getDoorsWithRooms().entrySet())
                {
                    if (entry.getValue().equals(current))
                    {
                        path.add(entry.getKey());
                        break;
                    }
                }
            }
            current = parent;
        }
        Collections.reverse(path);
        return path;
    }

    private int heuristicCost(Room current, Room goal)
    {
        return Math.abs(current.getID() - goal.getID());
    }

    public static void main(String[] args)
    {
        AbstractRoomLoader loader = new RoomLoader();
        ThetaStarBot bot = new ThetaStarBot(loader);
        List<Door> path = bot.findShortestPathThetaStar();
        if (path != null)
        {
            System.out.println("Shortest path: " + path);
        }
        else
        {
            System.out.println("No path found.");
        }
    }
}

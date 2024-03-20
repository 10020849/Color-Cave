import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

import java.util.*;

public class ColorCaveStarter extends JPanel implements MouseListener

{

    Rectangle r;

    Rectangle r2;

    Rectangle r3;

    Room room, end;

    JFrame frame;

    AbstractRoomLoader loader;

    public ColorCaveStarter()

    {

        frame = new JFrame("Maze of the Minotaur");

        frame.setSize(1000, 1000);

        frame.add(this);

        frame.addMouseListener(this);

        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        // loader = new RoomLoader(); //need to extend abstract with concrete class

    }

    public void paintComponent(Graphics g)

    {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);

        g2.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        ////////////// HEADER /////////////////

        g2.setColor(Color.WHITE);

        g2.setFont(new Font("Arial", Font.BOLD, 44));

        g2.drawString("Maze of the Minotaur", 80, 40);

        g2.setFont(new Font("Arial", Font.BOLD, 24));

        // g2.drawString(room.getName(),80, 80); // uncomment when you have a room
        // object

        // g2.drawString(room.getDescription(),80, 120);

        g2.drawString("Number of Moves: " + Room.getNumMoves(), 80, 600);

        /////////// PAINT DOORS ///////////////

        Door d = Door.RED; // Doors should come from Room object

        g2.setColor(enumToColor(d));

        r = new Rectangle(200, 200, 100, 200);

        g2.fill(r);

        Door d2 = Door.BLUE;

        g2.setColor(enumToColor(d2));

        r2 = new Rectangle(400, 200, 100, 200);

        g2.fill(r2);

        Door d3 = Door.GREEN;

        g2.setColor(enumToColor(d3));

        r3 = new Rectangle(600, 200, 100, 200);

        g2.fill(r3);

    }

    public void mouseClicked(MouseEvent e)

    {

        if (r.contains(e.getX(), e.getY()) || r2.contains(e.getX(), e.getY()) || r3.contains(e.getX(), e.getY())) {

            System.out.println("Inside the Rectangle");

            /* Move from room to next room */

        } else {

            System.out.println("Outside the Rectangle");

        }

        repaint();

    }

    // Other mouse listener methods we don't need to use

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    private Color enumToColor(Door d) {

        switch (d) {

            case RED:
                return Color.RED;

            case BLUE:
                return Color.BLUE;

            case GREEN:
                return Color.GREEN;

            case PINK:
                return Color.PINK;

            case YELLOW:
                return Color.YELLOW;

            default:

                return Color.WHITE;

        }

    }

    public static void main(String[] args)

    {

        ColorCaveStarter app = new ColorCaveStarter();

    }

}
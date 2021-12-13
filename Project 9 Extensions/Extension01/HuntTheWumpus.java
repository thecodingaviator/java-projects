/*
Name: Parth Parth
Date: 11/29/2021
File: HuntTheWumpus.java
Section: A
*/

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class HuntTheWumpus {
  private class LandscapePanel extends JPanel {

    /**
     * Creates the drawing canvas
     * 
     * @param height the height of the panel in pixels
     * @param width  the width of the panel in pixels
     **/
    public LandscapePanel(int height, int width) {
      super();
      this.setPreferredSize(new Dimension(width, height));
      this.setBackground(Color.black);
    }

    /**
     * Method overridden from JComponent that is responsible for
     * drawing components on the screen. The supplied Graphics
     * object is used to draw.
     * 
     * @param g the Graphics object used for drawing
     */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      try {
        scape.draw(g, scale, lost);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  } // end class LandscapePanel

  private class Control extends KeyAdapter implements ActionListener {
    public void keyTyped(KeyEvent e) {
      System.out.println("Key Pressed: " + e.getKeyChar());
      if (("" + e.getKeyChar()).equalsIgnoreCase("q")) {
        state = PlayState.STOP;
      }
      
      // if w is pressed
      if (("" + e.getKeyChar()).equalsIgnoreCase("w")) {
        // if hunter is not at the top of the map
        if (hunter.getCurrent().getY() > 0) {
          // move the hunter up
          hunter.move(vertices[hunter.getCurrent().getX()][hunter.getCurrent().getY() - 1]);
          this.moveSound();
        }
      }

      // if s is pressed
      if (("" + e.getKeyChar()).equalsIgnoreCase("s")) {
        // if hunter is not at the bottom of the map
        if (hunter.getCurrent().getY() < scape.getHeight() - 1) {
          // move the hunter down
          hunter.move(vertices[hunter.getCurrent().getX()][hunter.getCurrent().getY() + 1]);
          this.moveSound();
        }
      }

      // if a is pressed
      if (("" + e.getKeyChar()).equalsIgnoreCase("a")) {
        // if hunter is not at the left of the map
        if (hunter.getCurrent().getX() > 0) {
          // move the hunter left
          hunter.move(vertices[hunter.getCurrent().getX() - 1][hunter.getCurrent().getY()]);
          this.moveSound();
        }
      }

      // if d is pressed
      if (("" + e.getKeyChar()).equalsIgnoreCase("d")) {
        // if hunter is not at the right of the map
        if (hunter.getCurrent().getX() < scape.getWidth() - 1) {
          // move the hunter right
          hunter.move(vertices[hunter.getCurrent().getX() + 1][hunter.getCurrent().getY()]);
          this.moveSound();
        }
      }

      // if hunter is at the same location as the wumpus
      if (Vertex.matchPosition(wumpus.getCurrent(), hunter.getCurrent())) {
        // remove key listener
        win.removeKeyListener(win.getKeyListeners()[0]);
        // make wumpus visible
        wumpus.setVisible();
        lost = 1;
        System.out.println("You lost because the Wumpus ate your hunter!");
      }

      // if i is pressed
      if (("" + e.getKeyChar()).equalsIgnoreCase("i")) {
        // if hunter is just below the wumpus
        if (hunter.getCurrent().getY() == wumpus.getCurrent().getY() + 1 && hunter.getCurrent().getX() == wumpus.getCurrent().getX()) {
          // remove key listener
          win.removeKeyListener(win.getKeyListeners()[0]);
          // make wumpus visible
          wumpus.setVisible();
          lost = 2;
          System.out.println("You won!");
        }
        // if not, then the hunter has lost
        else {
          this.emptyShot();
        }
      }

      // if k is pressed
      if (("" + e.getKeyChar()).equalsIgnoreCase("k")) {
        // if hunter is just above the wumpus
        if (hunter.getCurrent().getY() == wumpus.getCurrent().getY() - 1 && hunter.getCurrent().getX() == wumpus.getCurrent().getX()) {
          // remove key listener
          win.removeKeyListener(win.getKeyListeners()[0]);
          // make wumpus visible
          wumpus.setVisible();
          lost = 2;
          System.out.println("You won!");
        }
        // if not, then the hunter has lost
        else {
          this.emptyShot();
        }
      }

      // if j is pressed
      if (("" + e.getKeyChar()).equalsIgnoreCase("j")) {
        // if hunter is just to the left of the wumpus
        if (hunter.getCurrent().getX() - 1 == wumpus.getCurrent().getX() && hunter.getCurrent().getY() == wumpus.getCurrent().getY()) {
          // remove key listener
          win.removeKeyListener(win.getKeyListeners()[0]);
          // make wumpus visible
          wumpus.setVisible();
          lost = 2;
          System.out.println("You won!");
        }
        // if not, then the hunter has lost
        else {
          this.emptyShot();
        }
      }

      // if l is pressed
      if (("" + e.getKeyChar()).equalsIgnoreCase("l")) {
        // if hunter is just to the right of the wumpus
        if (hunter.getCurrent().getX() + 1 == wumpus.getCurrent().getX() && hunter.getCurrent().getY() == wumpus.getCurrent().getY()) {
          // remove key listener
          win.removeKeyListener(win.getKeyListeners()[0]);
          // make wumpus visible
          wumpus.setVisible();
          lost = 2;
          System.out.println("You won!");
        }
        // if not, then the hunter has lost
        else {
          this.emptyShot();
        }
      }
    }

    private void moveSound() {
      File sound = new File("./sounds/move.wav");
      try {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(sound));
        clip.start();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    private void emptyShot() {
      // remove key listener
      win.removeKeyListener(win.getKeyListeners()[0]);
      // make wumpus visible
      wumpus.setVisible();
      lost = 3;
      System.out.println("You shot the arrow in an empty room!");
    }

    public void actionPerformed(ActionEvent event) {
      // If the Quit button was pressed
      if (event.getActionCommand().equalsIgnoreCase("Quit")) {
        System.out.println("Quit button clicked");
        state = PlayState.STOP;
      }
      // If the Reset button was pressed
      else if (event.getActionCommand().equalsIgnoreCase("Reset")) {
        System.out.println("Reset button clicked");
        reset();
      }
    }
  } // end class Control

  private JFrame win;
  private LandscapePanel canvas;
  private Landscape scape;
  private int scale;
  private Graph graph;
  private Hunter hunter;
  private Wumpus wumpus;
  private Vertex[][] vertices;
  private int lost;
  private PlayState state;

  private enum PlayState {
    PLAY, STOP
  }

  public HuntTheWumpus() {
    this.state = PlayState.PLAY;
    this.lost = 0;

    this.scale = 64;
    this.vertices = new Vertex[8][8];
    for (int i = 0; i < vertices.length; i++) {
      for (int j = 0; j < vertices[0].length; j++) {
        vertices[i][j] = new Vertex(i, j);
      }
    }

    this.graph = new Graph();
    // for all vertices in vertices
    for (int i = 0; i < vertices.length; i++) {
      for (int j = 0; j < vertices[0].length; j++) {
        // add all adjacent vertices to the current vertex
        if (i > 0) {
          vertices[i][j].connect(vertices[i - 1][j], Vertex.Direction.NORTH);
          graph.addBiEdge(vertices[i][j], vertices[i - 1][j]);
        }
        if (i < vertices.length - 1) {
          vertices[i][j].connect(vertices[i + 1][j], Vertex.Direction.SOUTH);
          graph.addBiEdge(vertices[i][j], vertices[i + 1][j]);
        }
        if (j > 0) {
          vertices[i][j].connect(vertices[i][j - 1], Vertex.Direction.WEST);
          graph.addBiEdge(vertices[i][j], vertices[i][j - 1]);
        }
        if (j < vertices[0].length - 1) {
          vertices[i][j].connect(vertices[i][j + 1], Vertex.Direction.EAST);
          graph.addBiEdge(vertices[i][j], vertices[i][j + 1]);
        }
      }
    }

    // select a vertex at random
    Vertex wumpusVertex = vertices[(int) (Math.random() * vertices.length)][(int) (Math.random() * vertices[0].length)];
    this.wumpus = new Wumpus(wumpusVertex);

    this.graph.shortestPath(wumpusVertex);

    // select a vertex that is not the wumpus and more than 2 away from the wumpus
    Vertex hunterVertex = vertices[(int) (Math.random() * vertices.length)][(int) (Math.random() * vertices[0].length)];
    while(hunterVertex.compareTo(wumpusVertex) <= 2) {
      hunterVertex = vertices[(int) (Math.random() * vertices.length)][(int) (Math.random() * vertices[0].length)];
    }

    this.hunter = new Hunter(hunterVertex);

    this.scape = new Landscape(scale * 8, scale * 8, this.hunter, this.wumpus);
    // add all vertices to landscape
    for (int i = 0; i < vertices.length; i++) {
      for (int j = 0; j < vertices[0].length; j++) {
        this.scape.addBackgroundAgent(vertices[i][j]);
      }
    }

    this.buildgame();
  }

  public void buildgame() {
    // Make the main window
    this.win = new JFrame("Parth's Hunt the Wumpus");
    win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // make the main drawing canvas (this is the usual
    // LandscapePanel we have been using). and put it in the window
    this.canvas = new LandscapePanel(this.scape.getWidth(), this.scape.getHeight());
    this.win.add(this.canvas, BorderLayout.CENTER);
    this.win.pack();

    // make the buttons and put them into the frame below the canvas.
    JButton quit = new JButton("Quit");
    JButton reset = new JButton("Reset");
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    panel.add(reset);
    panel.add(quit);
    this.win.add(panel, BorderLayout.SOUTH);
    this.win.pack();

    // Add key and button controls.
    // We are binding the key control object to the entire window.
    // That means that if a key is pressed while the window is
    // in focus, then control.keyTyped will be executed.
    // Because we are binding quit (the button) to control, control.actionPerformed
    // will
    // be called if the quit button is pressed.
    Control control = new Control();
    this.win.addKeyListener(control);
    this.win.setFocusable(true);
    this.win.requestFocus();
    quit.addActionListener(control);
    reset.addActionListener(control);

    // make it all visible.
    this.win.setVisible(true);
  }

  public void repaint() {
    this.win.repaint();
  }

  public void dispose() {
    this.win.dispose();
  }

  public void reset() {
    this.dispose();
    this.state = PlayState.PLAY;
    this.lost = 0;

    // reset all vertices
    for (int i = 0; i < vertices.length; i++) {
      for (int j = 0; j < vertices[0].length; j++) {
        vertices[i][j] = new Vertex(i, j);
      }
    }

    // new graph because old vertoces have been replaced
    this.graph = new Graph();
    // for all vertices in vertices
    for (int i = 0; i < vertices.length; i++) {
      for (int j = 0; j < vertices[0].length; j++) {
        // add all adjacent vertices to the current vertex
        if (i > 0) {
          vertices[i][j].connect(vertices[i - 1][j], Vertex.Direction.NORTH);
          graph.addBiEdge(vertices[i][j], vertices[i - 1][j]);
        }
        if (i < vertices.length - 1) {
          vertices[i][j].connect(vertices[i + 1][j], Vertex.Direction.SOUTH);
          graph.addBiEdge(vertices[i][j], vertices[i + 1][j]);
        }
        if (j > 0) {
          vertices[i][j].connect(vertices[i][j - 1], Vertex.Direction.WEST);
          graph.addBiEdge(vertices[i][j], vertices[i][j - 1]);
        }
        if (j < vertices[0].length - 1) {
          vertices[i][j].connect(vertices[i][j + 1], Vertex.Direction.EAST);
          graph.addBiEdge(vertices[i][j], vertices[i][j + 1]);
        }
      }
    }

    // select a vertex at random
    Vertex wumpusVertex = vertices[(int) (Math.random() * vertices.length)][(int) (Math.random() * vertices[0].length)];
    this.wumpus = new Wumpus(wumpusVertex);

    this.graph.shortestPath(wumpusVertex);

    // select a vertex that is not the wumpus and more than 2 away from the wumpus
    Vertex hunterVertex = vertices[(int) (Math.random() * vertices.length)][(int) (Math.random() * vertices[0].length)];
    while(hunterVertex.compareTo(wumpusVertex) <= 2) {
      hunterVertex = vertices[(int) (Math.random() * vertices.length)][(int) (Math.random() * vertices[0].length)];
    }

    this.hunter = new Hunter(hunterVertex);

    this.scape = new Landscape(scale * 8, scale * 8, this.hunter, this.wumpus);
    // add all vertices to landscape
    for (int i = 0; i < vertices.length; i++) {
      for (int j = 0; j < vertices[0].length; j++) {
        this.scape.addBackgroundAgent(vertices[i][j]);
      }
    }

    this.buildgame();
  }

  public static void main(String[] argv) throws InterruptedException {
    HuntTheWumpus w = new HuntTheWumpus();
    while (w.state == PlayState.PLAY) {
      w.repaint();
      Thread.sleep(33);
    }
    System.out.println("Disposing window");
    w.dispose();
  }
}

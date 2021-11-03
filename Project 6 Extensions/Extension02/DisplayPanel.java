import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class DisplayPanel {
  BSTMap map;
  int xScaled;
  int yScaled;

  public DisplayPanel(BSTMap map) {
    this.map = map;
    xScaled = 0;
    yScaled = 0;
    this.setBackground(Color.white);
    this.setForeground(Color.black);
  }

  protected void paint(Graphics g) {
    g.setColor(this.getBackground());
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    g.setColor(this.getForeground());

    this.xScaled=5;
    this.yScaled=5;

  }
}

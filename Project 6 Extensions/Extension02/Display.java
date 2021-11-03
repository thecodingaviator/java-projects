import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Display extends JFrame {
  JScrollPane scrollPane;
  DisplayPanel displayPanel;

  public Display(BSTMap map) {
    this.displayPanel=new DisplayPanel(map);
    
  }
}

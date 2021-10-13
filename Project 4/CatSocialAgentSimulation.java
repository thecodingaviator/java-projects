import java.util.Random;

public class CatSocialAgentSimulation {
  public static void main(String[] args) throws InterruptedException {
    // set width and height
    int w=500;
    int h=500;
    // if command line arguments were used, use those
    if(args.length>1) {
      w=Integer.parseInt(args[0]);
      h=Integer.parseInt(args[1]);
    }

    // create a landscape
    Landscape scape=new Landscape(w, h);

    // number of agents
    final int N=200;
    
    Random gen = new Random();
    int radiusOfSensitivity=100;

    // create N agents on landscape scape
    for(int i=0;i<N;i++) {
      scape.addAgent(new CatSocialAgent(gen.nextDouble()*(scape.getWidth() - 20),
			gen.nextDouble() * (scape.getHeight() - 20), gen.nextInt(2), radiusOfSensitivity));
    }

    // create and paint a GUI
    LandscapeDisplay display=new LandscapeDisplay(scape);
    display.repaint();

    // update and repaint 200 times
    for(int i=0; i<200; i++) {
      for(Agent e: scape.getList()) {
        e.updateState(scape);
      }
      display.repaint();
      Thread.sleep(50);
    }

  }
}

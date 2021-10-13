import java.util.Random;

public class CatSocialAgentSimulation {
  public static void main(String[] args) throws InterruptedException {
    int w=500;
    int h=500;
    if(args.length>1) {
      w=Integer.parseInt(args[0]);
      h=Integer.parseInt(args[1]);
    }

    Landscape scape=new Landscape(w, h);

    final int N=200;
    
    Random gen = new Random();

    int radiusOfSensitivity=100;

    for(int i=0;i<N;i++) {
      scape.addAgent(new CatSocialAgent(gen.nextDouble()*(scape.getWidth() - 20),
			gen.nextDouble() * (scape.getHeight() - 20), gen.nextInt(2), radiusOfSensitivity));
    }

    LandscapeDisplay display=new LandscapeDisplay(scape);
    display.repaint();

    for(int i=0; i<200; i++) {
      for(Agent e: scape.getList()) {
        e.updateState(scape);
      }
      display.repaint();
      //Thread.sleep(50);
    }

  }
}

/*
 * Began 12/14/2012
 * @Chinmay Patwardhan
 */

   import javax.swing.JFrame;
    public class Driver
   {
       public static void main(String[] args) throws Exception
      { 
         JFrame frame = new JFrame("Pokemon Brown Version");
         frame.setSize(Constants.WIDTH, Constants.HEIGHT);
         frame.setLocation(200, 100);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new Panel());
		 frame.setResizable(false);//won't accidentally change size
         frame.setVisible(true);
      }
   }
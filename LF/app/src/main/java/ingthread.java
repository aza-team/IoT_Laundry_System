/**
 * Created by kmnii on 2017-05-27.
 */

public class ingthread extends Thread{
    public void run(){
        try {
            sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

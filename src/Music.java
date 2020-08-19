package call;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.*;
public class Music {
	private static Player player =null;
	public void getMusic() {
	try {
	    File f = new File("E:\\dianmingqi\\call\\lib\\xiaqi2.mp3");
	    FileInputStream fileInputStream = new FileInputStream(f);
	    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
	    player = new Player(bufferedInputStream);
	    player.play();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import miscellaneous.ObjectOnScreen;
import chargeInfo.Position;

public class HomeScreen extends ObjectOnScreen{

	BufferedImage img = null;
	
	public HomeScreen(String img_location){		
		try {
		    img = ImageIO.read(new File(img_location));
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		super.display_number = 0;
	}
	
	public BufferedImage get_image(){
		return img;
	}
	
	public boolean is_inside(int x, int y){
		if(x > 242 && x < 242 + 319){
			if(y > 318 && y < 318 + 108){
				return true;
			}
		}
		return false;
	}
	
	public boolean is_inside(Position p){
		return is_inside((int)p.get_x(), (int)p.get_y());
	}
	
	
}

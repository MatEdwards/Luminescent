package astechzgo.luminescent.main;

import static astechzgo.luminescent.utils.DisplayUtils.SCREEN_HEIGHT;
import static astechzgo.luminescent.utils.DisplayUtils.SCREEN_WIDTH;
import static astechzgo.luminescent.utils.DisplayUtils.setDisplayMode;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import astechzgo.luminescent.rendering.Player;

public class Luminescent
{
	
	public static Player thePlayer = new Player();
	public static double moveSpeed = 0.5;
	
	public static long lastMove = System.currentTimeMillis();
	
	public static void Init()
	{
		try {
			Cursor emptyCursor = new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null);
			Mouse.setNativeCursor(emptyCursor);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void Shutdown()
	{
		
	}
	
	public static void Tick()
	{
		thePlayer.Render();
		
		int multiplier = (int) (System.currentTimeMillis() - lastMove);
		lastMove = System.currentTimeMillis();
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			Luminescent.moveSpeed = 0.88;
		}
		else
		{
			Luminescent.moveSpeed = 0.5;
		}
		
		double speed = Luminescent.moveSpeed * multiplier;
		
		if(thePlayer.getPosX() < 0)
		{
			thePlayer.setPosX(SCREEN_WIDTH);
		}
		if(thePlayer.getPosX() > SCREEN_WIDTH)
		{
			thePlayer.setPosX(0);
		}
		
		if(thePlayer.getPosY() < 0)
		{
			thePlayer.setPosY(SCREEN_HEIGHT);
		}
		if(thePlayer.getPosY() > SCREEN_HEIGHT)
		{
			thePlayer.setPosY(0);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			thePlayer.setPosY(thePlayer.getPosY() + speed);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			thePlayer.setPosY(thePlayer.getPosY() - speed);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			thePlayer.setPosX(thePlayer.getPosX() + speed);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			thePlayer.setPosX(thePlayer.getPosX() - speed);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			Display.destroy();
			System.exit(0);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F11))
		{
			if(Display.isFullscreen()) {
				setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
			}
			else {
				setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, true);
			}
		}
	}
	
}
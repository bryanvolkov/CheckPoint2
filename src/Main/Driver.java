
package Main;

import java.io.FileNotFoundException;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Driver {

    public static final int DISPLAY_HEIGHT = 480;
    public static final int DISPLAY_WIDTH = 640;
    public static final Logger LOGGER = Logger.getLogger(Driver.class.getName());
    public String line;

    // method: start
    // ;Purpose: This method starts everything that is neeed to have OpenGL
    public void start() {
        try {
            createWindow();
            initGL();
            render();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method: initGL()
    // Purpose: This method initializes all the OpenGL tools
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-DISPLAY_WIDTH/2, DISPLAY_WIDTH/2, -DISPLAY_HEIGHT/2, DISPLAY_HEIGHT/2, 100, -100);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        glEnable(GL_DEPTH_TEST);
    }

    // method: CreateWindow
    // purpose: This method creastes the window for the project
    private void createWindow() throws Exception {
        Display.setFullscreen(false);
        Display.setDisplayMode(new DisplayMode(640, 480));
        Display.setTitle("Check Point 2");
        Display.create();
    }

    // method: render
    // purpose: This method keeps the window running and also paints anything that it is commanded on the window
    private void render() throws FileNotFoundException {

        while (!(Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))) {
            try {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glLoadIdentity();
                glPointSize(1);

                Display.update();
                Display.sync(60);
            } catch (Exception e) {
            }
        }
        Display.destroy();
    }
    
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.start();
    }
    
}
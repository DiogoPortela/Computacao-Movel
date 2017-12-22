package ipca.hrem.com.ResourceManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.HashMap;

public abstract class FontManager {
    //-------------------------Variables-------------------------//
    static HashMap<String, Label.LabelStyle> loadedFonts;

    //-------------------------Functions-------------------------//

    public static void Start(){
        loadedFonts = new HashMap<String, Label.LabelStyle>();
    }

    public static boolean containsFont(String fontName){
        if(loadedFonts.containsKey(fontName)){
            return true;
        }
        return false;
    }
    public static Label.LabelStyle getFont(String fontName){
        if(loadedFonts.containsKey(fontName)){
            return loadedFonts.get(fontName);
        }
        return null;
    }
    public static Label.LabelStyle loadFont(String fontName, int size, Color color){
        if(!loadedFonts.containsKey(fontName)){
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontName));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = size;
            //parameter.borderWidth = 1;
            parameter.color = color;
            //parameter.shadowOffsetX = 0;
            //parameter.shadowOffsetY = 0;
            //parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
            BitmapFont font = generator.generateFont(parameter);
            generator.dispose();
            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = font;

            loadedFonts.put(fontName, labelStyle);
            return labelStyle;
        }
        return loadedFonts.get(fontName);
    }
    public static boolean unloadFont(String fontName){
        if(loadedFonts.containsKey(fontName)){
            loadedFonts.remove(fontName);
            return true;
        }
        return false;
    }

    public static void dispose(){
        loadedFonts.clear();
    }
}

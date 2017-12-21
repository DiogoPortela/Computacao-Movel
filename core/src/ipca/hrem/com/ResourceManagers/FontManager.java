package ipca.hrem.com.ResourceManagers;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.HashMap;

public abstract class FontManager {
    /*//-------------------------Variables-------------------------//
    static HashMap<String, BitmapFont> loadedFonts;

    //-------------------------Functions-------------------------//

    public static void Start(){
        loadedFonts = new HashMap<String, BitmapFont>();
    }

    public static boolean containsFont(String fontName){
        if(loadedFonts.containsKey(fontName)){
            return true;
        }
        return false;
    }
    public static BitmapFont getFont(String fontName){
        if(loadedFonts.containsKey(fontName)){
            return loadedFonts.get(fontName);
        }
        return null;
    }
    public static BitmapFont loadFont(String fontName){
        if(!loadedFonts.containsKey(fontName)){
            loadedFonts.put(fontName, new BitmapFont(new FileHandle(fontName)));
            return loadedFonts.get(fontName);
        }
        return loadedFonts.get(fontName);
    }
    public static boolean unloadTexture(String textureName){
        if(loadedTextures.containsKey(textureName)){
            loadedTextures.get(textureName).dispose();
            loadedTextures.remove(textureName);
            return true;
        }
        return false;
    }

    public static void dispose(){
        for ( Map.Entry<String, Texture> kv: loadedTextures.entrySet()) {
            kv.getValue().dispose();
        }
        loadedTextures.clear();
    }*/
}

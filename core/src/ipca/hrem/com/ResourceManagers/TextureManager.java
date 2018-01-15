package ipca.hrem.com.ResourceManagers;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

//Class that hold all textures that are currently being displayed.
public abstract class TextureManager {
    //-------------------------Variables-------------------------//
    static HashMap<String, Texture> loadedTextures;

    //-------------------------Functions-------------------------//

    public static void Start(){
        loadedTextures = new HashMap<String, Texture>();
    }

    public static boolean containsTexture(String textureName){
        if(loadedTextures.containsKey(textureName)){
            return true;
        }
        return false;
    }
    public static Texture getTexture(String textureName){
        if(loadedTextures.containsKey(textureName)){
            return loadedTextures.get(textureName);
        }
        return null;
    }
    public static Texture loadTexture(String textureName){
        if(!loadedTextures.containsKey(textureName)){
            loadedTextures.put(textureName, new Texture(textureName));
            return loadedTextures.get(textureName);
        }
        return loadedTextures.get(textureName);
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
    }
}

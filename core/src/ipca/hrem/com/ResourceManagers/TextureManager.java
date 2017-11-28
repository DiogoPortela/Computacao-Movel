package ipca.hrem.com.ResourceManagers;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

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
            return (Texture) loadedTextures.get(textureName);
        }
        return null;
    }
    public static boolean loadTexture(String textureName){
        if(!loadedTextures.containsKey(textureName)){
            loadedTextures.put(textureName, new Texture(textureName));
            return true;
        }
        return false;
    }
    public static boolean unloadTexture(String textureName){
        if(loadedTextures.containsKey(textureName)){
            loadedTextures.remove(textureName);
            return true;
        }
        return false;
    }

    public static void dispose(){
        loadedTextures.clear();
    }
}

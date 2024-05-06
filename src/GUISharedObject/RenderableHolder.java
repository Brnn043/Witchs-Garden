package GUISharedObject;

import Items.Character.Player;
import Items.Character.Slime;
import Items.Veggies.BaseVeggies;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
    private static final RenderableHolder instance = new RenderableHolder();

    private List<IRenderable> entities;
    private Comparator<IRenderable> comparator;
    public static Image playerSprite;
//    public static AudioClip explosionSound;

    static {
        loadResource();
    }

    public RenderableHolder() {
        entities = new ArrayList<IRenderable>();
        comparator = (IRenderable o1, IRenderable o2) -> {
            if (o1.getZ() > o2.getZ())
                return 1;
            return -1;
        };
    }

    public static RenderableHolder getInstance() {
        return instance;
    }

    // use static !!
    public static void loadResource() {
        playerSprite = new Image(ClassLoader.getSystemResource("Broom.png").toString());
//        explosionSound = new AudioClip(ClassLoader.getSystemResource("Explosion.wav").toString());
    }

    public void add(IRenderable entity) {
        System.out.println("add");
        entities.add(entity);
        Collections.sort(entities, comparator);
        for(IRenderable x: entities){
            if(x instanceof Player) System.out.println("player");
            if(x instanceof Slime) System.out.println("slime");
            if(x instanceof BaseVeggies) System.out.println("veggie");
        }
    }

    public void update() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).isDestroyed())
                entities.remove(i);
        }
    }

    public List<IRenderable> getEntities() {
        return entities;
    }
}

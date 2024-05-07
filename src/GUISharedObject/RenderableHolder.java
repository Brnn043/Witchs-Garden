package GUISharedObject;

import Items.Character.Player;
import Items.Character.Slime;
import Items.Inventory.Broom;
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
    public static Image witchSprite;
    public static Image witchWalkSprite;
    public static Image witchBroomSprite;
    public static Image witchWalkBroomSprite;
    public static Image witchAttackSprite;
    public static Image broomSprite;
//    public static AudioClip explosionSound;
    public static Image sunnyBackground;

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
        witchSprite = new Image(ClassLoader.getSystemResource("Witch/witch.png").toString());
        witchWalkSprite = new Image(ClassLoader.getSystemResource("Witch/witch_walk.GIF").toString());
        witchBroomSprite = new Image(ClassLoader.getSystemResource("Witch/witch_broom.png").toString());
        witchWalkBroomSprite = new Image(ClassLoader.getSystemResource("Witch/witch_walk_broom.GIF").toString());
        witchAttackSprite = new Image(ClassLoader.getSystemResource("Witch/witch_attack.GIF").toString());
        broomSprite = new Image(ClassLoader.getSystemResource("Broom.png").toString());
//        explosionSound = new AudioClip(ClassLoader.getSystemResource("Explosion.wav").toString());
    }

    public void add(IRenderable entity) {
        if(entity instanceof Player) System.out.println("add player");
        if(entity instanceof Broom) System.out.println("add broom");
        if(entity instanceof Slime) System.out.println("add slime");
        if(entity instanceof BaseVeggies) System.out.println("add veggie");

        entities.add(entity);
        Collections.sort(entities, comparator);
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

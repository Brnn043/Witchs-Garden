package GUISharedObject;

import Items.Character.Player;
import Items.Character.Slime;
import Items.Inventory.Broom;
import Items.Veggies.BaseVeggies;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
    private static final RenderableHolder instance = new RenderableHolder();
    private final List<IRenderable> backgroundEntities;
    private final List<IRenderable> entities;
    private final Comparator<IRenderable> comparator;
    public static Image witchSprite;
    public static Image witchWalkSprite;
    public static Image witchBroomSprite;
    public static Image witchWalkBroomSprite;
    public static Image witchAttackSprite;
    public static Image witchRightSprite;
    public static Image witchRightBroomSprite;
    public static Image witchLeftSprite;
    public static Image witchLeftBroomSprite;
    public static Image broomSprite;
    public static Image normalSlimeSprite;
    public static Image hitHardSlimeSprite;
    public static Image speedSlimeSprite;
    public static Image rainbowDrakeIdleSprite;
    public static Image rainbowDrakeDieSprite;
    public static Image redFlowerIdleSprite;
    public static Image redFlowerDieSprite;
    public static Image daffodilIdleSprite;
    public static Image daffodilDieSprite;
    public static AudioClip hitSound;
    public static AudioClip collectSound;
    public static AudioClip clockSound;
    public static AudioClip mainManuSong;
    public static AudioClip storySong;
    public static AudioClip gameSong;

    static {
        loadResource();
    }

    public RenderableHolder() {
        entities = new ArrayList<IRenderable>();
        backgroundEntities = new ArrayList<>();
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
        witchSprite = new Image(ClassLoader.getSystemResource("Witch/witch.PNG").toString());
        witchWalkSprite = new Image(ClassLoader.getSystemResource("Witch/witch_walk.GIF").toString());
        witchBroomSprite = new Image(ClassLoader.getSystemResource("Witch/witch_broom.PNG").toString());
        witchWalkBroomSprite = new Image(ClassLoader.getSystemResource("Witch/witch_walk_broom.GIF").toString());
        witchAttackSprite = new Image(ClassLoader.getSystemResource("Witch/witch_attack.GIF").toString());
        witchRightSprite = new Image(ClassLoader.getSystemResource("Witch/witch_r.GIF").toString());
        witchRightBroomSprite = new Image(ClassLoader.getSystemResource("Witch/witch_r_b.GIF").toString());
        witchLeftSprite = new Image(ClassLoader.getSystemResource("Witch/witch_l.GIF").toString());
        witchLeftBroomSprite = new Image(ClassLoader.getSystemResource("Witch/witch_l_b.GIF").toString());
        broomSprite = new Image(ClassLoader.getSystemResource("Broom/AnimatedBroom.gif").toString());
        normalSlimeSprite = new Image(ClassLoader.getSystemResource("Slime/Slime1.png").toString());
        hitHardSlimeSprite = new Image(ClassLoader.getSystemResource("Slime/Slime3.png").toString());
        speedSlimeSprite = new Image(ClassLoader.getSystemResource("Slime/Slime2.png").toString());
        rainbowDrakeIdleSprite = new Image(ClassLoader.getSystemResource("Veggie/RainbowDrake_Idle.gif").toString());
        rainbowDrakeDieSprite = new Image(ClassLoader.getSystemResource("Veggie/RainbowDrake_Dying.gif").toString());
        redFlowerIdleSprite = new Image(ClassLoader.getSystemResource("Veggie/RedFlower_Idle.gif").toString());
        redFlowerDieSprite = new Image(ClassLoader.getSystemResource("Veggie/RedFlower_Dying.gif").toString());
        daffodilIdleSprite = new Image(ClassLoader.getSystemResource("Veggie/Daffodil_Idle.gif").toString());
        daffodilDieSprite = new Image(ClassLoader.getSystemResource("Veggie/Daffodil_Dying.gif").toString());
        hitSound = new AudioClip(ClassLoader.getSystemResource("Sound/hit.wav").toString());
        collectSound = new AudioClip(ClassLoader.getSystemResource("Sound/collect.wav").toString());
        clockSound = new AudioClip(ClassLoader.getSystemResource("Sound/clock.wav").toString());
        mainManuSong = new AudioClip(ClassLoader.getSystemResource("Sound/mainManuSong.mp3").toString());
        storySong = new AudioClip(ClassLoader.getSystemResource("Sound/storySong.mp3").toString());
        gameSong = new AudioClip(ClassLoader.getSystemResource("Sound/gameSong.mp3").toString());
    }

    public void add(IRenderable entity) {
        if(entity instanceof Player) System.out.println("add player");
        if(entity instanceof Broom) System.out.println("add broom");
        if(entity instanceof Slime) System.out.println("add slime");
        if(entity instanceof BaseVeggies) System.out.println("add veggie");

        entities.add(entity);
        Collections.sort(entities, comparator);
    }

    public void addBackground(IRenderable entity) {
        backgroundEntities.add(entity);
        Collections.sort(backgroundEntities, comparator);
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

    public List<IRenderable> getBackgroundEntities() { return backgroundEntities; }
}

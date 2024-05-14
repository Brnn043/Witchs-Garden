package GUISharedObject;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// this class is used to manage resources and entities
public class RenderableHolder {
    private static RenderableHolder instance = new RenderableHolder();
    private ArrayList<Entity> backgroundEntities;
    private ArrayList<Entity> entities;
    private Comparator<Entity> comparator;
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
    public static Image redFlowerIdleSprite;
    public static Image daffodilIdleSprite;
    public static AudioClip hitSound;
    public static AudioClip collectSound;
    public static AudioClip clockSound;
    public static AudioClip mainMenuSong;
    public static AudioClip storySong;
    public static AudioClip gameSong;

    static {
        loadResource();
    }

    public RenderableHolder() {
        entities = new ArrayList<Entity>();
        backgroundEntities = new ArrayList<Entity>();
        comparator = (Entity o1, Entity o2) -> {
            if (o1.getZ() > o2.getZ())
                return 1;
            return -1;
        };
    }

    public static void loadResource() {
        //loading images
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
        redFlowerIdleSprite = new Image(ClassLoader.getSystemResource("Veggie/RedFlower_Idle.gif").toString());
        daffodilIdleSprite = new Image(ClassLoader.getSystemResource("Veggie/Daffodil_Idle.gif").toString());

        //loading sounds
        hitSound = new AudioClip(ClassLoader.getSystemResource("Sound/hit.wav").toString());
        collectSound = new AudioClip(ClassLoader.getSystemResource("Sound/collect.wav").toString());
        clockSound = new AudioClip(ClassLoader.getSystemResource("Sound/clock.wav").toString());
        mainMenuSong = new AudioClip(ClassLoader.getSystemResource("Sound/mainMenuSong.mp3").toString());
        storySong = new AudioClip(ClassLoader.getSystemResource("Sound/storySong.mp3").toString());
        gameSong = new AudioClip(ClassLoader.getSystemResource("Sound/gameSong.mp3").toString());
    }

    public void add(Entity entity) {
        entities.add(entity);
        Collections.sort(entities, comparator);
    }

    public void addBackground(Entity entity) {
        backgroundEntities.add(entity);
        Collections.sort(backgroundEntities, comparator);
    }

    public void update() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).isDestroyed())
                entities.remove(i);
        }
    }

    public static RenderableHolder getInstance() { return instance; }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getBackgroundEntities() { return backgroundEntities; }
}

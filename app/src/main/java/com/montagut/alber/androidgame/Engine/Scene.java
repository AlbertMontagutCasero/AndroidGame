package com.montagut.alber.androidgame.Engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.SparseIntArray;
import android.widget.Toast;

import com.montagut.alber.androidgame.Engine.characters.Booster;
import com.montagut.alber.androidgame.Engine.characters.Coin;
import com.montagut.alber.androidgame.Engine.characters.Crab;
import com.montagut.alber.androidgame.Engine.characters.Door;
import com.montagut.alber.androidgame.Engine.characters.Enemy;

import com.montagut.alber.androidgame.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Scene
{
    private GameEngine gameEngine;
    private String scene[];
    private Paint paint;

    private int sceneWidth;
    private int sceneHeight;
    private SparseIntArray CHARS;
    private String GROUND;
    private String WALLS;
    private int WATERLEVEL;
    private int SKY;
    private int WATERSKY;
    private int WATER;

    private List<Coin> coins;
    private List<Enemy> enemies;
    private List<Booster> boosters;
    private List<Door> doors;

    Scene(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        paint = new Paint();
        CHARS = new SparseIntArray();
        WATERLEVEL = 999;
        coins = new ArrayList<>();
        enemies = new ArrayList<>();
        boosters = new ArrayList<>();
        doors = new ArrayList<>();
    }

    public List<Coin> getCoins()
    {
        return coins;
    }

    public List<Enemy> getEnemies()
    {
        return enemies;
    }

    public List<Booster> getBoosters()
    {
        return boosters;
    }

    public List<Door> getDoors()
    {
        return doors;
    }

    private void reset()
    {
        coins.clear();
        enemies.clear();
        boosters.clear();
        doors.clear();
        CHARS.clear();
    }

    public void changeToLevel(int resource)
    {
        reset();
        loadFromFile(resource);
    }


    void loadFromFile(int resource)
    {
        InputStream res = gameEngine.getContext().getResources().openRawResource(resource);
        BufferedReader reader = new BufferedReader(new InputStreamReader(res));
        List<String> lines = new ArrayList<>();
        try
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.isEmpty())
                {
                    continue;
                }
                line = line.trim();
                if (!line.contains("="))
                {
                    continue;                  // NO VALID LINE
                }
                if (line.startsWith("="))
                {
                    continue;                 // COMMENT
                }
                String[] parts = line.split("=", 2);
                String cmd = parts[0].trim();
                String args = parts[1].trim();
                String[] parts2;
                switch (cmd)
                {
                    case "SCENE":
                        lines.add(args);
                        break;
                    case "CHARS":
                        parts2 = args.split(" ");
                        for (String def : parts2)
                        {
                            String[] item = def.split("=");
                            if (item.length != 2)
                            {
                                continue;
                            }
                            char c = item[0].trim().charAt(0);
                            int idx = Integer.parseInt(item[1].trim());
                            CHARS.put(c, idx);
                        }
                        break;
                    case "GROUND":
                        GROUND = args;
                        break;
                    case "WALLS":
                        WALLS = args;
                        break;
                    case "WATER":
                        parts2 = args.split(",");
                        if (parts2.length != 4)
                        {
                            continue;
                        }
                        WATERLEVEL = Integer.parseInt(parts2[0].trim());
                        SKY = Integer.parseInt(parts2[1].trim());
                        WATERSKY = Integer.parseInt(parts2[2].trim());
                        WATER = Integer.parseInt(parts2[3].trim());
                        break;
                    case "COIN":
                        parts2 = args.split(",");
                        if (parts2.length != 2)
                        {
                            continue;
                        }
                        int coinX = Integer.parseInt(parts2[0].trim()) * 16;
                        int coinY = Integer.parseInt(parts2[1].trim()) * 16;
                        Coin coin = new Coin(gameEngine, coinX, coinY);
                        coins.add(coin);
                        break;
                    case "BOOSTER":
                        parts2 = args.split(",");
                        if (parts2.length != 3)
                        {
                            continue;
                        }
                        int boosterX = Integer.parseInt(parts2[0].trim()) * 16;
                        int boosterY = Integer.parseInt(parts2[1].trim()) * 16;
                        int duration = Integer.parseInt(parts2[2].trim());
                        Booster booster = new Booster(gameEngine, boosterX, boosterY, duration);
                        boosters.add(booster);
                        break;
                    case "DOOR":
                        parts2 = args.split(",");
                        if (parts2.length != 2)
                        {
                            continue;
                        }
                        int doorX = Integer.parseInt(parts2[0].trim()) * 16;
                        int doorY = Integer.parseInt(parts2[1].trim()) * 16;
                        //TODO indicate scene to load from file
                        // String sceneToLoad = parts2[2].trim();
                        Door door = new Door(gameEngine, doorX, doorY, R.raw.mini);
                        doors.add(door);
                        break;
                    case "CRAB":
                        parts2 = args.split(",");
                        if (parts2.length != 3)
                        {
                            continue;
                        }
                        int crabX0 = Integer.parseInt(parts2[0].trim()) * 16;
                        int crabX1 = Integer.parseInt(parts2[1].trim()) * 16;
                        int crabY = Integer.parseInt(parts2[2].trim()) * 16;
                        Crab crab = new Crab(gameEngine, crabX0, crabX1, crabY);
                        enemies.add(crab);
                        break;
                }
            }
            scene = lines.toArray(new String[0]);
            reader.close();
            sceneHeight = scene.length;
            sceneWidth = scene[0].length();
        } catch (IOException e)
        {
            Toast.makeText(gameEngine.getContext(), "Error loading scene:" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean isGround(int row, int col)
    {
        if (row < 0)
        {
            return false;
        }
        if (row >= sceneHeight)
        {
            return false;
        }
        if (col < 0)
        {
            return false;
        }
        if (col >= sceneWidth)
        {
            return false;
        }
        char sc = scene[row].charAt(col);
        return (GROUND.indexOf(sc) != -1);
    }

    public boolean isWall(int row, int col)
    {
        if (row < 0)
        {
            return false;
        }
        if (row >= sceneHeight)
        {
            return false;
        }
        if (col < 0)
        {
            return false;
        }
        if (col >= sceneWidth)
        {
            return false;
        }
        char sc = scene[row].charAt(col);
        return (WALLS.indexOf(sc) != -1);
    }

    public int getSceneWidth()
    {
        return sceneWidth;
    }

    public int getSceneHeight()
    {
        return sceneHeight;
    }

    public int getWaterLevel()
    {
        return WATERLEVEL;
    }

    public int getWidth()
    {
        return sceneWidth * 16;
    }

    public int getHeight()
    {
        return sceneHeight * 16;
    }

    // Scene physics
    void physics(int delta)
    {
        for (Coin coin : coins)
        {
            coin.physics(delta);
        }

        for (Enemy enemy : enemies)
        {
            enemy.physics(delta);
        }

        for (Booster booster : boosters)
        {
            booster.physics(delta);
        }

        for (Door door : doors)
        {
            door.physics(delta);
        }
    }

    // Scene draw
    void draw(Canvas canvas, int offsetX, int offsetY, int screenWidth, int screenHeight)
    {
        if (scene == null)
        {
            return;
        }

        // Compute which tiles will be drawn
        int l = Math.max(0, offsetX / 16);
        int r = Math.min(scene[0].length(), offsetX / 16 + screenWidth / 16 + 2);
        int t = Math.max(0, offsetY / 16);
        int b = Math.min(scene.length, offsetY / 16 + screenHeight / 16 + 2);

        // Do the x-y loops over the visible scene
        for (int y = t; y < b; y++)
        {

            // Compute the background index (sky / water)
            int bgIdx = SKY;
            if (y == WATERLEVEL)
            {
                bgIdx = WATERSKY;
            }
            else if (y > WATERLEVEL)
            {
                bgIdx = WATER;
            }
            Bitmap bgBitmap = gameEngine.getBitmap(bgIdx);

            for (int x = l; x < r; x++)
            {
                // Draw the background tile
                canvas.drawBitmap(bgBitmap, x * 16, y * 16, paint);

                // Compute the bitmap index for the current tile
                char c = scene[y].charAt(x);
                int index = CHARS.get(c);
                if (index == SKY)
                {
                    continue;
                }
                Bitmap bitmap = gameEngine.getBitmap(index);
                canvas.drawBitmap(bitmap, x * 16, y * 16, paint);
            }
        }

        for (Door door : doors)
        {
            door.draw(canvas);
        }

        for (Coin coin : coins)
        {
            coin.draw(canvas);
        }

        for (Booster booster : boosters)
        {
            booster.draw(canvas);
        }

        for (Enemy enemy : enemies)
        {
            enemy.draw(canvas);
        }


    }
}

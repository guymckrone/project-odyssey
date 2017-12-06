package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by guymc on 11/29/2017.
 */

public class GameScreen implements Screen, GestureDetector.GestureListener {
    final ProjectOdyssey game;
    private Stage stage;
    int[] inventory = new int[10];
    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch;
    private ImageButton iceButton;
    private ImageButton marketButton;
    public int ice;
    OrthographicCamera camera;
    float playTime;
    int playIntSec;
    int playIntHour;
    String gameClock;


    public GameScreen(final ProjectOdyssey game) {
        this.game = game;
        Gdx.graphics.getDeltaTime();
        playTime = 0;
        playIntSec = 0;
        playIntHour = 0;
        ice = 0;
        stage = new Stage();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Button skin
        Skin playButtonSkin = new Skin();
        playButtonSkin.add("iceButton", new Texture("buttons/ice_cube.png"));

        // Create button style
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = playButtonSkin.getDrawable("iceButton"); // Unpressed
        playButtonStyle.imageDown = playButtonSkin.getDrawable("iceButton"); // Pressed

        // Play button
        iceButton = new ImageButton(playButtonStyle);
        int buttonSize = (int) (100 * Gdx.graphics.getDensity());
        iceButton.setSize(buttonSize, buttonSize);
        int width = (int) ((Gdx.graphics.getWidth() - iceButton.getWidth())/2);
        int height = (int) ((Gdx.graphics.getHeight() - iceButton.getHeight())/4);
        iceButton.setBounds(width, height, iceButton.getWidth(), iceButton.getHeight());
        iceButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ice++;
                //iceButton.setDisabled(false);

            }
        });
        stage.addActor(iceButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin marketButtonSkin = new Skin();
        marketButtonSkin.add("marketButton", new Texture("buttons/market_arrow.png"));

        // Create button style
        ImageButton.ImageButtonStyle marketButtonStyle = new ImageButton.ImageButtonStyle();
        marketButtonStyle.imageUp = marketButtonSkin.getDrawable("marketButton"); // Unpressed
        marketButtonStyle.imageDown = marketButtonSkin.getDrawable("marketButton"); // Pressed

        // Play button
        marketButton = new ImageButton(marketButtonStyle);
        int buttonSize2 = (int) (100 * Gdx.graphics.getDensity());
        marketButton.setSize(buttonSize2, buttonSize2);
        int width2 = (int) ((Gdx.graphics.getWidth() - marketButton.getWidth())/2);
        int height2 = (int) ((Gdx.graphics.getHeight() - marketButton.getHeight())/2);
        marketButton.setBounds(width2, height2, marketButton.getWidth(), marketButton.getHeight());
        marketButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Market(game));
                //marketButton.setDisabled(false);

            }
        });
        stage.addActor(marketButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

    }

    @Override
    public void render(float delta) {
        playTime = playTime + Gdx.graphics.getDeltaTime();
        playIntSec = (int)playTime;
        /*if(playIntSec == 60){
            playIntSec = 0;
            playIntHour++;
        }*/
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        //game.font.draw(game.batch, "Time: " + playIntHour + ":" + playIntSec, 100, 200);
        game.font.draw(game.batch, "Welcome to your Odyssey", 100, 150);
        game.font.draw(game.batch, "You have " + ice + " ice.", 100, 100);
        game.batch.end();

        batch.begin();
        iceButton.draw(batch, 1);
        batch.end();
        camera.update();

        if(ice >= 10) {
            batch.begin();
            marketButton.draw(batch, 1);
            batch.end();
        }



    }

    public void inventory(){

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}

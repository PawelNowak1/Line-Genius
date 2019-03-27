package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Ball;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.CurrentLine;
import com.pawelinkball.game.entities.DestroyableBox;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.Line;
import com.pawelinkball.game.entities.StartBallTexture;
import com.pawelinkball.game.input.Input;
import com.pawelinkball.game.scenes.Hud;

import java.util.Random;


/**
 * Created by Pawel on 07.07.2017.
 */
public abstract class AbstractLevelClass extends AbstractGameScreen{

    public OrthographicCamera cam;

    protected Input input;
    protected Array<Line> lines;
    protected Array<Box> boxes;
    protected Array<Ball> balls;
    protected Array<Hole> holes;
    protected Array<DestroyableBox> destroyableBoxes;
    protected Array<StartBallTexture> startTextures;
    protected ShapeRenderer shapeRenderer;
    protected Random rand;

    protected World world;
    protected Hud hud;

    protected Array<Body> bodiesBall;
    protected BodyDef bodyDefBall;
    protected FixtureDef fixtureDefBall;
    public CurrentLine currentLine;
    public double[] currentRadOfBalls;
    private float[] speedX, speedY;
    private boolean hit = false;
    private boolean win = true;
    private int ballCounter;
    private int destroyBody = -1, destroyBox1 = -1, destroyBox2 = -1;
    private long currentScore;

    private long levelTime;
    public long startTime = 0;
    private Preferences preferences;

    public AbstractLevelClass(Game game){
        super(game);
        currentLine = new CurrentLine();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 6, 10);
        lines = new Array<Line>();
        boxes = new Array<Box>();
        holes = new Array<Hole>();
        destroyableBoxes = new Array<DestroyableBox>();
        startTextures = new Array<StartBallTexture>();
        preferences = Gdx.app.getPreferences("game");

        ballCounter = 0;
        currentScore = preferences.getLong("CurrentScore");
        currentRadOfBalls = new double[20];
        speedX = new float[20];
        speedY = new float[20];

        //startTime = System.currentTimeMillis();
        rand = new Random();
        input = new Input(this);
        Gdx.input.setInputProcessor(input);
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(input);
        shapeRenderer = new ShapeRenderer();
        balls = new Array<Ball>();
        bodiesBall = new Array<Body>();

        hud = new Hud(batch, arcadeModeOn);
        shapeRenderer.setAutoShapeType(true);
        initBox2DBall();
    }

    @Override
    public void render(float deltaTime) {

        handleBackButton();
        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Gdx.gl.glLineWidth(balls.get(0).getLineWidth());
        shapeRenderer.setColor(Color.LIGHT_GRAY);
        shapeRenderer.setProjectionMatrix(cam.combined);

        for(Box box : boxes)
            box.draw(shapeRenderer);

        shapeRenderer.end();
        batch.begin();
        for(Hole hole : holes)
            hole.draw(batch);
        for(StartBallTexture startBallTexture : startTextures)
            startBallTexture.draw(batch);
        for(DestroyableBox destroyableBox : destroyableBoxes) {
            if (destroyableBox.isActive())
                destroyableBox.draw(batch);
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderLines();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i < balls.size; i++) {
            hud.renderBallIcon(balls.get(i).getColor(), balls.get(i).getRay(), i, shapeRenderer, balls.size);
            if(balls.get(i).isActive())
                balls.get(i).draw(shapeRenderer, bodiesBall.get(i).getPosition());
        }
        shapeRenderer.setColor(Color.LIGHT_GRAY);
        shapeRenderer.end();

        renderHud();
        batch.setColor(Color.WHITE);

        for (Ball ball : balls)
            ball.setLastCollisionPoint(new Vector2(-4, -3));
        if(win) {
            world.step(1 / 60f, 6, 2);
        }
        else {
            if(Gdx.input.justTouched()){
                preferences.flush();
                if(ballCounter >= bodiesBall.size)
                    gameWin();
                else
                    restartCurrentLevel();
            }
        }

        if(destroyBody != -1){
                bodiesBall.get(destroyBody).setTransform(-20, -20, 10);
                bodiesBall.get(destroyBody).setActive(false);
                destroyBody = -1;
        }

        destroyBox();
    }

    protected void handleBackButton(){
        if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.BACK) || Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)){
            if(arcadeModeOn)
                game.setScreen(new StartLevel(game));
            else
                game.setScreen(new MainMenuScreen(game));
        }
    }
    private void destroyBox(){
        if(destroyBox1 == -1)
            return;
        destroyableBoxes.get(destroyBox1).body.setTransform(-10, -10, 10);
        destroyableBoxes.get(destroyBox1).body.setActive(false);
        destroyBox1 = -1;
        if(destroyBox2 == -1)
            return;
        destroyableBoxes.get(destroyBox2).body.setTransform(-10, -10, 10);
        destroyableBoxes.get(destroyBox2).body.setActive(false);
        destroyBox2 = -1;
    }
    public void collisionWithDesWall(Vector2 collisionPoint, String mess, String index){
        if(destroyBox1 == -1)
            collisionWithWall(collisionPoint, mess);
        int i = Integer.parseInt(mess);
        int j = Integer.parseInt(index);
        if(balls.get(i).getColorID().equals(destroyableBoxes.get(j).getID())) {
            if(destroyBox1 == -1)
                destroyBox1 = j;
            else
                destroyBox2 = j;
            destroyableBoxes.get(j).active = false;
        }
    }

    private void renderLines(){
        if(currentLine.isTouching()){
            shapeRenderer.line(currentLine.begin, currentLine.end);
            touchingLine();
        }

        for(int i = 0; i < balls.size; i++) {
            boolean oneLine = true;
            for (Line line : lines) {
                if (oneLine && Intersector.intersectSegmentCircle(new Vector2(line.getBegin()), new Vector2(line.getEnd()), new Vector2(bodiesBall.get(i).getPosition()), balls.get(i).getRay() * balls.get(i).getRay())) {
                    collisionLineWithBall(line, i);
                    oneLine = false;
                }
                shapeRenderer.line(line.getBegin(), line.getEnd());
            }
        }
    }
    private void renderHud(){
        if (levelTime - (System.currentTimeMillis() - startTime) / 1000 < 0 && isGameActive() && win)
            lose();
        if(win){
            if(isGameActive())
                hud.update(levelTime - (System.currentTimeMillis() - startTime) / 1000);
            else
                hud.update(levelTime);
        }
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    private void collisionLineWithBall(Line line, int i){
        if(!Double.isNaN(line.getAngle())){
            double newAngle = 2 * line.getAngle() - currentRadOfBalls[i];
            balls.get(i).setSpeedX((float) (balls.get(i).getMultiplier() * Math.cos(newAngle)));
            balls.get(i).setSpeedY((float) (balls.get(i).getMultiplier() * Math.sin(newAngle)));
            lines.removeValue(line, false);
            bodiesBall.get(i).setLinearVelocity(balls.get(i).getSpeedX(), balls.get(i).getSpeedY());
            currentRadOfBalls[i] = fixDegrees(newAngle);
            bodiesBall.get(i).setAngularVelocity(balls.get(i).getAngularVelocity());
        }
    }

    public void collisionWithWall(Vector2 collisionPoint, String mess){
        int i = Integer.parseInt(mess);
        if(balls.get(i).getLastCollisionPoint().dst(collisionPoint) < 0.2f) {
            Gdx.app.log("BEEP BEEP " + balls.get(i).getLastCollisionPoint().dst(collisionPoint), "color: " + balls.get(i).getColorID() + " numer: " + i);
            return;
        }

        double tan = (collisionPoint.y - bodiesBall.get(i).getPosition().y) / (collisionPoint.x - bodiesBall.get(i).getPosition().x);
        double a = Math.atan((-1) / tan);
        double newAngle = 2 * a - currentRadOfBalls[i];
        balls.get(i).setSpeedX((float) (balls.get(i).getMultiplier() * Math.cos(newAngle))); // może źle działać
        balls.get(i).setSpeedY((float) (balls.get(i).getMultiplier() * Math.sin(newAngle)));
        bodiesBall.get(i).setLinearVelocity(balls.get(i).getSpeedX(), balls.get(i).getSpeedY());

        currentRadOfBalls[i] = fixDegrees(newAngle);
        bodiesBall.get(i).setAngularVelocity(balls.get(i).getAngularVelocity());
        balls.get(i).setLastCollisionPoint(new Vector2(collisionPoint));

    }

    public void collisionWithColorWall(Vector2 collisionPoint, String mess, String index){
        collisionWithWall(collisionPoint, mess);
        int i = Integer.parseInt(mess);
        int j = Integer.parseInt(index);
        balls.get(i).setColorID(boxes.get(j).getColorID());
        balls.get(i).setColor(boxes.get(j).getColorID());
    }
    public void ballToHole(String col, String mess){
        int i = Integer.parseInt(mess);
        if(balls.get(i).getColorID().equals(col)){
            ballCounter++;
            destroyBody = i;
            balls.get(i).setActive(false);
            if(arcadeModeOn) {
                currentScore += 50;
                hud.addScore(50);
            }
            if (ballCounter >= bodiesBall.size){
                win = false;
                hudWin();
            }
        }
        else
            lose();
    }
    protected void hudWin(){
        hud.win();
    }
    private void lose(){
        if(arcadeModeOn) {
            preferences.putLong("CurrentScore", 0);
            preferences.flush();
        }
        hud.gameOver();
        win = false;
    }

    private void gameWin(){
        if(arcadeModeOn){
            preferences.putLong("CurrentScore", currentScore);
            if(preferences.getLong("HighScore") < currentScore)
                preferences.putLong("HighScore", currentScore);
            preferences.flush();
            game.setScreen(InkBall.arcadeManager.getLevel(game));
        } else {
            if(InkBall.arcadeManager.currentLevel == 33) {
                game.setScreen(new StartLevel(game));
                return;
            }
            if(InkBall.arcadeManager.currentLevel >= preferences.getInteger("LevelScore"))
                preferences.putInteger("LevelScore", InkBall.arcadeManager.currentLevel + 1);
            InkBall.arcadeManager.currentLevel++;
            preferences.flush();
            game.setScreen(InkBall.arcadeManager.getLevelClass(game, InkBall.arcadeManager.currentLevel));
        }
    }

    private double fixDegrees(double angle){ // przenies do jakies innej klasy w sumie
        if(angle >= 0 && angle < 2 * Math.PI)
            return angle;
        if(angle < 0){
            while (!(angle >= 0 && angle < 2 * Math.PI))
                angle = angle + Math.PI * 2;
            return angle;
        } else {
            while (!(angle >= 0 && angle < Math.PI * 2))
                angle = angle - Math.PI * 2;
            return angle;
        }
    }

    public void collision(String mess1, String mess2){
        int m1 = Integer.parseInt(mess1);
        int m2 = Integer.parseInt(mess2);
        currentRadOfBalls[m1] = Math.atan(bodiesBall.get(m1).getLinearVelocity().y / bodiesBall.get(m1).getLinearVelocity().x);
        currentRadOfBalls[m2] = Math.atan(bodiesBall.get(m2).getLinearVelocity().y / bodiesBall.get(m2).getLinearVelocity().x);
        balls.get(m1).setMultiplier(bodiesBall.get(m1).getLinearVelocity().x / (float) Math.cos(currentRadOfBalls[m1]));
        balls.get(m2).setMultiplier(bodiesBall.get(m2).getLinearVelocity().x / (float) Math.cos(currentRadOfBalls[m2]));
    }

    public void collisionBasedOnGamasutra(Vector2 point, String mess1, String mess2){
        int m1 = Integer.parseInt(mess1);
        int m2 = Integer.parseInt(mess2);
        Vector2 N2 = bodiesBall.get(m1).getPosition().sub(bodiesBall.get(m2).getPosition());
        Vector2 N = N2.nor();
        float a1 = bodiesBall.get(m1).getLinearVelocity().dot(N);
        float a2 = bodiesBall.get(m2).getLinearVelocity().dot(N);
        N.x = N.x * (a1 - a2);
        N.y = N.y * (a1 - a2);
        Vector2 v1 = bodiesBall.get(m1).getLinearVelocity().sub(N);
        Vector2 v2 = bodiesBall.get(m2).getLinearVelocity().add(N);

        bodiesBall.get(m1).setAngularVelocity(balls.get(m1).getAngularVelocity());
        bodiesBall.get(m2).setAngularVelocity(balls.get(m2).getAngularVelocity());
        while (v1.len() < 1f) {
            Gdx.app.log("dodanie","1");
            v1.scl(1.25f);
        }
        while (v2.len() < 1f){
            Gdx.app.log("dodanie","2");
            v2.scl(1.25f);
        }

        bodiesBall.get(m1).setLinearVelocity(v1);
        bodiesBall.get(m2).setLinearVelocity(v2);
    }

    public boolean isGameActive(){
        return bodiesBall.get(0).getLinearVelocity().len() > 0.1;
    }

    private void restartCurrentLevel(){
        if(arcadeModeOn) {
            if(preferences.getLong("HighScore") < currentScore)
                preferences.putLong("HighScore", currentScore);
            preferences.flush();
            game.setScreen(InkBall.arcadeManager.getLevelClass(game, InkBall.arcadeManager.currentLevel));
        }
        else
            game.setScreen(InkBall.arcadeManager.getLevelClass(game, InkBall.arcadeManager.currentLevel));
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
        if (startTime != 0)
            levelTime = levelTime - (System.currentTimeMillis() - startTime) / 1000;
    }

    @Override
    public void resume() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        world.dispose();
    }

    protected void initTime(long n){
        levelTime = n;
    }

    protected void startTime(){
        startTime = System.currentTimeMillis();
    }

    private void initBox2DBall(){
        bodyDefBall = new BodyDef();
        bodyDefBall.type = BodyDef.BodyType.DynamicBody;

        CircleShape circle = new CircleShape();
        fixtureDefBall = new FixtureDef();
        fixtureDefBall.shape = circle;
        fixtureDefBall.density = 1;
        fixtureDefBall.restitution = 1;
        fixtureDefBall.friction = 1;
    }

    protected void initB(int i, double rad, float multi, float angVelocity, String colorID, String file){
        bodyDefBall.position.set(-5 * i - 4, -5 * i - 4);

        balls.add(new Ball(colorID, file, multi, angVelocity));
        bodiesBall.add(world.createBody(bodyDefBall));

        currentRadOfBalls[i] = rad;
        speedX[i] = (float)(Math.cos(currentRadOfBalls[i]) * multi);
        speedY[i] = (float)(Math.sin(currentRadOfBalls[i]) * multi);

        bodiesBall.get(i).setUserData(Integer.toString(i));
        bodiesBall.get(i).setAngularVelocity(angVelocity);
        bodiesBall.get(i).setLinearVelocity(0, 0);
        fixtureDefBall.shape.setRadius(0);
        fixtureDefBall.friction = 1;
        fixtureDefBall.restitution = 1;
        Fixture fixture = bodiesBall.get(i).createFixture(fixtureDefBall);

    }
    protected void startBall(int i, float ray, float posX, float posY){
        bodiesBall.get(i).setTransform(posX, posY, (float) currentRadOfBalls[i]);
        balls.get(i).setRay(ray);
        fixtureDefBall.shape.setRadius(ray);
        bodiesBall.get(i).createFixture(fixtureDefBall);
        bodiesBall.get(i).setLinearVelocity(speedX[i], speedY[i]);
    }

    public void addCurrentLine(){
        if(hit)
            hit = false;
        else
            lines.add(new Line(new Vector2(currentLine.begin), new Vector2(currentLine.end)));

    }

    public void touchingLine(){
        for (int i = 0; i < balls.size; i++) {
            if (Intersector.intersectSegmentCircle(new Vector2(currentLine.begin), new Vector2(currentLine.end), new Vector2(bodiesBall.get(i).getPosition()), balls.get(i).getRay() * balls.get(i).getRay())) {
                collisionLineWithBall(new Line(new Vector2(currentLine.begin), new Vector2(currentLine.end)), i);
                currentLine.setTouching(false);
                hit = true;
            }
        }
    }

    public boolean isPlaceForBall(int i, float positX, float positY){
        for (int j = 0; j < i; j++){
            if (Intersector.overlaps(new Circle(bodiesBall.get(j).getPosition(), balls.get(j).getRay()), new Circle(
                    positX, positY, 2 * balls.get(j).getRay()
            )))
                return false;
        }
        return true;
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Armin on 6/25/2016.
 */
public class GamePanel extends JLayeredPane implements MouseMotionListener, OnLevelUpListener {

    private static final int SCREEN_HEIGHT_CONSTANT = 752;
	private static final int SCREEN_WIDTH_CONSTANT = 1000;

	private static final int ADVANCETIME_CONSTANT = 60;
	private static final int REDRAWTIME_CONSTANT = 45;
	private static final int ZOMBIE_PRODUCETIME_CONSTANT = 7000;
	private static final int SUN_PRODUCETIME_CONSTANT = 5000;

	private static final int SUN_SCORE_CONSTANT = 50;
	private static final int PEASHOOTER_SCORE_CONSTANT = 100;
	private static final int FREEZE_PEASHOOTER_SCORE_CONSTANT = 175;

	private static final int NUM_ROW_CONSTANT = 5;
	private static final int NUM_COLUMN_CONSTANT = 9;
	private static final int NUM_COLLIDER_CONSTANT = 45;
	private static final int SIZE_ROW_CONSTANT = 120;
	private static final int SIZE_COLUMN_CONSTANT = 100;

	private static final int LAST_LEVEL_CONSTANT = 2;
	private static final int CONDITION_LEVEL_CONSTANT = 100;

	private Image bgImage;
    private Image peashooterImage;
    private Image freezePeashooterImage;
    private Image sunflowerImage;
    private Image peaImage;
    private Image freezePeaImage;

    private Image normalZombieImage;
    private Image coneHeadZombieImage;

    private Collider[] colliders;
    private ArrayList<ArrayList<Zombie>> laneZombies;
    private ArrayList<ArrayList<AbstractPea>> lanePeas;
    private ArrayList<Sun> activeSuns;

    private Timer redrawTimer;
    private Timer advancerTimer;
    private Timer sunProducer;
    private Timer zombieProducer;

    private static MessageDialog messageDialog;
    private JLabel sunScoreboard;
    private GameWindow.PlantType activePlantingBrush = GameWindow.PlantType.None;

    private int mouseX, mouseY;

    private int sunScore;
    static int totalLevelPoint = 0;
    public int currentLevel = Integer.parseInt(LevelData.LEVEL_NUMBER);


    private ArrayList<OnLevelUpListener> mLevelUpObservers = new ArrayList<>();

    public void addLevelUpObservers(OnLevelUpListener onLevelUpListener) {
        mLevelUpObservers.add(onLevelUpListener);
    }

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void onLevelUp() {
        setZombieProducer(ZOMBIE_PRODUCETIME_CONSTANT / 3);
    }

    public GamePanel(JLabel sunScoreboard) {
        setSize(SCREEN_WIDTH_CONSTANT, SCREEN_HEIGHT_CONSTANT);
        setLayout(null);
        addMouseMotionListener(this);
        this.sunScoreboard = sunScoreboard;

        int initSunScore = 150;
        setSunScore(initSunScore);

        messageDialog = new MessageDialog(GamePanel.this);

        loadBackGroundImage();
        loadPlantImage();
        loadZombieImage();

        produceLaneZombies();
        produceLanePeas();
        produceColliders();
        produceSuns();

        setSunProducer(SUN_PRODUCETIME_CONSTANT);
        setZombieProducer(ZOMBIE_PRODUCETIME_CONSTANT);
        setRedrawTimer(REDRAWTIME_CONSTANT);
        setAdvanceTimer(ADVANCETIME_CONSTANT);

        addLevelUpObservers(this);
    }

	private void setZombieProducer(int produceTime) {
		zombieProducer = new Timer(produceTime, (ActionEvent e) -> {
            Random rnd = new Random();
            LevelData lvl = new LevelData();
            String[] Level = lvl.LEVEL_CONTENT[Integer.parseInt(lvl.LEVEL_NUMBER) - 1];
            int[][] LevelValue = lvl.LEVEL_VALUE[Integer.parseInt(lvl.LEVEL_NUMBER) - 1];
            int l = rnd.nextInt(5);
            int t = rnd.nextInt(100);
            Zombie z = null;
            for (int i = 0; i < LevelValue.length; i++) {
                if (t >= LevelValue[i][0] && t <= LevelValue[i][1]) {
                    z = Zombie.getZombie(Level[i], GamePanel.this, l);
                }
            }
            laneZombies.get(l).add(z);
        });
        zombieProducer.start();
	}

	private void setSunProducer(int produceTime) {
		sunProducer = new Timer(produceTime, (ActionEvent e) -> {
            Random rnd = new Random();
            Sun sta = new Sun(this);
            sta.setMovingStrategy( new SunAdvanceStrategy(this, sta, rnd.nextInt(800) + 100, 0, rnd.nextInt(300) + 200));
            activeSuns.add(sta);
            /* object put on container to look sun */
            add(sta, Singleton.sunInstance()); /* Singleton */
        });
        sunProducer.start();
	}

	private void setAdvanceTimer(int advanceTime) {
		advancerTimer = new Timer(advanceTime, (ActionEvent e) -> advance());
        advancerTimer.start();
	}

	private void setRedrawTimer(int redrawTime) {
		redrawTimer = new Timer(redrawTime, (ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();
	}

	private void produceSuns() {
		activeSuns = new ArrayList<>();
	}

	private void produceColliders() {
		colliders = new Collider[NUM_COLLIDER_CONSTANT];
        for (int object = 0; object < NUM_COLLIDER_CONSTANT; object++) {
            Collider collider = new Collider();
            collider.setLocation(44 + getColumn(object) * SIZE_COLUMN_CONSTANT, 109 + getRow(object) * SIZE_ROW_CONSTANT);
            collider.setAction(new PlantActionListener(getColumn(object), getRow(object)));
            colliders[object] = collider;
            add(collider, Singleton.coliderInstance()); /* Singleton */
        }
	}

	private int getRow(int collider) {
		return collider / NUM_COLUMN_CONSTANT;
	}

	private int getColumn(int collider) {
		return collider % NUM_COLUMN_CONSTANT;
	}

	private void produceLanePeas() {
		lanePeas = new ArrayList<>();
		for(int line=1; line <6; line++) {
			lanePeas.add(new ArrayList<>()); //line
		}
	}

	private void produceLaneZombies() {
		laneZombies = new ArrayList<>();
		for(int line=1; line <6; line++) {
			laneZombies.add(new ArrayList<>()); //line
		}

	}

	private void loadZombieImage() {
		normalZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/zombie1.png")).getImage();
        coneHeadZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/zombie2.png")).getImage();
	}

	private void loadPlantImage() {
		peashooterImage = new ImageIcon(this.getClass().getResource("images/plants/peashooter.gif")).getImage();
        freezePeashooterImage = new ImageIcon(this.getClass().getResource("images/plants/freezepeashooter.gif")).getImage();
        sunflowerImage = new ImageIcon(this.getClass().getResource("images/plants/sunflower.gif")).getImage();
        peaImage = new ImageIcon(this.getClass().getResource("images/pea.png")).getImage();
        freezePeaImage = new ImageIcon(this.getClass().getResource("images/freezepea.png")).getImage();
	}

	private void loadBackGroundImage() {
		bgImage = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();
	}

    private void advance() {
        for (int row = 0; row < NUM_ROW_CONSTANT; row++) {
            for (Zombie z : laneZombies.get(row)) {
                z.advance();
            }

            for (int object = 0; object < lanePeas.get(row).size(); object++) {
                AbstractPea p = lanePeas.get(row).get(object);
                p.advance();
            }

        }

        for (int object = 0; object < activeSuns.size(); object++) {
            activeSuns.get(object).advance();
        }

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(bgImage, 0, 0, null);

        //Draw Plants
        for (int collider = 0; collider < NUM_COLLIDER_CONSTANT; collider++) {
            Collider c = colliders[collider];
            int rowPadding = 129;
            int columnPadding = 60;
            if (c.assignedPlant != null) {
                Plant p = c.assignedPlant;
                if (p instanceof Peashooter) {
                    graphics.drawImage(peashooterImage, columnPadding+getColumn(collider)*SIZE_COLUMN_CONSTANT, rowPadding+getRow(collider)*SIZE_ROW_CONSTANT, null);
                }
                if (p instanceof FreezePeashooter) {
                    graphics.drawImage(freezePeashooterImage, columnPadding+getColumn(collider)*SIZE_COLUMN_CONSTANT, rowPadding+getRow(collider)*SIZE_ROW_CONSTANT, null);
                }
                if (p instanceof Sunflower) {
                    graphics.drawImage(sunflowerImage, columnPadding+getColumn(collider)*SIZE_COLUMN_CONSTANT, rowPadding+getRow(collider)*SIZE_ROW_CONSTANT, null);
                }
            }
        }

        for (int row = 0; row < NUM_ROW_CONSTANT; row++) {
            for (Zombie z : laneZombies.get(row)) {
            	int rowPadding = 109;
                if (z instanceof NormalZombie) {
                    graphics.drawImage(normalZombieImage, z.getPosX(), rowPadding + (row * SIZE_ROW_CONSTANT), null);
                }
                else if (z instanceof ConeHeadZombie) {
                    graphics.drawImage(coneHeadZombieImage, z.getPosX(), rowPadding + (row * SIZE_ROW_CONSTANT), null);
                }
            }

            for (int object = 0; object < lanePeas.get(row).size(); object++) {
                AbstractPea pea = lanePeas.get(row).get(object);
                int rowPadding = 130;
                if (pea instanceof FreezePea) {
                    graphics.drawImage(freezePeaImage, pea.getPosX(), rowPadding + (row * SIZE_ROW_CONSTANT), null);
                } else {
                    graphics.drawImage(peaImage, pea.getPosX(), rowPadding + (row * SIZE_ROW_CONSTANT), null);
                }
            }

        }

    }

    private class PlantActionListener implements ActionListener {

        int x, y;
        Planting planting;

        public PlantActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	switch(activePlantingBrush) {
        	case Sunflower:
        		planting = new PlantingSunflower(GamePanel.this, SUN_SCORE_CONSTANT, x, y);
        		break;
        	case Peashooter:
        		planting = new PlantingPeashooter(GamePanel.this, PEASHOOTER_SCORE_CONSTANT, x, y);
        		break;
        	case FreezePeashooter:
        		planting = new PlantingFreezePeashooter(GamePanel.this, FREEZE_PEASHOOTER_SCORE_CONSTANT, x, y);
        		break;
        	default: return;
        	}

            planting.activePlanting();
            activePlantingBrush = GameWindow.PlantType.None;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void setLevelPoint(int levelPoint) {
        totalLevelPoint = totalLevelPoint + levelPoint;
        System.out.println(totalLevelPoint);
        boolean isLevelUp = totalLevelPoint >= CONDITION_LEVEL_CONSTANT;
		if (isLevelUp) {
            currentLevel = 2;
            for (OnLevelUpListener listener : mLevelUpObservers) {
                listener.onLevelUp();
            }
            messageDialog.levelUpDialog();
//            if ("1".equals(LevelData.LEVEL_NUMBER)) {
//            	messageDialog.levelUpDialog();
//            	LevelData.write("2");
//            	GameWindow.begin();
//            } else {
//            	messageDialog.gameClearDialog();
//            	LevelData.write("1");
//                System.exit(0);
//            }
            totalLevelPoint = 0;
        }
    }

    public GameWindow.PlantType getActivePlantingBrush() {
        return activePlantingBrush;
    }

    public void setActivePlantingBrush(GameWindow.PlantType activePlantingBrush) {
        this.activePlantingBrush = activePlantingBrush;
    }

    public ArrayList<ArrayList<Zombie>> getLaneZombies() {
        return laneZombies;
    }

    public void setLaneZombies(ArrayList<ArrayList<Zombie>> laneZombies) {
        this.laneZombies = laneZombies;
    }

    public ArrayList<ArrayList<AbstractPea>> getLanePeas() {
        return lanePeas;
    }

    public void setLanePeas(ArrayList<ArrayList<AbstractPea>> lanePeas) {
        this.lanePeas = lanePeas;
    }

    public ArrayList<Sun> getActiveSuns() {
        return activeSuns;
    }

    public void setActiveSuns(ArrayList<Sun> activeSuns) {
        this.activeSuns = activeSuns;
    }

    public Collider[] getColliders() {
        return colliders;
    }

    public void setColliders(Collider[] colliders) {
        this.colliders = colliders;
    }

    public MessageDialog getMessageDialog() {
    	return messageDialog;
    }
}

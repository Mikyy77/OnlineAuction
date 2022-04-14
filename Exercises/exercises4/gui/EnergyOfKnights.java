package gui;

import game.ClashFollower;
import game.Game;
import javafx.scene.control.TextField;

public class EnergyOfKnights extends TextField implements ClashFollower {
    private Game game;
    private int energy;

    public EnergyOfKnights(Game game) {
        super();
        this.game = game;
    }

    public void inform() {
        energy = 0;

        for (int i = 0; i < game.getNumberOfWarriors(); ++i)
            energy += game.getKnight(i).getEnergy();

        setText(Integer.toString(energy));
    }
}
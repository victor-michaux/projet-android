package efrei.edu.projetandroid.game;

import java.util.ArrayList;

public class PlayerThrow {

    private ArrayList<Integer> droppedBowlingPins;
    private boolean gutter = false;

    public PlayerThrow()
    {
        this.droppedBowlingPins = new ArrayList<>();
    }

    public ArrayList<Integer> getPinTouched() {
        return droppedBowlingPins;
    }

    public void setPinTouched(ArrayList<Integer> droppedBowlingPins) {
        this.droppedBowlingPins = droppedBowlingPins;
    }

    public void setGutter(final boolean gutter) {
        this.gutter = gutter;
    }

    public boolean hasStrike()
    {
        for (int i = 1; i < 10; i++)
        {
            if(!this.droppedBowlingPins.contains(i)) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<Integer> getDroppedBowlingPins() {
        return droppedBowlingPins;
    }

    public void setDroppedBowlingPins(ArrayList<Integer> droppedBowlingPins) {
        this.droppedBowlingPins = droppedBowlingPins;
    }

    public boolean isGutter() {
        return gutter;
    }
}

package efrei.edu.projetandroid.game;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerRound implements Serializable {
    private PlayerThrow firstThrow;
    private PlayerThrow secondThrow;

    private boolean finished;

    public PlayerRound()
    {
        this.firstThrow = null;
        this.secondThrow = null;
        this.finished = false;
    }

    public PlayerRound(final PlayerThrow firstThrow)
    {
        this.firstThrow = firstThrow;
        this.secondThrow = null;
        this.finished = firstThrow.hasStrike();
    }

    public PlayerRound(final PlayerThrow firstThrow, final PlayerThrow secondThrow)
    {
        this.firstThrow = firstThrow;
        this.secondThrow = secondThrow;
        this.finished = true;
    }

    public boolean play(BallThrowType ballThrowType, final ArrayList<Integer> droppedBowlingPins)
    {
        if(this.firstThrow == null) {
            this.writeThrowResult(ballThrowType, droppedBowlingPins);
            this.finished = this.firstThrow.hasStrike();
        } else if(this.canPlaySecondThrow()) {
            this.writeThrowResult(ballThrowType, droppedBowlingPins);
            this.finished = true;
        }

        // If Strike on first throw then next player should play
        return this.finished;
    }

    public boolean isFinished()
    {
        return this.finished;
    }

    private void writeThrowResult(BallThrowType throwType, ArrayList<Integer> droppedBowlingPins)
    {
        PlayerThrow currentPlayerThrow = this.getCurrentPlayerThrow();

        switch (throwType)
        {
            case GUTTER:
                currentPlayerThrow.setGutter(true);
                break;
            case PIN_TOUCHED:
                currentPlayerThrow.setPinTouched(droppedBowlingPins);
                break;
            default:
                break;
        }
    }

    private PlayerThrow getCurrentPlayerThrow()
    {
        if(this.firstThrow == null) {
            this.firstThrow = new PlayerThrow();
            return this.firstThrow;
        } else {
            this.secondThrow = new PlayerThrow();
            return this.secondThrow;
        }
    }

    private boolean canPlaySecondThrow()
    {
        return this.firstThrow != null && !this.firstThrow.hasStrike();
    }

    public PlayerThrow getFirstThrow() {
        return firstThrow;
    }

    public void setFirstThrow(PlayerThrow firstThrow) {
        this.firstThrow = firstThrow;
    }

    public PlayerThrow getSecondThrow() {
        return secondThrow;
    }

    public void setSecondThrow(PlayerThrow secondThrow) {
        this.secondThrow = secondThrow;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}

package efrei.edu.projetandroid.game;

import java.util.ArrayList;

public class PlayerRound {
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
            return this.firstThrow;
        } else {
            return this.secondThrow;
        }
    }

    private boolean canPlaySecondThrow()
    {
        return this.firstThrow != null && !this.firstThrow.hasStrike();
    }
}

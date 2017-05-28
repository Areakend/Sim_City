package model.event;

import java.util.ArrayList;
import java.util.List;

import localization.LocalizedTexts;
import model.GameBoard;

/**
 * The GodsGiftEvent gives resources to the city.
 */
public class KingsTaxEvent extends Event {

    /**
     * Default Constructor.
     * @return 
     */
	public KingsTaxEvent() {
        super();
    }
	

    /**
     * Applies the king's tax on the currency.
     */
	@Override
    public List<Event> applyEffects(GameBoard gameBoard) {
		gameBoard.getResources().currency =(int) Math.round(gameBoard.getResources().currency*0.3);
        return new ArrayList<>(0);
    }

    /**
     * Return an empty message.
     */
	@Override
    public String getMessage(LocalizedTexts texts) {
        return "Le roi taxe votre ville de 70% de votre argent.";
    }


}


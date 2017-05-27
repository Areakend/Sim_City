package model.event;

import java.util.ArrayList;
import java.util.List;

import localization.LocalizedTexts;
import model.GameBoard;

/**
 * The GodsGiftEvent gives resources to the city.
 */
public class GodsGiftEvent extends Event {

    /**
     * Default Constructor.
     * @return 
     */
	public GodsGiftEvent() {
        super();
    }
	

    /**
     * Destroys a tile with a probability of 10%.
     */
	@Override
    public List<Event> applyEffects(GameBoard gameBoard) {
		gameBoard.getResources().food = gameBoard.getResources().foodCapacity;
		gameBoard.getResources().wood = gameBoard.getResources().woodCapacity;
		gameBoard.getResources().steel = gameBoard.getResources().steelCapacity;
		gameBoard.getResources().rock = gameBoard.getResources().rockCapacity;
        return new ArrayList<>(0);
    }

    /**
     * Return an empty message.
     */
	@Override
    public String getMessage(LocalizedTexts texts) {
        return "Dieu a été clément avec votre ville ! Vous avez gagné des ressources.";
    }


}


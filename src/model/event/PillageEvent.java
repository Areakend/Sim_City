package model.event;

import java.util.ArrayList;
import java.util.List;

import localization.LocalizedTexts;
import model.GameBoard;

/**
 * The PillageEvent steals some resources, unless knights are there to defend the city. 
 */
public class PillageEvent extends Event {

    /**
     * Default Constructor.
     * @return 
     */
	public PillageEvent() {
        super();
    }
	

    /**
     * Destroys a tile with a probability of 10%.
     */
	@Override
    public List<Event> applyEffects(GameBoard gameBoard) {
		if (gameBoard.getResources().knight>0){
			gameBoard.getResources().knight -= 1;
			gameBoard.getResources().population -= 1;
		}
		else{
			gameBoard.getResources().food = (int) Math.round(gameBoard.getResources().food*0.7);
			gameBoard.getResources().wood = (int) Math.round(gameBoard.getResources().wood*0.7);
			gameBoard.getResources().steel = (int) Math.round(gameBoard.getResources().steel*0.7);
			gameBoard.getResources().rock = (int) Math.round(gameBoard.getResources().rock*0.7);
			gameBoard.getResources().currency = (int) Math.round(gameBoard.getResources().currency*0.7);
			
		}
        return new ArrayList<>(0);
    }

    /**
     * Return an empty message.
     */
	@Override
    public String getMessage(LocalizedTexts texts) {
        return "Votre ville se fait piller ! Vos chevaliers sont là (ou pas) pour vous défendre.";
    }


}


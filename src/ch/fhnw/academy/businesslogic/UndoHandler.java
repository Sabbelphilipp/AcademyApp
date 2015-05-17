package ch.fhnw.academy.businesslogic;

import ch.fhnw.academy.model.movie;

/**
 * Created by Reto Giger on 14.05.2015.
 */
public class UndoHandler extends UndoRedoHandler {

    public UndoHandler(movie movie){
        super(movie);
    }

    public RedoHandler getRedo(){
        return new RedoHandler(movie);
    }

}

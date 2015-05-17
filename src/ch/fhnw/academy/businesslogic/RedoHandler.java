package ch.fhnw.academy.businesslogic;

import ch.fhnw.academy.model.movie;

/**
 * Created by Reto Giger on 14.05.2015.
 */
public class RedoHandler extends UndoRedoHandler{
    public RedoHandler(movie movie){
        super(movie);
    }

    public UndoHandler getUndo(){
        return new UndoHandler(movie);
    }
}

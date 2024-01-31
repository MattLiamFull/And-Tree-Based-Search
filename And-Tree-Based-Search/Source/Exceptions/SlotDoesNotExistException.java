package Source.Exceptions;

public class SlotDoesNotExistException extends Exception{

    public SlotDoesNotExistException(){
        super();
    }

    public SlotDoesNotExistException(String message){
        super(message);
    }
}
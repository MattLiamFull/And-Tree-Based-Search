package Source.Exceptions;

public class CourseLabDoesNotExistException extends Exception{

    public CourseLabDoesNotExistException(){
        super();
    }

    public CourseLabDoesNotExistException(String message){
        super(message);
    }
}

package ng.com.justjava.bookkeeping.Exception;

public class GLAccountNotFoundException extends RuntimeException{
    public GLAccountNotFoundException(String message,Throwable err){
        super(message,err);
    }
    public GLAccountNotFoundException(String message){
        super(message);
    }
}

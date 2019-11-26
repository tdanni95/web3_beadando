package webprog.exceptions;

public class InvalidNeptunKod extends Throwable{
    public InvalidNeptunKod(String neptunkod){
        super(neptunkod);
    }
}

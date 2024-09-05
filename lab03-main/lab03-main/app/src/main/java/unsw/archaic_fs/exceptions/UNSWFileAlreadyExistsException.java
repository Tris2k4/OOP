package unsw.archaic_fs.exceptions;
import java.io.FileNotFoundException;
public class UNSWFileAlreadyExistsException extends FileNotFoundException {
    public UNSWFileAlreadyExistsException(String message) {
        super(message);
    }
}

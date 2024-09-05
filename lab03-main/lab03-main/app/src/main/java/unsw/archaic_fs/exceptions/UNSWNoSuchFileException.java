package unsw.archaic_fs.exceptions;
import java.nio.file.NoSuchFileException;
public class UNSWNoSuchFileException extends NoSuchFileException {
    public UNSWNoSuchFileException(String message) {
        super(message);
    }
}

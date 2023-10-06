import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendableObjectOutputStream extends ObjectOutputStream {
    private boolean append;

    protected AppendableObjectOutputStream(boolean append) throws IOException, SecurityException {
        super();
        this.append = append;
    }

    public AppendableObjectOutputStream(OutputStream out, boolean append) throws IOException {
        super(out);
        this.append = append;
        if (!append) {
            writeStreamHeader();
        }
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        if (!append) {
            super.writeStreamHeader();
        }
    }
}
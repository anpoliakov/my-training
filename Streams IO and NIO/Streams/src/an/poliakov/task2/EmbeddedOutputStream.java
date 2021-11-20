package an.poliakov.task2;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class EmbeddedOutputStream extends FilterOutputStream {
    static final int BUFFER_SIZE = 65535;
    private final byte[] BUFFER;
    private int bufferPosition;
    private boolean closed;

    public EmbeddedOutputStream(OutputStream out) {
        super(Objects.requireNonNull(out,"out is null"));
        this.BUFFER = new byte[BUFFER_SIZE];
        this.bufferPosition = 0;
    }

    @Override
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b});
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        write(bytes,0,bytes.length);
    }

    @Override
    public void write(byte[] bytes, int offset, int lenght) throws IOException {

    }
}

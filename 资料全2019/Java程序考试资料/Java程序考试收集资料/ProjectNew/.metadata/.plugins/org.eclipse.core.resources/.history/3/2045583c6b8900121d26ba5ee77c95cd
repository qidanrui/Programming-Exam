package common;

import java.io.*;

public class LittleEndianDataInputStream extends FilterInputStream implements DataInput {
    private DataInputStream dataInputStream;
    private int byteCount;

    public LittleEndianDataInputStream(InputStream inputStream) {
        super(inputStream);
        dataInputStream = new DataInputStream(inputStream);
    }

    public int getByteCount() {
        return byteCount;
    }

    public int read() throws IOException {
        byteCount++;
        return super.read();
    }

    private int readCheckEOF() throws IOException {
        int byteRead = read();
        if (byteRead == -1)
            throw new EOFException();
        return byteRead;
    }

    public int readUnsignedByte() throws IOException {
        return (readCheckEOF() & 0xFF);
    }

    public short readShort() throws IOException {
        int byte1 = (read() & 0xFF);
        int byte2 = (read() & 0xFF) << 8;
        return ((short) (byte1 | byte2));
    }

    public int readUnsignedShort() throws IOException {
        int byte1 = (read() & 0xFF);
        int byte2 = (read() & 0xFF) << 8;
        return (byte1 | byte2);
    }

    public int readInt() throws IOException {
        return (readCheckEOF() & 0xFF) |
                (readCheckEOF() & 0xFF) << 8 |
                (readCheckEOF() & 0xFF) << 16 |
                (readCheckEOF() & 0xFF) << 24;
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    public long readLong() throws IOException {
        return (
                ((long) (readCheckEOF() & 0xff)) |
                ((long) (readCheckEOF() & 0xff) << 8) |
                ((long) (readCheckEOF() & 0xff) << 16) |
                ((long) (readCheckEOF() & 0xff) << 24) |
                ((long) (readCheckEOF() & 0xff) << 32) |
                ((long) (readCheckEOF() & 0xff) << 40) |
                ((long) (readCheckEOF() & 0xff) << 48) |
                ((long) (readCheckEOF() & 0xff) << 56)
                );
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    public void readFully(byte[] b) throws IOException {
        readFully(b, 0, b.length);
    }

    public void readFully(byte[] b, int off, int len) throws IOException {
        while (len > 0) {
            int bytesRead = read(b, off, len);
            if (bytesRead == -1) {
                throw new EOFException("Unexpected end of file");
            } else {
                len -= bytesRead;
                off += bytesRead;
            }
        }
    }

    public int skipBytes(int n) throws IOException {
        int bytesRead = 0;
        while (bytesRead < n) {
            int read = read();
            if (read == -1) {
                break;
            } else {
                bytesRead++;
            }
        }
        return bytesRead;
    }

    public boolean readBoolean() throws IOException {
        return readInt() != 0;
    }

    public byte readByte() throws IOException {
        return (byte) readCheckEOF();
    }

    public char readChar() throws IOException {
        return (char) ((readCheckEOF() << 8) | (readCheckEOF() & 0xff));
    }

    public String readLine() throws IOException {
        return dataInputStream.readLine();
    }

    public String readUTF() throws IOException {
        return dataInputStream.readUTF();
    }

    public synchronized void mark(int readlimit) {
    }

    public boolean markSupported() {
        return false;
    }

    public synchronized void reset() throws IOException {
        throw new IOException();
    }
}

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;
public class Main {
    private static final int N = 12;
    private static final int M = 4;
    private static final int A = 25;
    private static final int B = 35;
    private static final Random RANDOM = new Random();
    public static void main(String[] args) {
        System.out.println("Випадкові потоки полів: ");
        Vector<InputStream> streams = new Vector<>();
        for (int i = 0; i < M; i++) {
            byte[] bytes = generateRandomByteArray(N);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            streams.add(inputStream);
            System.out.println(Arrays.toString(bytes));
        }
        InputStream combinedStreams = new SequenceInputStream(streams.elements());
        System.out.println("Комбінований потік: ");
        printStream(combinedStreams);
        streams.forEach(Main::closeStream);
        closeStream(combinedStreams);
    }
    private static byte[] generateRandomByteArray(int size){
        byte[] array = new byte[size];
        for(int i = 0; i < size; i++){
            array[i] = (byte) (RANDOM.nextInt(B - A + 1) + A);
        }
        return array;
    }
    private static void printStream(InputStream inputStream) {
        try {
            System.out.println(Arrays.toString(inputStream.readAllBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void closeStream(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
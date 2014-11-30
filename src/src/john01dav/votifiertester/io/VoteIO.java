package src.john01dav.votifiertester.io;

import java.io.*;

public class VoteIO {

    public static void save(File file, Vote vote) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(vote);

        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static Vote load(File file) throws IOException, ClassNotFoundException, ClassCastException{
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Object object = objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        Vote vote = ((Vote) object);
        return vote;
    }

}

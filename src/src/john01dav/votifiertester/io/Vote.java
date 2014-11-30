package src.john01dav.votifiertester.io;

import src.john01dav.votifiertester.gui.VotifierTester;
import src.john01dav.votifiertester.crypto.EncryptionHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.nio.charset.Charset;

public class Vote implements Serializable{
    private String serviceName;
    private String serverIP;
    private String username;
    private String userIP;
    private String publicKey;

    public static Vote fromVotifierTester(VotifierTester votifierTester){
        return new Vote(votifierTester.getServiceName(), votifierTester.getServerIP(), votifierTester.getUsername(), votifierTester.getVoterIP(), votifierTester.getPublicKey());
    }

    public Vote(String serviceName, String serverIP, String username, String userIP, String publicKey){
        this.serviceName = serviceName;
        this.serverIP = serverIP;
        this.username = username;
        this.userIP = userIP;
        this.publicKey = publicKey;
    }

    public void vote() throws IOException{
        String[] serverIPParsed;
        String serverIP;
        EncryptionHandler encryptionHandler;
        int port;

        String voteData = "VOTE\n" + serviceName + "\n" + username + "\n" + userIP + "\n";


        byte[] rawData = voteData.getBytes(Charset.forName("UTF-8"));
        byte[] data = new byte[256];
        byte[] encryptedData;

        serverIPParsed = this.serverIP.split(":");

        if(serverIPParsed.length != 2){
            throw new IOException("Invalid server IP. You must use the format IP:PORT.");
        }

        serverIP = serverIPParsed[0];
        try{
            port = Integer.parseInt(serverIPParsed[1]);
        }catch(NumberFormatException e){
            throw new IOException(e.getMessage());
        }

        encryptionHandler = new EncryptionHandler(publicKey);
        encryptedData = encryptionHandler.encrypt(rawData);

        if(encryptedData.length == 0){
            throw new IOException("Encryption Failed");
        }

        for(int x=0;x<256;x++){
            try{
                data[x] = encryptedData[x];
            }catch(ArrayIndexOutOfBoundsException e){
                encryptedData[x] = 0;
            }
        }

        Socket socket = new Socket(serverIP, port);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
        socket.close();
    }

    public String getServiceName(){
        return serviceName;
    }

    public String getServerIP(){
        return serverIP;
    }

    public String getUsername(){
        return username;
    }

    public String getVoterIP(){
        return userIP;
    }

    public String getPublicKey(){
        return publicKey;
    }

}

package com.example.lms;

import java.io.*;

public class User implements Serializable {

    String username;
    String password;
    Archive archive = new Archive();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Archive getArchive() {
        return archive;
    }


    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
//    public void saveToFile() throws IOException {
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/java/com/example/lms/userdata/" + this.username + ".txt"));
//        out.writeObject(this);
//    }
}

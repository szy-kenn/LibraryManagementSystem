package com.example.lms;
public interface Member {
    String username = null;
    String password = null;

    Archive archive = new Archive();

    default String getUsername() {
        return username;
    }
    default String getPassword() {
        return password;
    }
    default Archive getArchive() {
        return archive;
    }


}

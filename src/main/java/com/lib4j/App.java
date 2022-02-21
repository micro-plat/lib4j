package com.lib4j;

import com.lib4j.security.Md5;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println(Md5.encrypt("abc"));
        System.out.println("Hello World!");
    }
}

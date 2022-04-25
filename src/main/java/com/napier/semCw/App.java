package com.napier.semCw;

public class App {
    public static void main(String[] args) {
        DatabaseSingleton db = DatabaseSingleton.getInstance();

        // Connect to database
        if(args.length < 1) {
            db.connect("localhost:33060", 30000);
        } else {
            db.connect(args[0], Integer.parseInt(args[1]));
        }

        db.printNumOfSpeakersFromList(new String[] {"Chinese", "English", "Hindi", "Spanish", "Arabic"});

        // Disconnect from database
        db.disconnect();
    }
}
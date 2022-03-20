package com.napier.semCw;

import org.junit.jupiter.api.BeforeAll;

public class AppIntegrationTest
{
    static DatabaseSingleton db;

    @BeforeAll
    static void init()
    {
        db = DatabaseSingleton.getInstance();
        db.connect("localhost:33060", 30000);
    }
}

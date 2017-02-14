package com.wisdomlanna.www.dagger2_mvp_example.main;


import android.support.test.espresso.core.deps.guava.base.Charsets;
import android.support.test.espresso.core.deps.guava.io.Resources;

import com.wisdomlanna.www.dagger2_mvp_example.module.NetworkModule;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;

abstract class BaseTestServer {

    private MockWebServer server;

    abstract void setup();

    abstract void destroy();

    @Before
    public void startMockWebServer() throws IOException {
        server = new MockWebServer();
        server.start();
        NetworkModule.ENPOINT = server.url("/").toString();
        setup();
    }

    String getDataFromFile(String resource) throws IOException {
        return Resources.toString(Resources.getResource(resource), Charsets.UTF_8);
    }

    @After
    public void stopServer() throws Exception {
        server.shutdown();
        destroy();
    }

    MockWebServer getServer() {
        return server;
    }
}
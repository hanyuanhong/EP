package com.hyh;

import org.junit.Test;

public class TestDemo {
    @Test
    public void testSay() {
        Demo demo = new Demo();
        String worlds = demo.say("maven");
        System.out.println(worlds);
    }
}

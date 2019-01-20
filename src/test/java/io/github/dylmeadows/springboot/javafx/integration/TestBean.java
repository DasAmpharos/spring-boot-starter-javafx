package io.github.dylmeadows.springboot.javafx.integration;

import org.springframework.stereotype.Component;

@Component
public class TestBean implements OnInit {
    @Override
    public void onInit() {
        System.out.println("TestBean::onInit()");
    }
}

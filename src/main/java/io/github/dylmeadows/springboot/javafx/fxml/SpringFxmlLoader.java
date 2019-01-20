package io.github.dylmeadows.springboot.javafx.fxml;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public final class SpringFxmlLoader {

    private final ApplicationContext ctx;

    public final <T extends Parent> T load(URL location) throws IOException {
        Assert.notNull(location, "location cannot be null");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);
        loader.setControllerFactory(ctx::getBean);
        return loader.load();
    }

    public final <T extends Parent> T load(URL location, ResourceBundle resources) throws IOException {
        Assert.notNull(location, "location cannot be null");
        Assert.notNull(resources, "resources cannot be null");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);
        loader.setResources(resources);
        loader.setControllerFactory(ctx::getBean);
        return loader.load();
    }

    public final <T extends Parent> T load(InputStream inputStream) throws IOException {
        Assert.notNull(inputStream, "inputStream cannot be null");
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(ctx::getBean);
        return loader.load(inputStream);
    }

    public final <T extends Parent> T load(InputStream inputStream, ResourceBundle resources) throws IOException {
        Assert.notNull(inputStream, "inputStream cannot be null");
        Assert.notNull(resources, "resources cannot be null");
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(resources);
        loader.setControllerFactory(ctx::getBean);
        return loader.load(inputStream);
    }
}

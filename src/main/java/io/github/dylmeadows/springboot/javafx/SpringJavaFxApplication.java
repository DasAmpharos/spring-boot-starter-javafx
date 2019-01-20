package io.github.dylmeadows.springboot.javafx;

import io.github.dylmeadows.springboot.javafx.fxml.SpringFxmlLoader;
import javafx.application.Application;
import lombok.Getter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public abstract class SpringJavaFxApplication extends Application {

    @Getter
    private ConfigurableApplicationContext context;
    @Getter
    private SpringFxmlLoader springFxmlLoader;

    @Override
    public final void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        context = new SpringApplicationBuilder(getClass())
            .main(getClass())
            .headless(false)
            .run(args);
        springFxmlLoader = context.getBean(SpringFxmlLoader.class);
    }

    @Override
    public final void stop() {
        context.close();
    }
}

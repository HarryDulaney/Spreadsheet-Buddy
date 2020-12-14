package com.spreadsheetbuddy;

import com.spreadsheetbuddy.view.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.Collection;


@SpringBootApplication
public class SheetBuddyApp extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(SheetBuddyApp.class, MainView.class, args);

    }


    @Override
    public Collection<javafx.scene.image.Image> loadDefaultIcons() {
        return Arrays.asList(new javafx.scene.image.Image(getClass().getResource("/img/XLSX-ICON.png").toExternalForm()),
                new javafx.scene.image.Image(getClass().getResource("/img/XLSX-ICON.png").toExternalForm()));
//                new javafx.scene.image.Image(getClass().getResource("/icons/gear_36x36.png").toExternalForm()),
//                new javafx.scene.image.Image(getClass().getResource("/icons/gear_42x42.png").toExternalForm()),
//                new Image(getClass().getResource("/icons/gear_64x64.png").toExternalForm()));
    }

//
//    @Override
//    public void beforeInitialView(final Stage stage, final ConfigurableApplicationContext ctx) {
//    }

}

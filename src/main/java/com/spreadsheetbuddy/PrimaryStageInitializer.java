package com.spreadsheetbuddy;

import com.spreadsheetbuddy.controller.ViewController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {

    private final FxWeaver fxWeaver;
    private final int startHeight;
    private final int startWidth;

    @Value("${sheetbuddy.start.title}")
    String title;

    @Autowired
    public PrimaryStageInitializer(@Value("${sheetbuddy.start.height}") String height,
                                   @Value("${sheetbuddy.start.width}") String width,
                                   FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
        this.startHeight = Integer.parseInt(height);
        this.startWidth = Integer.parseInt(width);
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.stage;
        stage.setTitle(title);
        Scene scene = new Scene(fxWeaver.loadView(ViewController.class), startWidth, startHeight);
        stage.setScene(scene);
        stage.show();
    }
}
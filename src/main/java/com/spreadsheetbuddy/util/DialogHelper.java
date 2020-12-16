package com.spreadsheetbuddy.util;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is a class of static methods to utilize for Alerts, Pop-ups and UI
 * Dialogs.
 * @author Harry Dulaney
 */
@Component
public final class DialogHelper {

    private final FxWeaver fxWeaver;

    @Autowired
    public DialogHelper(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    public static void defaultDialog(StackPane root, String title, String body, DialogAction action) {

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(title));
        content.setBody(new Text(body));

        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

        JFXButton okButton = new JFXButton("Ok");
        okButton.setOnAction(event -> {
            dialog.close();
            action.onAction();

        });
        content.setActions(okButton);

        dialog.show();
    }

    public static void threeChoiceDialog(StackPane root,
                                         String title,
                                         String body,
                                         String s1,
                                         String s2,
                                         String s3,
                                         DialogAction a1,
                                         DialogAction a2,
                                         DialogAction a3) {

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(title));
        content.setBody(new Text(body));

        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

        Stage stage = new Stage();
        stage.setScene(new Scene(root, 415, 175));

        JFXButton b1 = new JFXButton(s1);
        b1.setOnAction(event -> {
            dialog.close();
            a1.onAction();
            stage.close();

        });
        content.setActions(b1);

        JFXButton b2 = new JFXButton(s2);
        b2.setOnAction(event -> {
            dialog.close();
            a2.onAction();
            stage.close();

        });

        JFXButton b3 = new JFXButton(s3);
        b3.setOnAction(event -> {
            dialog.close();
            a3.onAction();
            stage.close();

        });

        content.setActions(b3, b2, b1);

        stage.show();
        dialog.show();


    }

    public static void showAndWaitAlert(String content, String header, String title, AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setTitle(title);
        alert.showAndWait();


    }

    public static void showAlert(String content, String header, String title, AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setTitle(title);
        alert.show();


    }

    public static File showSaveFilePrompt(String title, String fileExtension, String initFileName, StageStyle stageStyle) {

        FileChooser fchooser = new FileChooser();
        fchooser.setTitle(title);
        fchooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(fileExtension, "*".concat(fileExtension)));

        if (!initFileName.isEmpty()) {
            fchooser.setInitialFileName(initFileName);
        }

        return fchooser.showSaveDialog(new Stage(stageStyle));
    }

    public static File showFilePrompt(String title, String fileExtension) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter(fileExtension, "*".concat(fileExtension)));
        return fileChooser.showOpenDialog(new Stage(StageStyle.TRANSPARENT));

    }

    public static String showInputPrompt(String header, String content, String title) {

        TextInputDialog textDialog = new TextInputDialog();
        textDialog.setHeaderText(header);
        textDialog.setContentText(content);
        textDialog.setTitle(title);
        textDialog.showAndWait();
        if (textDialog.getResult().isEmpty()) {
            return "canceled!!@#";
        } else
            return textDialog.getResult();
    }


    public static void showSimpleAlert(String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.showAndWait();

    }

    public static void showInfoAlert(String content, String header, String title, boolean wait) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setTitle(title);
        alert.showAndWait();


    }

    public static void showInfoAlert(String content, boolean wait) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(content);
        if (wait) {
            alert.showAndWait();
        } else {
            alert.show();
        }

    }
}


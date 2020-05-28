package com.pty.grape2.core.skin;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.skins.PromptLinesWrapper;
import com.jfoenix.skins.ValidationPane;
import com.sun.javafx.scene.control.skin.TextAreaSkin;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author lxl
 */
public class JFXTextAreaSkinCustom extends TextAreaSkin {

    private boolean invalid = true;

    private ScrollPane scrollPane;
    private Text promptText;

    private ValidationPane<JFXTextArea> errorContainer;
    private PromptLinesWrapper<JFXTextArea> linesWrapper;

    public JFXTextAreaSkinCustom(JFXTextArea textArea) {
        super(textArea);
        // init text area properties
        scrollPane = (ScrollPane) getChildren().get(0);
        textArea.setWrapText(false);

        linesWrapper = new PromptLinesWrapper<>(
                textArea,
                super.promptTextFill,
                textArea.textProperty(),
                textArea.promptTextProperty(),
                () -> promptText);

        linesWrapper.init(() -> createPromptNode(), scrollPane);
        errorContainer = new ValidationPane<>(textArea);
        getChildren().addAll(linesWrapper.line, linesWrapper.focusedLine, linesWrapper.promptContainer, errorContainer);

        registerChangeListener(textArea.disableProperty(), "DISABLE_NODE");
        registerChangeListener(textArea.focusColorProperty(), "FOCUS_COLOR");
        registerChangeListener(textArea.unFocusColorProperty(), "UNFOCUS_COLOR");
        registerChangeListener(textArea.disableAnimationProperty(), "DISABLE_ANIMATION");
    }

    @Override
    protected void handleControlPropertyChanged(String propertyReference) {
        if ("DISABLE_NODE".equals(propertyReference)) {
            linesWrapper.updateDisabled();
        } else if ("FOCUS_COLOR".equals(propertyReference)) {
            linesWrapper.updateFocusColor();
        } else if ("UNFOCUS_COLOR".equals(propertyReference)) {
            linesWrapper.updateUnfocusColor();
        } else if ("DISABLE_ANIMATION".equals(propertyReference)) {
            // remove error clip if animation is disabled
            errorContainer.updateClip();
        } else {
            super.handleControlPropertyChanged(propertyReference);
        }
    }

    @Override
    protected void layoutChildren(final double x, final double y, final double w, final double h) {
        super.layoutChildren(x, y, w, h);

        final double height = getSkinnable().getHeight();
        linesWrapper.layoutLines(x, y, w, h, height, promptText == null ? 0 : promptText.getLayoutBounds().getHeight() + 3);
        errorContainer.layoutPane(x, height + linesWrapper.focusedLine.getHeight(), w, h);
        linesWrapper.updateLabelFloatLayout();


        if (invalid) {
            invalid = false;
            // set the default background of text area viewport to white
            Region viewPort = (Region) scrollPane.getChildrenUnmodifiable().get(0);
            viewPort.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,
                    CornerRadii.EMPTY,
                    Insets.EMPTY)));
            // reapply css of scroll pane in case set by the user
            viewPort.applyCss();
            errorContainer.invalid(w);
            // focus
            linesWrapper.invalid();
        }
    }

    private void createPromptNode() {
        if (promptText != null || !linesWrapper.usePromptText.get()) {
            return;
        }
        promptText = new Text();
        promptText.setManaged(false);
        promptText.getStyleClass().add("text");
        promptText.visibleProperty().bind(linesWrapper.usePromptText);
        promptText.fontProperty().bind(getSkinnable().fontProperty());
        promptText.textProperty().bind(getSkinnable().promptTextProperty());
        promptText.fillProperty().bind(linesWrapper.animatedPromptTextFill);
        promptText.setLayoutX(1);
        promptText.setTranslateX(1);
        promptText.getTransforms().add(linesWrapper.promptTextScale);
        linesWrapper.promptContainer.getChildren().add(promptText);
        if (getSkinnable().isFocused() && ((JFXTextArea) getSkinnable()).isLabelFloat()) {
            promptText.setTranslateY(-Math.floor(scrollPane.getHeight()));
            linesWrapper.promptTextScale.setX(0.85);
            linesWrapper.promptTextScale.setY(0.85);
        }

        try {
            reflectionFieldConsumer("promptNode", field -> {
                Object oldValue = field.get(this);
                if (oldValue != null) {
                    removeHighlight(Arrays.asList(((Node) oldValue)));
                }
                field.set(this, promptText);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> void reflectionFieldConsumer(String fieldName, JFXTextAreaSkinCustom.CheckedConsumer<Field> consumer) {
        Field field = null;
        try {
            field = TextAreaSkin.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            consumer.accept(field);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private interface CheckedConsumer<T> {
        void accept(T t) throws Exception;
    }

}
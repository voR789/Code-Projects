package com.example.wordle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class WordleController implements javafx.fxml.Initializable {

    private Scanner scan;
    private Scanner scan2;
    private ArrayList<String> words;
    private ArrayList<String> valid;

    private final String keyboard = "qwertyuiopasdfghjklzxcvbnm";
    private final int[] key = {10, 23, 21, 12, 2, 13, 14, 15, 7, 16, 17, 18, 25, 24, 8, 9, 0, 3, 11, 4, 6, 22, 1, 20, 5, 19};


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        words = new ArrayList<>();
        valid = new ArrayList<>();
        readFile();

        defaultColor = Color.GRAY;
        defaultSubColor = Color.web("b5b5b5");
        correctColor = Color.GREEN;
        wrongColor = Color.RED;
        inWordColor = Color.YELLOW;
        globalTextFill = Color.BLACK;
        backgroundColor = Color.web("e1e1e1");
        font = "Verdana";
        colorMenu = new ContextMenu();
        MenuItem light = new MenuItem("Light");
        light.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                defaultColor = Color.GRAY;
                defaultSubColor = Color.web("b5b5b5");
                correctColor = Color.GREEN;
                wrongColor = Color.RED;
                inWordColor = Color.YELLOW;
                globalTextFill = Color.BLACK;
                backgroundColor = Color.web("e1e1e1");
                layout.addEventFilter(KeyEvent.ANY, eventHandler);
            }
        });
        MenuItem dark = new MenuItem("Dark");
        dark.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                defaultColor = Color.web("3D3D3D");
                defaultSubColor = Color.web("575757");
                correctColor = Color.web("00897B");
                wrongColor = Color.web("C62828");
                inWordColor = Color.web("FF8F00");
                globalTextFill = Color.web("D3D3D3");
                backgroundColor = Color.web("212121");
                layout.addEventFilter(KeyEvent.ANY, eventHandler);
            }
        });
        MenuItem mono = new MenuItem("Monochrome");
        mono.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                defaultColor = Color.web("818589");
                defaultSubColor = Color.web("A9A9A9");
                correctColor = Color.web("#758C74");
                wrongColor = Color.web("#8C7474");
                inWordColor = Color.web("#8B8C74");
                globalTextFill = Color.web("E5E4E2");
                backgroundColor = Color.web("818589");
                layout.addEventFilter(KeyEvent.ANY, eventHandler);
            }
        });
        MenuItem dusk = new MenuItem("Dusk");
        dusk.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                defaultColor = Color.web("46244C");
                defaultSubColor = Color.web("835280");
                correctColor = Color.web("712B75");
                wrongColor = Color.web("C74B50");
                inWordColor = Color.web("D49B54");
                globalTextFill = Color.web("F9F9F3");
                backgroundColor = Color.web("343435");
                layout.addEventFilter(KeyEvent.ANY, eventHandler);
            }
        });
        MenuItem terra = new MenuItem("Terracotta");
        terra.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                defaultColor = Color.web("FCF8E8");
                defaultSubColor = Color.web("F1E9C7");
                correctColor = Color.web("94B49F");
                wrongColor = Color.web("DF7861");
                inWordColor = Color.web("ECB390");
                globalTextFill = Color.web("764b45");
                backgroundColor = Color.web("B98E88");
                layout.addEventFilter(KeyEvent.ANY, eventHandler);
            }
        });
        MenuItem pastel = new MenuItem("Pastel");
        pastel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                defaultColor = Color.web("A5DEE5");
                defaultSubColor = Color.web("f4c2c2");
                correctColor = Color.web("E0F9B5");
                wrongColor = Color.web("fdc7c7");
                inWordColor = Color.web("f9f2bb");
                globalTextFill = Color.web("493961");
                backgroundColor = Color.web("fffacd");
                layout.addEventFilter(KeyEvent.ANY, eventHandler);
            }
        });
        MenuItem albino = new MenuItem("Albino");
        albino.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                defaultColor = Color.web("A2B5BB");
                defaultSubColor = Color.web("CFD2CF");
                correctColor = Color.web("B6D2B6");
                wrongColor = Color.web("D2B6B6");
                inWordColor = Color.web("D3D6B0");
                globalTextFill = Color.web("EB1D36");
                backgroundColor = Color.web("F5EDDC");
                layout.addEventFilter(KeyEvent.ANY, eventHandler);
            }
        });
        MenuItem forest = new MenuItem("Forest");
        forest.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                defaultColor = Color.web("D8E9A8");
                defaultSubColor = Color.web("5EA777");
                correctColor = Color.web("4E9F3D");
                wrongColor = Color.web("004C24");
                inWordColor = Color.web("829F3D");
                globalTextFill = Color.web("191A19");
                backgroundColor = Color.web("E8FCEF");
                layout.addEventFilter(KeyEvent.ANY, eventHandler);
            }
        });
        MenuItem royal = new MenuItem("Royal");
        royal.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                defaultColor = Color.web("C0C0C0");
                defaultSubColor = Color.web("CD7F32");
                correctColor = Color.web("F0A500");
                wrongColor = Color.web("A73200");
                inWordColor = Color.web("A96B37");
                globalTextFill = Color.web("7851a9");
                backgroundColor = Color.web("334756");
                layout.addEventFilter(KeyEvent.ANY, eventHandler);
            }
        });
        colorMenu.getItems().addAll(light, dark, mono, dusk, terra, pastel, albino, forest, royal);

        fontMenu = new ContextMenu();
        for (int i = 0; i < javafx.scene.text.Font.getFamilies().size(); i++) {
            MenuItem temp = new MenuItem(javafx.scene.text.Font.getFamilies().get(i));
            temp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (!temp.getText().contains("dings")) {
                        font = temp.getText();
                        layout.addEventFilter(KeyEvent.ANY, eventHandler);
                    } else
                        System.out.println("Wingdings/Webdings does not work, please try another font");
                }
            });
            fontMenu.getItems().add(temp);
        }

        for (int i = 0; i < 26; i++) {
            Label l = new Label(String.valueOf(keyboard.charAt(i)));
            l.setFont(Font.font(font,15));
            l.setTextFill(globalTextFill);
            Rectangle r = new Rectangle(26, 26);
            r.setArcHeight(5);
            r.setArcWidth(5);
            r.setFill(Color.GRAY);
            StackPane s = new StackPane(r, l);
            wordBank.getChildren().add(s);
        }
    }

    public void readFile() {
        {
            try {
                scan = new Scanner(new File("C:\\Users\\Tvo19\\IdeaProjects\\Hangman\\sgb-words.txt"));
                scan2 = new Scanner(new File("C:\\Users\\Tvo19\\IdeaProjects\\Hangman\\valid-wordle-words.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("Files not found.");
            }
        }
        while (scan.hasNextLine()) {
            words.add(scan.nextLine());
        }
        while (scan2.hasNextLine()) {
            valid.add(scan2.nextLine());
        }
    }


    @FXML
    Rectangle background;

    @FXML
    BorderPane layout;

    @FXML
    Rectangle titleBack;

    @FXML
    Rectangle fontBack;

    @FXML
    Rectangle colorBack;

    @FXML
    Rectangle wordBankBack;

    @FXML
    Label title;

    @FXML
    Label fonts;

    @FXML
    Label colors;

    @FXML
    GridPane board;

    @FXML
    Button start;

    @FXML
    FlowPane wordBank;


    ContextMenu colorMenu;
    Color defaultColor;
    // Default (GRAY)
    Color defaultSubColor;
    // Default2 (b5b5b5)
    Color correctColor;
    // Correct (GREEM)
    Color wrongColor;
    // Wrong (RED)
    Color inWordColor;
    // In-word (YELLOW)
    Color globalTextFill;
    // (BLACK)
    Color backgroundColor;
    // (e1e1e1)

    @FXML
    ContextMenu fontMenu;
    String font;


    @FXML
    public void showColorMenu(ContextMenuEvent e) {
        colorMenu.show(colors, e.getScreenX(), e.getScreenY());
    }

    @FXML
    public void showFontMenu(ContextMenuEvent e) {
        fontMenu.show(fonts, e.getScreenX(), e.getScreenY());
    }

    private String answer = "";
    private String choice = "";
    private int turn = 0;

    @FXML
    public void startGame() {
        background.setFill(backgroundColor);
        titleBack.setFill(defaultSubColor);
        fontBack.setFill(defaultSubColor);
        colorBack.setFill(defaultSubColor);
        wordBankBack.setFill(defaultSubColor);
        title.setTextFill(globalTextFill);
        fonts.setTextFill(globalTextFill);
        colors.setTextFill(globalTextFill);
        title.setFont(new Font(font, 34.0));
        fonts.setFont(new Font(font, 13.0));
        colors.setFont(new Font(font, 13.0));
        answer = words.get((int) (Math.random() * 1001));
        turn = 0;
        index = 0;
        choice = "";
        grid.clear();
        board.getColumnConstraints().clear();
        board.getRowConstraints().clear();
        board.getChildren().clear();
        board.getRowConstraints().add(turn, new RowConstraints(25));
        layout.removeEventFilter(KeyEvent.ANY, eventHandler);

        for (int i = 0; i < 5; i++) {
            board.getColumnConstraints().add(new ColumnConstraints(25));
            Rectangle border = new Rectangle(28, 28);
            border.setArcHeight(5);
            border.setArcWidth(5);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.DIMGRAY);
            border.setStrokeWidth(2);
            GridPane.setHalignment(border, HPos.CENTER);
            board.add(border, i, 0);
        }
        for (int i = 0; i < wordBank.getChildren().size(); i++) {
            ((Rectangle) ((StackPane) wordBank.getChildren().get(i)).getChildren().get(0)).setFill(defaultColor);
            ((Label) ((StackPane) wordBank.getChildren().get(i)).getChildren().get(1)).setTextFill(globalTextFill);
            ((Label) ((StackPane) wordBank.getChildren().get(i)).getChildren().get(1)).setFont(new Font(font, 15));
        }
        grid.add(FXCollections.observableArrayList());
    }

    private int index = 0;
    private StackPane holder;
    private Label letter;
    private Rectangle letBox;
    private StackPane letCon;
    private final ObservableList<ObservableList<StackPane>> grid = FXCollections.observableArrayList();


    @FXML
    public void type(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            if (valid.contains(choice)) {
                for (int i = 0; i < 5; i++) {
                    if (choice.charAt(i) == answer.charAt(i)) {
                        ((Rectangle) grid.get(turn).get(i).getChildren().get(0)).setFill(correctColor);
                        if (((Rectangle) ((StackPane) wordBank.getChildren().get(key[(int) (choice.charAt(i)) - 97])).getChildren().get(0)).getFill().equals(defaultColor) || ((Rectangle) ((StackPane) wordBank.getChildren().get(key[(int) (choice.charAt(i)) - 97])).getChildren().get(0)).getFill().equals(inWordColor)) {
                            ((Rectangle) ((StackPane) wordBank.getChildren().get(key[(int) (choice.charAt(i)) - 97])).getChildren().get(0)).setFill(correctColor);
                        }
                    } else if (answer.contains(String.valueOf(choice.charAt(i)))) {
                        ((Rectangle) grid.get(turn).get(i).getChildren().get(0)).setFill(inWordColor);
                        if (((Rectangle) ((StackPane) wordBank.getChildren().get(key[(int) (choice.charAt(i)) - 97])).getChildren().get(0)).getFill().equals(defaultColor)) {
                            ((Rectangle) ((StackPane) wordBank.getChildren().get(key[(int) (choice.charAt(i)) - 97])).getChildren().get(0)).setFill(inWordColor);
                        }
                    } else if (((Rectangle) ((StackPane) wordBank.getChildren().get(key[(int) (choice.charAt(i)) - 97])).getChildren().get(0)).getFill().equals(defaultColor)) {
                        ((Rectangle) ((StackPane) wordBank.getChildren().get(key[(int) (choice.charAt(i)) - 97])).getChildren().get(0)).setFill(wrongColor);
                    }
                }
                if (choice.equalsIgnoreCase(answer)) {
                    System.out.println("Win");
                    layout.addEventFilter(KeyEvent.ANY, eventHandler);
                } else if (turn == 5) {
                    System.out.println("Lose!, word was: " + answer);
                    layout.addEventFilter(KeyEvent.ANY, eventHandler);
                } else {
                    choice = "";
                    index = 0;
                    turn++;
                    board.addRow(turn);
                    board.getRowConstraints().add(new RowConstraints(25));
                    for (int i = 0; i < 5; i++) {
                        Rectangle border = new Rectangle(28, 28);
                        border.setArcHeight(5);
                        border.setArcWidth(5);
                        border.setFill(Color.TRANSPARENT);
                        border.setStroke(Color.DIMGRAY);
                        border.setStrokeWidth(2);
                        GridPane.setHalignment(border, HPos.CENTER);
                        board.add(border, i, turn);
                    }
                    grid.add(FXCollections.observableArrayList());
                }
            }
        } else if (e.getCode() == KeyCode.BACK_SPACE) {
            if (!choice.isBlank()) {
                ((Rectangle) grid.get(turn).get(index - 1).getChildren().get(0)).setFill(Color.TRANSPARENT);
                ((Label) grid.get(turn).get(index - 1).getChildren().get(1)).setText("");
                index--;
                choice = choice.substring(0, choice.length() - 1);
            }
        } else if ((index < answer.length()) && e.getText().length() != 0 && Character.isLetter(e.getText().charAt(0))) {
            choice += e.getText();

            letter = new Label(e.getText());
            letter.setPrefSize(25, 25);
            letter.setFont(Font.font(font, FontWeight.BOLD, 15));
            letter.setTextFill(globalTextFill);
            letter.setAlignment(Pos.CENTER);

            letBox = new Rectangle(30, 30);
            letBox.setArcHeight(8);
            letBox.setArcWidth(8);
            letBox.setFill(defaultColor);

            letCon = new StackPane(letBox, letter);
            letCon.setAlignment(Pos.CENTER);
            GridPane.setHalignment(letCon, HPos.CENTER);
            board.add(letCon, index, turn);
            grid.get(turn).add(index++, letCon);
        }
    }

    EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            keyEvent.consume();
        }
    };


}

package com.example.snakeandladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class PlayAreaController {
    @FXML
    Text number;
    @FXML
    ImageView bluePlayer,redPlayer,blue,red;
    int turn = 1;
    HashMap<Pair<Double,Double>,Pair<Double,Double>> snakeLadderCoordinates;
    @FXML
    public void roll(MouseEvent event) throws IOException {
        Random random = new Random();
        int rolling = random.nextInt(6)+1;
        number.setText(""+rolling);

        getSnakeLadderCoordinates();

        if(turn==1){
            Pair<Double,Double> moveCoordinates = movement(bluePlayer.getTranslateX(),bluePlayer.getTranslateY(),rolling);
            bluePlayer.setTranslateX(moveCoordinates.getKey());
            bluePlayer.setTranslateY(moveCoordinates.getValue());
            if(snakeLadderCoordinates.containsKey(moveCoordinates)){
                bluePlayer.setTranslateX(snakeLadderCoordinates.get(moveCoordinates).getKey());
                bluePlayer.setTranslateY(snakeLadderCoordinates.get(moveCoordinates).getValue());
            }
            checkWin(bluePlayer.getTranslateX(), bluePlayer.getTranslateY());
        }
        else{
            Pair<Double,Double> moveCoordinates = movement(redPlayer.getTranslateX(),redPlayer.getTranslateY(),rolling);
            redPlayer.setTranslateX(moveCoordinates.getKey());
            redPlayer.setTranslateY(moveCoordinates.getValue());
            redPlayer.setTranslateX(moveCoordinates.getKey());
            redPlayer.setTranslateY(moveCoordinates.getValue());
            if(snakeLadderCoordinates.containsKey(moveCoordinates)){
                redPlayer.setTranslateX(snakeLadderCoordinates.get(moveCoordinates).getKey());
                redPlayer.setTranslateY(snakeLadderCoordinates.get(moveCoordinates).getValue());
            }
            checkWin(redPlayer.getTranslateX(), redPlayer.getTranslateY());
        }

        if(rolling!=6) {
            if (turn == 1) {
                turn = 2;
                blue.setOpacity(0.0);
                red.setOpacity(1.0);
            } else {
                turn = 1;
                red.setOpacity(0.0);
                blue.setOpacity(1.0);
            }
        }
    }

    Pair<Double,Double> movement(Double x,Double y,int rolling){
        Double moveX = x;
        Double moveY = y;
        if(moveY%100==0) {
            moveX += rolling * 60;
            if (moveX > 600) {
                moveX = 1200 - moveX + 60;
                moveY -= 50;
            }
        }
        else {
            moveX -= rolling*60;
            if(moveX<60){
                if(moveY==-450) return new Pair<>(x,y);
                moveX = -1*(moveX-60);
                moveY -= 50;
            }
        }
        return new Pair<>(moveX, moveY);
    }

    void checkWin(Double x,Double y) throws IOException {
        if(x==60 && y==-450){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Result");
            if(turn==1){
                alert.setContentText("Blue Player Has Won THe Game");
            }
            else{
                alert.setContentText("Red Player Has Won The Game");
            }
            alert.showAndWait();
            GamePage page = new GamePage();
            HelloApplication.root.getChildren().setAll(page.root);
        }
    }

    void getSnakeLadderCoordinates(){
        snakeLadderCoordinates = new HashMap<>();
        snakeLadderCoordinates.put(new Pair<>(60.0,0.0),new Pair<>(180.0,-150.0));
        snakeLadderCoordinates.put(new Pair<>(240.0,0.0),new Pair<>(420.0,-50.0));
        snakeLadderCoordinates.put(new Pair<>(540.0,0.0),new Pair<>(600.0,-150.0));
        snakeLadderCoordinates.put(new Pair<>(480.0,-100.0),new Pair<>(240.0,-400.0));
        snakeLadderCoordinates.put(new Pair<>(60.0,-100.0),new Pair<>(120.0,-200.0));
        snakeLadderCoordinates.put(new Pair<>(600.0,-250.0),new Pair<>(420.0,-300.0));
        snakeLadderCoordinates.put(new Pair<>(600.0,-350.0),new Pair<>(600.0,-450.0));
        snakeLadderCoordinates.put(new Pair<>(60.0,-350.0),new Pair<>(60.0,-450.0));
        snakeLadderCoordinates.put(new Pair<>(240.0,-50.0),new Pair<>(420.0,0.0));
        snakeLadderCoordinates.put(new Pair<>(420.0,-250.0),new Pair<>(420.0,-150.0));
        snakeLadderCoordinates.put(new Pair<>(120.0,-300.0),new Pair<>(120.0,-50.0));
        snakeLadderCoordinates.put(new Pair<>(240.0,-300.0),new Pair<>(60.0,-250.0));
        snakeLadderCoordinates.put(new Pair<>(420.0,-400.0),new Pair<>(240.0,-100.0));
        snakeLadderCoordinates.put(new Pair<>(480.0,-450.0),new Pair<>(480.0,-350.0));
        snakeLadderCoordinates.put(new Pair<>(360.0,-450.0),new Pair<>(360.0,-350.0));
        snakeLadderCoordinates.put(new Pair<>(180.0,-450.0),new Pair<>(120.0,-350.0));
    }
}

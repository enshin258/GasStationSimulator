package sample;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import sample.Events.ArriveOfCar;
import sample.Objects.*;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public AnchorPane main_layout;

    @FXML
    public ScrollPane queue_to_LPG_layout;
    @FXML
    public ScrollPane queue_to_ON_layout;
    @FXML
    public ScrollPane queue_to_PB98_layout;

    @FXML
    public ScrollPane LPG_layout;
    @FXML
    public ScrollPane PB98_layout;
    @FXML
    public ScrollPane ON_layout;
    @FXML
    public ScrollPane queue_to_cash_layout;
    @FXML
    public ScrollPane cash_layout;
    @FXML
    public ScrollPane queue_to_car_wash_layout;
    @FXML
    public ScrollPane car_wash_layout;


    @FXML
    public HBox queue_to_LPG_box;
    @FXML
    public HBox queue_to_PB98_box;
    @FXML
    public HBox queue_to_ON_box;
    @FXML
    public VBox LPG_box;
    @FXML
    public VBox PB98_box;
    @FXML
    public VBox ON_box;
    @FXML
    public HBox queue_to_cash_box;
    @FXML
    public VBox cash_box;
    @FXML
    public HBox queue_to_car_wash_box;
    @FXML
    public HBox car_wash_box;


    @FXML
    public Slider queue_to_LPG_slider;
    @FXML
    public Slider queue_to_ON_slider;
    @FXML
    public Slider queue_to_PB98_slider;
    @FXML
    public Slider LPG_slider;
    @FXML
    public Slider PB98_slider;
    @FXML
    public Slider ON_slider;
    @FXML
    public Slider queue_to_cash_slider;
    @FXML
    public Slider queue_to_car_wash_slider;
    @FXML
    public Slider cash_slider;


    @FXML
    public Button start_simulation_button;
    @FXML
    public Button pause_simulation_button;
    @FXML
    ToggleButton fast_simulation_button;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        queue_to_LPG_layout.setContent(queue_to_LPG_box);
        queue_to_ON_layout.setContent(queue_to_ON_box);
        queue_to_PB98_layout.setContent(queue_to_PB98_box);
        LPG_layout.setContent(LPG_box);
        PB98_layout.setContent(PB98_box);
        ON_layout.setContent(ON_box);
        queue_to_cash_layout.setContent(queue_to_cash_box);
        cash_layout.setContent(cash_box);
        queue_to_car_wash_layout.setContent(queue_to_car_wash_box);
        car_wash_layout.setContent(car_wash_box);


        SimGenerator simGenerator = new SimGenerator();
        SimManager simManager = new SimManager();
        simManager.setSimTimeRatio(0.8);



        start_simulation_button.setOnAction(actionEvent -> {
            try {
                Board board = new Board(queue_to_LPG_box,queue_to_PB98_box,queue_to_ON_box,LPG_box,PB98_box,ON_box,queue_to_cash_box,cash_box,queue_to_car_wash_box,car_wash_box);
                GasStation gasStation = new GasStation(
                        board,
                        50,//number of cars
                        0.5,
                        30,
                        100,
                        2,
                        3,
                        4,
                        2,
                        2,
                        simGenerator,
                        simManager,
                        new QueueToCash((int)queue_to_cash_slider.getValue()),
                        new QueueToLPG((int)queue_to_LPG_slider.getValue()),
                        new QueueToPB98((int)queue_to_PB98_slider.getValue()),
                        new QueueToON((int)queue_to_ON_slider.getValue()),
                        new QueueToCarWash((int)queue_to_car_wash_slider.getValue()),
                        (int)LPG_slider.getValue(),
                        (int)ON_slider.getValue(),
                        (int)PB98_slider.getValue(),
                        (int)cash_slider.getValue(),
                        true
                );
                if(fast_simulation_button.isSelected())
                {
                    SimManager.simMode = SimParameters.SimMode.ASAP;
                }
                else
                {
                    SimManager.simMode = SimParameters.SimMode.ASTRONOMICAL;
                }

                System.out.println("SIMULATION WITH PARAMETERS");
                System.out.println(gasStation.toString());

                MyThread myThread = new MyThread(gasStation);
                new ArriveOfCar(gasStation,0);
                myThread.start();
            } catch (SimControlException e) {
                e.printStackTrace();
            }
        });
        pause_simulation_button.setOnAction(actionEvent ->
        {
            System.out.println("PAUSE SIMULATION");
            simManager.pauseSimulation();
        });
    }
}

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene; 
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage; 
import javax.imageio.ImageIO;

 public class HelloMenu extends Application {
     
     //Pfusch
     
     public static double pressedX;
     public static double pressedY;
     public static double releasedX;
     public static double releasedY;
     public static double memoryX;
     public static double memoryY;
     public static int number=1;
     public static int cor=0;
     public static int corb=0;
     public static int corB=0;
     public static int corR=0;
     public static int korPosZahlX=1;
     public static int korPosZahlY=1;
     public static Number style=0;
     public static Number pictureSize=0;
     public static int pwidth=100;
     public static int pheight=100;
 
     @Override public void start(Stage stage) {

        
         
        File file = new File("");
        final FileChooser fileChooser = new FileChooser();
        
        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(
        "Standard Circle", "Standard Square","Blue Circle", 
         "Red Circle"));
        cb.getSelectionModel().select(0);
        
              
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        scene.setCursor(Cursor.DEFAULT);
        GridPane layout = new GridPane();
        StackPane stackPane = new StackPane();
        //stackPane.setMaxSize(400, 900);

        Canvas canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //menu
                // Create MenuBar
        MenuBar menuBar = new MenuBar();
        
        // Create menus
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");
        Menu Counter = new Menu("Counter: "+String.valueOf(number));
        
        // Create MenuItems
        MenuItem menuLoadPicturea = new MenuItem("Load Picture Small 75*55");
        MenuItem menuLoadPictureb = new MenuItem("Load Picture Small 55*75");
        MenuItem menuLoadPicturec = new MenuItem("Load Picture Large 145*107");
        MenuItem menuSavePicture = new MenuItem("Save Picture");
        MenuItem exitItem = new MenuItem("Exit");
        
        MenuItem menuResetPicture = new MenuItem("Clear Picture");
        MenuItem menuResetCounter = new MenuItem("Reset Counter");
        MenuItem menuCounterUp = new MenuItem("Counter +");
        MenuItem menuCounterDown = new MenuItem("Counter -");
        
        MenuItem helpBox = new MenuItem("Help");
               
        // Add menuItems to the Menus
        fileMenu.getItems().addAll(menuLoadPicturea, menuLoadPictureb, menuLoadPicturec,menuSavePicture,exitItem);
        editMenu.getItems().addAll(menuResetPicture, menuResetCounter,menuCounterUp,menuCounterDown);
        helpMenu.getItems().addAll(helpBox);
        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, editMenu,helpMenu,Counter);
        Text myLabel=new Text(" Use left Mouse to draw positions, right Mouse to copy previous X/Y, CTRL+Mouse to draw horizontal positions!");
        myLabel.setFill(Color.WHITE);
        myLabel.setFont(Font.font("ARIAL", 16));
        Text myLabelb=new Text("  Choose Style: ");
        myLabelb.setFill(Color.WHITE);
        myLabelb.setFont(Font.font("ARIAL", 16));
        //String MyNumber =String.valueOf(number);
        //Text myLabelc=new Text(MyNumber);
        //myLabelc.setFill(Color.WHITE);
        //myLabelc.setFont(Font.font("ARIAL", 16));

        HBox box = new HBox();
        HBox boxi = new HBox();
        box.getChildren().add(myLabel);
        box.getChildren().add(myLabelb);
        box.getChildren().add(cb);
        //box.getChildren().add(myLabelc);
        
       
        
        stackPane.getChildren().add(canvas);
        stackPane.getChildren().add(boxi);
        layout.add(menuBar,0,0);
        layout.add(stackPane,0,1);
        layout.add(box,0,2);
        
        root.getChildren().add(layout);
        
//Selector Style

        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener <Number>()
        {public void changed(ObservableValue ov, Number value, Number new_value){
        style=new_value;cor=0;corb=0;corB=0;corR=0;
        } 
        
        });
        


//Menu

// Exit MenuItem.
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        System.exit(0);
    }
});

// LoadPictureA MenuItem.
        menuLoadPicturea.setAccelerator(KeyCombination.keyCombination("Ctrl+1"));
        menuLoadPicturea.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
           fileChooser.setTitle("Load Picture");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            File file = fileChooser.showOpenDialog(stage);
            pwidth=724;pheight=540;
            Image image = new Image(file.toURI().toString(),pwidth, pheight,false, false);
            
         //clear Canvas
         canvas.setWidth(image.getWidth());
         canvas.setHeight(image.getHeight());
         gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

         // simple displays ImageView the image as is
         ImageView iv1 = new ImageView();
         iv1.setImage(image);
         iv1.setSmooth(false);
         iv1.setCache(false);
         stackPane.getChildren().set(0,iv1);
         stackPane.getChildren().set(1,canvas);
         number=1;cor=0;corb=0;corB=0;corR=0;
    }
});        

// LoadPictureB MenuItem.
        menuLoadPictureb.setAccelerator(KeyCombination.keyCombination("Ctrl+2"));
        menuLoadPictureb.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
           fileChooser.setTitle("Load Picture");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            File file = fileChooser.showOpenDialog(stage);
            pwidth=540;pheight=724;
            Image image = new Image(file.toURI().toString(),pwidth, pheight,false, false);
            
         //clear Canvas
         canvas.setWidth(image.getWidth());
         canvas.setHeight(image.getHeight());
         gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

         // simple displays ImageView the image as is
         ImageView iv1 = new ImageView();
         iv1.setImage(image);
         iv1.setSmooth(false);
         iv1.setCache(false);
         StackPane.setAlignment(iv1, Pos.BOTTOM_LEFT);
         stackPane.getChildren().set(0,iv1);
         stackPane.getChildren().set(1,canvas);
         StackPane.setAlignment(canvas, Pos.BOTTOM_LEFT);
         number=1;cor=0;corb=0;corB=0;corR=0;
    }
});        
  
// LoadPictureC MenuItem.
        menuLoadPicturec.setAccelerator(KeyCombination.keyCombination("Ctrl+3"));
        menuLoadPicturec.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
           fileChooser.setTitle("Load Picture");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            File file = fileChooser.showOpenDialog(stage);
            pwidth=1400;pheight=1000;
            Image image = new Image(file.toURI().toString(),pwidth, pheight,false, false);
            
         //clear Canvas
         canvas.setWidth(image.getWidth());
         canvas.setHeight(image.getHeight());
         gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

         // simple displays ImageView the image as is
         ImageView iv1 = new ImageView();
         iv1.setImage(image);
         iv1.setSmooth(false);
         iv1.setCache(false);
         stackPane.getChildren().set(0,iv1);
         stackPane.getChildren().set(1,canvas);
         number=1;cor=0;corb=0;corB=0;corR=0;
    }
});        
        
// Save MenuItem.
        menuSavePicture.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        menuSavePicture.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        WritableImage image = stackPane.snapshot(new SnapshotParameters(), null);
            fileChooser.setTitle("Save Picture");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            File file = fileChooser.showSaveDialog(stage);
            try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
        	// TODO: handle exception here
             }
            }
    
});

// Reset PictureMenuItem.
        menuResetPicture.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        menuResetPicture.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
          gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                number=1;
                cor=0;corb=0;corB=0;corR=0;
                Counter.setText("Counter: "+String.valueOf(number));
    }
});

// Reset CounterMenuItem.
        menuResetCounter.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        menuResetCounter.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
             number=1;
             cor=0;corb=0;corB=0;corR=0;
             Counter.setText("Counter: "+String.valueOf(number));
    }
});        
    
//// CounterUp
        menuCounterUp.setAccelerator(KeyCombination.keyCombination("Ctrl+U"));
        menuCounterUp.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
             number+=1;cor=0;corb=0;corB=0;corR=0;
             Counter.setText("Counter: "+String.valueOf(number));
    }
});        
    
//// CounterDown
        menuCounterDown.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        menuCounterDown.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
             number-=1; cor=0;corb=0;corB=0;corR=0;
             Counter.setText("Counter: "+String.valueOf(number));
    }
}); 
        
//// Help
        helpMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        helpMenu.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
       
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Help&About MyPositions ©2020 SSW Soft");
        ButtonType ok = new ButtonType("Thank You!");
         // Remove default ButtonTypes
        alert.getButtonTypes().clear();
 
        alert.getButtonTypes().addAll(ok);
        alert.setHeaderText("Short Instructions");
        alert.setContentText("Use 'Load Image´ to load an image in the appropriate format. Draw Callouts with Mouse (vertical) or CTRL+Mouse (horizontal). If you use the right Mouse button, you copy the past X- or (+CTRL) Y-Position, for more orderly callouts. You can choose an appropriate style as well.");
  
        alert.showAndWait();
          
    }
}); 
        


//Mouse links
        stackPane.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
            if (((MouseEvent) event).getButton().equals(MouseButton.PRIMARY)){
                
            scene.setCursor(Cursor.CROSSHAIR);
            pressedX = event.getX();
            pressedY = event.getY();
            memoryX=pressedX;
            memoryY=pressedY;
            }
            //gc.strokeLine(0, 0, event.getX(), event.getY());
            //stackPane.getChildren().set(1,canvas);
            
            }
        });
        

            stackPane.addEventHandler(MouseEvent.MOUSE_RELEASED, 
            new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
            if (((MouseEvent) event).getButton().equals(MouseButton.PRIMARY)){
                
            releasedX = event.getX();
            releasedY = event.getY();
            Counter.setText("Counter: "+String.valueOf(number));
                  
            //ZEICHNEN

            //VERTIKAL
            if(event.isControlDown()){
            if(number>=10){cor=-5;corb=-9;corB=-3;corR=-3;} 
            
            //Fälle
            
            //Standard
            if(style.intValue()==0){
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(5);
            gc.strokeLine(pressedX+10, pressedY+5, pressedX+10, releasedY);
            gc.setLineWidth(2.0);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(pressedX+10, pressedY, pressedX+10, releasedY);
            gc.setFill(Color.WHITE); 
            gc.setStroke(Color.WHITE); 
            gc.strokeOval(pressedX-10, pressedY, 40, 40);
            gc.fillOval(pressedX-10, pressedY, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.BLACK);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX+corb+korPosZahlX, pressedY+28+korPosZahlY);
            number+=1;}
            
            //Standard Square
            if(style.intValue()==1){
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(5);
            gc.strokeLine(pressedX+10, pressedY+5, pressedX+10, releasedY);
            gc.setLineWidth(2.0);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(pressedX+10, pressedY, pressedX+10, releasedY);
            gc.setFill(Color.WHITE); 
            gc.fillRect(pressedX-10, pressedY, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.BLACK);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX+corb+korPosZahlX, pressedY+28+korPosZahlY);
            number+=1;}

            
            //blau
            if(style.intValue()==2){
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(5);
            gc.setFill(Color.BLUE);   
            gc.strokeOval(pressedX-10, pressedY, 40, 40);
            gc.strokeLine(pressedX+10, pressedY, pressedX+10, releasedY);
            gc.fillOval(pressedX-10, pressedY, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.WHITE);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX-1+cor+korPosZahlX+corB, pressedY+28+korPosZahlY);
            number+=1;}
            
            //rot
            if(style.intValue()==3){
            gc.setStroke(Color.RED);
            gc.setLineWidth(5);
            gc.setFill(Color.RED);   
            gc.strokeOval(pressedX-10, pressedY, 40, 40);
            gc.strokeLine(pressedX+10, pressedY, pressedX+10, releasedY);
            gc.fillOval(pressedX-10, pressedY, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.WHITE);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX-1+cor+korPosZahlX+corR, pressedY+28+korPosZahlY);
            number+=1;}
            
            }
            
            //Horizontal
            else {
            if(number>=10){cor=-5;corb=-9;corB=-3;corR=-3;}     
            
            //Fälle normal
            
            //Standard
            if(style.intValue()==0){
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(5);
            gc.strokeLine(pressedX+5, pressedY+5, releasedX, pressedY+5);
            gc.setLineWidth(2.0);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(pressedX, pressedY+5, releasedX, pressedY+5);
            gc.setFill(Color.WHITE); 
            gc.setStroke(Color.WHITE); 
            gc.strokeOval(pressedX, pressedY-15, 40, 40);
            gc.fillOval(pressedX, pressedY-15, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.BLACK);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX+9+corb+korPosZahlX, pressedY+14+korPosZahlY);
            number+=1;}
            
            //Standard Square
            if(style.intValue()==1){
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(5);
            gc.strokeLine(pressedX+5, pressedY+5, releasedX, pressedY+5);
            gc.setLineWidth(2.0);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(pressedX, pressedY+5, releasedX, pressedY+5);
            gc.setFill(Color.WHITE); 
            gc.fillRect(pressedX, pressedY-15, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.BLACK);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX+9+corb+korPosZahlX, pressedY+14+korPosZahlY);
            number+=1;}
            
            //blau
            if(style.intValue()==2){
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(5);
            gc.setFill(Color.BLUE);       
            gc.strokeOval(pressedX, pressedY-15, 40, 40);
            gc.strokeLine(pressedX, pressedY+5, releasedX, pressedY+5);
            gc.fillOval(pressedX, pressedY-15, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.WHITE);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX+9+cor+korPosZahlX+corB, pressedY+14+korPosZahlY);
            number+=1;}
            
            //red
            if(style.intValue()==3){
            gc.setStroke(Color.RED);
            gc.setLineWidth(5);
            gc.setFill(Color.RED);       
            gc.strokeOval(pressedX, pressedY-15, 40, 40);
            gc.strokeLine(pressedX, pressedY+5, releasedX, pressedY+5);
            gc.fillOval(pressedX, pressedY-15, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.WHITE);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX+9+cor+korPosZahlX+corR, pressedY+14+korPosZahlY);
            number+=1;}
            

            
            }

            
            stackPane.getChildren().set(1,canvas);
            scene.setCursor(Cursor.DEFAULT);
            }}
            
        });
 
            
 //Right Mouse
 
        stackPane.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
            if (((MouseEvent) event).getButton().equals(MouseButton.SECONDARY)){
                
            scene.setCursor(Cursor.CROSSHAIR);
            pressedX = event.getX();
            pressedY = event.getY();
           

            //gc.strokeLine(0, 0, event.getX(), event.getY());
            //stackPane.getChildren().set(1,canvas);
            }
            }
        });
            
 stackPane.addEventHandler(MouseEvent.MOUSE_RELEASED, 
            new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
            if (((MouseEvent) event).getButton().equals(MouseButton.SECONDARY)){
                
            releasedX = event.getX();
            releasedY = event.getY();
            Counter.setText("Counter: "+String.valueOf(number));
                  
            //ZEICHNEN

            //VERTIKAL
            if(event.isControlDown()){
            if(number>=10){cor=-5;corb=-9;corB=-3;corR=-3;} 
            
            //Fälle
            
            //Standard
            if(style.intValue()==0){
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(5);
            gc.strokeLine(pressedX+10, memoryY+5, pressedX+10, releasedY);
            gc.setLineWidth(2.0);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(pressedX+10, memoryY, pressedX+10, releasedY);
            gc.setFill(Color.WHITE); 
            gc.setStroke(Color.WHITE); 
            gc.strokeOval(pressedX-10, memoryY, 40, 40);
            gc.fillOval(pressedX-10, memoryY, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.BLACK);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX+corb+korPosZahlX, memoryY+28+korPosZahlY);
            number+=1;}
            
            //Standard Square
            if(style.intValue()==1){
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(5);
            gc.strokeLine(pressedX+10, memoryY+5, pressedX+10, releasedY);
            gc.setLineWidth(2.0);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(pressedX+10, memoryY, pressedX+10, releasedY);
            gc.setFill(Color.WHITE); 
            gc.fillRect(pressedX-10, memoryY, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.BLACK);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX+corb+korPosZahlX, memoryY+28+korPosZahlY);
            number+=1;}

            
            //blau
            if(style.intValue()==2){
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(5);
            gc.setFill(Color.BLUE);   
            gc.strokeOval(pressedX-10, memoryY, 40, 40);
            gc.strokeLine(pressedX+10, memoryY, pressedX+10, releasedY);
            gc.fillOval(pressedX-10, memoryY, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.WHITE);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX-1+cor+korPosZahlX+corB, memoryY+28+korPosZahlY);
            number+=1;}
            
            //rot
            if(style.intValue()==3){
            gc.setStroke(Color.RED);
            gc.setLineWidth(5);
            gc.setFill(Color.RED);   
            gc.strokeOval(pressedX-10, memoryY, 40, 40);
            gc.strokeLine(pressedX+10, memoryY, pressedX+10, releasedY);
            gc.fillOval(pressedX-10, memoryY, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.WHITE);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, pressedX-1+cor+korPosZahlX+corR, memoryY+28+korPosZahlY);
            number+=1;}
            
            }
            
            //Horizontal
            else {
            if(number>=10){cor=-5;corb=-9;corB=-3;corR=-3;}     
            
            //Fälle normal
            
            //Standard
            if(style.intValue()==0){
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(5);
            gc.strokeLine(memoryX+5, pressedY+5, releasedX, pressedY+5);
            gc.setLineWidth(2.0);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(memoryX, pressedY+5, releasedX, pressedY+5);
            gc.setFill(Color.WHITE); 
            gc.setStroke(Color.WHITE); 
            gc.strokeOval(memoryX, pressedY-15, 40, 40);
            gc.fillOval(memoryX, pressedY-15, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.BLACK);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, memoryX+9+corb+korPosZahlX, pressedY+14+korPosZahlY);
            number+=1;}
            
            //Standard Square
            if(style.intValue()==1){
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(5);
            gc.strokeLine(memoryX+5, pressedY+5, releasedX, pressedY+5);
            gc.setLineWidth(2.0);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(memoryX, pressedY+5, releasedX, pressedY+5);
            gc.setFill(Color.WHITE); 
            gc.fillRect(memoryX, pressedY-15, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.BLACK);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, memoryX+9+corb+korPosZahlX, pressedY+14+korPosZahlY);
            number+=1;}
            
            //blau
            if(style.intValue()==2){
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(5);
            gc.setFill(Color.BLUE);       
            gc.strokeOval(memoryX, pressedY-15, 40, 40);
            gc.strokeLine(memoryX, pressedY+5, releasedX, pressedY+5);
            gc.fillOval(memoryX, pressedY-15, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.WHITE);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, memoryX+9+cor+korPosZahlX+corB, pressedY+14+korPosZahlY);
            number+=1;}
            
            //red
            if(style.intValue()==3){
            gc.setStroke(Color.RED);
            gc.setLineWidth(5);
            gc.setFill(Color.RED);       
            gc.strokeOval(memoryX, pressedY-15, 40, 40);
            gc.strokeLine(memoryX, pressedY+5, releasedX, pressedY+5);
            gc.fillOval(memoryX, pressedY-15, 40, 40);
            gc.setFont(new Font("ARIAL", 32));
            gc.setLineWidth(3.0);
            gc.setFill(Color.WHITE);
            String MyNumber =String.valueOf(number);
            gc.fillText(MyNumber, memoryX+9+cor+korPosZahlX+corR, pressedY+14+korPosZahlY);
            number+=1;}
            

            
            }

            
            stackPane.getChildren().set(1,canvas);
            scene.setCursor(Cursor.DEFAULT);
            }}
            
        });
 
        
        
        
//UNDO
scene.setOnKeyPressed(e -> {
    if ((e.getCode() == KeyCode.Z)&& e.isControlDown()) {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    number=1;
    }
});
         stage.setTitle("MyPositions 1.0 ©2020 SSW Soft");
         stage.setWidth(1024);
         stage.setHeight(800);
         stage.setScene(scene); 
         stage.sizeToScene(); 
         //stage.setMaximized(true);
         stage.show(); 
     }


     
 }


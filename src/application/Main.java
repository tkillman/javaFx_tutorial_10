package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



//// *************�׸��� �����
public class Main extends Application {
			
			
	
	@Override
	public void start(Stage stage) {
		try {
			////// Canvas +gc
			Canvas canvas = new Canvas(800,500); //��ȭ�� ����
			GraphicsContext gc; 
			gc = canvas.getGraphicsContext2D(); // ��ȭ���� �׸� ���� ��üȭ
			gc.setStroke(Color.BLACK); // �� ����
			gc.setLineWidth(1); // �� ����
			
			///////////// ColorPicker
			ColorPicker cp = new ColorPicker();
			cp.setValue(Color.BLACK);
			cp.setOnAction(e->{
				gc.setStroke(cp.getValue());
				
			});
			///////Label
			Label label = new Label();
			
			/////////Slider
			Slider slider = new Slider();
			slider.setMin(1);
			slider.setMax(100);
			slider.setShowTickLabels(true);
			slider.setShowTickMarks(true);
			slider.valueProperty().addListener(e->{
				double value = slider.getValue();
				String str= String.format("%.1f", value);
				label.setText(str);
				gc.setLineWidth(slider.getValue());
				
			});
			
			
			//Container
			GridPane grid = new GridPane();
			grid.addRow(0, cp,slider, label);
			grid.setHgap(20);
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setPadding(new Insets(20,0,0,0));
			
			StackPane pane = new StackPane();
		
			pane.getChildren().addAll(canvas, grid);
			
			//Scene
			
			Scene scene = new Scene(pane,800,500);
			
			scene.setOnMousePressed(e->{
				gc.beginPath(); // �׸��� ����
				gc.lineTo(e.getSceneX(), e.getSceneY());
				gc.stroke();
			});
			
			scene.setOnMouseDragged(e->{
				gc.lineTo(e.getSceneX(), e.getSceneY());
				gc.stroke();
			});
			
			stage.setScene(scene);
			stage.show();
			
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

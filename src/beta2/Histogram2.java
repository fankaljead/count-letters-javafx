package beta2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class Histogram2 extends Application {
	final static char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//������ĸ����
		
	static int[] letterTimes = new int[letters.length];//�洢ÿ����ĸ���ֵĸ���
	
	
	public static int[] countLetters(String s){
		
		s = s.toUpperCase();//���ַ���ȫ��ת��Ϊ��д
		
		char[] sToLetters = s.toCharArray();//���ַ���ת��Ϊ�ַ�����
		
		for(int i = 0; i < letterTimes.length; i++){//ѭ��ͳ��26����ĸ�ĳ��ִ���
			
			for(int j = 0; j < sToLetters.length; j++){
				if(letters[i] == sToLetters[j])
					letterTimes[i]++;
			}
		}		
		return letterTimes;
	}

	//��������������
	public static void letterTimesClear(){
		for(int i = 0; i < letterTimes.length; i++)
			letterTimes[i] = 0;
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override public void start(Stage stage) {
    	
    	VBox vbox = new VBox();
    	HBox hbox = new HBox();//���������ť
        
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<String, Number> bc = 
            new BarChart<>(yAxis, xAxis);
        
        TextArea textArea = new TextArea();//�����û������ı�
		
		Button btEnter = new Button("Enter");//���˼�����26��ĸ��ͳ��
		Button btClear = new Button("Clear");//����ı���ͳ��ͼ
		
		XYChart.Series series = new XYChart.Series();
		
		
        bc.setTitle("Alphabetic statistics");//����ͳ��ͼ����
        xAxis.setLabel("Number");//������������          
        yAxis.setLabel("A-Z");//���ñ�ͳ����ĸ      
 
        

     
        btEnter.setStyle("-fx-font: 22 arial;-fx-base: #b6e7c9; ");//���ð�ť����
        btClear.setStyle("-fx-font: 22 arial;-fx-base: #b6e7c9; ");
        
        vbox.setSpacing(10);//���ô��������ť��hbox���Ҽ��
        hbox.setAlignment(Pos.CENTER);//���ô��������ť��hbox����
        
        hbox.setSpacing(10);//�������½ڵ�ļ��
       
        
       //����enter�¼�
        btEnter.setOnAction(e -> {
        	letterTimesClear();//������������
        	series.getData().clear();//���ͼ��
        	
        	letterTimes = countLetters(textArea.getText());//����ͳ����ĸ����
           
        	
        	//����ĸ���������Ƴ���״ͼ
    		for(int i = 0; i < letters.length; i++){    			
    			if(letterTimes[i] != 0)
    				series.getData().add(new XYChart.Data(String.valueOf(letters[i]), letterTimes[i]));
    		
			}
    		
			
        });
        
       //����clear�¼�
        btClear.setOnAction(e -> {
        	
        	series.getData().clear();//��״ͼ��������
			letterTimesClear();//������������		
			textArea.setText("");//�ı����е��������
		});     
        
        hbox.getChildren().addAll(btEnter, btClear);
        vbox.getChildren().addAll(bc, textArea, hbox); 
        
        Scene scene  = new Scene(vbox, 1000, 600);
        stage.setTitle("Super count");
        bc.getData().add(series);
        stage.setScene(scene);
        stage.show();
        
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
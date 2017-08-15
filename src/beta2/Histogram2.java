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
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//所有字母常量
		
	static int[] letterTimes = new int[letters.length];//存储每个字母出现的个数
	
	
	public static int[] countLetters(String s){
		
		s = s.toUpperCase();//将字符串全部转化为大写
		
		char[] sToLetters = s.toCharArray();//将字符串转化为字符数组
		
		for(int i = 0; i < letterTimes.length; i++){//循环统计26个字母的出现次数
			
			for(int j = 0; j < sToLetters.length; j++){
				if(letters[i] == sToLetters[j])
					letterTimes[i]++;
			}
		}		
		return letterTimes;
	}

	//将计数数组清零
	public static void letterTimesClear(){
		for(int i = 0; i < letterTimes.length; i++)
			letterTimes[i] = 0;
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override public void start(Stage stage) {
    	
    	VBox vbox = new VBox();
    	HBox hbox = new HBox();//存放两个按钮
        
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<String, Number> bc = 
            new BarChart<>(yAxis, xAxis);
        
        TextArea textArea = new TextArea();//用于用户输入文本
		
		Button btEnter = new Button("Enter");//按此键进行26字母的统计
		Button btClear = new Button("Clear");//清除文本及统计图
		
		XYChart.Series series = new XYChart.Series();
		
		
        bc.setTitle("Alphabetic statistics");//设置统计图名字
        xAxis.setLabel("Number");//设置数据栏名          
        yAxis.setLabel("A-Z");//设置被统计字母      
 
        

     
        btEnter.setStyle("-fx-font: 22 arial;-fx-base: #b6e7c9; ");//设置按钮属性
        btClear.setStyle("-fx-font: 22 arial;-fx-base: #b6e7c9; ");
        
        vbox.setSpacing(10);//设置存放两个按钮的hbox左右间距
        hbox.setAlignment(Pos.CENTER);//设置存放两个按钮的hbox居中
        
        hbox.setSpacing(10);//设置上下节点的间距
       
        
       //设置enter事件
        btEnter.setOnAction(e -> {
        	letterTimesClear();//计数数组清零
        	series.getData().clear();//清空图表
        	
        	letterTimes = countLetters(textArea.getText());//进行统计字母个数
           
        	
        	//将字母个数数据制成柱状图
    		for(int i = 0; i < letters.length; i++){    			
    			if(letterTimes[i] != 0)
    				series.getData().add(new XYChart.Data(String.valueOf(letters[i]), letterTimes[i]));
    		
			}
    		
			
        });
        
       //设置clear事件
        btClear.setOnAction(e -> {
        	
        	series.getData().clear();//柱状图数据清零
			letterTimesClear();//计数数组清零		
			textArea.setText("");//文本框中的内容清空
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

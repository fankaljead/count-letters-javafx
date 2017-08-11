package beta3;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Histogram extends Application {
	
	
	final static char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//所有字母常量
		
	static int[] letterTimes = new int[26];//存储每个字母出现的个数
	
	//找到最多的字母个数
	public static int maxInLetterTimes(){
		int max = 0;
		for(int i = 0; i < letterTimes.length; i++){
			if(max < letterTimes[i])
				max = letterTimes[i];
		}
		return max;
	}
	
	
	public static int[] countLetters(Scanner fInput){
		
		
		String s = new String();
		
		while(fInput.hasNext()){
			s += fInput.next();
		}
		
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

	public static void letterTimesClear(){
		for(int i = 0; i < letterTimes.length; i++)
			letterTimes[i] = 0;
	}
	
	public void start(Stage primaryStage){
		VBox vbox = new VBox();
		
		HBox hbox1 = new HBox();//存放柱状图
		HBox hbox2 = new HBox();//存放26个字母
		HBox hbox3 = new HBox();//存放两个按钮
		
		
		
		Label[] label = new Label[26];//存放26个字母
		for(int i = 0; i < 26; i++){
			label[i] = new Label(String.valueOf((char) ('A' + i)));
			hbox2.getChildren().add(label[i]);
		}
		hbox2.setSpacing(9);//设置每个字母间隔为9
		
		Text text = new Text("Please enter the file source:");//提示用户输入
		text.setFont(new Font("SansSerif", 20));//设置提示词语的字体及大小
		
		TextField textField = new TextField();//用于用户输入文本
		
		Button btEnter = new Button("Enter");//按此键进行26字母的统计
		Button btClear = new Button("Clear");//清除文本及统计图
		Rectangle[] rec = new Rectangle[26];
		
		hbox1.setAlignment(Pos.BOTTOM_LEFT);
		btEnter.setOnAction(e -> {
			File file = new File(textField.getText());
			Scanner fInput = null;
			try {
				fInput = new Scanner(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				textField.setText("File was not been found");
			}
			letterTimes = countLetters(fInput);
			int max = maxInLetterTimes();
			for(int i = 0; i < rec.length; i++){
				
				rec[i] = new Rectangle(100 + letterTimes[i]*100/max, 100 + letterTimes[i]*100/max, 10, letterTimes[i]*100/max);
				
				hbox1.getChildren().add(rec[i]);
				rec[i].setFill(Color.color(Math.random(), Math.random(), Math.random()));
				
			}
			letterTimesClear();//将字母出现个数清除
		});
		btClear.setOnAction(e -> {
			textField.setText("");
			letterTimesClear();				
			for(int i = 0; i < rec.length; i++){
				hbox1.getChildren().remove(rec[i]);//移除已经画出的图
			}
		});
		
		hbox3.getChildren().addAll(btEnter, btClear);	
		hbox3.setPadding(new Insets(10,20, 20, 10));
		hbox3.setSpacing(20);
		
		
		
		
		hbox1.setSpacing(10);
		
		//Line line = new Line(100, 500, 800, 500);
		
		vbox.getChildren().addAll(hbox1, hbox2, text, textField, hbox3);
		vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vbox, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Histogram");
		primaryStage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}

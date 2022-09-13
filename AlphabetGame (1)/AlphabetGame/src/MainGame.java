/***********************************************************************************************
Name:Tanya Gupta
Course: 19FA CS-170-02
Lab #: project
Submission Date: 10 pm, Dec. 4, 2019
Description: final project 
**********************************************************************************************/
/*************imports**********/
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedMap;

import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.stage.Stage;  
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.concurrent.Task;

//main game running
public class MainGame extends Application 
{  
	
	//Initializing global variables  
	  protected int answer_index; //variable that saves the right answer
	  protected Group root, M_scrn_root, S_scrn_root; //displays the features on the screen
	  protected  Media WrongAnswermp3path; //background music
	  protected  MediaPlayer WrongAnswerSound; 
	  protected  Button score_text;
	  protected  TextField playerName;
	  protected  Image instruction, image, image1, image2, image3, QueryImage; //image variables
	  protected  ImageView instructionView, imageView, imageView1, imageView2, imageView3, QueryImageView; //allowing images to display
	  protected  Button AButton, BButton, CButton, DButton, ExitButton, NextButton, StartButton, MainButton; //buttons
	  protected  Scene scene, scene5, MainScene, scoreScene;  //the full "panel"
	  protected  char x, y, z, queryLetter; //extra variables needed for methods and saving letter being used
	  protected  int i, curr_score, counter; //index
	  protected String s="";
	  protected String rec_file = ".\\src\\alphabet-RorW\\records.csv";
	  protected ArrayList<String> scorecard = new ArrayList<String>();

	  public void read_records(String recfilepath) throws IOException {
		  BufferedReader csvReader = new BufferedReader(new FileReader(recfilepath));
		  String row;
		while ((row = csvReader.readLine()) != null) {
		      String	data = row;
		      // do something with the data
		      scorecard.add(data);
		  }
		  csvReader.close();
	  }

	  public void write_records(String recfilepath) throws IOException {
		  FileWriter writer = new FileWriter(recfilepath);
	        int size = scorecard.size();
	        for (int i=0;i<size;i++) {
	            String str = scorecard.get(i).toString();
	            writer.write(str);
	            if(i < size-1)
	                writer.write("\n");
	        }
	        writer.close(); 
      	  }

		// Function to remove duplicates from an ArrayList 
	    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
	    { 
	  
	        // Create a new ArrayList 
	        ArrayList<T> newList = new ArrayList<T>(); 
	  
	        // Traverse through the first list 
	        for (T element : list) { 
	  
	            // If this element is not present in newList 
	            // then add it 
	            if (!newList.contains(element)) { 
	  
	                newList.add(element); 
	            } 
	        } 
	  
	        // return the new list 
	        return newList; 
	    } 
	  
	  
	  public void add_records(String name) throws IOException {
		      scorecard.add(name);
		      scorecard= removeDuplicates(scorecard);    
		      Collections.sort(scorecard, Collections.reverseOrder());
	  }
	  
	  /***********first screen of game*********/
	  public  Scene  paint_game_screen(Stage stage)  throws FileNotFoundException {
			//Creating an image 
			
	        /****INITIALIZING IMAGES***********/
		    image=new Image(new FileInputStream(GetRandomAlphabetFilepath("words", GetRandomAlphabet())));  
		    image1=new Image(new FileInputStream(GetRandomAlphabetFilepath("words", GetRandomAlphabet())));
		    image2=new Image(new FileInputStream(GetRandomAlphabetFilepath("words", GetRandomAlphabet()))); 
		    image3=new Image(new FileInputStream(GetRandomAlphabetFilepath("words", GetRandomAlphabet()))); 
		    x=' ';
		    y=' ';
		    z=' ';
		    
		    
		    /******getting a random alphabet to ask the question********/
		    queryLetter=GetRandomAlphabet();
	        QueryImage = new Image(new FileInputStream(GetRandomAlphabetFilepath("images", queryLetter))); 
	    
	        /********randomizes which answer choice is the right answer******/
		    answer_index = (int)(4*Math.random());
			switch (answer_index) 
			{
				case 0:
					x=GetRandomAlphabet();
					y=GetRandomAlphabet();
					z=GetRandomAlphabet();
					while(x==queryLetter)
					{
						x=GetRandomAlphabet();
					}
					while(y==queryLetter || y==x)
					{
						y=GetRandomAlphabet();
					}
					while(z==queryLetter || z==x || z==y)
					{
						z=GetRandomAlphabet();
					}
				    image = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", queryLetter)));  
				    image1 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", x)));  
				    image2 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", y)));  
				    image3 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", z)));  
				    break;
				    
				case 1:
					x=GetRandomAlphabet();
					y=GetRandomAlphabet();
					z=GetRandomAlphabet();
					while(x==queryLetter)
					{
						x=GetRandomAlphabet();
					}
					while(y==queryLetter || y==x)
					{
						x=GetRandomAlphabet();
					}
					while(z==queryLetter || z==x || z==y)
					{
						x=GetRandomAlphabet();
					}
					image = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", x)));  
					image1 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", queryLetter)));  
					image2 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", y)));  
					image3 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", z)));  
					break;
			    
				case 2:
					x=GetRandomAlphabet();
					y=GetRandomAlphabet();
					z=GetRandomAlphabet();
					while(x==queryLetter)
					{
						x=GetRandomAlphabet();
					}
					while(y==queryLetter || y==x)
					{
						x=GetRandomAlphabet();
					}
					while(z==queryLetter || z==x || z==y)
					{
						x=GetRandomAlphabet();
					}
				    image = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", x)));  
				    image1 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", y)));  
				    image2 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", queryLetter)));  
				    image3 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", z)));  
				    break;
				    
				case 3:
					x=GetRandomAlphabet();
					y=GetRandomAlphabet();
					z=GetRandomAlphabet();
					while(x==queryLetter)
					{
						x=GetRandomAlphabet();
					}
					while(y==queryLetter || y==x)
					{
						x=GetRandomAlphabet();
					}
					while(z==queryLetter || z==x || z==y)
					{
						x=GetRandomAlphabet();
					}
				    image = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", x)));  
				    image1 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", y)));  
				    image2 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", z)));  
				    image3 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", queryLetter)));  
				    break;
				    
				default:
					break;
		      
			}
		
		//background music	
		instruction = new Image(new FileInputStream(".\\src\\alphabet-RorW\\inst.jpg"));  
			
		//Setting the image view 
	    imageView = new ImageView(image); 
	    imageView1 = new ImageView(image1); 
	    imageView2 = new ImageView(image2); 
	    imageView3 = new ImageView(image3); 
	    QueryImageView = new ImageView(QueryImage); 
	    instructionView = new ImageView(instruction);

	    //creating buttons
		  AButton = new Button("(A)");
		  BButton = new Button("(B)");
	      CButton = new Button("(C)");
	      DButton = new Button("(D)");
	      ExitButton = new Button("Exit");
	      NextButton = new Button("Next Alphabet");
	      StartButton = new Button("Start Game");
	      MainButton = new Button("Main Screen");
	      
	      //setting size of buttons
	      ExitButton.setPrefSize(100, 37); 
	      NextButton.setPrefSize(150, 37); 
	      StartButton.setPrefSize(200, 100);
	      MainButton.setPrefSize(150, 37);
	      
	    //setting positions of the buttons
	      AButton.setLayoutX(165);
	      BButton.setLayoutX(380);
	      CButton.setLayoutX(605);
	      DButton.setLayoutX(820);
	      ExitButton.setLayoutX(820);
	      NextButton.setLayoutX(500);
	      MainButton.setLayoutX(200);
	      StartButton.setLayoutX(401);
	      
	      AButton.setLayoutY(541);
	      BButton.setLayoutY(541);
	      CButton.setLayoutY(541);
	      DButton.setLayoutY(541);
	      ExitButton.setLayoutY(640);    
	      NextButton.setLayoutY(640);
	      MainButton.setLayoutY(640);
	      StartButton.setLayoutY(450);
	      
	      //Setting the position of the image 
	      imageView.setX(75); 
	      imageView.setY(325); 
	      imageView1.setX(300); 
	      imageView1.setY(325); 
	      imageView2.setX(525); 
	      imageView2.setY(325); 
	      imageView3.setX(750); 
	      imageView3.setY(325); 
	      QueryImageView.setX(400); 
	      QueryImageView.setY(50); 
	      instructionView.setX(700);
	      instructionView.setY(50);
	      
	      //setting the fit height and width of the image view 
	      imageView.setFitHeight(190); 
	      imageView.setFitWidth(190); 
	      imageView1.setFitHeight(190); 
	      imageView1.setFitWidth(190); 
	      imageView2.setFitHeight(190); 
	      imageView2.setFitWidth(190); 
	      imageView3.setFitHeight(190); 
	      imageView3.setFitWidth(190); 
	      QueryImageView.setFitHeight(226); 
	      QueryImageView.setFitWidth(226); 
	      instructionView.setFitWidth(280);
	      instructionView.setFitHeight(280);
	      
	      //Setting the preserve ratio of the image view 
	      imageView.setPreserveRatio(true);  
	      imageView1.setPreserveRatio(true);  
	      imageView2.setPreserveRatio(true);  
	      imageView3.setPreserveRatio(true);  
	      QueryImageView.setPreserveRatio(true);  
	      instructionView.setPreserveRatio(true);
	      
	      //Creating a Group object  
	      root = new Group(imageView, imageView1, imageView2, imageView3, QueryImageView, AButton, BButton, CButton, DButton, ExitButton, NextButton, instructionView);  
	      //Creating a scene object 
	      scene = new Scene(root, 1000, 700);
	      return (scene);
    }
	  

	  /**************the actual game screens themselves, next command*********/
	  public Scene paint_next_screen(Stage stage) throws FileNotFoundException {
	  
				//Creating an image 
				
		        /****INITIALIZING IMAGES***********/
			    image=new Image(new FileInputStream(GetRandomAlphabetFilepath("words", GetRandomAlphabet())));  
			    image1=new Image(new FileInputStream(GetRandomAlphabetFilepath("words", GetRandomAlphabet())));
			    image2=new Image(new FileInputStream(GetRandomAlphabetFilepath("words", GetRandomAlphabet()))); 
			    image3=new Image(new FileInputStream(GetRandomAlphabetFilepath("words", GetRandomAlphabet()))); 
			    x=' ';
			    y=' ';
			    z=' ';
			    
			    
			    /******getting a random alphabet to ask the question********/
			    queryLetter=GetRandomAlphabet();
		        QueryImage = new Image(new FileInputStream(GetRandomAlphabetFilepath("images", queryLetter))); 
		    
		        /********randomizes which answer choice is the right answer******/
		        //also makes sure that pictures do not repeat
			    answer_index = (int)(4*Math.random());
				switch (answer_index) 
				{
					case 0:
						x=GetRandomAlphabet();
						y=GetRandomAlphabet();
						z=GetRandomAlphabet();
						while(x==queryLetter)
						{
							x=GetRandomAlphabet();
						}
						while(y==queryLetter || y==x)
						{
							y=GetRandomAlphabet();
						}
						while(z==queryLetter || z==x || z==y)
						{
							z=GetRandomAlphabet();
						}
					    image = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", queryLetter)));  
					    image1 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", x)));  
					    image2 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", y)));  
					    image3 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", z)));  
					    break;
					    
					case 1:
						x=GetRandomAlphabet();
						y=GetRandomAlphabet();
						z=GetRandomAlphabet();
						while(x==queryLetter)
						{
							x=GetRandomAlphabet();
						}
						while(y==queryLetter || y==x)
						{
							x=GetRandomAlphabet();
						}
						while(z==queryLetter || z==x || z==y)
						{
							x=GetRandomAlphabet();
						}
						image = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", x)));  
						image1 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", queryLetter)));  
						image2 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", y)));  
						image3 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", z)));  
						break;
				    
					case 2:
						x=GetRandomAlphabet();
						y=GetRandomAlphabet();
						z=GetRandomAlphabet();
						while(x==queryLetter)
						{
							x=GetRandomAlphabet();
						}
						while(y==queryLetter || y==x)
						{
							x=GetRandomAlphabet();
						}
						while(z==queryLetter || z==x || z==y)
						{
							x=GetRandomAlphabet();
						}
					    image = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", x)));  
					    image1 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", y)));  
					    image2 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", queryLetter)));  
					    image3 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", z)));  
					    break;
					    
					case 3:
						x=GetRandomAlphabet();
						y=GetRandomAlphabet();
						z=GetRandomAlphabet();
						while(x==queryLetter)
						{
							x=GetRandomAlphabet();
						}
						while(y==queryLetter || y==x)
						{
							x=GetRandomAlphabet();
						}
						while(z==queryLetter || z==x || z==y)
						{
							x=GetRandomAlphabet();
						}
					    image = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", x)));  
					    image1 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", y)));  
					    image2 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", z)));  
					    image3 = new Image(new FileInputStream(GetRandomAlphabetFilepath("words", queryLetter)));  
					    break;
					    
					default:
						break;
			      
				}
			
				
				
			//Setting the image view 
		    imageView = new ImageView(image); 
		    imageView1 = new ImageView(image1); 
		    imageView2 = new ImageView(image2); 
		    imageView3 = new ImageView(image3); 
		    QueryImageView = new ImageView(QueryImage); 
		    
		      
		//Setting the position of the image 
		      imageView.setX(75); 
		      imageView.setY(325); 
		      imageView1.setX(300); 
		      imageView1.setY(325); 
		      imageView2.setX(525); 
		      imageView2.setY(325); 
		      imageView3.setX(750); 
		      imageView3.setY(325); 
		      QueryImageView.setX(400); 
		      QueryImageView.setY(50); 
		      
		      //setting the fit height and width of the image view 
		      imageView.setFitHeight(190); 
		      imageView.setFitWidth(190); 
		      imageView1.setFitHeight(190); 
		      imageView1.setFitWidth(190); 
		      imageView2.setFitHeight(190); 
		      imageView2.setFitWidth(190); 
		      imageView3.setFitHeight(190); 
		      imageView3.setFitWidth(190); 
		      QueryImageView.setFitHeight(226); 
		      QueryImageView.setFitWidth(226); 
		      
		      //Setting the preserve ratio of the image view 
		      imageView.setPreserveRatio(true);  
		      imageView1.setPreserveRatio(true);  
		      imageView2.setPreserveRatio(true);  
		      imageView3.setPreserveRatio(true);  
		      QueryImageView.setPreserveRatio(true);  
		      //Creating a Group object  
			  root.getChildren().clear();
			  root.getChildren().add(imageView);
			  root.getChildren().add(imageView1);
			  root.getChildren().add(imageView2);
			  root.getChildren().add(imageView3);
			  root.getChildren().add(QueryImageView);
			  root.getChildren().add(AButton);
			  root.getChildren().add(BButton);
			  root.getChildren().add(CButton);
			  root.getChildren().add(DButton);
			  root.getChildren().add(ExitButton);
			  root.getChildren().add(NextButton);
			  //root.getChildren().add(MainButton);
	
		      //root = new Group(imageView, imageView1, imageView2, imageView3, QueryImageView, 
			  //AButton, BButton, CButton, DButton, ExitButton, NextButton, MainButton);  
		      //root.getChildren().add(DButton);
		      //Creating a scene object 
		      scene = new Scene(root, 1000, 700);
		      return (scene);
	    }
	  
	  public static String padRight(String s, int n) {
		     return String.format("%-" + n + "s", s);  
		}

		public static String padLeft(String s, int n) {
		    return String.format("%" + n + "s", s);  
		}

	    
	  	/*************end screen
	  	 * @throws IOException ***********/
		public Scene paint_score_screen(Stage stage) throws IOException {
			Scene SScene;
			read_records(rec_file);
			s=playerName.getText();
			scorecard.add(Integer.toString(curr_score) + s);
			Label heading = new Label();
			heading.setText("Score  Board");
			heading.setFont(new Font("Arial", 28));
			heading.setScaleX(2);
			heading.setScaleY(2);
			heading.setPadding(new Insets(73, 100, 100, 325));

			Label fields = new Label();
			fields.setText("Leader  Board");
			fields.setFont(new Font("Arial", 28));
			fields.setScaleX(1.5);
			fields.setScaleY(1.5);
			fields.setPadding(new Insets(172, 100, 100, 100));

			Label header = new Label();
			header.setText("Player-Name    Score");
			header.setFont(new Font("Arial", 28));
			header.setScaleX(1.2);
			header.setScaleY(1.2);
			header.setPadding(new Insets(222, 100, 100, 100));

			String scr = s + " Your Score is :: " + curr_score;
			score_text = new Button(scr);
		    score_text.setPrefSize(200, 200);
		      
		    //setting positions of the buttons
		    score_text.setLayoutX(400);
		    score_text.setLayoutY(250);
			S_scrn_root = new Group(score_text, ExitButton, heading, fields, header);

			for (int z=0; (z<5)&&(z< scorecard.size()); z++) {
				Label rec = new Label();
				Object o = scorecard.get(z);
				String name = (o.toString()).substring(1);
				name = padRight(name, 10);
				int score = Character.getNumericValue((o.toString()).charAt(0));
				rec.setText(name + padLeft((o.toString()), 2));
				rec.setFont(new Font("Arial", 28));
				rec.setScaleX(1.1);
				rec.setScaleY(1.1);
				rec.setPadding(new Insets(222 + (z+1) * 50, 100, 82, 100));
				S_scrn_root.getChildren().add(rec);
			}
			SScene = new Scene(S_scrn_root, 1000, 700, Color.PINK);
		    return (SScene);
		}

	  /****start screen*******/
	public Scene paint_main_screen(Stage stage) {
		Scene MScene;
		playerName = new TextField();
	    Label lab1 = new Label();
	    lab1.setText("Enter Your Name Here");
	    lab1.setFont(new Font("Arial", 30));
	    lab1.setScaleX(2);
	    lab1.setScaleY(2);
	    lab1.setPadding(new Insets(100, 100, 100, 280));
	    playerName.setText(lab1.getText());
	    playerName.setPrefColumnCount(46);
	    playerName.setAlignment(Pos.CENTER);
	    playerName.autosize();
	    playerName.snapPositionX(200);
	    playerName.snapPositionY(200);
	    playerName.setLayoutX(200);
	    playerName.setLayoutY(400);

		lab1.setText("Alphabet Game !!");
	    M_scrn_root = new Group(lab1, playerName, StartButton, ExitButton);
		MScene = new Scene(M_scrn_root, 1000, 700, Color.BLUE);
	    return (MScene);
	}
	
	
	/******includes background music, scene title, and assigns the buttons to actions******/
	public void start(Stage stage) throws FileNotFoundException 
	{  
		File file = new File(".\\src\\alphabet-RorW\\happy.mp3"); //file location
		System.out.println("file: " + file);
	AudioClip myAudio = new AudioClip(file.toURI().toString());
	myAudio.setCycleCount(10);		//play 10 times
	myAudio.play();
		
		scene = paint_game_screen(stage);
		MainScene = paint_main_screen(stage);
	    
	      //Setting title to the Stage 
	      stage.setTitle("Alphabets Game ...... By Tanya Gupta");  
	      stage.setScene(MainScene);
	      stage.show();
	  
      
      
      /**************set each button to action******/
	      
	  //first answer choice
      AButton.setOnAction(new EventHandler<ActionEvent>()  
	  { 
		  @Override 
		  public void handle(ActionEvent e) 
          { 
              if (answer_index==0) //(if correct answer)
              {
            	  try 
            	  {
              		curr_score++;

            		Image rightImage = new Image(new FileInputStream(".\\src\\alphabet-RorW\\right.jpg")) ;
					ImageView rightimageView = new ImageView(rightImage);
					rightimageView.setX(0);
					rightimageView.setY(0);
					rightimageView.setFitHeight(280);
					rightimageView.setFitWidth(280);
				    root.getChildren().add(rightimageView);
				    stage.setScene(scene);
				 
            	  } 
            	  catch (FileNotFoundException e1) 
            	  {
					e1.printStackTrace();
				  }
              }
              else //(if wrong answer)
              {
            	  try 
            	  {
            		Image wrongImage = new Image(new FileInputStream(".\\src\\alphabet-RorW\\wrong.jpg")) ;
  					ImageView wrongimageView = new ImageView(wrongImage);
					wrongimageView.setX(0);
					wrongimageView.setY(0);
					wrongimageView.setFitHeight(280);
					wrongimageView.setFitWidth(280);
  				    root.getChildren().add(wrongimageView);
  				    stage.setScene(scene);
            	  } 
            	  
            	  catch (FileNotFoundException e1) 
            	  {
					e1.printStackTrace();
				  }
              }
          } 
      }
      );
      
      //second answer choice
      BButton.setOnAction(new EventHandler<ActionEvent>()  
	  { 
		  @Override 
		  public void handle(ActionEvent e) 
          { 
        	  
              if (answer_index==1) //if correct answer
              {
            	  try 
            	  {
            		curr_score++;
            		Image rightImage = new Image(new FileInputStream(".\\src\\alphabet-RorW\\right.jpg")) ;
  					ImageView rightimageView = new ImageView(rightImage);
					rightimageView.setX(0);
					rightimageView.setY(0);
					rightimageView.setFitHeight(280);
					rightimageView.setFitWidth(280);
  				    root.getChildren().add(rightimageView);
  					scene5 = new Scene(root, 1000, 700);
  				    stage.setScene(scene5);
				   
            	  } 
            	  catch (FileNotFoundException e1) 
            	  {
					e1.printStackTrace();
				  }
              }
              else //if wrong answer
              {
            	  try 
            	  {
            		    Image wrongImage = new Image(new FileInputStream(".\\src\\alphabet-RorW\\wrong.jpg")) ;
    					ImageView wrongimageView = new ImageView(wrongImage);
    					wrongimageView.setX(0);
    					wrongimageView.setY(0);
    					wrongimageView.setFitHeight(280);
    					wrongimageView.setFitWidth(280);
    				    root.getChildren().add(wrongimageView);
    					scene5 = new Scene(root, 1000, 700);
    				    stage.setScene(scene5);
 
				  } 
            	  catch (FileNotFoundException e1) 
            	  {
					e1.printStackTrace();
				  }
              }
          } 
      }
      );
      
      //third answer choice
      CButton.setOnAction(new EventHandler<ActionEvent>()  
	  { 
		  @Override 
		  public void handle(ActionEvent e) 
          { 
        	  
              if (answer_index==2) //if correct answer
              {
            	  try 
            	  {
              		curr_score++;
            		  
            		Image rightImage = new Image(new FileInputStream(".\\src\\alphabet-RorW\\right.jpg")) ;
  					ImageView rightimageView = new ImageView(rightImage);
					rightimageView.setX(0);
					rightimageView.setY(0);
					rightimageView.setFitHeight(280);
					rightimageView.setFitWidth(280);
  				    root.getChildren().add(rightimageView);
  					scene5 = new Scene(root, 1000, 700);
  				    stage.setScene(scene5);
            	  } 
            	  catch (FileNotFoundException e1) 
            	  {
					e1.printStackTrace();
				  }
              }
              else //if wrong answer
              {
            	  try 
            	  {

          		    Image wrongImage = new Image(new FileInputStream(".\\src\\alphabet-RorW\\wrong.jpg")) ;
  					ImageView wrongimageView = new ImageView(wrongImage);
					wrongimageView.setX(0);
					wrongimageView.setY(0);
					wrongimageView.setFitHeight(280);
					wrongimageView.setFitWidth(280);
  				    root.getChildren().add(wrongimageView);
  					scene5 = new Scene(root, 1000, 700);
  				    stage.setScene(scene5);

				  } 
            	  catch (FileNotFoundException e1) 
            	  {
					e1.printStackTrace();
				  }
              }
          } 
      }
      );
      
      //fourth answer choice
      DButton.setOnAction(new EventHandler<ActionEvent>()  
	  { 
		  @Override 
		  public void handle(ActionEvent e) 
          { 
              if (answer_index==3) //if correct answer
              {
            	  try 
            	  {
              		curr_score++;
            		  
            		Image rightImage = new Image(new FileInputStream(".\\src\\alphabet-RorW\\right.jpg")) ;
  					ImageView rightimageView = new ImageView(rightImage);
					rightimageView.setX(0);
					rightimageView.setY(0);
					rightimageView.setFitHeight(280);
					rightimageView.setFitWidth(280);
  				    root.getChildren().add(rightimageView);
  					scene5 = new Scene(root, 1000, 700);
  				    stage.setScene(scene5);
            	  } 
            	  catch (FileNotFoundException e1) 
            	  {
					e1.printStackTrace();
				  }
              }
              else //if wrong answer
              {
            	  try 
            	  {

          		    Image wrongImage = new Image(new FileInputStream(".\\src\\alphabet-RorW\\wrong.jpg")) ;
  					ImageView wrongimageView = new ImageView(wrongImage);
					wrongimageView.setX(0);
					wrongimageView.setY(0);
					wrongimageView.setFitHeight(280);
					wrongimageView.setFitWidth(280);
  				    root.getChildren().add(wrongimageView);
  					scene5 = new Scene(root, 1000, 700);
  				    stage.setScene(scene5);

				  } 
            	  catch (FileNotFoundException e1) 
            	  {
					e1.printStackTrace();
				  }
              }
          } 
      }
      );
      
      /***********when user presses next, next button shows up***/
      NextButton.setOnAction(new EventHandler<ActionEvent>()  
	  { 
		  @Override 
		  public void handle(ActionEvent e) 
          { 
			  counter++;
			  if (counter >= 5) {
				  try {
					scene = paint_score_screen(stage);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  stage.setScene(scene);
			  }
			  else {
				try {
					scene = paint_next_screen(stage);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				stage.setScene(scene);  
			  }
          } 
      }
      );      
      
      /***starts the game*/
      StartButton.setOnAction(new EventHandler<ActionEvent>()  
	  { 
    	  // Start Game
		  @Override 
		  public void handle(ActionEvent e) 
          { 
			counter = 0;
			curr_score = 0;
		    stage.setScene(scene);
          } 
      }
      ) ;      

     
      /********exits game*****/
      ExitButton.setOnAction(new EventHandler<ActionEvent>()  
	  { 
    	  @Override 
		  public void handle(ActionEvent exit) {
    		  try {
				write_records(rec_file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		  System.exit(0);
    		  stage.close();
    	  }
      });
     }  
	
	/******method to get a random alphabet's filepath to use in methods above********/ 
   public static String GetRandomAlphabetFilepath(String whatData, char letter) 
   {
	   String FilePath="";
	   FilePath = ".\\src\\alphabet-" + whatData + "\\" + letter + ".png";
	   return(FilePath);
   }
   
   /**********method to get a random alphabet********/
   public static char GetRandomAlphabet() 
   {
	   int randomNo = (int)(25*Math.random());
	   char fn = (char)(randomNo + (int)('A'));
	   return fn;
   }
   

   /******runs the game*******/
   public static void main(String args[]) 
   { 
	   launch(args); 
   }

}
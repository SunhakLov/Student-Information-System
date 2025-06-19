package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;


public class Main extends Application {
	
	private int deletePressCount = 1;
	
	@Override
	public void start(Stage stage) {
		//Build GUI Here
		
		// Title Label
		Label title = new Label("🏫 Student Information System 🏫") ;
		title.setStyle("-fx-font-family: 'Monospaced'; -fx-font-size: 24px; -fx-font-weight: bold; -fx-color: #003663;");
		HBox titleBox = new HBox(title);
		titleBox.setAlignment(Pos.CENTER); // this centers the label
		titleBox.setStyle("-fx-padding: 0 0 10 0;");
		
		//Input Field
		TextField studentID = new TextField() ;
		studentID.setPromptText("Input Student ID (XXXXXXXXX) : ");
		
		TextField fullName = new TextField() ;
		fullName.setPromptText("Input Full Name : ");
		
		TextField grade = new TextField() ;
		grade.setPromptText("Input Grade (0.0 - 100.0) : ");
		
	    
		
		// ComboBox
		ComboBox<String> subjects = new ComboBox<>() ;
		subjects.getItems().addAll("Math", "Computer Science", "Physics") ;
		subjects.setPromptText("Select Major");
		subjects.setStyle("-fx-background-color: #FFFFFF ; -fx-border-radius: 10 ; -fx-border-color: black ; ");
	
		ToggleGroup levelGroup = new ToggleGroup();

	    RadioButton freshmanBtn = new RadioButton("Freshman");
	    RadioButton sophomoreBtn = new RadioButton("Sophomore");
	    RadioButton juniorBtn = new RadioButton("Junior");
	    RadioButton seniorDutyBtn = new RadioButton("Senior");
	   
	    
	    freshmanBtn.setToggleGroup(levelGroup);
	    sophomoreBtn.setToggleGroup(levelGroup);
	    juniorBtn.setToggleGroup(levelGroup);
	    seniorDutyBtn.setToggleGroup(levelGroup);
	    VBox levelButtonsI = new VBox(10, freshmanBtn, sophomoreBtn);
	    VBox levelButtonsII = new VBox(10, juniorBtn, seniorDutyBtn);
	    HBox leverButtonsHorizontal = new HBox(20, levelButtonsI, levelButtonsII) ;
	    
	    leverButtonsHorizontal.setVisible(false);
	    leverButtonsHorizontal.setManaged(false);
	    
		// Listener on ComboBox
	    
	    subjects.setOnAction(e -> {
	        if (subjects.getValue() != null) {
	        	leverButtonsHorizontal.setVisible(true);
	        	leverButtonsHorizontal.setManaged(true);
	        }
	    });
        
		//Btn
		Button addBtn = new Button("Submit") ;
		Button resetBtn = new Button("Reset") ;
		Button viewAll = new Button("View All Student") ;
		
		Button search = new Button("Search Student 🔍") ;
		Button averageGrade = new Button("Average Grade") ;
		Button levelDistribution = new Button("Grade Level Distribution") ;
		Button deleteStudent = new Button("Delete") ;
		
		HBox optionsI = new HBox(10, addBtn,resetBtn, search) ;
		HBox optionsII = new HBox(10, viewAll, averageGrade) ;
		HBox optionsIII = new HBox(10,levelDistribution) ;
		
		String buttonOriginalStyle = "-fx-background-radius: 10;" + "-fx-font-family: 'Monospaced';" +"-fx-text-fill:#FFFFFF ;" + "-fx-background-color: #003663;" + "-fx-font-weight: bold;" + "-fx-padding:10px ;";
		String pressButtonStyle =  "-fx-background-radius: 10;" + "-fx-font-family: 'Monospaced';" + "-fx-text-fill: #FFFFFF;" + "-fx-background-color: #001F3F;" +"-fx-font-weight: bold;" +"-fx-padding: 10.5px;" +"-fx-effect: innershadow(gaussian, rgba(0,0,0,0.4), 6, 0.5, 0, 1);" ;
		
	
		buttonEffects(addBtn, buttonOriginalStyle, pressButtonStyle);
		buttonEffects(resetBtn, buttonOriginalStyle, pressButtonStyle);
		buttonEffects(viewAll, buttonOriginalStyle, pressButtonStyle);
		buttonEffects(search, buttonOriginalStyle, pressButtonStyle);
		buttonEffects(averageGrade, buttonOriginalStyle, pressButtonStyle);
		buttonEffects(levelDistribution, buttonOriginalStyle, pressButtonStyle);
		buttonEffects(deleteStudent, buttonOriginalStyle, pressButtonStyle);
		
		//Disable Btn
		addBtn.disableProperty().bind(
	            studentID.textProperty().isEmpty()
	            .or(fullName.textProperty().isEmpty())
	            .or(grade.textProperty().isEmpty())
	            .or(subjects.getSelectionModel().selectedItemProperty().isNull())
	        );
		
		
		search.disableProperty().bind(studentID.textProperty().isEmpty().and(fullName.textProperty().isEmpty()));
		
		
		// Text Area
		TextArea text = new TextArea() ;
		text.setEditable(false);
		text.setPromptText("Display");
		text.setPrefHeight(200);
		text.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: black; -fx-border-width: 1;");
		
		
		studentID.setTooltip(new Tooltip("Enter Student ID"));
		fullName.setTooltip(new Tooltip("Enter Student Name"));
		grade.setTooltip(new Tooltip("Enter Student Grade"));
		
		
		addBtn.setTooltip(new Tooltip("Click to Add Student"));
		resetBtn.setTooltip(new Tooltip("Click to Reset"));
		viewAll.setTooltip(new Tooltip("Click to View All Student"));
		search.setTooltip(new Tooltip("Click to Search Student By ID"));
		averageGrade.setTooltip(new Tooltip("Click to View Grade Average"));
		deleteStudent.setTooltip(new Tooltip("Click twice to Delete"));
		levelDistribution.setTooltip(new Tooltip("Click to View Academic Year Distribution"));
		
		// Style Label
		String labelStyle = "-fx-font-size: 16px;"+ "-fx-text-fill: #003663;" ;
		
		Label idLabel = new Label("Student ID (XXXXXXXXX):");
		idLabel.setStyle(labelStyle);

		Label nameLabel = new Label("Student Full Name:");
		nameLabel.setStyle(labelStyle);

		Label gradeLabel = new Label("Student Grade:");
		gradeLabel.setStyle(labelStyle);

		Label majorLabel = new Label("Select Major:");
		majorLabel.setStyle(labelStyle);
		
		Label academicYears = new Label("Select Academic Years:");
		academicYears.setStyle(labelStyle);

		Label displayLabel = new Label("Display:");
		displayLabel.setStyle(labelStyle);

		Label deleteLabel = new Label("Press Twice to delete :");
		deleteLabel.setStyle(labelStyle);
		
		Label optionsLabel = new Label("Options:");
		optionsLabel.setStyle(labelStyle);
		
		deleteStudent.setVisible(false);
		deleteStudent.setManaged(false);
		deleteLabel.setVisible(false);
		deleteLabel.setManaged(false);
		
		// add events
		resetBtn.disableProperty().bind(
	            studentID.textProperty().isEmpty()
	            .and(fullName.textProperty().isEmpty())
	            .and(grade.textProperty().isEmpty())
	            .and(text.textProperty().isEmpty())
	            .and(subjects.getSelectionModel().selectedItemProperty().isNull())
	        );
		// delete student

		deleteStudent.setOnAction(e -> {
		    if (deletePressCount %2 != 0) {
		        text.setText("⚠ Press Again To Confirm Deletion ⚠");
		        deletePressCount++ ;
		    } else {
		        text.setText("@ Student Has Successfully Deleted @");
		        deletePressCount = 1 ;
		        studentID.clear();
			    fullName.clear();
			    grade.clear();
			    subjects.getSelectionModel().clearSelection();
			    subjects.setPromptText("Select Major"); 
			    leverButtonsHorizontal.setVisible(false);
			    leverButtonsHorizontal.setManaged(false);
			    levelGroup.selectToggle(null); 
			    deleteStudent.setVisible(false);
				deleteStudent.setManaged(false);
				deleteLabel.setVisible(false);
				deleteLabel.setManaged(false);
		    }
		});
		// add button
		addBtn.setOnAction(e -> {
            try {
            	String ID = studentID.getText().trim() ;
            	String studentName = fullName.getText().trim() ;
        		String studentGrade = grade.getText().trim();
                String subject = subjects.getValue();
                Toggle selectedToggle = levelGroup.getSelectedToggle();
                String category = (selectedToggle != null) ? ((RadioButton) selectedToggle).getText() : "";
           
                //Pattern for Name ID and Grade
        		Pattern namePattern = Pattern.compile("^[a-zA-Z]+(\\s[a-zA-Z]+)*$") ;
        		Pattern IdPattern = Pattern.compile("\\d{9}") ;

                if (ID.isEmpty() || studentName.isEmpty() || studentGrade.isEmpty() || subject == null || category.isEmpty()) {
                    text.setText("Please complete all fields before adding a student.");
                    return;
                }
                

                double gradeValue = Double.parseDouble(studentGrade);
                
                if (gradeValue < 0 || gradeValue > 100) {
                	
                    text.setText("Grade must be between (0 - 100)");
                    
                    return;
                }
                
                Matcher matcherName = namePattern.matcher(studentName) ;
                
                if(!matcherName.matches()|| studentName.length() < 2 || studentName.length()> 50) {

                    text.setText("<!> Invalid Name !! Please enter the Student Name again <!>");
                    
                    return;
                }
                
                Matcher matcherID = IdPattern.matcher(ID) ;
                
                if(!matcherID.matches()) {

                    text.setText("<!> Invalid ID !! Please enter the Student ID again <!>");
                    
                    return;
                }
                
                try (
                		PrintWriter writer = new PrintWriter(new FileWriter("students.txt", true)); 
                		
                		Scanner fileScanner = new Scanner(new File("students.txt"));	
                		
                		) {
                	
                	boolean duplicated = false ;
                	
                	String line  ;
        			String[] studentDetails = null ;
        			
        			while(fileScanner.hasNextLine()) {
        				line = fileScanner.nextLine() ;
        				studentDetails = line.split(",") ;
        				
        				if(ID.equalsIgnoreCase(studentDetails[0])) {
        					duplicated = true ;
        				}
        			}

        			if(!duplicated) {
        				writer.println(ID + "," + studentName.toUpperCase() + "," + gradeValue + "," + subject.toUpperCase()+ "," + category.toUpperCase());

                        text.setText("Student's Information has succssfully added");
                        text.setText("Student added:\n" + ID + ", " + studentName + "," + studentGrade + ", " + subject+ "," + category);
        			}
        			else text.setText("<!> Student has already exist <!>"); 
                    
        			studentID.clear(); fullName.clear(); grade.clear();
                    subjects.getSelectionModel().clearSelection();
        		    subjects.setPromptText("Select Major"); 
        		    leverButtonsHorizontal.setVisible(false);
        		    leverButtonsHorizontal.setManaged(false);
        		    levelGroup.selectToggle(null); 
        			
                } catch(IOException ex) {
        			text.setText("\n<!> Error Writing to File <!>");
                }
        		
            } catch (NumberFormatException ex) {
                text.setText("Grade must be a valid number.");
            }
        });
		
		resetBtn.setOnAction(e -> {
		    studentID.clear();
		    fullName.clear();
		    grade.clear();
		    subjects.getSelectionModel().clearSelection();
		    subjects.setPromptText("Select Major"); 
		    text.clear();
		    leverButtonsHorizontal.setVisible(false);
		    leverButtonsHorizontal.setManaged(false);
		    levelGroup.selectToggle(null); 
		    deleteStudent.setVisible(false);
			deleteStudent.setManaged(false);
			deleteLabel.setVisible(false);
			deleteLabel.setManaged(false);
		});
		
		viewAll.setOnAction(e -> {
            File file = new File("students.txt");
            if (!file.exists()) {
                text.setText("<!> No student currently in the system. <!>");
                return;
            }

            StringBuilder sb = new StringBuilder();
            try (Scanner scanner = new Scanner(file)) {
            	
            	if(!scanner.hasNextLine()) {
            		text.setText("<!> The list is EMPTY <!>");
            		return ;
    			}

                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().split(",");
                    if (parts.length == 5) {
                  
                        sb.append("\n============").append(" Student ").append("================")
                          .append("\nStudent Name    :  ").append(parts[1])
                          .append("\nStudent ID            :  ").append(parts[0]) 
                          .append("\nStudent Grade    :  ").append(String.format("%.2f", Double.parseDouble(parts[2])))
                          .append("\nStudent Subject :  ").append(parts[3]) 
                          .append("\nAcademic Year   :  ").append(parts[4]) 
                          .append("\n==================================\n");
           
                    }
                }
                text.setText(sb.toString());
            } catch (Exception ex) {
                text.setText("Error reading file.");
            }
        });
		
		search.setOnAction(e -> {
            File file = new File("students.txt");
            if (!file.exists()) {
                text.setText("<!> No student currently in the system. <!>");
                return;
            }

            StringBuilder sb = new StringBuilder();
            boolean found = false;
            String ID = studentID.getText().trim() ;
            
            try (Scanner scanner = new Scanner(file)) {
            	
            	Pattern IdPattern = Pattern.compile("\\d{9}") ;
                Matcher matcherID = IdPattern.matcher(ID) ;
                
                if(!matcherID.matches()) {

                    text.setText("<!> Invalid ID !! Please enter the Student ID again <!>");
                    
                    return;
                }
                
            	if(!scanner.hasNextLine()) {
            		text.setText("<!> The list is EMPTY <!>");
            		return ;
    			}

                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().split(",");
                    if (parts.length == 5 && parts[0].equalsIgnoreCase(ID)) {
                    	found = true ;
                        sb.append("\n============").append(" Student ").append("================")
                          .append("\nStudent Name    :  ").append(parts[1])
                          .append("\nStudent ID            :  ").append(parts[0]) 
                          .append("\nStudent Grade    :  ").append(String.format("%.2f", Double.parseDouble(parts[2])))
                          .append("\nStudent Subject :  ").append(parts[3]) 
                          .append("\nAcademic Year   :  ").append(parts[4]) 
                          .append("\n==================================\n");
                        break ;
                    }
                }
                
                if(found == true) {
                	text.setText(sb.toString());
                	deleteStudent.setVisible(true);
            		deleteStudent.setManaged(true);
            		deleteLabel.setVisible(true);
            		deleteLabel.setManaged(true);
                }
                else {
                    	sb.append("\n============").append(" Student ").append("================")
                        .append("\nStudent Name    :  Not Founded")
                        .append("\nStudent ID            :  Not Founded")
                        .append("\nStudent Grade    :  Not Founded")
                        .append("\nStudent Subject :  Not Founded")
                        .append("\n==================================\n");
                    	text.setText(sb.toString());
               }
                
                text.setText(sb.toString());
            } catch (Exception ex) {
            	text.setText("<!> Error Reading to File <!>");
            }
        });
		
		
		averageGrade.setOnAction(e -> {
			double totalMathGrade = 0, totalComputerGrade = 0, totalPhysicGrade = 0;
			int countMath = 0, countComputer = 0, countPhysic = 0;
			
			// Calculate total price for cars, including those from the file
			try (Scanner fileScanner = new Scanner(new File("students.txt"))) {
				
				String line  ;
    			String[] studentDetails = null ;
				while (fileScanner.hasNextLine()) {
					line = fileScanner.nextLine();
					studentDetails = line.split(",");
					
					double gradeData = Double.parseDouble(studentDetails[2]);
					String stuSubject = studentDetails[3];

					if (stuSubject.equalsIgnoreCase("Math")) {
						totalMathGrade += gradeData;
						countMath++ ;
					} else if (stuSubject.equalsIgnoreCase("Computer Science")) {
						totalComputerGrade += gradeData;
						countComputer++ ;
					}
					else if (stuSubject.equalsIgnoreCase("Physics")) {
						totalPhysicGrade += gradeData;
						countPhysic++ ;
					}
				}
			} catch (FileNotFoundException ex) {
				text.setText("<!> Error Reading to File <!>");
			}
			StringBuilder sb = new StringBuilder();
			double total = totalMathGrade + totalComputerGrade + totalPhysicGrade ;
			int totalCount =  countMath + countComputer + countPhysic ;
			
            // Type Averages
			sb.append("==================================")
              .append("\n\t\t   View Average Grade ")
              .append("\n==================================\n") ;
            if (countMath > 0)
                sb.append("Average Math Grade: ").append(String.format("%.2f", totalMathGrade / countMath)).append("\n");
            if (countComputer > 0)
                sb.append("Average  Computer Science Grade: ").append(String.format("%.2f", totalComputerGrade / countComputer)).append("\n");
            if (countPhysic > 0)
                sb.append("Average  Physics Grade: ").append(String.format("%.2f", totalPhysicGrade / countPhysic)).append("\n");
            if (countPhysic + countMath + countComputer < 0)
                sb.append("<!> No student currently in the system. <!>");
            if (countPhysic + countMath + countComputer >= 0) {
            	sb.append("==================================\n") ;
            	sb.append("Average Grade of All Students: ").append(String.format("%.2f", (total) / totalCount)).append("\n");
            }
            sb.append("\n");
            text.setText(sb.toString());
        });
		
		levelDistribution .setOnAction(e -> {
			int Freshman = 0;
			int Sophomore = 0;
			int Junior = 0;
			int Senior = 0;
			
			int totalStudent = 0 ;
			
			try(Scanner fileScanner = new Scanner(new File("students.txt"))){

				while(fileScanner.hasNextLine()) {

					String line = fileScanner.nextLine() ;
					String[] studentDetails = line.split(",") ;

					if (studentDetails[4].equalsIgnoreCase("FRESHMAN")) {
						Freshman++;
						totalStudent++ ;
					} else if (studentDetails[4].equalsIgnoreCase("SOPHOMORE")) {
						Sophomore++;
						totalStudent++ ;
					}
					else if (studentDetails[4].equalsIgnoreCase("JUNIOR")) {
						Junior++;
						totalStudent++ ;
					}
					else if (studentDetails[4].equalsIgnoreCase("SENIOR")) {
						Senior++;
						totalStudent++ ;
					}

				}
			}
			catch(FileNotFoundException ex) {
				text.setText("<!> Error Reading to File <!>");
			}
			StringBuilder sb = new StringBuilder();
			
			sb.append("==================================")
            .append("\n\t   View Grade Level Distribution ")
            .append("\n==================================")
            .append("\nFreshman  : ").append(Freshman)
            .append("\nSophomore : ").append(Sophomore)
            .append("\nJunior : ").append(Junior)
            .append("\nSenior : ").append(Senior)
            .append("\n==================================\n")
			.append("Total Student: ").append(totalStudent);
			
			text.setText(sb.toString());
        	text.setText(sb.toString());
			
        });
		

		// Layout 
		
		VBox form = new VBox(10) ;
		form.setStyle("-fx-padding: 20 ; -fx-background-color: #FFFFFF; -fx-font-size: 15px;");
		form.getChildren().addAll(
			    titleBox,
			    idLabel,
			    studentID, 
			    nameLabel,
			    fullName,
			    gradeLabel,
			    grade,
			    majorLabel,
			    subjects,
			    academicYears,
			    leverButtonsHorizontal,
			    optionsI,
			    displayLabel,
			    text,
			    deleteLabel,
			    deleteStudent,
			    optionsLabel,
			    optionsII,
			    optionsIII
			);

		
		Scene scene = new Scene(form,560,880) ;
		
		stage.setScene(scene) ;
		stage.setTitle("Student Information Systems");
		
		stage.show();
		
	}
	
	// apply effects to buttons
	public void buttonEffects(Button button, String originalStyle, String pressedStyle) {
	    button.setStyle(originalStyle);
	    button.setOnMouseEntered(e -> button.setStyle(pressedStyle));
	    button.setOnMouseExited(e -> button.setStyle(originalStyle));
	    button.setOnMousePressed(e -> button.setStyle(pressedStyle));
	    button.setOnMouseReleased(e -> button.setStyle(originalStyle));
	};
	
	public static void main(String[] args) {
		
		launch(args);
	}
}

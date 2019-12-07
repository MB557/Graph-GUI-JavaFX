package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


class vertex
{
	private double xCoordinate;		//For Vertex
	private double yCoordinate;
	private String vertexName;
	
	void setXCoordinate(double xCoordinate) 
	{
		this.xCoordinate = xCoordinate;
	}
	
	double getXCoordinate() 
	{
		return this.xCoordinate;
	}
		
	void setYCoordinate(double yCoordinate) 
	{
		this.yCoordinate = yCoordinate;
	}
	
	double getYCoordinate() 
	{
		return this.yCoordinate;
	}
	
	void setName(String vertexName) 
	{
		this.vertexName = vertexName;
	}
		
	String getName() 
	{
		return this.vertexName;
	}
	
	public String toString() 
	{
		return this.vertexName+" "+this.xCoordinate+" "+this.yCoordinate;
	}
}

class EDGE1
{
	private double weight;			//For Edges
	private String fromVtex;
	private String toVtex;
	
	void setWeight(double weight) 
	{
		this.weight = weight;
	}
	
	double getWeight() 
	{
		return this.weight;
	}
	
	void setfromVtex(String fromVtex) 
	{
		this.fromVtex = fromVtex;
	}
	
	String getfromVtex() 
	{
		return this.fromVtex;
	}
	
	void settoVtex(String toVtex) 
	{
		this.toVtex = toVtex;
	}
	
	String gettoVtex() 
	{
		return this.toVtex;
	}
	
	public String toEdgeString() 
	{
		return this.fromVtex+" "+this.toVtex+" "+this.weight;
	}
}

class sortVertex implements Comparator<vertex>
{
	public int compare(vertex a, vertex b) 
	{
		return a.getName().compareToIgnoreCase(b.getName());
	}
}

class SortingEdges implements Comparator<EDGE1>
{
	public int compare(EDGE1 a, EDGE1 b) 
	{
		return a.gettoVtex().compareToIgnoreCase(b.gettoVtex());
	}
}

public class GraphGUI extends Application
{
    @Override
    public void start(Stage myStage) 
    {

        myStage.setTitle("Q1 Graph");
        
        ArrayList<vertex> Alist = new ArrayList<vertex>();
    	TreeMap<String,ArrayList<EDGE1>> G = new TreeMap<String,ArrayList<EDGE1>>();
    	
    	BackgroundFill background_fill = new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);   
        
        GridPane rootNode = new GridPane();
        rootNode.setBackground(background);
        rootNode.setPadding(new Insets(35));
        rootNode.setHgap(55);
        rootNode.setVgap(10);
        rootNode.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(rootNode);
        
        Label Vertex = new Label("Vertex");													//LABELS
        Label XCoordinate = new Label("X Coordinate");
        Label YCoordinate = new Label("Y Coordinate");
        Label FromVertex = new Label("From Vertex");										
        Label ToVertex = new Label("To Vertex");
        Label Weight = new Label("Weight");
        
        TextField inpVertex = new TextField();												// TEXTFIELDS
        inpVertex.setPromptText("Input Vertex");
        TextField xCoor = new TextField();
        xCoor.setPromptText("Input X Coordinate");
        TextField yCoor = new TextField();
        yCoor.setPromptText("Input Y Coordinate");
        TextField Display1 = new TextField();
        Display1.setPromptText("--for output--");
        TextField Display2 = new TextField();
        Display2.setPromptText("--for output--");
        TextField Display3 = new TextField();	
        Display3.setPromptText("--for output--");
        TextField Display4 = new TextField();
        Display4.setPromptText("--for output--");
        
        TextField fromVertex = new TextField();	
        fromVertex.setPromptText("Input From Vertex");										// TEXTFIELDS
        TextField toVertex = new TextField();
        toVertex.setPromptText("Input To Vertex");
        TextField weight = new TextField();
        weight.setPromptText("Input Weight");
        TextField Display5 = new TextField();
        Display5.setPromptText("--for output--");
        TextField Display6 = new TextField();
        Display6.setPromptText("--for output--");
        TextField Display7 = new TextField();
        Display7.setPromptText("--for output--");
        TextField Display8 = new TextField();
        Display8.setPromptText("--for output--");
        TextField Display9 = new TextField();
        Display9.setPromptText("--for output--");
        TextField Display10 = new TextField();
        Display10.setPromptText("--for output--");
        TextField Display11 = new TextField();
        Display11.setPromptText("--for output--");
        
        Button addVertexB = new Button("Add Vertex");										// BUTTONS
        Button searchVertexB = new Button("Search Vertex");
        Button delVertexB = new Button("Delete Vertex");									
        Button modifyVertexB = new Button("Modify Vertex");
        Button addEdgeB = new Button("Add Edge");
		Button searchEdgeB = new Button("Search Edge");
		Button delEdgeB = new Button("Delete Edge");
		Button modifyEdgeB = new Button("Modify Edge");
		Button impDataB = new Button("Import data");
		Button expDataB = new Button("Export Data");
		Button DispPathB = new Button("Display Path");
		
		Alert A_addVertexB = new Alert(AlertType.ERROR);
		Alert A_searchVertexB = new Alert(AlertType.ERROR);
		Alert A_delVertexB = new Alert(AlertType.ERROR);
		Alert A_modifyVertexB = new Alert(AlertType.ERROR);
		Alert A_addEdgeB = new Alert(AlertType.ERROR);
		Alert A_searchEdgeB = new Alert(AlertType.ERROR);
		Alert A_delEdgeB = new Alert(AlertType.ERROR);
		Alert A_modifyEdgeB = new Alert(AlertType.ERROR);
		Alert A_impDataB = new Alert(AlertType.ERROR);
		Alert A_expDataB = new Alert(AlertType.ERROR);
		Alert A_DispPathB = new Alert(AlertType.ERROR);
		
		addVertexB.setStyle("-fx-border-color: DARKSLATEGRAY; -fx-border-width: 5px;");
		searchVertexB.setStyle("-fx-border-color: DARKSLATEGRAY; -fx-border-width: 5px;");
		delVertexB.setStyle("-fx-border-color: DARKSLATEGRAY; -fx-border-width: 5px;");
		modifyVertexB.setStyle("-fx-border-color: DARKSLATEGRAY; -fx-border-width: 5px;");
		addEdgeB.setStyle("-fx-border-color: DARKSLATEGRAY; -fx-border-width: 5px;");
		searchEdgeB.setStyle("-fx-border-color: DARKSLATEGRAY; -fx-border-width: 5px;");
		delEdgeB.setStyle("-fx-border-color: DARKSLATEGRAY; -fx-border-width: 5px;");
		modifyEdgeB.setStyle("-fx-border-color: DARKSLATEGRAY; -fx-border-width: 5px;");
		impDataB.setStyle("-fx-border-color: DARKSLATEGRAY; -fx-border-width: 5px;");
		expDataB.setStyle("-fx-border-color: DARKSLATEGRAY; -fx-border-width: 5px;");
		DispPathB.setStyle("-fx-border-color: DARKSLATEGRAY; -fx-border-width: 5px;");

		addVertexB.setMaxWidth(Double.MAX_VALUE);
        searchVertexB.setMaxWidth(Double.MAX_VALUE);
        delVertexB.setMaxWidth(Double.MAX_VALUE);
        modifyVertexB.setMaxWidth(Double.MAX_VALUE);
        addEdgeB.setMaxWidth(Double.MAX_VALUE);
        searchEdgeB.setMaxWidth(Double.MAX_VALUE);
        delEdgeB.setMaxWidth(Double.MAX_VALUE);
        modifyEdgeB.setMaxWidth(Double.MAX_VALUE);
        impDataB.setMaxWidth(Double.MAX_VALUE);
        expDataB.setMaxWidth(Double.MAX_VALUE);
        DispPathB.setMaxWidth(Double.MAX_VALUE);
        
        rootNode.addRow(0,Vertex,inpVertex, FromVertex, fromVertex);												// ADDING ROWS 
        rootNode.addRow(1,XCoordinate,xCoor, ToVertex, toVertex);
        rootNode.addRow(2,YCoordinate,yCoor, Weight, weight);
        rootNode.addRow(4, addVertexB, Display1, addEdgeB, Display5);
        rootNode.addRow(5, searchVertexB, Display2, searchEdgeB, Display6);
        rootNode.addRow(6, delVertexB, Display3, delEdgeB, Display7);
        rootNode.addRow(7, modifyVertexB, Display4, modifyEdgeB, Display8);
        rootNode.add(impDataB, 2, 12);
        rootNode.add(Display9, 3, 12);
        rootNode.add(expDataB, 2, 13);
        rootNode.add(Display10, 3, 13);
        rootNode.add(DispPathB, 2, 14);
        rootNode.add(Display11, 3, 14);        
        
        GridPane.setHalignment(searchVertexB, HPos.CENTER);
        
        addVertexB.setOnAction(new EventHandler<ActionEvent>() 								//ADD VERTEX FUNCTIONALITY
        {
        	public void handle(ActionEvent event) 
        	{
        		String vertexName = inpVertex.getText();
        		try
        		{
        			vertex obj = new vertex();
        	        obj.setYCoordinate(Double.valueOf(yCoor.getText()));
        	        obj.setXCoordinate(Double.valueOf(xCoor.getText()));	        	
        	        obj.setName(vertexName);
        	
        	        Alist.add(obj);
        	        Display1.setText("Vertex " + inpVertex.getText() + "(" + xCoor.getText() + ", " + yCoor.getText() + ") added.");     		
        		}
        		catch(Exception e)
        		{
        			A_addVertexB.setTitle("Error!");
        			A_addVertexB.setHeaderText("INVALID INPUT TYPE");
        			A_addVertexB.setContentText("Ooops, make sure input is of type: nameVertex (String), X and Y Coor. (Integer).");
        			A_addVertexB.showAndWait();
        		}
        		Display2.setText("");
        		Display3.setText("");
        		Display4.setText("");
	        	inpVertex.setText("");
	        	xCoor.setText("");
	        	yCoor.setText("");
        	}
        });
        
        searchVertexB.setOnAction(new EventHandler<ActionEvent>() 							//SEARCH VERTEX FUNCTIONALITY
        {
        	public void handle(ActionEvent event) 
        	{

        		String vertexName = inpVertex.getText();
        		int var = 0;
        		String vName = "";
        		try
        		{
        			for(vertex entry : Alist)
                	{
                		if(entry.getName().equalsIgnoreCase(vertexName))
                		{
                			vName = entry.getName().toString();
                			var = 1;
                			break;
                		}        			
                	}
                		
                	if(var == 1)
            			Display2.setText("Vertex found: " +	vName);
                	else
        				throw new Exception();
        		}
        		catch(Exception e)
        		{
        			A_searchVertexB.setTitle("Error!");
        			A_searchVertexB.setHeaderText("--VERTEX "+"'"+vertexName+"'"+" NOT FOUND--");
        			A_searchVertexB.showAndWait();
        		}
        		
        		Display1.setText("");
        		Display3.setText("");
        		Display4.setText("");
	        	inpVertex.setText("");
	        	xCoor.setText("");
	        	yCoor.setText("");
        	}
        	
        });
        
        delVertexB.setOnAction(new EventHandler<ActionEvent>() 								//DELETE VERTEX FUNCTIONALITY
        {
        	
        	public void handle(ActionEvent event) 
        	{
        		
        		String vertexName = inpVertex.getText();   
        		try
        		{
        			for(vertex entry : Alist)
            		{
            			if(entry.getName().equalsIgnoreCase(vertexName))
            			{
            				ArrayList<EDGE1> obj = G.get(entry.getName());
        					for(EDGE1 itr0 : obj)
        					{
        						ArrayList<EDGE1> obj3 = G.get(itr0.gettoVtex());
        						
                				for(EDGE1 itr : obj3)
                				{
                					if (itr.gettoVtex().equalsIgnoreCase(itr0.getfromVtex()))
                					{
                						System.out.println(itr.toEdgeString());
                						obj3.remove(itr);
                						break;
               						}
               					}
        					}
            				G.remove(entry.getName());
                    		Alist.remove(entry);
                    		Display3.setText("Vertex " + entry.toString()  + " deleted.");
                    		break;
            			}		
            		}
        		}
        		
        		catch(Exception e)
        		{
        			A_delVertexB.setTitle("Error!");
        			A_delVertexB.setHeaderText("--VERTEX "+"'"+vertexName+"'"+" NOT FOUND--");
        			A_delVertexB.showAndWait();
        		}
        		Display1.setText("");
        		Display2.setText("");
        		Display4.setText("");
        		inpVertex.setText("");
        		xCoor.setText("");
	        	yCoor.setText("");
        	}
        	
        });
        
		modifyVertexB.setOnAction(new EventHandler<ActionEvent>() 							//MODIFY VERTEX FUNCTIONALITY
		{		        	
			public void handle(ActionEvent event) 
		    {
		    	String vertexName = inpVertex.getText();
		    	int result = -1;
		    	try
		    	{
		    		for(vertex entry : Alist)
	        		{
	        			if(entry.getName().equalsIgnoreCase(vertexName))
	        			{
	        				double xc = Double.valueOf(xCoor.getText());  
	        				entry.setXCoordinate(xc);
	        				double yc = Double.valueOf(yCoor.getText());		
	        				entry.setYCoordinate(yc);
	        				result = 1;
	        			}   				

			        }
					if(result == 1)
						Display4.setText("Vertex "+"'"+vertexName+"'"+" updated.");
					else
						throw new Exception();

		    	}
		    	catch(Exception e)
        		{
        			A_modifyVertexB.setTitle("Error!");
        			A_modifyVertexB.setHeaderText("--VERTEX "+"'"+vertexName+"'"+" DOES NOT EXIST--");
        			A_modifyVertexB.showAndWait();
        		}
				Display1.setText("");
        		Display3.setText("");
        		Display2.setText("");
		        inpVertex.setText("");
		        xCoor.setText("");
		        yCoor.setText("");
		    }
		        	
		});

		addEdgeB.setOnAction(new EventHandler<ActionEvent>() 								//ADD EDGE FUNCTIONALITY
		{		        	
			public void handle(ActionEvent event) 
			{
				int vertex1 = 0, vertex2 = 0;	
				double Weight = Double.valueOf(weight.getText());
				String fromVtex = fromVertex.getText();
				String toVtex = toVertex.getText();
				
				try
				{
					for(vertex elem : Alist)
					{
						if(elem.getName().equalsIgnoreCase(fromVtex))
							vertex1 = 1;
						if(elem.getName().equalsIgnoreCase(toVtex))
							vertex2 = 1;				
					}
					
					if(vertex1 == 1 && vertex2 == 1)
					{
						EDGE1 E = new EDGE1();
						E.setWeight(Weight);
						E.setfromVtex(fromVtex);
						E.settoVtex(toVtex);
						
						if(G.containsKey(fromVtex))
						{
							ArrayList<EDGE1> EdgeList = G.get(fromVtex);
							EdgeList.add(E);
							Collections.sort(EdgeList, new SortingEdges());
							G.remove(fromVtex);
							G.put(fromVtex, EdgeList);
						}
						else
						{
							ArrayList<EDGE1> EdgeList = new ArrayList<EDGE1>();
							EdgeList.add(E);
							Collections.sort(EdgeList, new SortingEdges());
							G.put(fromVtex, EdgeList);
						}    
						Display5.setText("Edge " + E.toEdgeString() + " Added.");
					}
					else
						throw new Exception();
				}
				catch(Exception e)
        		{
        			A_addEdgeB.setTitle("Error!");
        			A_addEdgeB.setHeaderText("--VERTEX DOES NOT EXIST--");
        			A_addEdgeB.showAndWait();
        		}
				
				Display6.setText("");
				Display7.setText("");
				Display8.setText("");
				fromVertex.setText("");
				toVertex.setText("");
				weight.setText("");		
			}
		});
		
		searchEdgeB.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent event) 
			{
				ArrayList<EDGE1> e1;
				String toVtex = toVertex.getText();
				String fromVtex = fromVertex.getText();
				try
				{
					if(G.containsKey(fromVtex))
					{
						e1 = G.get(fromVtex);
						for(EDGE1 run : e1)
							if(run.gettoVtex().equalsIgnoreCase(toVtex))
								Display6.setText("Edge "+run.getfromVtex()+" "+run.gettoVtex()+" "+run.getWeight()+" found.");	
					}
					else
						throw new Exception();
				}
				
				catch(Exception e)
        		{
        			A_searchEdgeB.setTitle("Error!");
        			A_searchEdgeB.setHeaderText("--EDGE NOT FOUND--");
        			A_searchEdgeB.showAndWait();
        		}
				fromVertex.setText("");
				toVertex.setText("");
				Display7.setText("");
				Display8.setText("");
			}
			
		});

		delEdgeB.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event) 
			{
				ArrayList<EDGE1> T;
				String fromVtex = fromVertex.getText();
				String toVtex = toVertex.getText();
				try
				{
					if(G.containsKey(fromVtex))
					{
						T = G.get(fromVtex);
						Iterator<EDGE1> itr = T.iterator();
						while(itr.hasNext()) {
							EDGE1 var = itr.next();
							if(var.gettoVtex().equalsIgnoreCase(toVtex))
							{
								Display7.setText(var.toEdgeString() + " deleted.");
								itr.remove();
								if(T.isEmpty())
									G.remove(fromVtex);
							}}
					}
					else
						throw new Exception();
					
				}
				catch(Exception e)
        		{
        			A_delEdgeB.setTitle("Error!");
        			A_delEdgeB.setHeaderText("--EDGE NOT FOUND--");
        			A_delEdgeB.showAndWait();
        		}
				fromVertex.setText("");
				toVertex.setText(""); 
				Display6.setText("");
				Display8.setText("");
			}
			
		});
		
		modifyEdgeB.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{
				String fromVtex = fromVertex.getText();
				String toVtex = toVertex.getText();
				double Weight = Double.parseDouble(weight.getText());
				try
				{
					if(G.containsKey(fromVtex))
					{
						ArrayList<EDGE1> T = G.get(fromVtex);
						for(EDGE1 run : T)
							if(run.gettoVtex().equalsIgnoreCase(toVtex))
							{
								T.get(T.indexOf(run)).setWeight(Weight);
								Display8.setText("Edge Modified " + T.get(T.indexOf(run)).toEdgeString());
							}
					}
					else
						throw new Exception();
				}
				
				catch(Exception e)
        		{
        			A_modifyEdgeB.setTitle("Error!");
        			A_modifyEdgeB.setHeaderText("--EDGE NOT MODIFIED (Edge not found)--");
        			A_modifyEdgeB.showAndWait();
        		}
				fromVertex.setText("");
				toVertex.setText(""); 
				weight.setText("");
				Display6.setText("");
				Display7.setText("");
			}
			
		});
		

		impDataB.setOnAction(new EventHandler<ActionEvent>()
		{
			@SuppressWarnings("resource")
			public void handle(ActionEvent event)
			{
				FileChooser filename = new FileChooser();
				File fileselect = filename.showOpenDialog(null);

				if(fileselect != null)
				{
					String file;
					file = fileselect.getAbsolutePath();
					try
					{
						ArrayList<EDGE1> t1;
						FileReader ReadFile;
						ReadFile = new FileReader(file);
						BufferedReader BuffReader;
						BuffReader = new BufferedReader(ReadFile);
						
						int no_Lines = Integer.parseInt(BuffReader.readLine());
						
						for(int i = 0 ; i < no_Lines ; i++)
						{
							vertex e1;
							String FLine[];
							e1 = new vertex();
							FLine = BuffReader.readLine().split(" ");							
							
							e1.setYCoordinate(Double.parseDouble(FLine[2]));
							e1.setXCoordinate(Double.parseDouble(FLine[1]));
							e1.setName(FLine[0]);														
							Alist.add(e1);
						}
						
						int int_var;
						int_var = Integer.parseInt(BuffReader.readLine());
						
						for(int i = 0 ; i < int_var ; i++)
						{
							int var1, var2;
							var1 = 0; 
							var2 = 0;
							
							EDGE1 e1 = new EDGE1();
							String FLine[];
							FLine = BuffReader.readLine().split(" ");
							
							e1.setfromVtex(FLine[0]);
							e1.settoVtex(FLine[1]);
							e1.setWeight(Double.parseDouble(FLine[2]));
							
							for(vertex entry : Alist)
							{
								if(entry.getName().equalsIgnoreCase(FLine[0]))
									var1 = 1;        
							}
							for(vertex entry : Alist)
							{
								if(entry.getName().equalsIgnoreCase(FLine[1]))
									var2 = 1;
							}
							
							if(var2 == 1 || var1 == 1)
							{
								if(G.containsKey(FLine[0]) == false)
								{
									t1 = new ArrayList<EDGE1>();
									t1.add(e1);
									Collections.sort(t1, new SortingEdges());
									G.put(FLine[0], t1);
								}
								else if(G.containsKey(FLine[0]) == true)
								{
									t1 = G.get(FLine[0]);
									t1.add(e1);
									Collections.sort(t1, new SortingEdges());
									G.remove(FLine[0]);
									G.put(FLine[0],t1);
								}
							}
						}
						
					} 
					catch(Exception e)
					{
						A_impDataB.setTitle("Error!");
	        			A_impDataB.setHeaderText("--FILE NOT IMPORTED--");
	        			A_impDataB.showAndWait();
					}
					
				}
			}
		});
		
		expDataB.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{
				FileChooser filename = new FileChooser();
				File fileselect = filename.showOpenDialog(null);
				
				if(fileselect != null) 
				{
					PrintWriter WriteIntoF = null;
					String fPath = fileselect.getAbsolutePath();
					
					try 
					{
						int int_var = 0;
						//ArrayList<EDGE1> T;
						
						WriteIntoF = new PrintWriter(fPath);
						WriteIntoF.print(Alist.size());
						WriteIntoF.println();
						
						for(vertex run : Alist)
						{
							WriteIntoF.write(run.toString());
							WriteIntoF.println();
						}
						
						for(Map.Entry<String, ArrayList<EDGE1>> run : G.entrySet())
						{
							int_var = int_var + run.getValue().size();

						}
						WriteIntoF.print(int_var);
						WriteIntoF.println();
						
						for(Map.Entry<String, ArrayList<EDGE1>> run : G.entrySet())
						{
							
							for(EDGE1 itr2 : run.getValue())
							{
								WriteIntoF.write(itr2.toEdgeString());
								WriteIntoF.println();
							}
						}
						WriteIntoF.close();
					}
					catch(Exception e)
					{
						A_expDataB.setTitle("Error!");
	        			A_expDataB.setHeaderText("--DATA NOT EXPORTED--");
	        			A_expDataB.showAndWait();
						WriteIntoF.close();
					}
				}
			}
		});
		
		DispPathB.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{
				String FindVertex = toVertex.getText();
				String SourceVertex = fromVertex.getText();
				try
				{
					Display11.setEditable(true);
					Display11.setText("");
					int var = 0;
					for(vertex run : Alist)
						if(run.getName().equalsIgnoreCase(FindVertex))
						{
							var = 1;
							break;
						}
					
					if(var == 0 || (G.containsKey(SourceVertex) == false))
						Display11.setText("Path does not exist");
					
					else 
					{
						
						Queue<String> shortestPathAlgo;
		                ArrayList<String> listStr;
		                TreeMap<String, String> parentMap;
		                TreeMap<String, Double> costMap;
		                shortestPathAlgo = new LinkedList<>();
						listStr = new ArrayList<>();
		                parentMap = new TreeMap<>();
		                costMap = new TreeMap<>();
		                
		                for(vertex run : Alist)
		                {
		                	costMap.put(run.getName(), 1000.0);
		                	parentMap.put(run.getName(), null);
		                }
		                
		                shortestPathAlgo.add(SourceVertex);	                
		                costMap.put(SourceVertex,0.0);
		                
		                while(shortestPathAlgo.isEmpty()==false)
		                {
		                	String minimumValue;
		                	Double min;
		                	String T;
		                	min = 1000.0;
		                	minimumValue = "";
		                	T = shortestPathAlgo.remove();
		                	listStr.add(T);
		                	
		                	if(G.get(T) != null)
		                	{	
			                	ArrayList<EDGE1> listVar1;
			                	listVar1 = G.get(T);
			                	for(EDGE1 run : listVar1)
				                	{
				                		if(listStr.contains(run.gettoVtex()) == false)
				                		{
				                			Double variable;
				                			variable = run.getWeight()+costMap.get(T);
				                			if(variable == costMap.get(run.gettoVtex())) 
				                			{
				                				if(T.compareToIgnoreCase(parentMap.get(run.gettoVtex())) < 0)
				                						parentMap.put(run.gettoVtex(), run.getfromVtex());
				                			}
				                			else if(variable < costMap.get(run.gettoVtex()))
				                			{
				                				costMap.put(run.gettoVtex(), variable);
				                				parentMap.put(run.gettoVtex(), T);
				                			}
				                		}
				                	}
		                	}
		                	
		                	for(Map.Entry<String, Double> run : costMap.entrySet())
		                	{
		                		if(listStr.contains(run.getKey()) == false && run.getValue() < min) 
		                		{
		                			min = run.getValue();
		                			minimumValue = run.getKey();

		                		}
		                	}
		                	
		                	if(minimumValue != "")
		                	{
		                		shortestPathAlgo.add(minimumValue);
		                	}
		                }
		                
			                if(parentMap.get(FindVertex) == null)
			                	throw new Exception();
			                else 
			                {
			                	String start;
			                	start = FindVertex;
			                	Stack<String> path = new Stack<String>();
			                	path.push(FindVertex);
			                	
			                	while(true)
			                	{
			                		start = parentMap.get(start);
			                		if(start == null)
			                			break;
			                		path.push(start);
			                	}
			                	if(path.peek() != SourceVertex)
			                		Display11.setText("No path exists");
			                	else
			                	{
			                		String popValue = path.pop();
			                		while(path.isEmpty() == false) 
			                		{
			                			popValue = popValue + " -> " + path.pop();
			                		}
			                		Display11.setText(popValue);
			                	}
		                }
					}
				}
				 catch(Exception e)
				{
					 	A_DispPathB.setTitle("Error!");
	        			A_DispPathB.setHeaderText("--PATH DOES NOT EXIST--");
	        			A_DispPathB.showAndWait();
				}
				fromVertex.setText("");
				toVertex.setText("");
				Display11.setEditable(false);
			}
		});
		
        myStage.setScene(myScene);
        myStage.show();
 
    }

    public static void main(String[] args) {
        launch(args);
    }

}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class interpreter {
	public int counter=0;
	String name;
	int value;
	String []sizes;
	int NAN;
	ArrayList<interpreter> myList = new ArrayList<interpreter>();
	public interpreter(String x, int y)
	{
		name=x;
		value=y;
	}
	
	public void dump()
	{
		for(int i=0; i<myList.size(); i++)
		{
			System.out.print("The name of the value:\t");
			System.out.println(myList.get(i).name);
			System.out.print("The value:\t");
			System.out.println(myList.get(i).value);
		}
	}
	
	public int Search(String key)
	{

		for(int i=0; i<counter; i++)
		{
			
			if(myList.get(i).name.equals(key))
			{
				
				return 1;
			}
		}
		return 0;
	}
	public void Readfile() throws IOException
	{
		
		String temp= "";
		FileReader Fr= new FileReader("maab.txt");
		BufferedReader hello = new BufferedReader(Fr);
		while( (temp=hello.readLine()) != null)
				
		{
			sizes= temp.split(" ");
			if(sizes[0].equals("Let"))
			{
				//System.out.println("Initializing\n");
				String t= sizes[1];
				int value= Integer.parseInt(sizes[3]);
				myList.add(new interpreter(t,value));
				counter++;
				
				
			}
			else{
				for(int i=0; i<counter; i++)
				{
					float temp2 = 0;
					if(sizes[0].equals("Print"))
					{
						
						for(int j=0; j<myList.size(); j++)
						{
						if(myList.get(j).name.equals(sizes[1]))
						{
							temp2=myList.get(j).value;
						}
						}
						System.out.println(temp2);
						break;
					}
					
					if(sizes.length>5)
					{
						String tempp[]=Arrays.copyOfRange(sizes,2, sizes.length);
						for(int j=0; j<tempp.length; j++)
						{

							if(!tempp[j].matches("(\\+|-)?[0-9]+"))
							{
								for(int k=0; k<myList.size(); k++)
								{
									if(myList.get(k).name.equals(tempp[j]))
									{
										tempp[j]=  Integer.toString(myList.get(k).value);
										
									}
								}
							}
						}
						String temp22= Arrays.toString(tempp);
						temp22 = temp22.replace(",", "");
						temp22 = temp22.replace(" ", "");
						temp22 = temp22.replace("[", "");
						temp22=temp22.substring(0,temp22.length()-1);
						Multiple_Operator P1= new Multiple_Operator();
						
						int value=P1.eval(temp22);
						
						for(int j=0; j<myList.size(); j++)
						{
						if(myList.get(j).name.equals(sizes[0]))
						{
							myList.get(j).value=value;
						}

						}
						break;
					}
					else if(myList.get(i).name.equals(sizes[0]))
					{
						//System.out.print(sizes[0]);
						//System.out.println("\texists");
						
						if(sizes[2].matches("(\\+|-)?[0-9]+"))
						{
							if(sizes[3].equals("+"))
							{
								//System.out.println("Adding");
								int  result= Integer.parseInt(sizes[2]) + Integer.parseInt(sizes[4]);
								myList.get(i).value=result;
							}
							else if(sizes[3].equals("-"))
							{
							//	System.out.println("Subtracting");
								int  result= Integer.parseInt(sizes[2]) - Integer.parseInt(sizes[4]);
								myList.get(i).value=result;
							}
							else if(sizes[3].equals("*"))
							{
								//System.out.println("Multiplying");
								int  result= Integer.parseInt(sizes[2]) * Integer.parseInt(sizes[4]);
								myList.get(i).value=result;
							}
							else if(sizes[3].equals("/"))
							{
								//System.out.println("Division");
								int  result= Integer.parseInt(sizes[2]) / Integer.parseInt(sizes[4]);
							   myList.get(i).value=result;
							}

						
			
						}

						
						for(int j=0; j<myList.size(); j++)
						{
						if(myList.get(j).name.equals(sizes[2]))
						{
							if(sizes[3].equals("+"))
							{
								//System.out.println("Adding");
								int  result= myList.get(j).value + myList.get(i).value;
								myList.get(i).value=result;
							}
							else if(sizes[3].equals("-"))
							{
								//System.out.println("Subtracting");
								int  result= myList.get(i).value - myList.get(j).value;
								myList.get(i).value=result;
							}
							else if(sizes[3].equals("*"))
							{
								//System.out.println("Multiplying");
								int  result= myList.get(i).value * myList.get(j).value;
								myList.get(i).value=result;
							}
							else if(sizes[3].equals("/"))
							{
								//System.out.println("Division");
								int  result= myList.get(i).value / myList.get(j).value;
								myList.get(i).value=result;
							}
							
						}
					
					}
					
				}
				}
			
			}
				
		}
		
	}

	public static void main(String[] args) throws IOException {

		interpreter T1  = new interpreter("A", 2);
		T1.Readfile();
	//	T1.dump();
		

	}

}

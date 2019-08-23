package com.wipro.rule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.wipro.rtvs.Rtvs;
import com.wipro.rtvs.Scm;
import com.wipro.rule.Team;;

@Service
public class ServiceMongo {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
    ServiceMongo mongoService;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
    ResourceLoader resourceLoader;
	
	public Rtvs updateRtvs(String tname)
	{
		Rtvs rtvs=new Rtvs();
		Query query = new Query(Criteria.where("rigletName").is(tname));
        rtvs = mongoTemplate.findOne(query, Rtvs.class);
        //System.out.println(rtvs.getAlm());
        return rtvs;
	}
	public Team removeAchievements(String tname)
	{
		//Query query = Query.query(Criteria.where("achievements").elemMatch(Criteria.where("reward").exists(true)));
		
		Query query2 = new Query();
		query2.addCriteria(
		    new Criteria().andOperator(
		    	Criteria.where("name").is(tname),
		        Criteria.where("achievements").elemMatch(Criteria.where("reward").exists(true))
		    )
		);

		Update update = 
				new Update().pull("achievements", 
						new BasicDBObject());
//"reward", 10
		mongoTemplate.updateMulti(query2, update, Team.class);

		return null;		
	}
	public Team updateScmReward(String tname)
	{
		System.out.println("updateScmReward");
		Team team=new Team();
		Query query = new Query(Criteria.where("name").is(tname));
        team = mongoTemplate.findOne(query, Team.class);
       // System.out.println(team.getAchievements().toString());
        team.setPoints(0);
        mongoTemplate.save(team);
		return team;
	}
	public String callTemplate() throws IOException
	{
		//String rule,String toolName,String Rtv,String metricName,String operator,int threshold,int reward,String MetricNname  
		//Resource resource = resourceLoader.getResource("classpath:Template.txt");
		Resource resource = new ClassPathResource("Template.txt");
		InputStream input = resource.getInputStream();
		File file = resource.getFile();
		File writefile = new File("src\\main\\resources/team_rules.drl");
		BufferedReader  reader = new BufferedReader(new FileReader(file));
		 String line = reader.readLine();
		FileWriter writer = new FileWriter(writefile);
         while (line != null) 
         {
            line = reader.readLine();
             line=line.replace("$RULENAME$","nims" );
             writer.append(line);
         }
         
		
		
		
		// BufferedReader br = new BufferedReader(new FileReader(file)); 	  
		 //String abc="nims";
		 
		// modifyFile();
		    
		 // appendFiles(file);
		  
		return "Jai Kishan";  
		
		
	}
	

	
		  
	
	public void modifyFile(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);
         
        String oldContent = "";
         
        BufferedReader reader = null;
         
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
           
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
            writer = new FileWriter(fileToBeModified);
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                 
                line = reader.readLine();
                line=line.replace(oldString, newString);
                writer.append(line);
            }
            
             
            //Replacing oldString with newString in the oldContent
             
           // String newContent = oldContent.replaceAll(oldString, newString);
             
            //Rewriting the input text file with newContent
             
          
             
          
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                 
                reader.close();
                 
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
public String fileloading() throws Exception {
		
		File writefile = new File("src\\main\\resources/team_rules.drl");
		String absolutePathWrite = writefile.getAbsolutePath();
		
		File readfile = new File("src\\main\\resources/Template.txt");
		String absolutePath = readfile.getAbsolutePath();
		System.out.println(absolutePath);
		 
		String textToAppend = new String ( Files.readAllBytes( Paths.get(absolutePath) ) );
	    System.out.println(textToAppend);
	    FileWriter fileWriter = new FileWriter(absolutePathWrite, true); //Set true for append mode
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println(textToAppend);  //New line
	    printWriter.close();
		return textToAppend;

}

	
	public static void appendFiles(File file) throws IOException {
		Resource resource = new ClassPathResource("Template.txt");
		InputStream input = resource.getInputStream();
		File file2 = resource.getFile();
		
		
		 BufferedWriter br = new BufferedWriter(new FileWriter(file2)); 	  
		  br.append('B');
	}
	
	public static boolean validate(File file,String  name)
	{
		int x=0;
		String rule[];
		//File file =new File("file1.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
            while(in.hasNext())
            {
                String line=in.nextLine();
                if(line.contains("rule "))
                {
                	rule=line.split(" ");
                	//System.out.println(rule[1].replace("\"", ""));
                	if(rule[1].replace("\"", "").equalsIgnoreCase(name))
                	{
                		x=0;
                	}
                	else
                	{
                		x=1;
                	}
                }
                    
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(x==1)
        	return true;
        else
        	return false;
		
	}
	public String concatTemplate(Rules rules) throws Exception {
		
		File writefile = new File("src\\main\\resources/team_rules.drl");
		String absolutePathWrite = writefile.getAbsolutePath();
		
		File readfile = new File("src\\main\\resources/Template.txt");
		String absolutePath = readfile.getAbsolutePath();
		System.out.println(absolutePath);
		
		
		String name=rules.getName();
		String mName=rules.getmName();
		String toolName=rules.gettoolName();
		String operator=rules.getOperator();
		float threshold=rules.getThreshold();
		int reward=rules.getReward();
		if(validate(writefile,name))
		{
		
		String textToAppend = new String ( Files.readAllBytes( Paths.get(absolutePath) ) );;
		textToAppend=textToAppend.replace("$RULENAME$",name );
		textToAppend=textToAppend.replace("$METRICNAME$",mName );
		textToAppend=textToAppend.replace("$TOOLNAME$",toolName );
		textToAppend=textToAppend.replace("$OPERATOR$",operator );
		textToAppend=textToAppend.replace("$THRESHOLD$",Float.toString(threshold) );
		textToAppend=textToAppend.replace("$REWARD$",Integer.toString(reward));
	    System.out.println(textToAppend);
	    FileWriter fileWriter = new FileWriter(absolutePathWrite, true); //Set true for append mode
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println(textToAppend);  //New line
	    printWriter.close();
	    return "success";
		}
		else
		{
			  return "same rule name can't be insert";
		}
		/*
		 * //Read File Content String content = new
		 * String(Files.readAllBytes(writefile.toPath())); System.out.println(content);
		 */
	       
	      
	}

}

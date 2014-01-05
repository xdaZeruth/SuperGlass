package superHeater;

import java.util.ArrayList;
import java.util.List;

import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.GeItem;

import tasks.banking.Banking;
import tasks.heating.Heating;
import core.Job;

@Manifest(name = "SuperGlass", description = "Superheat Glass for nearly 2M/hr courtesy of Zeruth.", topic = 2)
public class SuperHeater extends PollingScript{
	private List<Job> jobList = new ArrayList<Job>();
	
	public SuperHeater heat;
	
    public void start() {
		jobList.add(new Banking(ctx, heat));
		jobList.add(new Heating(ctx, heat));
		}
  //Create a method
    public static int getPrice(final int id) {
            return GeItem.getProfile(id).getPrice(GeItem.PriceType.CURRENT).getPrice();
    }
    public static void log(String string){
    	System.out.println(string);
    }
    
	@Override
	public int poll() {
		for(Job job : jobList) {
		    if(job.validate()) {
		        job.execute();
		    }
		}
		return 0;
		}
	}

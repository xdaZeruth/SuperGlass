package superHeater;

import java.util.ArrayList;
import java.util.List;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import tasks.banking.Banking;
import tasks.heating.Heating;
import core.Job;

@Manifest(name = "SuperGlass", description = "Superheat Glass for nearly 2M/hr courtesy of Zeruth.", topic = 2)
public class SuperHeater extends PollingScript{
	private List<Job> jobList = new ArrayList<Job>();
	
    public void start() {
		jobList.add(new Banking(ctx));
		jobList.add(new Heating(ctx));
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

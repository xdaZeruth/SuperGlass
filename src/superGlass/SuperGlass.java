package superGlass;
import java.util.ArrayList;
import java.util.List;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import core.Job;

@Manifest(name = "SuperGlass 0.65", description = "Superheat Glass for 2M/hr courtesy of Zeruth.")
public class SuperGlass extends PollingScript{
	private List<Job> jobList = new ArrayList<Job>();	
	public static long startTime = System.currentTimeMillis();
	
	@Override
    public void start() {
		jobList.add(new jobs.Banking(ctx));
		jobList.add(new jobs.Heating(ctx));
		}
	
	@Override
    public void stop() {
		jobList.remove(1);
		jobList.remove(0);
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

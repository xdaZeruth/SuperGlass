package superGlass;
import java.util.ArrayList;
import java.util.List;

import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;

import core.Job;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;

import jobs.Heating;

import org.powerbot.event.PaintListener;

import utils.Utils;

@Manifest(name = "SuperGlass 0.70", description = "Superheat Glass for 2M/hr courtesy of Zeruth.")
public class SuperGlass extends PollingScript implements PaintListener{
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
	
	@Override
	public void repaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setColor(Color.BLACK);
		g.fillRect(222, 12, 298, 148);
		g.setColor(Color.CYAN);
		g.drawRect(220, 10, 300, 150);
		g.drawString("SuperGlass V0.70", 320, 30);
		g.drawString("So Far", 330, 50);
		g.drawString("Per Hour", 430, 50);
		g.drawString("Money:", 230, 70);
			g.drawString(""+Heating.money, 330, 70);
				g.drawString(""+(Utils.perHour(Heating.money)), 430, 70);
		g.drawString("Casts:", 230, 90);
			g.drawString(""+Heating.casts, 330, 90);
				g.drawString(""+(Utils.perHour(Heating.casts)), 430, 90);
		g.drawString("Magic XP:", 230, 110);
			g.drawString(""+Heating.magicxp, 330, 110);
				g.drawString(""+(Utils.perHour(Heating.magicxp)), 430, 110);
		g.drawString("Craft XP:", 230, 130);
			g.drawString(""+Heating.craftxp, 330, 130);
				g.drawString(""+(Utils.perHour(Heating.craftxp)), 430, 130);


	//	g.drawRect(x, y, width, height);
		
		

	}
	}

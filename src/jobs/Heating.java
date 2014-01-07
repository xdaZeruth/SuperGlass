package jobs;
import org.powerbot.script.methods.MethodContext;
import utils.Utils;
import core.Job;

public class Heating extends Job {
	
	public int casts, money, craftxp, magicxp, glass, every5 = 0;
	private final int bucketOfSand = 1783;
	private final int moltenGlass = 1775;
	private final int seaweed = 401;
	   
	public Heating(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		return (ctx.backpack.select().id(seaweed).count()==13 
		&& ctx.backpack.select().id(bucketOfSand).count()==13
		&& ctx.backpack.select().id(moltenGlass).count()==0);
	}
	
	@Override
	public void execute() {
		if (ctx.backpack.select().id(moltenGlass).count()==0){
			while (ctx.backpack.select().id(moltenGlass).count()==0){
					sleep(1000,2000);
					ctx.keyboard.send("1");
					sleep(1000,2000);
					if (ctx.backpack.select().id(moltenGlass).count()==0){
						sleep(1000,2000);
						ctx.keyboard.send("1");
						sleep(1000,2000);
						System.out.println("----Debug--- Sent command twice.");
						if (ctx.backpack.select().id(moltenGlass).count()==0){
							sleep(1000,2000);
							ctx.keyboard.send("1");
							sleep(1000,2000);
							System.out.println("----Debug--- Sent command three times.");
						}
					}
				}
			}
		
		money+=(Utils.getPrice(moltenGlass)*(ctx.backpack.select().id(moltenGlass).count()));
		casts+=1;
		craftxp+=130;
		magicxp+=78;
		glass=(money/Utils.getPrice(moltenGlass));
		every5+=1;
		
		Utils.log("---Heated---");
		Utils.log("---Stats:---" 
				+ casts   + " Casts, " 
				+ money   + " Coins, " 
				+ craftxp + " CraftXP, " 
				+ magicxp + " MagicXP, "
				+ glass   + " Glass Made.");
		if(every5==5){
				Utils.log("--Per Hour--" 
				+ Utils.perHour(casts)   + " Casts,"
				+ Utils.perHour(money)   + " Coins, "
				+ Utils.perHour(craftxp) + " CraftXP, "
				+ Utils.perHour(magicxp) + " MagicXP, "
				+ Utils.perHour(glass)   + " Glass");
				every5=0;			
		}	
	}
}
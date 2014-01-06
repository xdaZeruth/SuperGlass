package jobs;
import org.powerbot.script.methods.MethodContext;
import utils.Utils;
import core.Job;

public class Heating extends Job {
	
	public int casts, money, craftxp, magicxp, glass, every5 = 0;
	   
	public Heating(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		return (ctx.backpack.select().id(401).count()==13 
		&& ctx.backpack.select().id(1783).count()==13
		&& ctx.backpack.select().id(1775).count()==0);
	}
	
	@Override
	public void execute() {
		while (ctx.backpack.select().id(1775).count()==0){
			while (!ctx.combatBar.getActionAt(1).isReady()){
				sleep(500,1000);
			}

			while (ctx.players.local().isIdle()){
				sleep(1000,2000);
				ctx.keyboard.send("1");
			}
		}
		while (ctx.backpack.select().id(1775).count()==0){
			sleep(500,1000);
		}
		
		money+=(Utils.getPrice(1775)*(ctx.backpack.select().id(1775).count()));
		casts+=1;
		craftxp+=130;
		magicxp+=78;
		glass=(money/Utils.getPrice(1775));
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

	

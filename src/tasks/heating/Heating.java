package tasks.heating;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.GeItem;
import superHeater.SuperHeater;
import core.Job;

public class Heating extends Job {
	
	public int casts, money, craftxp, magicxp = 0;
	
	   private SuperHeater heat;
	   

	
	public Heating(MethodContext arg0, SuperHeater heat) {
		super(arg0);
		this.heat = heat;
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
			if (ctx.players.local().isIdle()){
				ctx.keyboard.send("1");
				sleep(800, 1400);
				}
				while(!ctx.players.local().isIdle()){
					sleep(1000,3000);
				}
		}
		
		money+=(SuperHeater.getPrice(1775)*(ctx.backpack.select().id(1775).count()));
		casts+=1;
		craftxp+=130;
		magicxp+=78;
		
		SuperHeater.log("---Heated---");
		SuperHeater.log("---Stats:---" 
				+ casts   + " Casts, " 
				+ money   + " Coins, " 
				+ craftxp + " CraftXP, " 
				+ magicxp + " MagicXP");
			}	
	}
	

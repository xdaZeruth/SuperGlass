package tasks.heating;

import org.powerbot.script.methods.MethodContext;

import core.Job;

public class Heating extends Job {
	
	public int casts, money, craftxp, magicxp = 0;

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
			if (ctx.players.local().isIdle()){
				ctx.keyboard.send("1");
				sleep(800, 1400);
				}
				while(!ctx.players.local().isIdle()){
					sleep(1000,3000);
				}
		}
		
		money+=(732*(ctx.backpack.select().id(1775).count()));
		casts+=1;
		craftxp+=130;
		magicxp+=78;
		System.out.println("---Heated---");
		System.out.println("---Stats:---" 
				+ casts   + " Casts, " 
				+ money   + " Coins, " 
				+ craftxp + " CraftXP, " 
				+ magicxp + " MagicXP");
			}
		



		
	}
	

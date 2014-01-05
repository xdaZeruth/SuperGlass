package tasks.banking;

import org.powerbot.script.methods.MethodContext;

import superHeater.SuperHeater;
import core.Job;

public class Banking extends Job {
	private final int seaweed = 401;
	private final int bucketOfSand = 1783;
	private final int moltenGlass = 1775;
	private final int fire = 554;
	private final int astral = 9075;
	private final int bankID[] = {11402, 26972, 11758, 35647, 11402, 2213, 20980, 782};

	private SuperHeater heat;
	
	public Banking(MethodContext arg0, SuperHeater heat) {
		super(arg0);
		this.heat = heat;
	}

	

	@Override
	public boolean validate() {
        return ((ctx.backpack.select().id(401).count()!=13
                || ctx.backpack.select().id(1783).count()!=13) ||
                (ctx.backpack.select().id(1775).count()>=1));
}

	@Override
	public void execute() {
		System.out.println("test" + (SuperHeater.getPrice(1775)*1000000));
		while ((ctx.backpack.select().id(401).count()!=13
                && ctx.backpack.select().id(1783).count()!=13)){
			
		ctx.objects.select().id(bankID).nearest();
		sleep(250, 350);
		
            if (ctx.bank.open()) {
                sleep(1000,3000);
                	if (ctx.backpack.select().id(astral).count()<=1){
                        ctx.bank.withdraw(astral, 500);
                        sleep(1000,2000);
                	}            
                	if (ctx.backpack.select().id(fire).count()<=5){
                        ctx.bank.withdraw(fire, 1000);
                        sleep(1000,2000);
                	}  
                	while (ctx.backpack.select().id(seaweed).count()!=13 && ctx.backpack.select().id(bucketOfSand).count()!=13){
                    	if (ctx.backpack.select().id(moltenGlass).count()>=1){
                            ctx.bank.deposit(moltenGlass, ctx.backpack.select().id(moltenGlass).count());
                            sleep(1000, 2000);
                    	}
                    	if (ctx.backpack.select().id(seaweed).count()>=1){
                            ctx.bank.deposit(seaweed, ctx.backpack.select().id(seaweed).count());
                            sleep(1000,2000);
                    	}
                    	if (ctx.backpack.select().id(seaweed).count()!=13){
                            ctx.bank.withdraw(seaweed, 13);
                            sleep(1000,2000);
                    	}            		
                    	if (ctx.backpack.select().id(bucketOfSand).count()>=1){
                            ctx.bank.deposit(bucketOfSand, ctx.backpack.select().id(bucketOfSand).count());
                            sleep(1000,2000);
                    	}
                    	if (ctx.backpack.select().id(bucketOfSand).count()!=13){
                            ctx.bank.withdraw(bucketOfSand, 13);
                            sleep(1000,2000);
                    	}
                    	if (ctx.backpack.select().id(seaweed).count()==13 && ctx.backpack.select().id(bucketOfSand).count()==13){                       	
                    	SuperHeater.log("   ");
                    	SuperHeater.log("---Banked---");
                        ctx.bank.close();
                        sleep(1000, 1500);
                        
                    	}
            	}

		}
	}
	}
	}


	

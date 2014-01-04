package tasks.banking;

import org.powerbot.script.methods.MethodContext;
import core.Job;

public class Banking extends Job {
    
	int bankID[] = {11402, 26972, 11758, 35647, 11402, 2213, 20980, 782};

	public Banking(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
        return ((ctx.backpack.select().id(401).count()!=13 
        		|| ctx.backpack.select().id(1783).count()!=13) || 
        		(ctx.backpack.select().id(1775).count()>=1));
	}

	@Override
	public void execute() {
		while (ctx.backpack.select().id(1775).count()>=1){
			
		ctx.objects.select().id(bankID).nearest();
		sleep(250, 350);
		
            if (ctx.bank.open()) {
                sleep(1000,3000);
                if (ctx.bank.isOpen()){
                	if (ctx.backpack.select().id(9075).count()<=1){
                        ctx.bank.withdraw(9075, 500);
                        sleep(1000,2000);
                	}            
                	if (ctx.backpack.select().id(554).count()<=5){
                        ctx.bank.withdraw(554, 1000);
                        sleep(1000,2000);
                	}  
                	while (ctx.backpack.select().id(401).count()!=13 || ctx.backpack.select().id(1783).count()!=13){
                    	if (ctx.backpack.select().id(1775).count()>=1){
                            ctx.bank.deposit(1775, ctx.backpack.select().id(1775).count());
                            sleep(1000, 2000);
                    	}
                    	if (ctx.backpack.select().id(401).count()>=1){
                            ctx.bank.deposit(401, ctx.backpack.select().id(401).count());
                            sleep(1000,2000);
                    	}
                    	if (ctx.backpack.select().id(401).count()!=13){
                            ctx.bank.withdraw(401, 13);
                            sleep(1000,2000);
                    	}            		
                    	if (ctx.backpack.select().id(1783).count()>=1){
                            ctx.bank.deposit(1783, ctx.backpack.select().id(1783).count());
                            sleep(1000,2000);
                    	}
                    	if (ctx.backpack.select().id(1783).count()!=13){
                            ctx.bank.withdraw(1783, 13);
                            sleep(1000,2000);
                    	}
                    	if (ctx.backpack.select().id(401).count()==13 && ctx.backpack.select().id(1783).count()==13){                       	
                        System.out.println("   ");
                        System.out.println("---Banked---");
                        ctx.bank.close();
                        sleep(1000, 1500);
                    	}
            	}

		}
	}
	}
	}
}


	

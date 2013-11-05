
public class Pokemon {
	private int hp, attack, defense, spAttack, spDefense, speed;
	private int level;
	private String name;
	private String type;
	
	public Pokemon(String s, int l) { //wild pokemon constructor
		name = s; //name is provided
		level = l; //level is provided
		
		int rand = (int)(Constants.TYPES.length * Math.random());
		type = Constants.TYPES[rand]; //type is random
		
		//now assign stats based on the level, with up to +/-20% variation
		int statAvg = level*4; //level 5 would have stats around 20 for each
		
		double error = Math.random() * .4 + .8; // (.8 - 1.2) i.e. -20% to +20%
		hp = (int)(statAvg * error);

		error = Math.random() * .4 + .8;
		attack = (int)(statAvg * error);
		
		error = Math.random() * .4 + .8;
		defense = (int)(statAvg * error);
		
		error = Math.random() * .4 + .8;
		spAttack = (int)(statAvg * error);
		
		error = Math.random() * .4 + .8;
		spDefense = (int)(statAvg * error);
		
		error = Math.random() * .4 + .8;
		speed = (int)(statAvg * error);
	}
}

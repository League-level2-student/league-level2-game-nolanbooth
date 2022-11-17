package territorial.io;

public class Attack {
	Nation target;
	int strength;
	Nation attacker;

	public Attack(Nation target, Nation attacker, int strength) {
		this.strength = strength;
		this.target = target;
		this.attacker = attacker;

	}

}

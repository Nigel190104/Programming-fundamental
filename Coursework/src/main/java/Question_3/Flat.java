package Question_3;

public class Flat extends Property {

	private double MAINTENANCE_COSTS = 500;
	private int floor;

	public Flat(int hn, String str, String city, String pC,
			int numRooms, int fl) throws IllegalArgumentException {
		super(hn, str, city, pC, numRooms);
		this.floor = fl;
	}

	@Override
	public boolean isAvailable() {
		//check if there exists a professional in the rooms map if yes
		//Available is set to false
		boolean available = true;
		for (Room r : rooms.keySet()) {
			if (rooms.get(r).getType() == TenantType.PROFESSIONAL) {
				available = false;
			}
		}
		return available;
	}


	@Override
	public void occupy(Room r, ITenant t) {
		if (getAvailableRooms() != 0 && !(rooms.containsValue(t))
			  && t.getType() == TenantType.PROFESSIONAL
			  && isAvailable()) {
			rooms.put(r, t);
		}
	}

	@Override
	public String displayOccupiedProperty() {
		String roomsList = "";
		for (Room r : rooms.keySet()) {
			roomsList = "Room: " + r.getPrice() + "\n\t";
		}
		//will need to modify at a later date
		return toString() + roomsList + "Total: £" + getPrice()
			 + "(Council Tax: " + "£" + getCouncilTax() + ")";
	}

	@Override
	public String toString() {
		return super.toString() + "flat on " + floor + " floor :"
				+ getAvailableRooms() + " available" + ")";
	}

	@Override
	public double getPrice() {
		double totalPrice = 0;
		for (Room r : rooms.keySet()) {
			totalPrice += r.getPrice();
		}
		return totalPrice * 12 + MAINTENANCE_COSTS;
	}

}

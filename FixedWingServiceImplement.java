package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;

import fa.training.entities.FixedWing;
import fa.training.services.FixedWingService;
import fa.training.utils.BusinessRule;

public class FixedWingServiceImplement implements FixedWingService {

	private ArrayList<FixedWing> fixedWings;

	public FixedWingServiceImplement() {
		fixedWings = (ArrayList<FixedWing>) this.getAll();
	}

	@Override
	public void create(FixedWing fixedWing) {

		String lastID = null;
		if (this.fixedWings.size() != 0) {
			lastID = fixedWings.get(fixedWings.size() - 1).getID();
			// AP12345
			int number = Integer.parseInt(lastID.substring(2, 7));
			lastID = lastID.substring(0, 2) + (number + 1);

		} else {
			lastID = "FX10000";
		}
		fixedWing.setID(lastID);
		fixedWings.add(fixedWing);
		FileIO.writeToFile(fixedWings, this.FILE_NAME);
		System.out.println("Create fixed wing finished");

	}

	@Override
	public List<FixedWing> getAll() {
		ArrayList<FixedWing> fixedWings = new ArrayList<FixedWing>();
		FileIO.readToArray(fixedWings, FILE_NAME);
		return fixedWings;

	}

	@Override
	public void changeTypeAndMinRunwaySize(FixedWing fixedWing, String type, float runwaySize) throws Exception {
		if (!BusinessRule.validateUpdateTypeAndRunwaySize(fixedWing, type, runwaySize)) {
			return;
		}

		FixedWing chosenFixedWing = null;
		for (FixedWing fx : this.fixedWings) {
			if (fx.getID().equals(fixedWing.getID())) {
				fx = fixedWing;
				chosenFixedWing = fx;
				break;
			}

		}
		chosenFixedWing.setPlaneType(type);
		chosenFixedWing.setMinNeededRunwaySize(runwaySize);

		FileIO.writeToFile(this.fixedWings, this.FILE_NAME);

	}

}

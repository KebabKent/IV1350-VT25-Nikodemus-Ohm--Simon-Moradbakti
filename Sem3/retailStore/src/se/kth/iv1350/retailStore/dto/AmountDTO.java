package se.kth.iv1350.retailStore.dto;

public class AmountDTO {

	private final float amount;

	/**
	 * Skapar ett nytt objekt som innehåller ett visst belopp.
	 * 
	 * @param amount Det belopp som ska sparas.
	 */
	public AmountDTO(float amount) {
		this.amount = amount;
	}

	/**
	 * Returnerar det sparade beloppet.
	 * 
	 * @return Beloppet som ett float-värde.
	 */
	public float getAmount() {
		return amount;
	}

}

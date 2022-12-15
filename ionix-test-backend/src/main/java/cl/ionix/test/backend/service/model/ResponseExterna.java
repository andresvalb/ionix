package cl.ionix.test.backend.service.model;

/**
 * The Class ResponseExterna.
 */
public class ResponseExterna {

	/** The response code. */
	private int responseCode;
	
	/** The description. */
	private String description;
	
	/** The result. */
	private Result result;

	/**
	 * Gets the response code.
	 *
	 * @return the response code
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * Sets the response code.
	 *
	 * @param responseCode the new response code
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(Result result) {
		this.result = result;
	}

}

package cl.ionix.test.backend.service.model;

/**
 * The Class ResponseCifrar.
 */
public class ResponseCifrar {

	/** The response code. */
	private Integer responseCode;
	
	/** The description. */
	private String description;
	
	/** The elapsed time. */
	private Long elapsedTime;
	
	/** The result. */
	private ResultCifrar result;

	/**
	 * Gets the response code.
	 *
	 * @return the response code
	 */
	public Integer getResponseCode() {
		return responseCode;
	}

	/**
	 * Sets the response code.
	 *
	 * @param responseCode the new response code
	 */
	public void setResponseCode(Integer responseCode) {
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
	 * Gets the elapsed time.
	 *
	 * @return the elapsed time
	 */
	public Long getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * Sets the elapsed time.
	 *
	 * @param elapsedTime the new elapsed time
	 */
	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public ResultCifrar getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(ResultCifrar result) {
		this.result = result;
	}

}

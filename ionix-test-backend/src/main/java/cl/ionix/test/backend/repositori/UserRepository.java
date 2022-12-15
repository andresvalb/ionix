package cl.ionix.test.backend.repositori;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the list
	 */
	List<User> findByName(String name);

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the list
	 */
	List<User> findByEmail(String email);

	/**
	 * Delete users.
	 *
	 * @param email the email
	 */
	@Transactional
	@Modifying
	@Query("delete from User u where u.email=:email")
	void deleteUsers(@Param("email") String email);

}
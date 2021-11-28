package thl.gruppef.etankrest.etankrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thl.gruppef.etankrest.etankrestapi.entities.UserSettings;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {

}

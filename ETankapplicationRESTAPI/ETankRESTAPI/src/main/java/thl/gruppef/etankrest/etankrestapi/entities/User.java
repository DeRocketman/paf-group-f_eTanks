package thl.gruppef.etankrest.etankrestapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import thl.gruppef.etankrest.etankrestapi.request.UserRequest;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class User extends IdentifiedEntity {

    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @Column(length = 50, nullable = false)
    private String publicName;

    @Column(length = 100, nullable = false)
    private String password;

    @Lob
    private String userImage;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userSettings_id")
    UserSettings userSettings;

    @OneToMany(mappedBy = "user")
    List<GameStatistic> gameStatistics;


    public User() {

    }
}

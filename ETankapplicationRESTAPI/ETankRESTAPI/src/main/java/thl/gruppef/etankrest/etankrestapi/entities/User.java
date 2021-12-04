package thl.gruppef.etankrest.etankrestapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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
    @JsonIgnore
    private String password;

    @Lob
    @JsonIgnore
    private byte[] userImage;

    @OneToOne(mappedBy = "userRef")
    UserSettings userSettings;

    @OneToMany(mappedBy = "user")
    List<GameStatistic> gameStatistics;

}

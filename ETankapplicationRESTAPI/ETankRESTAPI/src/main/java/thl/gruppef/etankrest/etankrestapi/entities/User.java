package thl.gruppef.etankrest.etankrestapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Describes the database entity for User entities
 * */
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

    /**
     * Constructor of the class
     */
    public User() {
        userImage = "default";
    }
}

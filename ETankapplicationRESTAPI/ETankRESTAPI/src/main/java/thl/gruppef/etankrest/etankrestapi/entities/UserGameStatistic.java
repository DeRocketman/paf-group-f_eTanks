package thl.gruppef.etankrest.etankrestapi.entities;

import javax.persistence.*;

@Entity
public class UserGameStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userRef;

    private int gamePoints;

    private int playedGames;

    private int gameWins;

    private int roundWins;

    private int kills;

    private int deaths;

    private int shots;

    private int hitPoints;

    private float killDeathRate;

    private float hitRate;

}

/**
 * @Entity
 * @Table(name = "USERS")
 * public class User{
 *     @Id
 *     @Basic(optional = false)
 *     @NotNull
 *     @Column(name = "IDENTIFIER", nullable = false)
 *         private Long identifier;
 *
 *     @JoinColumn(referencedColumnName = "PASSWORDS.USERIDENTIFIER", name="USERS.IDENTIFIER")
 *     @OneToOne
 *     private Password password;
 *
 *     // getters, setters, and other User related information such as username
 * }
 *
 * @Entity
 * @Table(name = "PASSWORDS")
 * public class Password{
 *     @Id
 *     @Basic(optional = false)
 *     @NotNull
 *     @Column(name = "IDENTIFIER", nullable = false)
 *         private Long identifier;
 *
 *     @JoinColumn(name = "USERIDENTIFIER", referencedColumnName = "IDENTIFIER", nullable = false)
 *     @OneToOne(optional = false, fetch = FetchType.EAGER)
 *         private User useridentifier;
 *     //getters, setters, and other password related fields such as the password it's self
 * }
 */
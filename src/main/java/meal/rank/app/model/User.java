package meal.rank.app.model;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * The User JPA entity.
 *
 */
@Entity
//@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(
                name = User.FIND_BY_USERNAME,
                query = "select u from User u where username = :username"
        ),
        @NamedQuery(
                name = User.FIND_BY_GROUPNAME,
                query = "select u from User u where group.name = :groupname"
        )

})
public class User extends AbstractEntity {

    public static final String FIND_BY_USERNAME = "user.findByUserName";
    public static final String FIND_BY_GROUPNAME = "user.findByGroupName";

	@ManyToOne
    private Group group;
	
    private String username;
    private String nickname;
    private String passwordDigest;
    private String email;
    private String role;


    public User() {

    }

    public User(String username, String passwordDigest, String email, long ownerId, Group group, String role) {
        this.username = username;
        this.nickname = username;
        this.passwordDigest = passwordDigest;
        this.email = email;
        setOwnerId(ownerId);
        this.group = group;
        this.role = role;
    }
    public User(String username, String passwordDigest, String email, Group group, String role) {
        this.username = username;
        this.nickname = username;
        this.passwordDigest = passwordDigest;
        this.email = email;
        setOwnerId(0L);
        this.group = group;
        this.role = role;
    }
    public User(String username, String passwordDigest, String email, String role) {
        this.username = username;
        this.nickname = username;
  
        this.passwordDigest = passwordDigest;
        this.email = email;
        setOwnerId(0L);
        this.group = null;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setNIckname(String nickname) {
        this.nickname = nickname;
    }
    public String getNickname() {
        return nickname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' + 
                '}';
    }
}

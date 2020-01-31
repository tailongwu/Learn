package SpringCloud02.demo.user.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tbUser")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String avatar;

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", this.id.toString())
                .add("nickname", this.nickname)
                .toString();
    }

    public Long getId() {
        return this.id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getAvatar() {
        return this.avatar;
    }
}

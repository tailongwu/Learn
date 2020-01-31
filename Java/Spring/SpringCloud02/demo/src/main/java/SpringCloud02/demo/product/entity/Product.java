package SpringCloud02.demo.product.entity;

import com.google.common.base.MoreObjects;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tbProduct")
public class Product implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String coverImage;

    private int price;

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", this.id.toString())
                .add("name", this.name)
                .toString();
    }
}

package SpringCloud02.demo.product.entity;

import com.google.common.base.MoreObjects;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tbProduct_Comment")
public class ProductComment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Long productId;

    private Long authorId;

    private String content;

    private Date created;

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", this.id.toString())
                .add("productId", this.productId.toString())
                .add("authorId", this.authorId.toString())
                .add("content", this.content)
                .toString();
    }
}

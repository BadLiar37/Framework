package model;

import lombok.*;

import com.google.common.base.Preconditions;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Item {
    private final String modelId;
    private final String gender;
    private final int price;

    public Item(String modelId,String gender, int price){
        Preconditions.checkNotNull(modelId);
        Preconditions.checkNotNull(gender);
        Preconditions.checkArgument(price > 0);
        this.modelId = modelId;
        this.price = price;
        this.gender=gender;
    }
}

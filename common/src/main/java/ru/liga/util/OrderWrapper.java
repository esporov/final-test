package ru.liga.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderWrapper<K, V> implements Serializable {
    private K order;
    private List<V> items;

    @Override
    public String toString() {
        return "OrderWrapper{" +
                "order=" + order +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderWrapper<?, ?> that = (OrderWrapper<?, ?>) o;

        if (!Objects.equals(order, that.order)) return false;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        int result = order != null ? order.hashCode() : 0;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}

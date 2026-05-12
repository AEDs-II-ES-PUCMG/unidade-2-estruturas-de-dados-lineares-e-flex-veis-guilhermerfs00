import java.util.Comparator;

public class CriterioDeBuscaPorDescricao implements Comparator<ItemDePedido> {

    @Override
    public int compare(ItemDePedido item1, ItemDePedido item2) {
        if (item1 == null || item1.getProduto() == null || item1.getProduto().descricao == null) {
            return -1;
        }
        if (item2 == null || item2.getProduto() == null || item2.getProduto().descricao == null) {
            return -1;
        }
        return item1.getProduto().descricao.compareToIgnoreCase(item2.getProduto().descricao);
    }
}

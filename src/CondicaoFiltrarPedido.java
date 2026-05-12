import java.util.function.Predicate;

public class CondicaoFiltrarPedido implements Predicate<Pedido> {

    private String descricao;

    public CondicaoFiltrarPedido(String descricao) {
        this.descricao = descricao == null ? "" : descricao.trim();
    }

    @Override
    public boolean test(Pedido pedido) {

        return pedido.getItensDoPedido().buscarPor(
            (item, ignorado) -> item.getProduto().descricao.equalsIgnoreCase(descricao) ? 0 : 1,
            null
        ) != null;
    }
}

package src.com.hotel.visao;

import java.awt.event.ActionListener;

public interface PainelCadastro<T> {
    public void preencherCampos (T entidade);
    public T construirEntidade();
    public void addSalvarListener(ActionListener al);
    public void addCancelarListener(ActionListener al);
}
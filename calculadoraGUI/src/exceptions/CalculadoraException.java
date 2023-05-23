package exceptions;

public class CalculadoraException extends Exception {
    private int codigoErro;
    private String mensagemErro;

    public CalculadoraException(int codigoErro) {
        this.codigoErro = codigoErro;
        this.mensagemErro = obterMensagemErro(codigoErro);
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    private String obterMensagemErro(int codigoErro) {
        switch (codigoErro) {
            case 1:
                return "Divisão por zero não é permitida.";
            default:
                return "Erro desconhecido.";
        }
    }
}

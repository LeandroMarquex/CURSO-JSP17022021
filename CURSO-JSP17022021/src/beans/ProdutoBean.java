/**
 * 
 */
package beans;

/**
 * @author - Leandro MARQUES
 *
 * 
 */
public class ProdutoBean {

	private Long idProduto;
	private String nomeProduto;
	private double quantidadeProduto;
	private double valorProduto;
	
	
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public double getQuantidadeProduto() {
		return quantidadeProduto;
	}
	public void setQuantidadeProduto(double quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}
	public double getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}
	
	public String getValorEmTexto(){
		return Double.toString(valorProduto).replace('.', ',');
	}

}

package produto;

public class Produto {
	protected Integer codigoProduto;
	protected String nomeProduto;
	protected Integer quantidadeEstoque;
	protected String modeloProduto;
	protected Double precoProduto;
	protected String descricaoProduto;

	public Produto(Integer codigoProduto, String nomeProduto, Integer quantidadeEstoque, String modeloProduto,
			Double precoProduto, String descricaoProduto) {
		super();
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.quantidadeEstoque = quantidadeEstoque;
		this.modeloProduto = modeloProduto;
		this.precoProduto = precoProduto;
		this.descricaoProduto = descricaoProduto;
	}

	public Produto(String nomeProduto, Integer quantidadeEstoque, String modeloProduto, Double precoProduto,
			String descricaoProduto) {
		super();
		this.nomeProduto = nomeProduto;
		this.quantidadeEstoque = quantidadeEstoque;
		this.modeloProduto = modeloProduto;
		this.precoProduto = precoProduto;
		this.descricaoProduto = descricaoProduto;
	}


	public Integer getCodigoProduto() {
		return codigoProduto;
	}



	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}



	public String getNomeProduto() {
		return nomeProduto;
	}



	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}



	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}



	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}



	public String getModeloProduto() {
		return modeloProduto;
	}



	public void setModeloProduto(String modeloProduto) {
		this.modeloProduto = modeloProduto;
	}



	public Double getPrecoProduto() {
		return precoProduto;
	}



	public void setPrecoProduto(Double precoProduto) {
		this.precoProduto = precoProduto;
	}



	public String getDescricaoProduto() {
		return descricaoProduto;
	}



	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}



	public void abrirConexao() {

	}

}
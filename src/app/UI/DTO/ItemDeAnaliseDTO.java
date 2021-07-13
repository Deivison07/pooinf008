package app.UI.DTO;

public class ItemDeAnaliseDTO {
	private String descricao;
	private double percentual;
	
	public ItemDeAnaliseDTO (String descricao, double percentual) {
		this.setPercentual(percentual);
		this.setDescricao(descricao);
	}

    private void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return this.descricao;
    }

    private void setPercentual(double percentual) {
        this.percentual = percentual;
    }
    
    public double getPercentual() {
        return this.percentual;
    }
}

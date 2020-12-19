package dev.alexferreira.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Produto {

	@Id
	@SequenceGenerator(name = "prodSeq", sequenceName = "prod_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "prodSeq")
	@Column
	private Integer id;

	@Column
	private String descricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

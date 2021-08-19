package br.com.yunikonshine.fillawsdynamo.model.document;

import br.com.yunikonshine.fillawsdynamo.model.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocumentType implements BaseEnum {

	PROTOCOL("Protocolo"),
	RNM("RNM"),
	CPF("CPF"), 
	RG("RG"), 
	PASSPORT("Passaporte"),
	CNH("CNH"),
	PIS("PIS"), 
	WORK_CARD("Carteira de trabalho");

	private final String description;

}

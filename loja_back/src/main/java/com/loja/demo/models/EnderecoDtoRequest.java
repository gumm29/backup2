package com.loja.demo.models;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import com.loja.demo.entity.Endereco;
import com.loja.demo.repository.EnderecoRepository;

public class EnderecoDtoRequest {
  @NotEmpty
  private String rua;
  @NotEmpty
  private String numero;
  private String complemento;
  @NotEmpty
  private String bairro;
  @NotEmpty
  private String cidade;
  @NotEmpty
  private String uf;
  @NotEmpty
  private String cep;

  public String getRua() { return rua; }
  public void setRua(String rua) { this.rua = rua; }

  public String getNumero() {    return numero; }
  public void setNumero(String numero) { this.numero = numero; }

  public String getComplemento() { return complemento; }
  public void setComplemento(String complemento) { this.complemento = complemento; }

  public String getBairro() { return bairro; }
  public void setBairro(String bairro) { this.bairro = bairro; }

  public String getCidade() { return cidade; }
  public void setCidade(String cidade) { this.cidade = cidade; }

  public String getUf() { return uf;}
  public void setUf(String uf) { this.uf = uf; }

  public String getCep() { return cep; }
  public void setCep(String cep) { this.cep = cep; }

  public static Endereco converter(EnderecoDtoRequest enderecoDto, UUID clienteId){
    return new Endereco(enderecoDto, clienteId);
  }

  public Endereco atualizar(UUID id, EnderecoRepository request){
    Endereco endereco = request.getOne(id);
    endereco.setRua(this.rua);
    endereco.setNumero(this.numero);
    endereco.setComplemento(this.complemento);
    endereco.setBairro(this.bairro);
    endereco.setCidade(this.cidade);
    endereco.setUf(this.uf);
    endereco.setUf(this.uf);
    return endereco;
  }
}
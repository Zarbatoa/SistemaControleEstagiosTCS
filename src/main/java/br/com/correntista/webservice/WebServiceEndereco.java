package br.com.correntista.webservice;

import br.com.correntista.entidade.Endereco;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

/**
 *
 * @author Lucas Z
 */
public class WebServiceEndereco {
    
    private Client client;
    private WebResource webResource;
    
    public WebServiceEndereco() {
        ClientConfig clientConfig = new DefaultClientConfig(GensonProvider.class);
        client = Client.create(clientConfig);
        //Utilizado para imprimir as operacoes no console
        client.addFilter(new LoggingFilter(System.out));
        webResource = client.resource("https://viacep.com.br/ws/");
    }
    
    public Endereco pesquisarCep(String cep) {
        return webResource.path(cep).path("/json").get(Endereco.class);
    }
    
    
    // metodo main para testar o web service
//    public static void main(String[] args){
//        WebServiceEndereco webService = new WebServiceEndereco();
//        Endereco end = webService.pesquisarCep("88117215");
//        if(end != null) {
//            System.out.println("Log: " + end.getLogradouro());
//            System.out.println("Bairro: " + end.getBairro());
//            System.out.println("Cidade: " + end.getLocalidade());
//        } else {
//            System.out.println("Não encontrou ninguem com esse cep.");
//        }
//    }
    
}

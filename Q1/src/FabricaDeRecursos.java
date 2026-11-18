/**
 * Fábrica abstrata: cada implementação cria a família completa de recursos de um único provedor.
 * A tabela de preços permite estimar o custo antes de criar qualquer recurso,
 * pois uma solicitação recusada não pode consumir identificadores.
 */
package Q1.src;

public interface FabricaDeRecursos {

    String getNomeDoProvedor();

    TabelaDePrecos getTabelaDePrecos();

    MaquinaVirtual criarMaquinasVirtuais(int quantidade);

    ArmazenamentoDeObjetos criarArmazenamento(int gigabytes);

    FilaDeMensagens criarFila();
}
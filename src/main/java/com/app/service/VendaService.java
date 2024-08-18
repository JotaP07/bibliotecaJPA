package com.app.service;

import com.app.entity.*;
import com.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    private double calcularTotal(List<Item> itens) {
        double valorTotal = 0;
        for (Item i : itens) {
            Item item = this.itemService.findById(i.getId());
            valorTotal += item.getPreco();
        }
        return valorTotal;
    }

    public String save(Venda venda) {
        if (venda.getFuncionario() != null && venda.getFuncionario().getId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(venda.getFuncionario().getId())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
            venda.setFuncionario(funcionario);
        }

        if (venda.getCliente() != null && venda.getCliente().getId() != null) {
            Cliente cliente = clienteRepository.findById(venda.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            venda.setCliente(cliente);

            double valorTotal = this.calcularTotal(venda.getItens());
            venda.setTotal_valor(valorTotal);

            int idadeCliente = cliente.getIdade();
            if (idadeCliente < 18 && venda.getTotal_valor() != null && venda.getTotal_valor() > 500) {
                throw new RuntimeException("Venda não permitida para clientes menores de 18 anos com valor acima de R$ 500." +
                        "\nValor total: R$ " + venda.getTotal_valor());
            }
        }

        if (venda.getItens() != null) {
            List<Item> items = venda.getItens().stream()
                    .map(item -> itemRepository.findById(item.getId())
                            .orElseThrow(() -> new RuntimeException("Item não encontrado")))
                    .collect(Collectors.toList());
            venda.setItens(items);
        }

        vendaRepository.save(venda);
        return "Venda realizada com sucesso! Valor total: R$ " + venda.getTotal_valor();
    }

    public String saveAll(List<Venda> vendas) {
        double valorTotalGeral = 0;

        for (Venda venda : vendas) {
            if (venda.getFuncionario() != null && venda.getFuncionario().getId() != null) {
                Funcionario funcionario = funcionarioRepository.findById(venda.getFuncionario().getId())
                        .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
                venda.setFuncionario(funcionario);
            }

            if (venda.getCliente() != null && venda.getCliente().getId() != null) {
                Cliente cliente = clienteRepository.findById(venda.getCliente().getId())
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
                venda.setCliente(cliente);

                double valorTotal = this.calcularTotal(venda.getItens());
                venda.setTotal_valor(valorTotal);
                valorTotalGeral += valorTotal;

                int idadeCliente = cliente.getIdade();
                if (idadeCliente < 18 && venda.getTotal_valor() != null && venda.getTotal_valor() > 500) {
                    throw new RuntimeException("Venda não permitida para clientes menores de 18 anos com valor acima de R$ 500." +
                            "\nValor total: R$ " + venda.getTotal_valor());
                }
            }

            if (venda.getItens() != null && !venda.getItens().isEmpty()) {
                List<Item> itens = venda.getItens().stream()
                        .map(item -> itemRepository.findById(item.getId())
                                .orElseThrow(() -> new RuntimeException("Item não encontrado")))
                        .collect(Collectors.toList());
                venda.setItens(itens);
            }
        }
        vendaRepository.saveAll(vendas);
        return "Todas as Vendas foram realizadas com sucesso! " +
                "\nValor total geral: R$ " + valorTotalGeral;
    }



    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public Venda findById(long id) {
        return vendaRepository.findById(id).orElse(null);
    }

    public String update(Venda venda, long id) {
        if (vendaRepository.existsById(id)) {
            venda.setId(id);

            if (venda.getFuncionario() != null && venda.getFuncionario().getId() != null) {
                Funcionario funcionario = funcionarioRepository.findById(venda.getFuncionario().getId())
                        .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
                venda.setFuncionario(funcionario);
            }

            if (venda.getCliente() != null && venda.getCliente().getId() != null) {
                Cliente cliente = clienteRepository.findById(venda.getCliente().getId())
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
                venda.setCliente(cliente);

                double valorTotal = this.calcularTotal(venda.getItens());
                venda.setTotal_valor(valorTotal);

                int idadeCliente = cliente.getIdade();
                if (idadeCliente < 18 && venda.getTotal_valor() != null && venda.getTotal_valor() > 500) {
                    throw new RuntimeException("Venda não permitida para clientes menores de 18 anos com valor acima de R$ 500." +
                            "\nValor total: R$ " + venda.getTotal_valor());
                }
            }

            if (venda.getItens() != null) {
                List<Item> items = venda.getItens().stream()
                        .map(item -> itemRepository.findById(item.getId())
                                .orElseThrow(() -> new RuntimeException("Item não encontrado")))
                        .collect(Collectors.toList());
                venda.setItens(items);
            }

            vendaRepository.save(venda);
            return "Venda atualizada com sucesso! Valor total: R$ " + venda.getTotal_valor();
        } else {
            return "Venda não encontrada!";
        }
    }

    public String delete(long id) {
        if (vendaRepository.existsById(id)) {
            vendaRepository.deleteById(id);
            return "Venda deletada com sucesso!";
        } else {
            return "Venda não encontrada!";
        }
    }

    public void deleteAll() {
        vendaRepository.deleteAll();
    }

    public List<Venda> findByValorAcima(Double total_valor) {
        return this.vendaRepository.findByValorAcima(total_valor);
    }

    public List<Venda> findByValorAbaixo(Double total_valor) {
        return this.vendaRepository.findByValorAbaixo(total_valor);
    }

}
    
    


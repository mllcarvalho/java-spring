package com.treinamentosping.awpag.domain.service;

import com.treinamentosping.awpag.domain.exception.NegocioException;
import com.treinamentosping.awpag.domain.model.Cliente;
import com.treinamentosping.awpag.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroClienteService {

    private ClienteRepository clienteRepository;

    public Cliente buscar(Long clientId) {
        return clienteRepository.findById(clientId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {

        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .filter(c -> !c.equals(cliente))
                .isPresent();

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com este email");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clientId) {
        clienteRepository.deleteById(clientId);
    }
}

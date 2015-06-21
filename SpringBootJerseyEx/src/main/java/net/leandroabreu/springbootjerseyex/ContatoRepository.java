package net.leandroabreu.springbootjerseyex;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContatoRepository extends PagingAndSortingRepository<Contato, Long> {

}

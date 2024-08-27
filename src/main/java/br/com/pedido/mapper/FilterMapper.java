package br.com.pedido.mapper;


public interface FilterMapper<E, F> {
    E convertFilterToEntity(F filterDTO);
}